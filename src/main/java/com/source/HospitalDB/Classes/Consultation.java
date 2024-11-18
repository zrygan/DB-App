package main.java.com.source.HospitalDB.Classes;

public class Consultation {
    private int consultationID;
    private int prescriptionID;
    private int doctorID;
    private int patientID;
    private int vitalSignsID;
    private int labReportID;
    private java.sql.Timestamp consultationDate;

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
    public void setConsultationID(int consultationID) { this.consultationID = consultationID; }

    public int getPrescriptionID() { return prescriptionID; }
    public void setPrescriptionID(int prescriptionID) { this.prescriptionID = prescriptionID; }

    public int getDoctorID() { return doctorID; }
    public void setDoctorID(int doctorID) { this.doctorID = doctorID; }

    public int getPatientID() { return patientID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }

    public int getVitalSignsID() { return vitalSignsID; }
    public void setVitalSignsID(int vitalSignsID) { this.vitalSignsID = vitalSignsID; }

    public int getLabReportID() { return labReportID; }
    public void setLabReportID(int labReportID) { this.labReportID = labReportID; }

    public java.sql.Timestamp getConsultationDate() { return consultationDate; }
    public void setConsultationDate(java.sql.Timestamp consultationDate) { this.consultationDate = consultationDate; }

}
