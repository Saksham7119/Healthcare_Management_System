package models;

public class PatientAllergy {

    private Integer patientAllergyId;
    private Patient patient;
    private Allergy allergy;

    public PatientAllergy() {}

    public Integer getPatientAllergyId() {
        return patientAllergyId;
    }

    public void setPatientAllergyId(Integer patientAllergyId) {
        this.patientAllergyId = patientAllergyId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Allergy getAllergy() {
        return allergy;
    }

    public void setAllergy(Allergy allergy) {
        this.allergy = allergy;
    }
}
