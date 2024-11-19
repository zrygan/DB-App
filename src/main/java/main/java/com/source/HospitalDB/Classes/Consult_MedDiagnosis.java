package main.java.com.source.HospitalDB.Classes;

public class Consult_MedDiagnosis {
    private int consultationID;
    private int diagnosisID;

    public Consult_MedDiagnosis(int consultationID, int diagnosisID) {
        this.consultationID = consultationID;
        this.diagnosisID = diagnosisID;
    }

    // Getters and Setters
    public int getConsultationID() { return consultationID; }
    public void setConsultationID(int consultationID) { this.consultationID = consultationID; }

    public int getDiagnosisID() { return diagnosisID; }
    public void setDiagnosisID(int diagnosisID) { this.diagnosisID = diagnosisID; }

}
