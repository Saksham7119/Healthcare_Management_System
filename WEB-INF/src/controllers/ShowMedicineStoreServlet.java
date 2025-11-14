package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import models.Medicine;
import models.Manufacturer;
import models.User;
import java.util.ArrayList;
import com.google.gson.Gson;

@WebServlet("/view_medicine.do")
public class ShowMedicineStoreServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        Manufacturer manufacturer = Manufacturer.getByUserId(user.getUserId());
        Integer manufacturerId = manufacturer.getManufacturerId();

        ArrayList<Medicine> med = Medicine.collectMedicinesByManufacturerId(manufacturerId); 

        Gson gson = new Gson();
        String resp = gson.toJson(med);
        System.out.println(resp);
        response.getWriter().write(resp);
    }
} 