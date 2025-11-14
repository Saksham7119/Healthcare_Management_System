package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import utils.DBManager;

public class MedicineComposition {

    private Integer medicineCompositionId;
    private Medicine medicine;
    private GenericMedicine genericMedicine;

    private Integer medicineId;
    private Integer genericMedicineId;

    public MedicineComposition() {
    }

    public MedicineComposition(Medicine medicine, GenericMedicine genericMedicine) {
        this.medicine = medicine;
        this.genericMedicine = genericMedicine;
    }

    public MedicineComposition(Integer medicineCompositionId, Integer medicineId, Integer genericMedicineId) {
        this.medicineCompositionId = medicineCompositionId;
        this.medicineId = medicineId;
        this.genericMedicineId = genericMedicineId;
    }

    public MedicineComposition(Integer medicineCompositionId, Medicine medicine, GenericMedicine genericMedicine) {
        this.medicineCompositionId = medicineCompositionId;
        this.medicine = medicine;
        this.genericMedicine = genericMedicine;
    }

    public MedicineComposition(Integer medicineCompositionId, GenericMedicine genericMedicine, Integer medicineId) {
        this.medicineCompositionId = medicineCompositionId;
        this.genericMedicine = genericMedicine;
        this.medicineId = medicineId;
    }

    public boolean addMedicineComposition() {
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");
            String query = "INSERT INTO medicine_compositions (medicine_id , generic_medicine_id) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, medicine.getMedicineId());
            ps.setInt(2, genericMedicine.getGenericMedicineId());

            int i = ps.executeUpdate();

            if (i == 1)
                flag = true;

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public ArrayList<MedicineComposition> collectCompositions(List<Integer> medicineIds) {
        ArrayList<MedicineComposition> medicineCompositions = new ArrayList<>();

        String placeholders = medicineIds.stream()
                .map(id -> "?")
                .collect(Collectors.joining(","));

        try {
            Connection con = DBManager.getConnection();

            String query = "SELECT * FROM medicine_compositions WHERE medicine_id IN (" + placeholders + ")";
            PreparedStatement ps = con.prepareStatement(query);

            for (int i = 0; i < medicineIds.size(); i++) {
                ps.setInt(i + 1, medicineIds.get(i));
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                medicineCompositions.add(
                        new MedicineComposition(
                                rs.getInt("medicine_composition_id"),
                                rs.getInt("medicine_id"),
                                rs.getInt("generic_medicine_id")));
            }

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicineCompositions;
    }

    public static MedicineComposition collectCompositionsByMedicineId(int medicineId) {
        MedicineComposition medicineCompositions = null;

        try {
            Connection con = DBManager.getConnection();
            String query = "SELECT * FROM medicine_compositions WHERE medicine_id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, medicineId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GenericMedicine genericMedicine = null;
                genericMedicine = new GenericMedicine().getGenericMedicineById(rs.getInt("generic_medicine_id"));

                medicineCompositions = new MedicineComposition(
                                        rs.getInt("medicine_composition_id"),
                                        genericMedicine,
                                        rs.getInt("medicine_id")
                                    );
                                }

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicineCompositions;
    }

    public Integer getMedicineCompositionId() {
        return medicineCompositionId;
    }

    public void setMedicineCompositionId(Integer medicineCompositionId) {
        this.medicineCompositionId = medicineCompositionId;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public GenericMedicine getGenericMedicine() {
        return genericMedicine;
    }

    public void setGenericMedicine(GenericMedicine genericMedicine) {
        this.genericMedicine = genericMedicine;
    }

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getGenericMedicineId() {
        return genericMedicineId;
    }

    public void setGenericMedicineId(Integer genericMedicineId) {
        this.genericMedicineId = genericMedicineId;
    }

    
}

