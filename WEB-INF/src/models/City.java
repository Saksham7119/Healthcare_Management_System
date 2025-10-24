package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class City {

    private Integer cityId;
    private String name;
    private State state;

    public City() {}

    public Integer getCityId() {
        return cityId;
    }

    
     public City(Integer cityId, String name) {
        this.cityId = cityId;
        this.name = name;
    }

     public static ArrayList<City> getAllCities(){
        ArrayList<City> cities = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");

            String query = "SELECT * from cities";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
               cities.add( new City(rs.getInt("city_id") , rs.getString("name")));
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    public City getCityById(int cityId) {
        City m = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");

            String query = "SELECT * FROM cities WHERE city_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, cityId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                m = new City();
                m.setCityId(((rs.getInt("city_id"))));
                m.setName(rs.getString("name"));
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
