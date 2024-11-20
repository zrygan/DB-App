package com.source.HospitalDB.Classes;

public class Consult_MedDiagnosis {
    private final int consultationID;
    private final int diagnosisID;

    public Consult_MedDiagnosis(int consultationID, int diagnosisID) {
        this.consultationID = consultationID;
        this.diagnosisID = diagnosisID;
    }

    // Getters and Setters
    public int getConsultationID() { return consultationID; }

    public int getDiagnosisID() { return diagnosisID; }

}
