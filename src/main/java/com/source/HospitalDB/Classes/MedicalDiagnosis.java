package com.source.HospitalDB.Classes;

import com.source.HospitalDB.App;

public class MedicalDiagnosis {
    private final int diagnosisID;
    private final String diagnosisDescription;

    public MedicalDiagnosis(String diagnosisDescription) {
        App.inc_count_consult_medDiagnosis();
        diagnosisID = App.get_count_consult_medDiagnosis();
        this.diagnosisDescription = diagnosisDescription;
    }

    // Getters and Setters
    public int getDiagnosisID() { return diagnosisID; }

    public String getDiagnosisDescription() { return diagnosisDescription; }
    
}
