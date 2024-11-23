package com.source.HospitalDB.Classes;

import com.source.HospitalDB.App;

public class LabTest {
    private final int testID;
    private final String testDescription;

    public LabTest(String testDescription) {
        App.inc_count_labTests();
        testID = App.get_count_labTests();
        this.testDescription = testDescription;
    }

    public LabTest(int testID, String testDescription) {
        this.testID = testID;
        this.testDescription = testDescription;
    }

    // Getters and Setters
    public int getTestID() { return testID; }

    public String getTestDescription() { return testDescription; }

}