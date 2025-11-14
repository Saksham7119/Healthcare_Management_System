package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import models.Medicine;
import java.util.ArrayList;
import com.google.gson.Gson;

@WebServlet("/show_medicine_inventory_for_doctor.do")
public class ShowMedicineInventoryForDoctorServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
    
        ArrayList<Medicine> med = Medicine.collectMedicines(); 
    
        Gson gson = new Gson();
        String resp = gson.toJson(med);
        response.getWriter().write(resp);
    }
    
}

