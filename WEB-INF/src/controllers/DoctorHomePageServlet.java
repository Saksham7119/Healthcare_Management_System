package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// import models.User;
@WebServlet("/doctorHomePage.do")
public class DoctorHomePageServlet extends HttpServlet{
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
        request.getRequestDispatcher("doctorHomePage.jsp").forward(request, response);
    }


}