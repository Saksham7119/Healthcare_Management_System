package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DBManager;

public class Medicine {

    private Integer medicineId;
    private String name;
    private String description;
    private Boolean veg;
    private String sideEffect;
    private Manufacturer manufacturer;
    private MedicineComposition medicineComposition;
    private ArrayList<MedicineFormat> medicineFormats;

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
            Connection con = DBManager.getConnection();
            String query = "INSERT INTO medicines (name , description, veg, side_effect, manufacturer_id) VALUES (?,?,?,?,?)";
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public ArrayList<Medicine> collectMedicines(int manufacturerId){
        ArrayList<Medicine> medicine = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicine;
    }

    public static ArrayList<Medicine> collectMedicines(){
        ArrayList<Medicine> arrayListMedicine = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            String query = "select * from medicines";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Medicine medicine = new Medicine(rs.getInt("medicine_id"),rs.getString("name"),rs.getString("description"),rs.getBoolean("veg"),rs.getString("side_effect"));
                
                medicine.setMedicineComposition(MedicineComposition.collectCompositionsByMedicineId(rs.getInt("medicine_id")));
                medicine.setMedicineFormats(MedicineFormat.collectAllFormats(rs.getInt("medicine_id")));

                arrayListMedicine.add(medicine);
            }

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arrayListMedicine;
    }
    
    public static ArrayList<Medicine> collectMedicinesByManufacturerId(int manufacturerId){
        ArrayList<Medicine> arrayListMedicine = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            String query = "select * from medicines where manufacturer_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1 , manufacturerId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Manufacturer manufacturer = new Manufacturer(rs.getInt("manufacturer_id"));
                Medicine medicine = new Medicine(rs.getInt("medicine_id"),rs.getString("name"),rs.getString("description"),rs.getBoolean("veg"),rs.getString("side_effect"),manufacturer);
                
                medicine.setMedicineComposition(MedicineComposition.collectCompositionsByMedicineId(rs.getInt("medicine_id")));
                medicine.setMedicineFormats(MedicineFormat.collectAllFormats(rs.getInt("medicine_id")));

                arrayListMedicine.add(medicine);
            }

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arrayListMedicine;
    }

    public boolean deleteMedicine(int medicineId) {
    boolean flag = false;
    try {
        Connection con = DBManager.getConnection();
        String query = "DELETE FROM medicines WHERE medicine_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, medicineId);

        int rows = ps.executeUpdate();
        if (rows > 0) {
            flag = true;
        }

        con.close();
    } catch (SQLException e) {
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
    public MedicineComposition getMedicineComposition() {
        return medicineComposition;
    }
    public void setMedicineComposition(MedicineComposition medicineComposition) {
        this.medicineComposition = medicineComposition;
    }
    public ArrayList<MedicineFormat> getMedicineFormats() {
        return medicineFormats;
    }
    public void setMedicineFormats(ArrayList<MedicineFormat> medicineFormats) {
        this.medicineFormats = medicineFormats;
    }
    
}
