package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import models.MedicineDenomination;
import models.MedicineFormat;
import models.MedicineUnit;

import javax.servlet.annotation.WebServlet;

@WebServlet("/addMedicineDenomination.do")
public class AddMedicineDenominationServlet extends HttpServlet {
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
        Integer noOfDenomination = Integer.parseInt(request.getParameter("noOfDenomination"));

        ArrayList<MedicineDenomination> medicineDenominationArray = new ArrayList<>();
        System.out.println("Inside denomination servlet");
        for( int i = 0 ; i < noOfDenomination ; i++){
            int denomination = Integer.parseInt(request.getParameter("denominationValue" + (i+1)));
            int format = Integer.parseInt(request.getParameter("selectMedicineFormat" + (i+1)));
            int unit = Integer.parseInt(request.getParameter("medicineUnitSelect" + (i+1)));

            MedicineUnit medicineUnit = new MedicineUnit();
            medicineUnit.setMedicineUnitId(unit);

            MedicineFormat medicineFormat = new MedicineFormat();
            medicineFormat.setMedicineFormatId(format);

            MedicineDenomination medicineDenomination = new MedicineDenomination( medicineFormat , denomination , medicineUnit);
            medicineDenominationArray.add(medicineDenomination);
        }

        MedicineDenomination medicineDenominationObj = new MedicineDenomination();
            if (medicineDenominationObj.addMedicineDenominations(medicineDenominationArray)){
                System.out.println("Medicine Denomination added");
                  response.getWriter().write("Medicine Denomination Added successfully");
            }
    }
}
