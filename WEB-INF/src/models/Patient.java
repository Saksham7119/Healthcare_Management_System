package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DBManager;

public class Patient {

    private Integer patientId;
    private User user;
    private String bp;
    private Integer height;
    private Double weight;
    private BloodGroup bloodGroup;
    private String history;

    public Patient() {
    }

    public Patient(String bp, Integer weight, Integer height, String history, BloodGroup bloodGroup, User user) {
        this.bp = bp;
        this.weight = weight.doubleValue();
        this.height = height;
        this.history = history;
        this.bloodGroup = bloodGroup;
        this.user = user;
    }

    public Patient (Integer patientId , String bp , Integer height, Double weight , String history , BloodGroup bloodGroupObj , User user ){
        this.patientId = patientId;
        this.bp = bp;
        this.height = height;
        this.weight = weight;
        this.history = history;
        this.bloodGroup = bloodGroupObj;
        this.user = user;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public static Patient getByUserId(int userId) {
        Patient m = null;
        try {
            Connection con = DBManager.getConnection();

            String query = "SELECT * FROM patients WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                m = new Patient();
                m.setPatientId((rs.getInt("patient_id")));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    public Boolean addPatientDetails() {
        Boolean flag = false;
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO patients (bp, height, weight, history, blood_group_id, user_id) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, bp);
            ps.setInt(2, height);
            ps.setDouble(3, weight);
            ps.setString(4, history);
            ps.setInt(5, bloodGroup.getBloodGroupId());
            ps.setInt(6, user.getUserId());

            int i = ps.executeUpdate();
            if (i > 0) {
                flag = true;
            }
            con.close();    


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static Patient getPatientById(int patientId) {
        Patient patient = null;
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM patients WHERE patient_id = ?");
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                BloodGroup bloodGroupObj = BloodGroup.getBloodGroupById(rs.getInt(rs.getInt("blood_group_id")));
                User user = User.getUserInfoByUserId(rs.getInt("user_id"));

                patient = new Patient(
                    rs.getInt("patient_id" ), 
                    rs.getString("bp") , 
                    rs.getInt("height"),
                    rs.getDouble("weight"),
                    rs.getString("history"),
                    bloodGroupObj,
                    user
                );
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
