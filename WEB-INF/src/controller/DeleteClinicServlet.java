package controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Clinic;
import models.MedicineDenomination;
import models.User;
import models.UserNotification;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;


@WebServlet("/removeClinic.do")
public class DeleteClinicServlet extends HttpServlet{
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
        
    }
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{

        Integer clinicId = Integer.parseInt(request.getParameter("clinicId"));
        Clinic clinic = new Clinic().getClinicyById(clinicId);
        String deletedClinicName = clinic.getName();
        Boolean isDeleted = clinic.removeClinic(clinicId);

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        if(isDeleted){
            new UserNotification("Clinic Named " + deletedClinicName + " is removed from your profile!", user).addNotification();
            response.getWriter().write("Clinic removal successfully");
        } 
        else{
            new UserNotification("Clinic " + clinic.getName() + " removal failed!", user).addNotification();
            response.getWriter().write("Clinic Removal Failed");
        } 
    }
}
