package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DBManager;

public class SpanFrequency {

    private Integer spanFrequencyId;
    private String name;

    public SpanFrequency() {
    }

    public SpanFrequency(Integer id, String name) {
        this.spanFrequencyId = id;
        this.name = name;
    }

    public static ArrayList<SpanFrequency> collectSpanFrequencies() {
        ArrayList<SpanFrequency> arrayListSpanFreq = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM span_frequencies");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SpanFrequency sF = new SpanFrequency(rs.getInt("span_frequency_id"), rs.getString("name"));
                arrayListSpanFreq.add(sF);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayListSpanFreq;
    }

    public static SpanFrequency getSpanFrequencyById(Integer spanFrequencyId){
        SpanFrequency sF = null;
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM span_frequencies WHERE span_frequency_id=?");
            ps.setInt(1, spanFrequencyId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sF = new SpanFrequency(rs.getInt("span_frequency_id"), rs.getString("name"));
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sF;
    }

    public Integer getSpanFrequencyId() {
        return spanFrequencyId;
    }

    public void setSpanFrequencyId(Integer spanFrequencyId) {
        this.spanFrequencyId = spanFrequencyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
