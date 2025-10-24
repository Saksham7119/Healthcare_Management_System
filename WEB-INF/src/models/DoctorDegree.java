package models;

public class DoctorDegree {

    private Integer doctorDegreeId;
    private Doctor doctor;
    private Degree degree;

    public DoctorDegree() {}

    public Integer getDoctorDegreeId() {
        return doctorDegreeId;
    }

    public void setDoctorDegreeId(Integer doctorDegreeId) {
        this.doctorDegreeId = doctorDegreeId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }
}
