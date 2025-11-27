package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import models.Appointment;
import models.Patient;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/collect_appointments.do")
public class CollectAppointmentsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        Patient patient = Patient.getByUserId(user.getUserId());
        Integer patientId = patient.getPatientId();

        ArrayList<Appointment> appointments = Appointment.collectAppointmentsByPatientId(patientId);

        Gson gson = new Gson();
        String json = gson.toJson(appointments);
        response.getWriter().write(json);
    }

}
