package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manufacturer {

    private Integer manufacturerId;
    private String companyName;
    private String companyEmail;
    private String website;
    private User user;
    private String licenceNumber;

    public Manufacturer() {}

    public Manufacturer(String companyName , String companyEmail , String website , String licenceNumber , User user){
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyName = companyName;
        this.website = website;
        this.licenceNumber = licenceNumber;
        this.user = user;
    }

    //Constructors END ---------------
    
    
    //--------------JDBC START ---------------
    public boolean fillDetails(){
        boolean flag = false;

        //Need to handle NULL here if two website link is empty , sql will throw duplicate entry exception
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");
            String query = "INSERT INTO manufacturers (company_name, company_email, website, license_number, user_id) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1 , companyName);
            ps.setString(2 , companyEmail);
            ps.setString(3 , website);
            ps.setString(4 , licenceNumber);
            ps.setInt(5, user.getUserId());

            int i = ps.executeUpdate();
            if(i == 1) flag = true;
                
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static Manufacturer getByUserId(int userId) {
    Manufacturer m = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");

        String query = "SELECT * FROM manufacturers WHERE user_id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            m = new Manufacturer();
            m.setManufacturerId(rs.getInt("manufacturer_id"));
            m.setCompanyName(rs.getString("company_name"));
            m.setComapanyEmail(rs.getString("company_email"));
            m.setWebsite(rs.getString("website"));
            m.setLicenceNumber(rs.getString("license_number"));
        }
        con.close();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
    return m;
}

    //--------------JDBC END------------------

    public Integer getManufacturerId() {
        return manufacturerId;
    }



    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }



    public String getCompanyName() {
        return companyName;
    }



    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }



    public String getComapanyEmail() {
        return companyEmail;
    }



    public void setComapanyEmail(String comapanyEmail) {
        this.companyEmail = comapanyEmail;
    }



    public String getWebsite() {
        return website;
    }



    public void setWebsite(String website) {
        this.website = website;
    }



    public User getUser() {
        return user;
    }



    public void setUser(User user) {
        this.user = user;
    }



    public String getLicenceNumber() {
        return licenceNumber;
    }



    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }



    public Manufacturer(User user) {
        this.user = user;
    }
}
 
