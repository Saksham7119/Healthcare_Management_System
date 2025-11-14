package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.UserType;
@WebServlet("/signup.do")
public class SignupServlet extends HttpServlet{
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        java.sql.Date dob = java.sql.Date.valueOf(request.getParameter("dob"));
        Integer gender = Integer.parseInt(request.getParameter("gender"));
        String address = request.getParameter("address");
        String contact = request.getParameter("phone");
        Integer userType_id = Integer.parseInt(request.getParameter("userType_id"));
        
        String nextPage = "signup.jsp";
        System.out.println("SIGNUP SERVLET -------- USERTYPEID---" + userType_id);

        UserType userTypeObj = new UserType();
        userTypeObj.setUserTypeId(userType_id);
        User user = new User(name, email, password , contact , dob , gender , address, userTypeObj);
        if(user.signupUser())
            nextPage = "login.jsp";
        
        request.getRequestDispatcher(nextPage).forward(request, response);
    }  
}