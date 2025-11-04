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

public class MedicineFormat {

    private Integer medicineFormatId;
    private Medicine medicine;
    private Format format;

    public MedicineFormat() {}
    public MedicineFormat(Medicine medicine , Format format) {
        this.medicine = medicine ; 
        this.format = format;
    }
    public MedicineFormat(Integer medicineFormatId, Medicine medicine , Format format) {
        this.medicineFormatId = medicineFormatId ; 
        this.medicine = medicine ; 
        this.format = format;
    }

    public boolean SaveMedicineFormat() {
        boolean saved = false;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");
            String sql = "INSERT INTO medicine_formats (medicine_id, format_id) VALUES (?, ?)";
            pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, medicine.getMedicineId());
            pst.setInt(2, format.getFormatId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                saved = true;
                var rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    this.medicineFormatId = rs.getInt(1);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return saved;
    }

public ArrayList<MedicineFormat> collectFormats(List<Integer> medicineIds){
    ArrayList<MedicineFormat> medicineFormat = new ArrayList<>();

    if (medicineIds == null || medicineIds.isEmpty()) {
        return medicineFormat;
    }

    try {
        Connection con = DBManager.getConnection();
        
        String idList = medicineIds.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(","));
            
        String query = "select * from medicine_formats where medicine_id IN (" + idList + ")";
        
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            
            Medicine med = new Medicine();
            med.setMedicineId(rs.getInt("medicine_id"));

            Format fmt = new Format();
            fmt.setFormatId(rs.getInt("format_id"));

            medicineFormat.add(
                new MedicineFormat(
                    rs.getInt("medicine_format_id"),
                    med,
                    fmt
                )
            );
        }

        con.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return medicineFormat;
}

    public Integer getMedicineFormatId() {
        return medicineFormatId;
    }

    public void setMedicineFormatId(Integer medicineFormatId) {
        this.medicineFormatId = medicineFormatId;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }
}
