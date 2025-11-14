package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Manufacturer;
import models.User;

@WebServlet("/configureManufacturer.do")
public class ConfigureManufacturerServlet extends HttpServlet{
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
        request.getRequestDispatcher("ManufacturerDashboard.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
        String companyName = request.getParameter("companyName");
        String companyEmail = request.getParameter("companyEmail");
        String companyWebsite = request.getParameter("companyWebsite");
        String licenceNumber = request.getParameter("licenceNumber");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Manufacturer manufacturer = new Manufacturer(companyName , companyEmail , companyWebsite , licenceNumber, user);
        if (manufacturer.fillDetails()) {
            System.out.println("-------------Manufacturer Details are filled-------------");
            session.setAttribute("manufacturerDetailsAddedTrue", manufacturer);
        }

        request.getRequestDispatcher("ManufacturerDashboard.jsp").forward(request, response);
    }
}
