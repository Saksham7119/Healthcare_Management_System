package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.MedicineUnit;

@WebServlet("/getMedicineUnits")
public class MedicineUnitServlet extends HttpServlet {
    public void doGet(){}

    public void doPost ( HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

       ArrayList<MedicineUnit> units = MedicineUnit.getAllUnits();
        String json = new Gson().toJson(units);

        PrintWriter out = response.getWriter();
        out.print(json);
        System.out.println(json);
        out.flush();
    }
}
