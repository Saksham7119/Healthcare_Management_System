package controllers;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Doctor;
import models.Manufacturer;
import models.Patient;
import models.User;
import models.UserNotification;
import models.UserType;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userTypeId = request.getParameter("user_type_id");
        request.setAttribute("user_type_id", userTypeId);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(email, password);
        boolean flag = user.loginUser();
        System.out.println("---------LOGINUSER() FLAG----------" + flag);
        if (flag) {
            UserType userType = user.getUserType();
            Integer userTypeId = user.getUserType().getUserTypeId();
            Integer userId = user.getUserId();
            System.out.println("Login Page - UserType : " + userType);
            System.out.println("Login Page - UserTypeId : " + userTypeId);
            System.out.println("Login Page - UserName : " + user.getName());

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            
            if (userTypeId == 1){
                Patient patient = Patient.getByUserId(user.getUserId());
                session.setAttribute("patient", patient);
                session.setAttribute("userName" , user.getName());
                new UserNotification("Welcome Back, You just now logged in " + user.getName(), user).addNotification();
                response.sendRedirect("PatientHomePage.jsp");
                createUserFolder(userId.toString(), request);
            }
            else if (userTypeId == 2){
                Doctor doctor = Doctor.getByUserId(user.getUserId());
                session.setAttribute("doctor", doctor);
                session.setAttribute("userName" , user.getName());
                new UserNotification("Welcome Back, You just now logged in " + user.getName(), user).addNotification();
                response.sendRedirect("doctorHomePage.jsp");
                createUserFolder(userId.toString(), request);
            }
            else if (userTypeId == 3){
                Manufacturer manufacturer = Manufacturer.getByUserId(user.getUserId());
                session.setAttribute("manufacturer", manufacturer);
                session.setAttribute("userName" , user.getName());
                response.sendRedirect(" ManufacturerDashboard.jsp");
                new UserNotification("Welcome Back, You just now logged in " + user.getName(), user).addNotification();
                createUserFolder(userId.toString(), request);
            }   
        }
        else {
            request.setAttribute("error", "Invalid User Name or Password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }       
    
    }
    private void createUserFolder(String userId, HttpServletRequest request) {
        String basePath = getServletContext().getRealPath("/WEB-INF/uploads");
        
        File uploadsDir = new File(basePath);
        if (!uploadsDir.exists()) {
            uploadsDir.mkdirs(); 
        }

        File userFolder = new File(uploadsDir, userId);
        if (!userFolder.exists()) {
            boolean created = userFolder.mkdir();
            if (!created) {
                System.out.println("Failed to create folder for user: " + userId);
            } else {
                System.out.println("Folder created: " + userFolder.getAbsolutePath());
            }
        } else {
            System.out.println("Folder already exists for user: " + userId);
        }
    }   
}

