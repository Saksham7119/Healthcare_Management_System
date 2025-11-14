package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DBManager;

public class Specialization {

    private Integer specializationId;
    private String name;
    public Specialization() {}

    public Integer getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Integer specializationId) {
        this.specializationId = specializationId;
    }


    public Specialization(Integer specializationId, String name) {
        this.specializationId = specializationId;
        this.name = name;
    }

    public static ArrayList<Specialization> getAllSpecializations(){
        ArrayList<Specialization> arrayListSpecializations = new ArrayList<>();

        try {
            Connection con = DBManager.getConnection();

            String query = "SELECT  specialization_id , name FROM specializations";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                arrayListSpecializations.add( new Specialization(rs.getInt("specialization_id") , rs.getString("name")));
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arrayListSpecializations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
