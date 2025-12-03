package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

import utils.DBManager;

public class Schedule {

    private Integer scheduleId;
    private Time startTime;
    private Time endTime;
    private Integer patientLimit;
    private Clinic clinic;
    private Integer clinicId;
    private ArrayList<Appointment> appointment; 

    public Schedule() {}

    public Schedule(Time startTime, Time endTime, Integer patientLimit, Integer clinicId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.patientLimit = patientLimit;
        this.clinicId = clinicId;
    }

    public Schedule(Integer scheduleId, Time startTime, Time endTime, Integer patientLimit) {
        this.scheduleId = scheduleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.patientLimit = patientLimit;
    }
    public Schedule(Integer scheduleId, Time startTime, Time endTime, Integer patientLimit , ArrayList<Appointment> appointment) {
        this.scheduleId = scheduleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.patientLimit = patientLimit;
        this.appointment = appointment;
    }

    public Schedule(Integer scheduleId, Time starTime , Time endTime , Integer patientLimit, Clinic clinic){
        this.scheduleId = scheduleId;
        this.startTime = starTime;
        this.endTime = endTime;
        this.patientLimit = patientLimit;
        this.clinic = clinic;
    }


    public Boolean setClinicSchedule(){
        Boolean flag = false;
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO schedules (start_time , end_time , patient_limit, clinic_id) VALUES (?,?,?,?)");
            ps.setTime(1, startTime);
            ps.setTime(2, endTime);
            ps.setInt(3, patientLimit);
            ps.setInt(4 , clinicId);

            int i = ps.executeUpdate();
            if(i>0) flag = true;

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    public static ArrayList<Schedule> collectSchedules(int clinicId){
        ArrayList<Schedule> arrayListSchedule = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM schedules WHERE clinic_id=?");
            ps.setInt(1, clinicId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                arrayListSchedule.add(
                    new Schedule(rs.getInt("schedule_id") , rs.getTime("start_time"), rs.getTime("end_time"), rs.getInt("patient_limit"))
                );
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayListSchedule;
    }

    public static ArrayList<Schedule> collectSchedulesWithAppointments(int clinicId){
        ArrayList<Schedule> arrayListSchedule = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM schedules WHERE clinic_id=?");
            ps.setInt(1, clinicId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer scheduleId = rs.getInt("schedule_id");
                ArrayList<Appointment> appointments = Appointment.collectAppointmentsByScheduleId(scheduleId);
                arrayListSchedule.add(
                    new Schedule(
                        scheduleId, 
                        rs.getTime("start_time"), 
                        rs.getTime("end_time"), 
                        rs.getInt("patient_limit"),
                        appointments
                        )
                );
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayListSchedule;
    }

    public static Schedule getScheduleInfoById(Integer scheduleId){
        Schedule schedule = null;
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM schedules WHERE schedule_id=?");
            ps.setInt(1, scheduleId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Clinic clinic = Clinic.collectClinicObjectByClinicId(rs.getInt("clinic_id"));
                schedule = new Schedule(
                    rs.getInt("schedule_id") , 
                    rs.getTime("start_time"), 
                    rs.getTime("end_time"), 
                    rs.getInt("patient_limit"), 
                    clinic
                    );
                    
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schedule;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    public Integer getPatientLimit() {
        return patientLimit;
    }

    public void setPatientLimit(Integer patientLimit) {
        this.patientLimit = patientLimit;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public ArrayList<Appointment> getAppointment() {
        return appointment;
    }

    public void setAppointment(ArrayList<Appointment> appointment) {
        this.appointment = appointment;
    }
}
