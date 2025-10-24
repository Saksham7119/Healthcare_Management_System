package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Location {

    private Integer locationId;
    private String name;
    private City city;

    public Location() {}
    

    public Location(String name) {
        this.name = name;
    }

    public Location(String name, City city) {
        this.name = name;
        this.city = city;
    }


    public boolean addLocation() {
        boolean flag = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");
            String query = "INSERT INTO locations (name , city_id) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setInt(2, city.getCityId());

            int i = ps.executeUpdate();
            if (i == 1){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    this.locationId = rs.getInt(1);
                }
                flag = true;
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
