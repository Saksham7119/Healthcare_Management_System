package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Medicine {

    private Integer medicineId;
    private String name;
    private String description;
    private Boolean veg;
    private String sideEffect;
    private Manufacturer manufacturer;

    public Medicine() {}
    public Medicine(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public Medicine(Integer medicineId, String name, String description, Boolean veg, String sideEffect,Manufacturer manufacturer) {
        this.medicineId = medicineId;
        this.name = name;
        this.description = description;
        this.veg = veg;
        this.sideEffect = sideEffect;
        this.manufacturer = manufacturer;
    }
    public Medicine(Integer medicineId, String name, String description, Boolean veg, String sideEffect) {
        this.medicineId = medicineId;
        this.name = name;
        this.description = description;
        this.veg = veg;
        this.sideEffect = sideEffect;
    }

    public Medicine(String name, String description, Boolean veg, String sideEffect, Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.veg = veg;
        this.sideEffect = sideEffect;
        this.manufacturer = manufacturer;
    }


    public boolean addMedicine(){
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");
            String query = "INSERT INTO medicines (name , description, veg, side_effect, manufacturer_id) VALUES (?,?,?,?,?)";
            // PreparedStatement ps = con.prepareStatement(query);
            PreparedStatement ps = con.prepareStatement(query , PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setBoolean(3, veg);
            ps.setString(4, sideEffect);
            ps.setInt(5,manufacturer.getManufacturerId());

            int i = ps.executeUpdate();

            if(i == 1){
                ResultSet rs =  ps.getGeneratedKeys();
                if (rs.next()) {
                    this.medicineId = rs.getInt(1);
                }
                flag = true;
            }

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public ArrayList<Medicine> collectMedicines(int manufacturerId){
        ArrayList<Medicine> medicine = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");
            String query = "select * from medicines where manufacturer_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1 , manufacturerId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                medicine.add(
                    new Medicine(
                        rs.getInt("medicine_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBoolean("veg"),
                        rs.getString("side_effect")
                    )
                );
            }

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return medicine;
    }

    public boolean deleteMedicine(int medicineId) {
    boolean flag = false;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234"
        );
        String query = "DELETE FROM medicines WHERE medicine_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, medicineId);

        int rows = ps.executeUpdate();
        if (rows > 0) {
            flag = true;
        }

        con.close();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
    return flag;
}

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getVeg() {
        return veg;
    }

    public void setVeg(Boolean veg) {
        this.veg = veg;
    }

    public String getSideEffect() {
        return sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
    
}
