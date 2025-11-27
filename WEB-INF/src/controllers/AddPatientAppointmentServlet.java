package controllers;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Appointment;
import models.BloodGroup;
import models.Patient;
import models.Schedule;
import models.User;

@WebServlet("/bookPatientAppointment.do")
public class AddPatientAppointmentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String diagnosedCondition = request.getParameter("diagnosedCondition");
        String bp = request.getParameter("bloodPressure");
        Integer weight = Integer.parseInt(request.getParameter("patientWeight"));
        Integer height = Integer.parseInt(request.getParameter("patientHeight"));
        String medicalHistory = request.getParameter("patientMedicalHistory");
        Integer scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
        Date appointmentDate = Date.valueOf(request.getParameter("appointmentDate"));
        Integer bloodGroupId = Integer.parseInt(request.getParameter("patientBloodGroup"));

        BloodGroup bloodGroup = new BloodGroup().getBloodGroupById(bloodGroupId);

        Patient patientObj = new Patient(bp, weight, height, medicalHistory, bloodGroup, user);
        if (patientObj.addPatientDetails()) {
            System.out.println("Patient details added successfully.");
            Patient newPatientObj = Patient.getByUserId(user.getUserId());
            Schedule scheduleObj = new Schedule().getScheduleInfoById(scheduleId);

            Appointment appointmentObj = new Appointment(appointmentDate, diagnosedCondition, scheduleObj,
                    newPatientObj);
            if (appointmentObj.bookPatientAppointment()) {
                System.out.println("Appointment booked successfully.");
            } else {
                System.out.println("Failed to book appointment.");
            }
        }
        else {
            System.out.println("Failed to add patient details.");
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String jsonResponse = "Apointment is booked successfully";
        response.getWriter().write(jsonResponse);
    }
}
