package models;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Clinic {

    private Integer clinicId;
    private String name;
    private String address;
    private String contact;
    private String aboutMe;
    private Doctor doctor;
    private Integer firstVisitCharges;
    private Integer nextVisitCharges;
    private Location location;

    public Clinic() {}

    public Clinic(String name, String address, String contact, String aboutMe, Doctor doctor, Integer firstVisitCharges,
            Integer nextVisitCharges, Location location) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.aboutMe = aboutMe;
        this.doctor = doctor;
        this.firstVisitCharges = firstVisitCharges;
        this.nextVisitCharges = nextVisitCharges;
        this.location = location;
    }

    public boolean addClinic() {
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");
            String query = "INSERT INTO clinics (name , address, contact , about_me, first_visit_charges , next_visit_charges , doctor_id , location_id) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, contact);
            ps.setString(4, aboutMe);
            ps.setInt(5, firstVisitCharges);
            ps.setInt(6, nextVisitCharges);
            ps.setInt(7, doctor.getDoctorId());
            ps.setInt(8, location.getLocationId());

            int i = ps.executeUpdate();
            if (i == 1){
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    this.clinicId = rs.getInt(1);
                }
                flag = true;
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Integer getFirstVisitCharges() {
        return firstVisitCharges;
    }

    public void setFirstVisitCharges(Integer firstVisitCharges) {
        this.firstVisitCharges = firstVisitCharges;
    }

    public Integer getNextVisitCharges() {
        return nextVisitCharges;
    }

    public void setNextVisitCharges(Integer nextVisitCharges) {
        this.nextVisitCharges = nextVisitCharges;
    }
}
