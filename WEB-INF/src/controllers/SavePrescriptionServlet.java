package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Appointment;
import models.DayFrequency;
import models.MedicineDenomination;
import models.Prescription;
import models.SpanFrequency;

@WebServlet("/savePrescription.do")
public class SavePrescriptionServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer consumptionStatusFromReq = Integer.parseInt(request.getParameter("consumption_status"));
        Integer courseDuration = Integer.parseInt(request.getParameter("course_duration"));
        String description = request.getParameter("description");
        Integer appointmentId = Integer.parseInt(request.getParameter("appointment_id"));
        Integer medicineDenominationId = Integer.parseInt(request.getParameter("medicineDenomination"));
        Integer dayFrequencyId = Integer.parseInt(request.getParameter("day_frequency"));
        Integer spanFrequencyId = Integer.parseInt(request.getParameter("span_frequency"));
        Boolean consumptionStatus = null;
        String jsonResponse = null;

        if (consumptionStatusFromReq == 0)
            consumptionStatus = false;
        else if (consumptionStatusFromReq == 1)
            consumptionStatus = true;

        Appointment apt = Appointment.collectAppointmentsByAppointmentId(appointmentId);
        System.out.println((MedicineDenomination.getDenominationNameById(medicineDenominationId)).toString());
        DayFrequency dF = DayFrequency.getDayFrequencyById(dayFrequencyId);
        SpanFrequency sF = SpanFrequency.getSpanFrequencyById(spanFrequencyId);

        Prescription prescription = new Prescription(consumptionStatus, courseDuration, description, apt, medicineDenominationId, dF, sF);
        if (prescription.savePrescription()) {
            jsonResponse = "Prescription is successfully Saved!";
        } else {
            jsonResponse = "Failed to save the prescription";
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}
