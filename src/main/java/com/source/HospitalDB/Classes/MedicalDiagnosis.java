package com.source.HospitalDB.Classes;

public class MedicalDiagnosis {
    private final int diagnosisID;
    private final String diagnosisDescription;

    public MedicalDiagnosis(int diagnosisID, String diagnosisDescription) {
        this.diagnosisID = diagnosisID;
        this.diagnosisDescription = diagnosisDescription;
    }

    // Getters and Setters
    public int getDiagnosisID() { return diagnosisID; }

    public String getDiagnosisDescription() { return diagnosisDescription; }
    
}
