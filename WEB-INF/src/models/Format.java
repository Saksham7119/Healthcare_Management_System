package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Format {

    private Integer formatId;
    private String name;

    public Format() {}
    public Format(Integer formatId) {
        this.formatId = formatId;
    }
    public Format(String name) {
        this.name = name;
    }
    public Format(Integer formatId , String name) {
        this.formatId = formatId;
        this.name = name;
    }


     public static ArrayList<Format> getAllFormat() {
        ArrayList<Format> format = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");

            String query = "SELECT format_id , name FROM formats";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                format.add( new Format(rs.getInt("format_id") , rs.getString("name")));
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return format;
    }

    
    public Integer getFormatId() {
        return formatId;
    }

    public void setFormatId(Integer formatId) {
        this.formatId = formatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
