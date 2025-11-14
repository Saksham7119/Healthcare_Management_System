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

    public Patient() {}

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
