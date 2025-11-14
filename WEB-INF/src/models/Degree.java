package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DBManager;

public class Degree {

    private Integer degreeId;
    private String name;

    public Degree() {}

    public Integer getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Integer degreeId) {
        this.degreeId = degreeId;
    }

    public Degree(Integer degreeId, String name) {
        this.degreeId = degreeId;
        this.name = name;
    }


 public static ArrayList<Degree> getAllDegrees(){
        ArrayList<Degree> arrayListDegrees = new ArrayList<>();

        try {
            Connection con = DBManager.getConnection();

            String query = "SELECT  degree_id , name FROM degrees";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                arrayListDegrees.add( new Degree(rs.getInt("degree_id") , rs.getString("name")));
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arrayListDegrees;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
