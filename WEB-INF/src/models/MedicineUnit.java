package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DBManager;

public class MedicineUnit {

    private Integer medicineUnitId;
    private String unit;

    public MedicineUnit() {}
    public MedicineUnit(String unit) {
        this.unit = unit;
    }
    public MedicineUnit(Integer medicineUnitId) {
        this.medicineUnitId = medicineUnitId;
    }
    public MedicineUnit(Integer medicineUnitId , String unit) {
        this.medicineUnitId = medicineUnitId;
        this.unit = unit;
    }

    public static ArrayList<MedicineUnit> getAllUnits() {
        ArrayList<MedicineUnit> units = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");

            String query = "SELECT medicine_unit_id , unit FROM medicine_units";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                units.add( new MedicineUnit(rs.getInt("medicine_unit_id") ,rs.getString("unit")));
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return units;
    }

    public MedicineUnit getUnitNameById(int unitId){
        MedicineUnit md = null;
        try {
            Connection con = DBManager.getConnection();
            String query = "SELECT * FROM medicine_units where medicine_unit_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, unitId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                md = new MedicineUnit(rs.getString("unit"));
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return md;
    }

    public Integer getMedicineUnitId() {
        return medicineUnitId;
    }

    public void setMedicineUnitId(Integer medicineUnitId) {
        this.medicineUnitId = medicineUnitId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
