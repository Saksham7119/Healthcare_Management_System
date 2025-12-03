package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DBManager;

public class Test {

    private Integer testId;
    private String name;

    public Test() {
    }

    public Test(Integer testId, String name) {
        this.testId = testId;
        this.name = name;
    }

    public static ArrayList<Test> collectTests() {
        ArrayList<Test> tests = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tests");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Test ts = new Test(rs.getInt("test_id"), rs.getString("name"));
                tests.add(ts);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tests;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
