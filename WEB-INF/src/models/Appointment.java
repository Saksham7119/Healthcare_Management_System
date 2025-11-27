package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import utils.DBManager;

import java.sql.Date;

public class Appointment {

    private Integer appointmentId;
    private Date appointmentDate;
    private Schedule schedule;
    private String diagnosed;
    private Patient patient;
    private Integer billAmount;
    private Status status;

    public Appointment() {}



    public Integer getAppointmentId() {
        return appointmentId;
    }
    
    public Appointment(Date appointmentDate, String diagnosed, Schedule schedule, Patient patient) {
        this.appointmentDate = appointmentDate;
        this.diagnosed = diagnosed;
        this.schedule = schedule;
        this.patient = patient;
    }

    public Appointment(Integer appointmentID , Date appointmentDate, String diagnosed, Schedule schedule, Patient patient) {
        this.appointmentId = appointmentID;
        this.appointmentDate = appointmentDate;
        this.diagnosed = diagnosed;
        this.schedule = schedule;
        this.patient = patient;
    }



    public Boolean bookPatientAppointment(){
        Boolean flag = false;
        try {
            Connection con = utils.DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO appointments (appointment_date , diagnosed, schedule_id , patient_id) VALUES (?,?,?,?)");
            ps.setDate(1, appointmentDate);
            ps.setString(2, diagnosed);
            ps.setInt(3, schedule.getScheduleId());
            ps.setInt(4, patient.getPatientId());
            int i = ps.executeUpdate();

            if(i>=1)
                 flag = true; 

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static ArrayList<Appointment> collectAppointmentsByPatientId(int patientId){
        ArrayList<Appointment> arrayListAppointments = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM appointments WHERE patient_id = ?");
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Schedule scheduleInfoObj = Schedule.getScheduleInfoById(rs.getInt("schedule_id"));
                Patient patientInfoObj = Patient.getPatientById(rs.getInt("patient_id"));

                Appointment appointmentObj = new Appointment(
                    rs.getInt("appointment_id"),
                    rs.getDate("appointment_date"),
                    rs.getString("diagnosed"),
                    scheduleInfoObj,
                    patientInfoObj
                );

                arrayListAppointments.add(appointmentObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayListAppointments;
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



    public Schedule getSchedule() {
        return schedule;
    }



    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
