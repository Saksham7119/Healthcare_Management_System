package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Day {

    private Integer dayId;
    private String day;

    public Day() {}
    
    
    public Day(Integer dayId, String day) {
        this.dayId = dayId;
        this.day = day;
    }


        public static ArrayList<Day> getDays(){
        ArrayList<Day> days = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");

            String query = "SELECT day_id , day from days";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
               days.add( new Day(rs.getInt("day_id") , rs.getString("day")));
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return days;
    }
    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
