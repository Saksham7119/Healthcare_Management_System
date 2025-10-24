package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// import models.User;
@WebServlet("/MedicineStore.do")
public class MedicineStoreServlet extends HttpServlet{
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
        request.getRequestDispatcher("MedicineStore.jsp").forward(request, response);
    }
}

//     public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
//         String name = request.getParameter("name");
//         String email = request.getParameter("email");
//         String password = request.getParameter("password");
//         String contact = request.getParameter("phone");  
//         String dob = request.getParameter("dob");
//         // UserType userType = request.getParameter("userType_id");
        
//         String nextPage = "signup.jsp";

//         User user = new User(name, email, password , contact , dob , gender , address , city, userType);
//         if(user.signupUser())
//             nextPage = "login.jsp";
        
//         request.getRequestDispatcher(nextPage).forward(request, response);
//     }
// }