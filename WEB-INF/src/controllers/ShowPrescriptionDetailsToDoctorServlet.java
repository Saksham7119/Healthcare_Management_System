package controllers;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import models.Appointment;

import javax.servlet.http.HttpServletRequest;

@WebServlet("/getPatientDetailsForPrescription.do")
public class ShowPrescriptionDetailsToDoctorServlet extends HttpServlet {
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
        Integer clinicId = Integer.parseInt(request.getParameter("clinicId"));
        Integer appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        Integer patientId = Integer.parseInt(request.getParameter("patientId"));

        ArrayList<Appointment> appointment = Appointment.collectAppointmentsByPatientId(patientId);

        Gson gson = new Gson();
        String json = gson.toJson(appointment);
        response.getWriter().write(json);
    }
}
