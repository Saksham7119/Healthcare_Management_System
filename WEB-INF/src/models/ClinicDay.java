package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DBManager;

public class ClinicDay {

    private Integer clinicDayId;
    private Clinic clinic;
    private Day day;

    public ClinicDay() {}

    public Integer getClinicDayId() {
        return clinicDayId;
    }

    public ClinicDay(Integer clinicDayId, Day day) {
        this.clinicDayId = clinicDayId;
        this.day = day;
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
            Connection con = DBManager.getConnection();
            String query = "INSERT INTO clinic_days (clinic_id , day_id) VALUES (?,?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, clinic.getClinicId());
            ps.setInt(2, dayId);
            int i = ps.executeUpdate();

            if(i == 1){
                flag = true;
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return flag;
    }   

    public ArrayList<ClinicDay> fetchAllClinicDays(int clinicId){
        ArrayList<ClinicDay> arrayListClinicDay = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            String query = "SELECT * FROM clinic_days where clinic_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, clinicId);
    
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Day day = new Day();
                int dayId = rs.getInt("day_id");
                day.getDayById(dayId);

                arrayListClinicDay.add(
                   new ClinicDay(rs.getInt("clinic_day_id"),day)
                );                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arrayListClinicDay;
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
