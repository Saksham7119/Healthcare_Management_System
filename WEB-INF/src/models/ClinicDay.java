package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClinicDay {

    private Integer clinicDayId;
    private Clinic clinic;
    private Day day;

    public ClinicDay() {}

    public Integer getClinicDayId() {
        return clinicDayId;
    }

    public ClinicDay(Clinic clinic, Day day) {
        this.clinic = clinic;
        this.day = day;
    }

    public void setClinicDayId(Integer clinicDayId) {
        this.clinicDayId = clinicDayId;
    }

    public boolean addClinicDay(Clinic clinic , int dayId){
        boolean flag = false;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");
            String query = "INSERT INTO clinic_days (clinic_id , day_id) VALUES (?,?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, clinic.getClinicId());
            ps.setInt(2, dayId);
            int i = ps.executeUpdate();

            if(i == 1){
                flag = true;
            }

        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return flag;
    }   

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
