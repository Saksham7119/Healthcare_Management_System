package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import models.Format; // You must create this model class
import models.Manufacturer;
import models.Medicine;
import models.MedicineDenomination;
import models.MedicineFormat;
import models.MedicineUnit;

@WebServlet("/showMedicines.do")
public class ShowMedicinesServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Manufacturer manufacturer = (Manufacturer)session.getAttribute("manufacturer");

        // --- STEP 1: FETCH MASTER DATA (NAMES) ---
        // Fetch all master formats (e.g., "Tablet") and map them
        Format formatModel = new Format(); // Must be a model with collectAllFormats()
        ArrayList<Format> masterFormats = formatModel.getAllFormat(); 
        Map<Integer, Format> masterFormatMap = masterFormats.stream()
            .collect(Collectors.toMap(Format::getFormatId, f -> f)); 

        // Fetch all master units (e.g., "mg") and map them
        MedicineUnit unitModel = new MedicineUnit(); // Must be a model with collectAllUnits()
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
        
        // HYDRATION A: Fill in the full Format object (with name)
        for (MedicineFormat rawMf : rawMedicineFormats) {
            int masterFormatId = rawMf.getFormat().getFormatId(); // Get the ID from the placeholder
            Format fullFormat = masterFormatMap.get(masterFormatId); // Get the full object from the master map

            if (fullFormat != null) {
                fullyHydratedFormats.add(
                    new MedicineFormat(
                        rawMf.getMedicineFormatId(),
                        rawMf.getMedicine(), // Medicine is a placeholder (ID only)
                        fullFormat           // <-- FULL Format object (with Name)
                    )
                );
            }
        }
        
        // Create map for next hydration step
        Map<Integer, MedicineFormat> hydratedFormatMap = fullyHydratedFormats.stream()
            .collect(Collectors.toMap(MedicineFormat::getMedicineFormatId, fmt -> fmt));
        
        List<Integer> formatIds = new ArrayList<>(hydratedFormatMap.keySet()); 

        // --- STEP 4: FETCH & HYDRATE DENOMINATIONS ---
        MedicineDenomination denominationModel = new MedicineDenomination();
        ArrayList<MedicineDenomination> rawDenominations = denominationModel.collectDenominations(formatIds); 
        
        ArrayList<MedicineDenomination> fullyHydratedDenominations = new ArrayList<>();

        // HYDRATION B: Fill in the full MedicineFormat and MedicineUnit objects
        for (MedicineDenomination rawDenom : rawDenominations) {
            int formatId = rawDenom.getMedicineFormat().getMedicineFormatId(); 
            int unitId = rawDenom.getMedicineUnit().getMedicineUnitId();
            
            MedicineFormat fullFormat = hydratedFormatMap.get(formatId); // Get the FULLY HYDRATED format
            MedicineUnit fullUnit = masterUnitMap.get(unitId);            // Get the FULL unit

            if (fullFormat != null && fullUnit != null) {
                fullyHydratedDenominations.add(
                    new MedicineDenomination(
                        rawDenom.getMedicineDenominationId(),
                        fullFormat, // <-- FULL Format object (with Name)
                        rawDenom.getDenomination(),
                        fullUnit    // <-- FULL Unit object (with Name)
                    )
                );
            }
        }

        // --- STEP 5: PREPARE JSON RESPONSE ---
        Gson gson = new Gson();
        HashMap<String, Object> data = new HashMap<>();
        data.put("medicines", arrayListMedicine);
        data.put("denominations", fullyHydratedDenominations); // Use the fully hydrated list
        data.put("formats", fullyHydratedFormats); // Use the hydrated list
        data.put("units", masterUnits);
        
        String jsonResponse = gson.toJson(data);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }
}