package com.source.HospitalDB.Classes;

public class LabReport {
    private int labReportID;
    private int testID;

    public LabReport(int labReportID, int testID) {
        this.labReportID = labReportID;
        this.testID = testID;
    }

    // Getters and Setters
    public int getLabReportID() { return labReportID; }
    public void setLabReportID(int labReportID) { this.labReportID = labReportID; }

    public int getTestID() { return testID; }
    public void setTestID(int testID) { this.testID = testID; }
    
}
