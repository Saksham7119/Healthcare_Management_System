package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import models.User;
import models.UserNotification;

@WebServlet("/showNotification.do")
public class ShowNotificationServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        UserNotification notification = new UserNotification();
        ArrayList<UserNotification> arrayListNotification = notification.collectNotifications(user.getUserId());

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(arrayListNotification);

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);

    }
}