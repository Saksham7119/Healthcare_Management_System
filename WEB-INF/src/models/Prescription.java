package models;

public class Prescription {

    private Integer prescriptionId;
    private Appointment appointment;
    private MedicineDenomination medicineDenomination;
    private DayFrequency dayFrequency;
    private Boolean consumptionStatus;
    private SpanFrequency spanFrequency;
    private Integer courseDuration;
    private String description;

    public Prescription() {}

    public Integer getPrescriptionId() {
        return prescriptionId;
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
