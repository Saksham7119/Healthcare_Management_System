package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DBManager;

public class ClinicImage {

    private Integer clinicImageId;
    private Clinic clinic;
    private String image;

    public ClinicImage() {}
    

    public ClinicImage(Integer clinicImageId, String image, Clinic clinic) {
        this.clinicImageId = clinicImageId;
        this.image = image;
        this.clinic = clinic;
    }


    public ClinicImage(Clinic clinic, String image) {
        this.clinic = clinic;
        this.image = image;
    }

    public boolean addClinicImage(){
        boolean flag = false;
        try {
            Connection con = DBManager.getConnection();
            String query = "INSERT INTO clinic_images (image , clinic_id) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, image);
            ps.setInt(2, clinic.getClinicId());

            int i = ps.executeUpdate();
            if(i > 0) flag = true;

            con.close();            
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

     public static ArrayList<ClinicImage> fetchAllClinicImages(int clinicId){
        ArrayList<ClinicImage> arrayListClinicImage = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            String query = "SELECT * FROM clinic_images where clinic_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, clinicId);
    
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Clinic clinic = null;
                int clinicIdFromDb = rs.getInt("clinic_id");
                clinic = new Clinic().getClinicyById(clinicIdFromDb);
                arrayListClinicImage.add(
                    new ClinicImage( 
                    rs.getInt("clinic_image_id"),
                    rs.getString("image"),
                    clinic
                    )
                );
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arrayListClinicImage;
    }

    public Integer getClinicImageId() {
        return clinicImageId;
    }

    public void setClinicImageId(Integer clinicImageId) {
        this.clinicImageId = clinicImageId;
    }

    public String getImage() {
        return image;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
