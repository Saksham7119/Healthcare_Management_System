package models;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import utils.DBManager;

public class BloodGroup {
    private Integer bloodGroupId;
    private String name;

    public BloodGroup() {

    }

    public BloodGroup(Integer bloodGroupId, String name) {
        this.bloodGroupId = bloodGroupId;
        this.name = name;
    }

    public static ArrayList<BloodGroup> collectBloodGroups() {
        ArrayList<BloodGroup> bloodGroups = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM blood_groups");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BloodGroup bg = new BloodGroup(rs.getInt("blood_group_id"), rs.getString("name"));
                bloodGroups.add(bg);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bloodGroups;
    }

    public static BloodGroup getBloodGroupById(Integer bloodGroupId) {
        BloodGroup bg = null;
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM blood_groups WHERE blood_group_id=?");
            ps.setInt(1, bloodGroupId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bg = new BloodGroup(rs.getInt("blood_group_id"), rs.getString("name"));
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bg;
    }

    public Integer getBloodGroupId() {
        return bloodGroupId;
    }

    public void setBloodGroupId(Integer bloodGroupId) {
        this.bloodGroupId = bloodGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
