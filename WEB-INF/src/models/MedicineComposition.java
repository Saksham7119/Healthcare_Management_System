package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicineComposition {

    private Integer medicineCompositionId;
    private Medicine medicine;
    private GenericMedicine genericMedicine;

    public MedicineComposition() {}

    public MedicineComposition(Medicine medicine , GenericMedicine genericMedicine){
        this.medicine = medicine;
        this.genericMedicine = genericMedicine;
    }

    public boolean addMedicineComposition(){
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");
            String query = "INSERT INTO medicine_compositions (medicine_id , generic_medicine_id) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(query);

            System.out.println("Fetching medicine id for composition");
            System.out.println(medicine.getMedicineId());
            ps.setInt(1,medicine.getMedicineId() );
            System.out.println("Fetching generic medicine id for composition");
            System.out.println(genericMedicine.getGenericMedicineId());
            ps.setInt(2 , genericMedicine.getGenericMedicineId() );

            int i = ps.executeUpdate();

            if(i == 1)
                flag = true;

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return flag;
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
}
