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

public class MedicineDenomination {

    private Integer medicineDenominationId;
    private MedicineFormat medicineFormat;
    private Integer denomination;
    private MedicineUnit medicineUnit;
    private MedicineDenominationImage medicineDenominationImage;

    public MedicineDenomination() {}
    public MedicineDenomination(MedicineFormat medicineFormat , Integer denomination , MedicineUnit medicineUnit) {
        this.medicineFormat = medicineFormat;
        this.denomination = denomination;
        this.medicineUnit = medicineUnit;
    }
    public MedicineDenomination(Integer medicineDenominationId ,Integer denomination ,MedicineUnit medicineUnit, MedicineDenominationImage medicineDenominationImage) {
        this.medicineDenominationId = medicineDenominationId;
        this.denomination = denomination;
        this.medicineUnit = medicineUnit;
        this.medicineDenominationImage = medicineDenominationImage;
    }
    public MedicineDenomination(Integer medicineDenominationId, Integer denomination) {
        this.medicineDenominationId = medicineDenominationId;
        this.denomination = denomination;
    }

    public MedicineDenomination(Integer medicineDenominationId, MedicineFormat medicineFormat, Integer denomination,
            MedicineUnit medicineUnit) {
        this.medicineDenominationId = medicineDenominationId;
        this.medicineFormat = medicineFormat;
        this.denomination = denomination;
        this.medicineUnit = medicineUnit;
    }

    public MedicineDenomination(Integer denomination, MedicineFormat medicineFormat,
            MedicineUnit medicineUnit) {
        this.denomination = denomination;
        this.medicineFormat = medicineFormat;
        this.medicineUnit = medicineUnit;
    }

    public MedicineDenomination(Integer medicineDenominationId, Integer denomination, MedicineUnit medicineUnit) {
        this.medicineDenominationId = medicineDenominationId;
        this.denomination = denomination;
        this.medicineUnit = medicineUnit;
    }
    
    
    //--------------------------------------------------------------------------
    
    public boolean addMedicineDenominations(ArrayList<MedicineDenomination> denominations){
        boolean flag = true;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");
            String query = "INSERT INTO medicine_denominations (denomination , medicine_format_id , medicine_unit_id) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            for(MedicineDenomination md : denominations){
                ps.setInt(1, md.getDenomination());
                ps.setInt(2, md.medicineFormat.getMedicineFormatId());
                ps.setInt(3, md.medicineUnit.getMedicineUnitId());
                ps.addBatch();
            }

            int[] i = ps.executeBatch();            

            for(int n = 0 ; n<i.length ; n++){
                if (i[n]>0) {
                    System.out.println("Denomination no " + (n+1) + "is added");
                    flag = true;
                    
                } else {
                    System.out.println("Failed to add " + (n+1) + "denomination");
                }
            }            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return flag; 
    }

public ArrayList<MedicineDenomination> collectDenominations(List<Integer> formatIds){
    ArrayList<MedicineDenomination> medicineDenomination = new ArrayList<>();
    
    if (formatIds == null || formatIds.isEmpty()) {
        return medicineDenomination;
    }

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");
        
        String idList = formatIds.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(","));
            
        String query = "select * from medicine_denominations where medicine_format_id IN (" + idList + ")";
        
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

       while (rs.next()) {
        int formatId = rs.getInt("medicine_format_id");
        int unitId = rs.getInt("medicine_unit_id");

        // These are your placeholder objects:
        MedicineFormat mf = new MedicineFormat(); 
        mf.setMedicineFormatId(formatId); 
        
        MedicineUnit mu = new MedicineUnit();
        mu.setMedicineUnitId(unitId); 

        medicineDenomination.add(
            new MedicineDenomination(
                rs.getInt("medicine_denomination_id"),
                mf, // Placeholder object
                rs.getInt("denomination"),
                mu  // Placeholder object
            )
        );
    }

        con.close();

    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
    return medicineDenomination;
}



  public MedicineDenomination getDenominationNameById(int denominationId){
      MedicineDenomination medicineDenom = new MedicineDenomination();

        try {
            Connection con = DBManager.getConnection();
            String query = "select * from medicine_denominations where medicine_denomination_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1 , denominationId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                medicineDenom.setDenomination(denomination);
            }

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicineDenom;
    }

  public MedicineDenomination collectAllDenominationByFormatId(int medicineFormatId){
      MedicineDenomination medicineDenom = null;

        try {
            Connection con = DBManager.getConnection();
            String query = "select * from medicine_denominations where medicine_format_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1 , medicineFormatId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                MedicineUnit unit = null;
                unit = new MedicineUnit().getUnitNameById(rs.getInt("medicine_unit_id"));

                MedicineDenominationImage medicineDenominationImage = null;
                medicineDenominationImage = new MedicineDenominationImage().getDenominationImageByDenominationId(rs.getInt("medicine_denomination_id"));
                System.out.println(medicineDenominationImage);
                medicineDenom = new MedicineDenomination(rs.getInt("medicine_denomination_id") , rs.getInt("denomination") , unit , medicineDenominationImage);
            }

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicineDenom;
    }

    
    public boolean SaveMedicineDenomination() {
        boolean saved = false;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");
            String sql = "INSERT INTO medicine_denominations (denomination, medicine_format_id , medicine_unit_id) VALUES (?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, denomination);
            pst.setInt(2, medicineFormat.getMedicineFormatId());
            pst.setInt(3, medicineUnit.getMedicineUnitId());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                saved = true;
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } 

        return saved;
    }

        public boolean deleteDenomination(int denominationId) {
    boolean flag = false;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234"
        );
        String query = "DELETE FROM medicine_denominations WHERE  medicine_denomination_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, denominationId);

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

    //--------------------------------------------------------------------------
    public Integer getMedicineDenominationId() {
        return medicineDenominationId;
    }

    public void setMedicineDenominationId(Integer medicineDenominationId) {
        this.medicineDenominationId = medicineDenominationId;
    }

    public Integer getDenomination() {
        return denomination;
    }

    public MedicineFormat getMedicineFormat() {
        return medicineFormat;
    }

    public void setMedicineFormat(MedicineFormat medicineFormat) {
        this.medicineFormat = medicineFormat;
    }

    public MedicineUnit getMedicineUnit() {
        return medicineUnit;
    }

    public void setMedicineUnit(MedicineUnit medicineUnit) {
        this.medicineUnit = medicineUnit;
    }

    public void setDenomination(Integer denomination) {
        this.denomination = denomination;
    }
    public MedicineDenominationImage getMedicineDenominationImage() {
        return medicineDenominationImage;
    }
    public void setMedicineDenominationImage(MedicineDenominationImage medicineDenominationImage) {
        this.medicineDenominationImage = medicineDenominationImage;
    }
}
