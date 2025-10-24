package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Location;
import models.City;
import models.Clinic;
import models.ClinicDay;
import models.Doctor;

@WebServlet("/addClinic.do")
public class AddClinicServlet extends HttpServlet{
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
    }

    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException{
        String clinicName = request.getParameter("clinicName");
        String clinicAddress = request.getParameter("clinicAddress");
        String contact = request.getParameter("clinicContact");
        String aboutClinic = request.getParameter("aboutClinic");
        Integer firstVisitCharges = Integer.parseInt(request.getParameter("firstVisitCharges"));
        Integer nextVisitCharges = Integer.parseInt(request.getParameter("nextVisitCharges"));
        Integer clinicCity = Integer.parseInt(request.getParameter("clinicCity"));
        String[]  clinicDays = request.getParameterValues("clinicDays");

        HttpSession session = request.getSession();
        Doctor doctor = (Doctor) session.getAttribute("doctor");

        City city = new City();
        
        Location location = new Location(clinicAddress , city.getCityById(clinicCity));
        location.addLocation();
            
            Clinic clinic = new Clinic(clinicName , clinicAddress , contact, aboutClinic,  doctor, firstVisitCharges, nextVisitCharges, location);
            clinic.addClinic();

            ClinicDay clinicDay = new ClinicDay();
        
        for (String day : clinicDays) {
            int dayId = Integer.parseInt(day);
            clinicDay.addClinicDay(clinic , dayId);
        }
        request.getRequestDispatcher("doctorDashboard.jsp").forward(request, response);
    }
}