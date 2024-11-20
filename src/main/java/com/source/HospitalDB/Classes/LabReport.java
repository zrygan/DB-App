package com.source.HospitalDB.Classes;

public class LabReport {
    private final int labReportID;
    private final int testID;

    public LabReport(int labReportID, int testID) {
        this.labReportID = labReportID;
        this.testID = testID;
    }

    // Getters and Setters
    public int getLabReportID() { return labReportID; }

    public int getTestID() { return testID; }
    
}
