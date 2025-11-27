package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import models.Clinic;
import java.util.ArrayList;
import com.google.gson.Gson;

@WebServlet("/collectClinicForPatientAppointment.do")
public class ShowClinicForPatientAppointmentServlet extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        Integer clinicId = Integer.parseInt(request.getParameter("clinicId"));
        ArrayList<Clinic> clinics = Clinic.collectClinicByClinicId(clinicId);

        Gson gson = new Gson();
        String resp = gson.toJson(clinics);
        response.getWriter().write(resp);
    }
} 