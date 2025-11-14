package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.UserNotification;

@WebServlet("/deleteNotification.do")
public class DeleteNotificationServlet extends HttpServlet {
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
    }

    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
        Integer notificationID = Integer.parseInt(request.getParameter("notificationId"));
        UserNotification userNotification = new UserNotification();
        boolean isDeleted = userNotification.deleteNotification(notificationID);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        if(isDeleted) response.getWriter().write("success");
        else response.getWriter().write("error");
    }

}
    