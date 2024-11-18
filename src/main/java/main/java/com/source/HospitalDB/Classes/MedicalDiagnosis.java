package main.java.com.source.HospitalDB.Classes;

public class MedicalDiagnosis {
    private int diagnosisID;
    private String diagnosisDescription;

    public MedicalDiagnosis(int diagnosisID, String diagnosisDescription) {
        this.diagnosisID = diagnosisID;
        this.diagnosisDescription = diagnosisDescription;
    }

    // Getters and Setters
    public int getDiagnosisID() { return diagnosisID; }
    public void setDiagnosisID(int diagnosisID) { this.diagnosisID = diagnosisID; }

    public String getDiagnosisDescription() { return diagnosisDescription; }
    public void setDiagnosisDescription(String diagnosisDescription) { this.diagnosisDescription = diagnosisDescription; }
    
}
