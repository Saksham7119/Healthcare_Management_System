package controller;

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
import models.Manufacturer;
import models.Medicine;
import models.MedicineDenomination;
import models.MedicineDenominationImage; // <-- Crucial Model for image data
import models.MedicineFormat;
import models.MedicineUnit;

@WebServlet("/showMedicines.do")
public class ShowMedicinesServlet extends HttpServlet {
    
    // Default GET method (often left empty or redirects to a main page)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // You might want to redirect to an error page or the main dashboard here.
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Use POST method for this endpoint.");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 🚨 Controller Flow: Handle Request -> Call Model -> Prepare Response (JSON)

        HttpSession session = request.getSession();
        Manufacturer manufacturer = (Manufacturer)session.getAttribute("manufacturer");
        
        // Ensure the manufacturer is logged in
        if (manufacturer == null) {
             response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Manufacturer session not found.");
             return;
        }

        // --- STEP 1: FETCH MASTER DATA (NAMES) ---
        Format formatModel = new Format(); 
        ArrayList<Format> masterFormats = formatModel.getAllFormat(); 
        Map<Integer, Format> masterFormatMap = masterFormats.stream()
            .collect(Collectors.toMap(Format::getFormatId, f -> f)); 

        MedicineUnit unitModel = new MedicineUnit();
        ArrayList<MedicineUnit> masterUnits = unitModel.getAllUnits();
        Map<Integer, MedicineUnit> masterUnitMap = masterUnits.stream()
            .collect(Collectors.toMap(MedicineUnit::getMedicineUnitId, u -> u)); 

        // --- STEP 2: FETCH MEDICINES ---
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

        // --- STEP 5: PREPARE FINAL JSON RESPONSE ---
        Gson gson = new Gson();
        HashMap<String, Object> data = new HashMap<>();
        
        data.put("medicines", arrayListMedicine);
        data.put("denominations", fullyHydratedDenominations); 
        data.put("formats", fullyHydratedFormats); 
        data.put("units", masterUnits);
        
        // The separate image map for client-side lookup
        data.put("denominationImages", denominationImageMap); // <-- IMAGE DATA ADDED HERE

        String jsonResponse = gson.toJson(data);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }
}