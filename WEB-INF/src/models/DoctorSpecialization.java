package models;

public class DoctorSpecialization {

    private Integer doctorSpecializationId;
    private Doctor doctor;
    private Specialization specialization;

    public DoctorSpecialization() {}

    public Integer getDoctorSpecializationId() {
        return doctorSpecializationId;
    }

    public void setDoctorSpecializationId(Integer doctorSpecializationId) {
        this.doctorSpecializationId = doctorSpecializationId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
}
