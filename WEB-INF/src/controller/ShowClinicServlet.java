package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import models.Clinic;
import models.ClinicDay;
import models.ClinicImage;
import models.Day;
import models.Doctor;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/showClinics.do")
public class ShowClinicServlet extends HttpServlet {
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
    HttpSession session = request.getSession();
    Doctor doctor = (Doctor)session.getAttribute("doctor");
    // User user = (User)session.getAttribute("user"); // Not used, can be removed

    // --- 1. Fetch ALL Clinics for the Doctor ---
    Clinic clinic = new Clinic();
    ArrayList<Clinic> arrayListClinics = clinic.fetchAllClinics(doctor.getDoctorId());

    // --- 2. Initialize aggregated lists for Days and Images ---
    ArrayList<ClinicDay> arrayListClinicDays = new ArrayList<>();
    ArrayList<ClinicImage> arrayListClinicImages = new ArrayList<>();
    
    // --- 3. Loop through EACH Clinic to fetch its Days and Images ---
    ClinicDay clinicDayModel = new ClinicDay();
    ClinicImage clinicImageModel = new ClinicImage();
    
    for (Clinic c : arrayListClinics) {
        int clinicId = c.getClinicId(); // Get the valid ID for each fetched clinic

        // Fetch Clinic Days for this specific clinic
        ArrayList<ClinicDay> daysForThisClinic = clinicDayModel.fetchAllClinicDays(clinicId);
        arrayListClinicDays.addAll(daysForThisClinic);

        // Fetch Clinic Images for this specific clinic
        ArrayList<ClinicImage> imagesForThisClinic = clinicImageModel.fetchAllClinicImages(clinicId);
        arrayListClinicImages.addAll(imagesForThisClinic);
    }
   
    // --- 4. Prepare JSON Response (remains the same) ---
    Gson gson = new Gson();
    HashMap<String , Object> data = new HashMap<>();
    data.put("clinics" , arrayListClinics);
    data.put("clinicDays" , arrayListClinicDays);
    data.put("clinicImages" , arrayListClinicImages);

    String jsonResponse = gson.toJson(data);
    response.setContentType("application/json");
    response.getWriter().write(jsonResponse);    
}
}
