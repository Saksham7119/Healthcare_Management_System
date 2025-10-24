package models;

public class ClinicImage {

    private Integer clinicImageId;
    private Clinic clinic;
    private String image;

    public ClinicImage() {}

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
