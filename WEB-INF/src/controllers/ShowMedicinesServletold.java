package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors; // Used for stream operations in Steps 1, 2, 3

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

// Model Imports (Ensuring all are present)
import models.Format;
import models.GenericMedicine;
import models.Manufacturer;
import models.Medicine;
import models.MedicineComposition;
import models.MedicineDenomination;
import models.MedicineDenominationImage; // <-- Crucial Model for image data
import models.MedicineFormat;
import models.MedicineUnit;

@WebServlet("/showMedicines.do")
public class ShowMedicinesServletold extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        Manufacturer manufacturer = (Manufacturer)session.getAttribute("manufacturer");
        
        if (manufacturer == null) {
             response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Manufacturer session not found.");
             return;
        }

        Format formatModel = new Format(); 
        ArrayList<Format> masterFormats = formatModel.getAllFormat(); 
        Map<Integer, Format> masterFormatMap = masterFormats.stream()
            .collect(Collectors.toMap(Format::getFormatId, f -> f)); 

        MedicineUnit unitModel = new MedicineUnit();
        ArrayList<MedicineUnit> masterUnits = unitModel.getAllUnits();
        Map<Integer, MedicineUnit> masterUnitMap = masterUnits.stream()
            .collect(Collectors.toMap(MedicineUnit::getMedicineUnitId, u -> u)); 

        GenericMedicine genericMedicineModel = new GenericMedicine();
        ArrayList<GenericMedicine> masteGenericMedicines = genericMedicineModel.getAllGenericMedicines();
        Map<Integer , GenericMedicine> masterGenericMedicineMap = masteGenericMedicines.stream()
            .collect(Collectors.toMap(GenericMedicine::getGenericMedicineId, g -> g));

        Medicine medicine = new Medicine();
        ArrayList<Medicine> arrayListMedicine = medicine.collectMedicines(manufacturer.getManufacturerId());
        
        List<Integer> medicineIds = arrayListMedicine.stream()
            .map(Medicine::getMedicineId)
            .collect(Collectors.toList());


        // --- STEP 3: FETCH & HYDRATE MEDICINE FORMATS ---
        MedicineFormat medicineFormatModel = new MedicineFormat();
        ArrayList<MedicineFormat> rawMedicineFormats = medicineFormatModel.collectFormats(medicineIds);
        ArrayList<MedicineFormat> fullyHydratedFormats = new ArrayList<>();
        
        // HYDRATION A: Format Name Lookup
        for (MedicineFormat rawMf : rawMedicineFormats) {
            int masterFormatId = rawMf.getFormat().getFormatId(); 
            Format fullFormat = masterFormatMap.get(masterFormatId); 

            if (fullFormat != null) {
                fullyHydratedFormats.add(
                    new MedicineFormat(
                        rawMf.getMedicineFormatId(),
                        rawMf.getMedicine(), 
                        fullFormat 
                    )
                );
            }
        }
        
        Map<Integer, MedicineFormat> hydratedFormatMap = fullyHydratedFormats.stream()
            .collect(Collectors.toMap(MedicineFormat::getMedicineFormatId, fmt -> fmt));
        
        List<Integer> formatIds = new ArrayList<>(hydratedFormatMap.keySet()); 

        // ----------------------------------------------------------------------------------
        
        // --- STEP 4: FETCH DENOMINATIONS & IMAGE PATHS ---

        MedicineDenomination denominationModel = new MedicineDenomination();
        ArrayList<MedicineDenomination> rawDenominations = denominationModel.collectDenominations(formatIds); 
        
        // 4a. Fetch all Denomination IDs required for the image lookup
        List<Integer> denominationIds = rawDenominations.stream()
            .map(MedicineDenomination::getMedicineDenominationId)
            .collect(Collectors.toList());

        // 4b. FETCH IMAGE MAP: Call the Model to get the image paths map
        MedicineDenominationImage imageModel = new MedicineDenominationImage();
        Map<Integer, String> denominationImageMap = imageModel.collectImagePaths(denominationIds); // CALL TO MODEL
        
        // ----------------------------------------------------------------------------------
        
        ArrayList<MedicineDenomination> fullyHydratedDenominations = new ArrayList<>();

        // HYDRATION B: Fill in Format and Unit objects for Denominations
        for (MedicineDenomination rawDenom : rawDenominations) {
            int formatId = rawDenom.getMedicineFormat().getMedicineFormatId(); 
            int unitId = rawDenom.getMedicineUnit().getMedicineUnitId();
            
            MedicineFormat fullFormat = hydratedFormatMap.get(formatId); 
            MedicineUnit fullUnit = masterUnitMap.get(unitId);

            if (fullFormat != null && fullUnit != null) {
                fullyHydratedDenominations.add(
                    new MedicineDenomination(
                        rawDenom.getMedicineDenominationId(),
                        fullFormat, 
                        rawDenom.getDenomination(),
                        fullUnit
                    )
                );
            }
        }

        MedicineComposition compositionModel = new MedicineComposition();
        // Uses the new efficient DAO method to get all links
        ArrayList<MedicineComposition> rawCompositions = compositionModel.collectCompositions(medicineIds); 

        ArrayList<MedicineComposition> fullyHydratedCompositions = new ArrayList<>();

        for (MedicineComposition rawComp : rawCompositions) {
    
    // 1. Get the IDs from the raw (ID-only) object
    int genericId = rawComp.getGenericMedicineId(); 
    int medicineId = rawComp.getMedicineId(); // <-- Need this ID from the raw model
    
    // 2. Instantaneous lookup using the pre-loaded map
    GenericMedicine fullGeneric = masterGenericMedicineMap.get(genericId); 

    if (fullGeneric != null) {
        
        // 3. Create a STUB Medicine object just to satisfy the constructor, 
        //    passing only the ID. (Requires a new constructor in the Medicine model)
        Medicine stubMedicine = new Medicine(medicineId); 
        
        // 4. Use your existing three-argument constructor:
        fullyHydratedCompositions.add(
            new MedicineComposition(
                rawComp.getMedicineCompositionId(), // Composition ID
                stubMedicine,                       // Stub Medicine object (only has the ID)
                fullGeneric                         // Full Generic Medicine object
            )
        );
    }
}

        // --- STEP 5: PREPARE FINAL JSON RESPONSE ---
        Gson gson = new Gson();
        HashMap<String, Object> data = new HashMap<>();
        
        data.put("medicines", arrayListMedicine);
        data.put("denominations", fullyHydratedDenominations); 
        data.put("formats", fullyHydratedFormats); 
        data.put("units", masterUnits);
        data.put("genericMedicines", masteGenericMedicines); 
        data.put("compositions", rawCompositions);
        data.put("denominationImages", denominationImageMap);

        String jsonResponse = gson.toJson(data);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }
}