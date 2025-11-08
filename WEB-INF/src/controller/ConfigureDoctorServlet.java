package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Doctor;
import models.User;

@WebServlet("/configureDoctor.do")
public class ConfigureDoctorServlet extends HttpServlet{
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
        request.getRequestDispatcher("doctorHomePage.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
        String practiceStartDate = request.getParameter("practiceStartDate");
        String aboutDoctor = request.getParameter("aboutDoctor");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Doctor doctor = new Doctor(practiceStartDate , aboutDoctor , user);
       if (doctor.fillDoctorDetails()) {
            System.out.println("-------------Doctor Details are filled-------------");
            session.setAttribute("doctorDetailsAddedTrue", doctor);
        }

        response.sendRedirect("doctorHomePage.do");;
    }
}