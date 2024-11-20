package com.source.HospitalDB.Classes;

public class Consultation {
    private final int consultationID;
    private final int prescriptionID;
    private final int doctorID;
    private final int patientID;
    private final int vitalSignsID;
    private final int labReportID;
    private final java.sql.Timestamp consultationDate;

    public Consultation(int consultationID, int prescriptionID, int doctorID, int patientID, int vitalSignsID, int labReportID, java.sql.Timestamp consultationDate) {
        this.consultationID = consultationID;
        this.prescriptionID = prescriptionID;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.vitalSignsID = vitalSignsID;
        this.labReportID = labReportID;
        this.consultationDate = consultationDate;
    }

    // Getters and Setters
    public int getConsultationID() { return consultationID; }

    public int getPrescriptionID() { return prescriptionID; }

    public int getDoctorID() { return doctorID; }

    public int getPatientID() { return patientID; }

    public int getVitalSignsID() { return vitalSignsID; }

    public int getLabReportID() { return labReportID; }

    public java.sql.Timestamp getConsultationDate() { return consultationDate; }

}
