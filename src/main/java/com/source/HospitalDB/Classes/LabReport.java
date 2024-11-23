package com.source.HospitalDB.Classes;

import com.source.HospitalDB.App;


public class LabReport {
    private final int labReportID;
    private final int testID;
    private final int consultationID;

    public LabReport(int testID, int consultationID) {
        App.inc_count_labReport();
        labReportID = App.get_count_labReport();
        this.testID = testID;
        this.consultationID = consultationID;
    }

    public LabReport(int labReport_ID, int testID, int consultationID) {
        this.labReportID = labReport_ID;
        this.testID = testID;
        this.consultationID = consultationID;
    }

    // Getters and Setters
    public int getLabReportID() { return labReportID; }

    public int getTestID() { return testID; }
    
    public int getConsultationID() { return consultationID; }
}