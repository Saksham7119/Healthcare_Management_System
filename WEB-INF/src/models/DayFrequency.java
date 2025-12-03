package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DBManager;

public class DayFrequency {

    private Integer dayFrequencyId;
    private String name;

    public DayFrequency() {
    }

    public DayFrequency(Integer id , String name) {
        this.dayFrequencyId = id ;
        this.name = name;
    }

    public static ArrayList<DayFrequency> collectDayFrequencies() {
        ArrayList<DayFrequency> dayFreq = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM day_frequencies");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DayFrequency dF = new DayFrequency(rs.getInt("day_frequency_id"), rs.getString("name"));
                dayFreq.add(dF);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dayFreq;
    }

    public static DayFrequency getDayFrequencyById(Integer dayFrequencyId) {
        DayFrequency dayFreq = null;
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM day_frequencies WHERE day_frequency_id=?");
            ps.setInt(1, dayFrequencyId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dayFreq = new DayFrequency(rs.getInt("day_frequency_id"), rs.getString("name"));
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dayFreq;
    }



    public Integer getDayFrequencyId() {
        return dayFrequencyId;
    }

    public void setDayFrequencyId(Integer dayFrequencyId) {
        this.dayFrequencyId = dayFrequencyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
