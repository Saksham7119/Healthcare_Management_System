package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.GenericMedicine;
import models.Manufacturer;
import models.Medicine;
import models.MedicineComposition;
import models.UserNotification;
import models.User;

@WebServlet("/addMedicine.do")
public class AddMedicineServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String medicineName = request.getParameter("medicineName");
        String medicineDescription = request.getParameter("medicineDescription");
        String medicineSideEffect = request.getParameter("medicineSideEffect");
        Integer medicineType = Integer.parseInt(request.getParameter("medicineType"));
        Integer genericMedicineId = Integer.parseInt(request.getParameter("genericMedicineType"));

        System.out.println("-------MEDICINE TYPE ID--------- " + medicineType);
        Boolean medicineTypeBool;
        if (medicineType == 1)
            medicineTypeBool = true;
        else
            medicineTypeBool = false;

        HttpSession session = request.getSession();
        Manufacturer manufacturer = (Manufacturer) session.getAttribute("manufacturer");
        User user = (User) session.getAttribute("user");

        System.out.println("-------MANUFACTURER ID--------- " + manufacturer.getManufacturerId());

        Medicine medicine = new Medicine(medicineName, medicineDescription, medicineTypeBool, medicineSideEffect,
                manufacturer);
        if (medicine.addMedicine()) {
            System.out.println("---------addMedicine Working and Your New Medicine is Added--------");
            new UserNotification("Medicine " + medicine.getName() + " is added in the Store!", user).addNotification();
            GenericMedicine genericMedicine = new GenericMedicine();
            genericMedicine.setGenericMedicineId(genericMedicineId);
            MedicineComposition medicineComposition = new MedicineComposition(medicine, genericMedicine);
            medicineComposition.addMedicineComposition();
            session.setAttribute("medicineAdded", medicine);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String jsonResponse =  "Medicine " + medicine.getName() + "and medicine id " + medicine.getMedicineId() + " is Added Successfully";
            response.getWriter().write(jsonResponse);
        }
    }
}
