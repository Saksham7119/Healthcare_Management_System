package models;
import java.util.ArrayList;

import utils.DBManager;

import java.sql.Connection;
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

    private ArrayList<Schedule> schedule;
    private ArrayList<ClinicDay> clinicDay;
    private ArrayList<ClinicImage> clinicImage;

    public Clinic() {
    }

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

    public Clinic(Integer clinicId, String name, String address, String contact, String aboutMe, Doctor doctor,
            Integer firstVisitCharges, Integer nextVisitCharges, Location location, ArrayList<ClinicDay> clinicDay,
            ArrayList<ClinicImage> clinicImage) {
        this.clinicId = clinicId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.aboutMe = aboutMe;
        this.doctor = doctor;
        this.firstVisitCharges = firstVisitCharges;
        this.nextVisitCharges = nextVisitCharges;
        this.location = location;
        this.clinicDay = clinicDay;
        this.clinicImage = clinicImage;
    }

    public Clinic(Integer clinicId, String name, String address, String contact, String aboutMe,
            Integer firstVisitCharges, Integer nextVisitCharges, Location location) {
        this.clinicId = clinicId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.aboutMe = aboutMe;
        this.firstVisitCharges = firstVisitCharges;
        this.nextVisitCharges = nextVisitCharges;
        this.location = location;
    }

    public Clinic(Integer clinicId, String name, String address, String contact, String aboutMe,
            Integer firstVisitCharges, Integer nextVisitCharges, Doctor doctor, Location location) {
        this.clinicId = clinicId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.aboutMe = aboutMe;
        this.firstVisitCharges = firstVisitCharges;
        this.nextVisitCharges = nextVisitCharges;
        this.doctor = doctor;
        this.location = location;
    }

    // -------------------------------------------------------------------------------------------
    public boolean addClinic() {
        boolean flag = false;
        try {
            Connection con = DBManager.getConnection();
            String query = "INSERT INTO clinics (name , address, contact , about_me, first_visit_charges , next_visit_charges , doctor_id , location_id) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, contact);
            ps.setString(4, aboutMe);
            ps.setInt(5, firstVisitCharges);
            ps.setInt(6, nextVisitCharges);
            ps.setInt(7, doctor.getDoctorId());
            ps.setInt(8, location.getLocationId());

            int i = ps.executeUpdate();
            if (i == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    this.clinicId = rs.getInt(1);
                }
                flag = true;
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Boolean removeClinic(int clinicId) {
        Boolean flag = false;
        try {
            Connection con = DBManager.getConnection();
            String query = "DELETE FROM clinics WHERE  clinic_id = ?";
            PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, clinicId);

            int i = ps.executeUpdate();
            if (i > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    this.clinicId = rs.getInt(1);
                }

                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static ArrayList<Clinic> fetchAllClinics(int doctorId) {
        ArrayList<Clinic> arrayListClinic = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            String query = "SELECT * FROM clinics where doctor_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, doctorId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Location location = null;
                int locationId = rs.getInt("location_id");
                location = new Location().getLocationById(locationId);

                Integer clinicId = rs.getInt("clinic_id");

                Clinic clinicObj = new Clinic(clinicId, rs.getString("name"), rs.getString("address"),
                        rs.getString("contact"), rs.getString("about_me"), rs.getInt("first_visit_charges"),
                        rs.getInt("next_visit_charges"), location);

                clinicObj.setClinicDay(ClinicDay.fetchAllClinicDays(clinicId));
                clinicObj.setClinicImage(ClinicImage.fetchAllClinicImages(clinicId));

                arrayListClinic.add(clinicObj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arrayListClinic;
    }

    public static ArrayList<Clinic> fetchAllClinics() {
        ArrayList<Clinic> arrayListClinic = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            String query = "SELECT * FROM clinics";
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Location location = null;
                int locationId = rs.getInt("location_id");
                location = new Location().getLocationById(locationId);

                Doctor doctor = null;
                int doctorId = rs.getInt("doctor_id");
                doctor = new Doctor().getDoctorById(doctorId);

                Integer clinicId = rs.getInt("clinic_id");

                Clinic clinicObj = new Clinic(clinicId, rs.getString("name"), rs.getString("address"),
                        rs.getString("contact"), rs.getString("about_me"), rs.getInt("first_visit_charges"),
                        rs.getInt("next_visit_charges"), doctor, location);

                clinicObj.setClinicDay(ClinicDay.fetchAllClinicDays(clinicId));
                clinicObj.setClinicImage(ClinicImage.fetchAllClinicImages(clinicId));
                clinicObj.setSchedule(Schedule.collectSchedules(clinicId));

                arrayListClinic.add(clinicObj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arrayListClinic;
    }

    public static ArrayList<Clinic> collectAllClinicsByDoctorId(int doctorId) {
        ArrayList<Clinic> clinics = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM clinics where doctor_id=?");
            ps.setInt(1, doctorId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Location location = null;
                int locationId = rs.getInt("location_id");
                location = new Location().getLocationById(locationId);

                Integer clinicId = rs.getInt("clinic_id");

                Clinic clinicObj = new Clinic(clinicId, rs.getString("name"), rs.getString("address"),
                        rs.getString("contact"), rs.getString("about_me"), rs.getInt("first_visit_charges"),
                        rs.getInt("next_visit_charges"), location);

                clinicObj.setClinicDay(ClinicDay.fetchAllClinicDays(clinicId));
                clinicObj.setClinicImage(ClinicImage.fetchAllClinicImages(clinicId));

                clinics.add(clinicObj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clinics;
    }

    public Clinic getClinicyById(int clinicId) {
        Clinic clinic = new Clinic();

        try {
            Connection con = DBManager.getConnection();
            String query = "select * from clinics where clinic_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, clinicId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                clinic.setClinicId(rs.getInt("clinic_id"));
                clinic.setName(rs.getString("name"));
            }

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clinic;
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

    public ArrayList<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<Schedule> schedule) {
        this.schedule = schedule;
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

    public ArrayList<ClinicDay> getClinicDay() {
        return clinicDay;
    }

    public void setClinicDay(ArrayList<ClinicDay> clinicDay) {
        this.clinicDay = clinicDay;
    }

    public ArrayList<ClinicImage> getClinicImage() {
        return clinicImage;
    }

    public void setClinicImage(ArrayList<ClinicImage> clinicImage) {
        this.clinicImage = clinicImage;
    }
}
