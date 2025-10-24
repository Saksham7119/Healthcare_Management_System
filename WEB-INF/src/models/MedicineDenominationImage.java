package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicineDenominationImage {

    private Integer medicineDenominationImageId;
    private MedicineDenomination medicineDenomiation;
    private String image;

    public MedicineDenominationImage() {}

    public Integer getMedicineDenominationImageId() {
        return medicineDenominationImageId;
    }

    public void setMedicineDenominationImageId(Integer medicineDenominationImageId) {
        this.medicineDenominationImageId = medicineDenominationImageId;
    }

    public static void savePicPath(int denominationId, String picPath) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcaredb?user=root&password=1234");

            String query = "insert into medicine_denomination_images (image , medicine_denomination_id) VALUES(?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, picPath);
            ps.setInt(2, denominationId);

            ps.executeUpdate();

            con.close();
        } catch(SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MedicineDenomination getMedicineDenomiation() {
        return medicineDenomiation;
    }

    public void setMedicineDenomiation(MedicineDenomination medicineDenomiation) {
        this.medicineDenomiation = medicineDenomiation;
    }
}
