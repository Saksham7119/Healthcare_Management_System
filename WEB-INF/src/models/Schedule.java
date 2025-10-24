package models;

public class Schedule {

    private Integer scheduleId;
    private String startTime;
    private String endTime;
    private Integer patientLimit;
    private Clinic clinic;

    public Schedule() {}

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
}
