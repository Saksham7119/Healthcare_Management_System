package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;

import models.MedicineFormat;
import models.MedicineDenomination;
import models.Medicine;
import models.MedicineUnit;
import models.UserNotification;
import models.User;
import models.Format;

@WebServlet("/saveFormat.do")
@MultipartConfig
public class SaveFormatServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] formatIds = request.getParameterValues("selectMedicineFormat");
        System.out.println("formatIdParam = " + Arrays.toString(formatIds));
        String[] denominations = request.getParameterValues("denominationValue");
        String[] units = request.getParameterValues("medicineUnitSelect");

        boolean flag = false;

        HttpSession session = request.getSession();
        Medicine medicine = (Medicine) session.getAttribute("medicineAdded");
        User user = (User) session.getAttribute("user");

        if (medicine == null) {
            System.out.println("No medicine found in session. Cannot save formats.");
            response.getWriter().print(false);
            return;
        }

        System.out.println("Line 1 run huii");
        try {
            for (int i = 0; i < denominations.length; i++) {
                Integer formatId = Integer.parseInt(formatIds[i]);
                Integer denominationValue = Integer.parseInt(denominations[i]);
                Integer unitId = Integer.parseInt(units[i]);
                Integer currentMedicineId = medicine.getMedicineId();
                System.out.println("Current Session Medicine Id " + currentMedicineId);

                MedicineFormat medicineFormat = new MedicineFormat(
                        new Medicine(currentMedicineId),
                        new Format(formatId));

                if (medicineFormat.SaveMedicineFormat()) {
                    MedicineDenomination medicineDenomination = new MedicineDenomination(
                            denominationValue,
                            medicineFormat,
                            new MedicineUnit(unitId));
                            new UserNotification("Format for Medicine "+ medicine.getName()+" is added!", user);
                            if (medicineDenomination.SaveMedicineDenomination()) {
                                flag = true;
                                new UserNotification("Denominations for Medicine "+ medicine.getName()+" is added!", user).addNotification();;
                    } else {
                        System.out.println("MedicineDenomination not saved for index " + i);
                    }
                } else {
                    System.out.println("MedicineFormat not saved for formatId " + formatId);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Line 4 run huii");
        response.getWriter().print(flag);
    }
}
