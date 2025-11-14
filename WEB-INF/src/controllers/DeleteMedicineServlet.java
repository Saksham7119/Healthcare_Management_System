package controllers;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.MedicineDenomination;
import models.User;
import models.UserNotification;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;


@WebServlet("/deleteMedicine.do")
public class DeleteMedicineServlet extends HttpServlet{
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
        
    }
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
        Integer denominationId = Integer.parseInt(request.getParameter("denominationId"));
        MedicineDenomination medicineDenomination = new MedicineDenomination().getDenominationNameById(denominationId);
        Integer denominationName = medicineDenomination.getDenomination();
        Boolean isDeleted = medicineDenomination.deleteDenomination(denominationId);

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        if(isDeleted){
            UserNotification userNotification = new UserNotification("You deleted a Medicine  denomination "+ denominationName , user);
            userNotification.addNotification();
            response.getWriter().write("Medicine Deleted successfully");
        } 
        else{
            UserNotification userNotification = new UserNotification("Medicine Deletion Failed for denomination "+ denominationName , user);
            userNotification.addNotification();
            response.getWriter().write("Medicine Deletion Failed");
        } 
    }
}
