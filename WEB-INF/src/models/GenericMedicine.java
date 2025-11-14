package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DBManager;

public class GenericMedicine {

    private Integer genericMedicineId;
    private String name;

    public GenericMedicine() {}
    public GenericMedicine(Integer genericMedicineID) {
        this.genericMedicineId = genericMedicineID;
    }
    public GenericMedicine(Integer genericMedicineID ,  String name) {
        this.genericMedicineId = genericMedicineID;
        this.name = name;
    }

    public GenericMedicine(String name) {
        this.name = name;
    }
    //------------------------JDBC START------------------------------------

    public static ArrayList<GenericMedicine> getAllGenericMedicines(){
        ArrayList<GenericMedicine> genericMedicine = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");

            String query = "SELECT generic_medicine_id , name FROM generic_medicines";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                genericMedicine.add( new GenericMedicine(rs.getInt("generic_medicine_id") , rs.getString("name")));
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return genericMedicine;
    }

    public GenericMedicine getGenericMedicineById(int genericMedicineId){
        GenericMedicine gm = null;
        try {
            Connection con = DBManager.getConnection();
            String query = "SELECT * FROM generic_medicines where generic_medicine_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, genericMedicineId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                gm = new GenericMedicine(rs.getString("name"));
            }

            con.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gm;
    }
    //------------------------JDBC END------------------------------------

    public Integer getGenericMedicineId() {
        return genericMedicineId;
    }

    public void setGenericMedicineId(Integer genericMedicineId) {
        this.genericMedicineId = genericMedicineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
