package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DBManager;

public class Day {

    private Integer dayId;
    private String day;

    public Day() {
    }

    public Day(Integer dayId, String day) {
        this.dayId = dayId;
        this.day = day;
    }

    public static ArrayList<Day> getDays() {
        ArrayList<Day> days = new ArrayList<>();

        try {
            Connection con = DBManager.getConnection();

            String query = "SELECT day_id , day from days";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                days.add(new Day(rs.getInt("day_id"), rs.getString("day")));
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return days;
    }

    public Day getDayById(int dayId){
      Day day = new Day();

        try {
            Connection con = DBManager.getConnection();
            String query = "select * from days where day_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1 , dayId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                day.setDay(rs.getString("day"));
            }

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return day;
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
