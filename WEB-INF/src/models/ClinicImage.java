package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.DBManager;

public class ClinicImage {

    private Integer clinicImageId;
    private Clinic clinic;
    private String image;

    public ClinicImage() {}
    

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
