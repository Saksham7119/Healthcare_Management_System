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

import models.Day;

@WebServlet("/getDays.do")
public class GetDaysServlet extends HttpServlet {
    public void doGet(){}

    public void doPost ( HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

       ArrayList<Day> day = Day.getDays();
        String json = new Gson().toJson(day);

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
