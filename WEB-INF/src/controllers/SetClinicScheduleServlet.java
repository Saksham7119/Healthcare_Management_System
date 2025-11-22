package controllers;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Schedule;
import models.UserNotification;
import models.User;

@WebServlet("/setSchedule.do")
public class SetClinicScheduleServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String startTimeString = request.getParameter("startTime");
        LocalTime localStartTime = LocalTime
                .parse(startTimeString.length() == 5 ? startTimeString + ":00" : startTimeString);
        Time startTime = Time.valueOf(localStartTime);

        String endTimeString = request.getParameter("endTime");
        LocalTime localEndTime = LocalTime.parse(endTimeString.length() == 5 ? endTimeString + ":00" : endTimeString);
        Time endTime = Time.valueOf(localEndTime);

        Integer patientLimit = Integer.parseInt(request.getParameter("patientLimit"));
        Integer clinicId = Integer.parseInt(request.getParameter("clinicId"));

        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(patientLimit);
        System.out.println(clinicId);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Schedule schedule = new Schedule(startTime, endTime, patientLimit, clinicId);

        String jsonResponse;
        if (schedule.setClinicSchedule()) {
            new UserNotification("You have updated schedule for Clinic", user).addNotification();
            jsonResponse = "Your Have updated a clinic schedule!";
        } else {
            new UserNotification("Your Clinic schedule Updation Failed!", user).addNotification();
            jsonResponse = "Clinic Schedule Updation Failed!";

        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}
