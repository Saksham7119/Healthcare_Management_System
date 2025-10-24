package models;

import java.util.Date;

public class Appointment {

    private Integer appointmentId;
    private Date appointmentDate;
    private Integer scheduleId;
    private String diagnosed;
    private Patient patient;
    private Integer billAmount;
    private Status status;

    public Appointment() {}

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDiagnosed() {
        return diagnosed;
    }

    public void setDiagnosed(String diagnosed) {
        this.diagnosed = diagnosed;
    }

    public Integer getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Integer billAmount) {
        this.billAmount = billAmount;
    }
}
