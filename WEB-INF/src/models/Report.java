package models;

import java.util.Date;

public class Report{
    private Integer reportId;
    private String reportImage;
    private Date testDate;
    private String diagnosed;
    private Integer billAmount;
    private Test test;
    private Appointment appointment;

    public Report(){

    }

    public void setReportId(Integer reportId){
        this.reportId = reportId;
    }

    public Integer getReportId(){
        return reportId;
    }

    public void setReportImage(String reportImage){
        this.reportImage = reportImage;
    }

    public String getReportImage(){
        return reportImage;
    }

    public void setTestDate(Date testDate){
        this.testDate = testDate;
    }

    public Date getTestDate(){
        return testDate;
    }

    public void setDiagnosed(String diagnosed){
        this.diagnosed = diagnosed;
    }

    public String getDiagnosed(){
        return diagnosed;
    }

    public void setBillAmount(Integer billAmount){
        this.billAmount = billAmount;
    }

    public Integer getBillAmount(){
        return billAmount;
    }

    public void setTest(Test test){
        this.test = test;
    }

    public Test getTest(){
        return test;
    }

    public void setAppointment(Appointment appointment){
        this.appointment = appointment;
    }

    public Appointment getAppointment(){
        return appointment;
    }
}