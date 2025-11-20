package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import models.Clinic;
import models.Doctor;
import java.util.ArrayList;
import com.google.gson.Gson;

@WebServlet("/view_clinics.do")
public class ShowClinicServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        HttpSession session = request.getSession();
        Doctor doctor = (Doctor)session.getAttribute("doctor");

        Integer doctorId = doctor.getDoctorId();

        ArrayList<Clinic> clinics = Clinic.fetchAllClinics(doctorId);

        Gson gson = new Gson();
        String resp = gson.toJson(clinics);
        response.getWriter().write(resp);
    }
} 