package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.DBManager;

public class MedicineDenominationImage {

    private Integer medicineDenominationImageId;
    private MedicineDenomination medicineDenomiation;
    private String image;

    public MedicineDenominationImage() {}
    


    public MedicineDenominationImage(Integer medicineDenominationImageId, String image) {
        this.medicineDenominationImageId = medicineDenominationImageId;
        this.image = image;
    }



    public Integer getMedicineDenominationImageId() {
        return medicineDenominationImageId;
    }

    public void setMedicineDenominationImageId(Integer medicineDenominationImageId) {
        this.medicineDenominationImageId = medicineDenominationImageId;
    }

    public static void savePicPath(int denominationId, String picPath) {
        try {
            Connection con = DBManager.getConnection();

            String query = "insert into medicine_denomination_images (image , medicine_denomination_id) VALUES(?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, picPath);
            ps.setInt(2, denominationId);

            ps.executeUpdate();

            con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, String> collectImagePaths(List<Integer> denominationIds){
        Map<Integer, String> imageMap = new HashMap<>();

        try{
            Connection con = DBManager.getConnection();

            String query = "SELECT image FROM medicine_denomination_images WHERE medicine_denomination_id = ?";
            PreparedStatement ps = con.prepareStatement(query); 

            for (int denominationId : denominationIds) {
                ps.setInt(1, denominationId); 
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    imageMap.put(
                        denominationId, 
                        rs.getString("image")
                    );
                }
            } 
            ps.close();
             con.close();

        }
         catch (SQLException e ) {
            e.printStackTrace();
        }

        return imageMap;
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
