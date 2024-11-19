package com.source.HospitalDB.Classes;

public class LabTest {
    private int testID;
    private String testDescription;

    public LabTest(int testID, String testDescription) {
        this.testID = testID;
        this.testDescription = testDescription;
    }

    // Getters and Setters
    public int getTestID() { return testID; }
    public void setTestID(int testID) { this.testID = testID; }

    public String getTestDescription() { return testDescription; }
    public void setTestDescription(String testDescription) { this.testDescription = testDescription; }
}
