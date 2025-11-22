package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import models.Location;
import models.User;
import models.UserNotification;
import models.City;
import models.Clinic;
import models.ClinicDay;
import models.ClinicImage;
import models.Doctor;

@WebServlet("/addClinic.do")
public class AddClinicServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String clinicName = null;
        String clinicAddress = null;
        String contact = null;
        String aboutClinic = null;
        Integer firstVisitCharges = 0;
        Integer nextVisitCharges = 0;
        Integer clinicCity = 0;
        String[] clinicDays = null;
        String clinicImagePath = null;

        HttpSession session = request.getSession();
        // Doctor doctor = (Doctor) session.getAttribute("doctor");
        User user = (User) session.getAttribute("user");

        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> fileItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                for (FileItem fileItem : fileItems) {
                    if (fileItem.isFormField()) {
                        String fieldName = fileItem.getFieldName();
                        String fieldValue = fileItem.getString();

                        switch (fieldName) {
                            case "clinicName":
                                clinicName = fieldValue;
                                break;
                            case "clinicAddress":
                                clinicAddress = fieldValue;
                                break;
                            case "clinicContact":
                                contact = fieldValue;
                                break;
                            case "aboutClinic":
                                aboutClinic = fieldValue;
                                break;
                            case "firstVisitCharges":
                                firstVisitCharges = Integer.parseInt(fieldValue);
                                break;
                            case "nextVisitCharges":
                                nextVisitCharges = fieldValue.isEmpty() ? null : Integer.parseInt(fieldValue);
                                break;
                            case "clinicCity":
                                clinicCity = Integer.parseInt(fieldValue);
                                break;
                        }
                    } else {
                        if (fileItem.getFieldName().equals("clinicImage") && fileItem.getSize() > 0) {
                            String originalFileName = fileItem.getName();
                            String uploadPath = getServletContext().getRealPath("/WEB-INF/uploads/" + user.getUserId());

                            File uploadDir = new File(uploadPath);
                            if (!uploadDir.exists()) {
                                uploadDir.mkdirs();
                            }

                            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                            Date dt = new Date();
                            String newFileName = dt.getTime() + fileExtension;
                            File file = new File(uploadPath , newFileName);
                            clinicImagePath = user.getUserId()+"/"+newFileName;
                            fileItem.write(file);
                        }
                    }

                }
                 clinicDays = fileItems.stream()
                        .filter(FileItem::isFormField)
                        .filter(item -> item.getFieldName().equals("clinicDays[]") || item.getFieldName().equals("clinicDays"))
                        .map(item -> {
                            try {
                                return item.getString();
                            } catch (Exception e) {
                                e.printStackTrace();
                                return null;
                            }
                        })
                        .filter(s -> s != null && !s.isEmpty())
                        .toArray(String[]::new);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        City city = new City();
        Location location = new Location(clinicAddress, city.getCityById(clinicCity));
        location.addLocation();

        Doctor doctor = Doctor.getByUserId(user.getUserId());

        Clinic clinic = new Clinic(clinicName, clinicAddress, contact, aboutClinic, doctor, firstVisitCharges,nextVisitCharges, location);
        Boolean clinicAdded = clinic.addClinic();
        if (clinicAdded) {
            new UserNotification("Clinic " + clinic.getName() + " is added in your profile!", user).addNotification();
        }
            
        ClinicImage clinicImage = new ClinicImage(clinic , clinicImagePath);
        if(clinicImage.addClinicImage()) System.out.println("Clinic Image is added " + clinicImagePath);
        else System.out.println("Clinic Image is not added "+ clinicImagePath);

        ClinicDay clinicDay = new ClinicDay();

        for (String day : clinicDays) {
            int dayId = Integer.parseInt(day);
            clinicDay.addClinicDay(clinic, dayId);
        }
        request.getRequestDispatcher("doctorDashboard.jsp").forward(request, response);

        String read;
    }
}