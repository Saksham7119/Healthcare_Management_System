package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import models.Clinic;
import models.Doctor;
import models.User;

@WebServlet("/showClinicAppointment.do")
public class ShowClinicAppointmentsForDoctorServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        
        Doctor doctor = Doctor.getByUserId(user.getUserId());

        ArrayList<Clinic> clinic = Clinic.collectClinicAppointmentsByDoctorId(doctor.getDoctorId());

        Gson gson = new Gson();
        String json = gson.toJson(clinic);
        response.getWriter().write(json);
    }   
}
