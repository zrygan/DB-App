package com.source.HospitalDB.Classes;

import com.source.HospitalDB.App;


public class LabReport {
    private final int labReportID;
    private final int testID;

    public LabReport(int testID) {
        App.inc_count_labReport();
        labReportID = App.get_count_labReport();
        this.testID = testID;
    }

    // Getters and Setters
    public int getLabReportID() { return labReportID; }

    public int getTestID() { return testID; }
    
}
