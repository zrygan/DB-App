package com.source.HospitalDB.Classes;

public class LabTest {
    private final int testID;
    private final String testDescription;

    public LabTest(int testID, String testDescription) {
        this.testID = testID;
        this.testDescription = testDescription;
    }

    // Getters and Setters
    public int getTestID() { return testID; }

    public String getTestDescription() { return testDescription; }

}
