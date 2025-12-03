package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.DBManager;

public class Prescription {

    private Integer prescriptionId;
    private Appointment appointment;
    private MedicineDenomination medicineDenomination;
    private DayFrequency dayFrequency;
    private Boolean consumptionStatus;
    private SpanFrequency spanFrequency;
    private Integer courseDuration;
    private String description;

    //----helper
    private Integer medicineDenominationId;

    public Prescription() {}

    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    public Prescription(Boolean consumptionStatus , Integer courseDuration , String description , Appointment appointment ,  Integer medicineDenominationId, DayFrequency dayFrequency , SpanFrequency spanFrequency) 
    {
        this.consumptionStatus = consumptionStatus;
        this.courseDuration = courseDuration;
        this.description = description;
        this.appointment = appointment;
        this.medicineDenominationId = medicineDenominationId;
        this.dayFrequency = dayFrequency;
        this.spanFrequency = spanFrequency;
    }

    public Boolean savePrescription(){
        Boolean flag = false;
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO prescriptions(consumption_status , course_duration , description, appointment_id , medicine_denomination_id , day_frequency_id , span_frequency_id) VALUES (?,?,?,?,?,?,?)");
            ps.setBoolean(1, consumptionStatus);
            ps.setInt(2,courseDuration);
            ps.setString(3, description);
            ps.setInt(4 ,appointment.getAppointmentId());
            ps.setInt(5 , medicineDenominationId);
            ps.setInt(6, dayFrequency.getDayFrequencyId());
            ps.setInt(7, spanFrequency.getSpanFrequencyId());

            int i = ps.executeUpdate();
            if(i>=1) flag = true;

            con.close();

        } catch (SQLException e) {
           e.printStackTrace(); 
        }

        return flag;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Boolean getConsumptionStatus() {
        return consumptionStatus;
    }

    public void setConsumptionStatus(Boolean consumptionStatus) {
        this.consumptionStatus = consumptionStatus;
    }

    public Integer getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(Integer courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getDescription() {
        return description;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public MedicineDenomination getMedicineDenomination() {
        return medicineDenomination;
    }

    public void setMedicineDenomination(MedicineDenomination medicineDenomination) {
        this.medicineDenomination = medicineDenomination;
    }

    public DayFrequency getDayFrequency() {
        return dayFrequency;
    }

    public void setDayFrequency(DayFrequency dayFrequency) {
        this.dayFrequency = dayFrequency;
    }

    public SpanFrequency getSpanFrequency() {
        return spanFrequency;
    }

    public void setSpanFrequency(SpanFrequency spanFrequency) {
        this.spanFrequency = spanFrequency;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
