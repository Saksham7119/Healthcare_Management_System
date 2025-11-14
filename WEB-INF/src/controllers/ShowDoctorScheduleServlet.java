package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import models.Schedule;

import java.util.ArrayList;
import com.google.gson.Gson;

@WebServlet("/show_schedule.do")
public class ShowDoctorScheduleServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        Integer clinicId = Integer.parseInt(request.getParameter("clinicId"));
        ArrayList<Schedule> schd = Schedule.collectSchedules(clinicId); 
    
        Gson gson = new Gson();
        String resp = gson.toJson(schd);
        response.getWriter().write(resp);
    }
    
}

