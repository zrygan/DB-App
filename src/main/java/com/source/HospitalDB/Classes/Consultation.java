package com.source.HospitalDB.Classes;

import com.source.HospitalDB.App;

public class Consultation {
    private final int consultationID;
    private int prescriptionID;
    private final int doctorID;
    private final int patientID;
    private final int vitalSignsID;
    private int labReportID;
    private final java.sql.Timestamp consultationDate;

    public Consultation(int prescriptionID, int doctorID, int patientID, int vitalSignsID, int labReportID) {
        App.inc_count_consultation();
        consultationID = App.get_count_consultation();

        consultationDate = App.time_now();

        this.prescriptionID = prescriptionID;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.vitalSignsID = vitalSignsID;
        this.labReportID = labReportID;
    }

    // Getters and Setters
    public int getConsultationID() { return consultationID; }

    public int getPrescriptionID() { return prescriptionID; }

    public int getDoctorID() { return doctorID; }

    public int getPatientID() { return patientID; }

    public int getVitalSignsID() { return vitalSignsID; }

    public int getLabReportID() { return labReportID; }

    public java.sql.Timestamp getConsultationDate() { return consultationDate; }

    public void setLabReportID(int labReportID) { this.labReportID = labReportID; }

    public void setPrescriptionID(int prescriptionID) { this.prescriptionID = prescriptionID; }

}
