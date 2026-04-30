package controllers;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import models.Prescription;

import javax.servlet.http.HttpServletRequest;

@WebServlet("/getPatientPrescription.do")
public class ShowPrescriptionDetailsToPatientServlet extends HttpServlet {
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
        Integer appointmentId = Integer.parseInt(request.getParameter("appointmentId"));

       ArrayList<Prescription> prescription = Prescription.collectPrescriptionByAppointmentId(appointmentId);

        Gson gson = new Gson();
        String json = gson.toJson(prescription);
        response.getWriter().write(json);
    }
}
