package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Format;

@WebServlet("/getMedicineFormat")
public class MedicineFormatServlet extends HttpServlet {
    public void doGet(){}

    public void doPost ( HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

       ArrayList<Format> format = Format.getAllFormat();
        String json = new Gson().toJson(format);

        PrintWriter out = response.getWriter();
        out.print(json);
        System.out.println(json);
        out.flush();
    }
}
