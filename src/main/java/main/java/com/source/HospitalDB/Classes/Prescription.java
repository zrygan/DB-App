package main.java.com.source.HospitalDB.Classes;

import java.math.BigDecimal;

public class Prescription {
    private int prescriptionID;
    private int medicationID;
    private java.sql.Timestamp prescriptionDate;
    private int frequency;
    private BigDecimal dosage;
    private int doctorID;
    private int patientID;

    public Prescription(int prescriptionID, int medicationID, java.sql.Timestamp prescriptionDate, int frequency, BigDecimal dosage, int doctorID, int patientID) {
    this.prescriptionID = prescriptionID;
    this.medicationID = medicationID;
    this.prescriptionDate = prescriptionDate;
    this.frequency = frequency;
    this.dosage = dosage;
    this.doctorID = doctorID;
    this.patientID = patientID;
    }

    // Getters and Setters
    public int getPrescriptionID() { return prescriptionID; }
    public void setPrescriptionID(int prescriptionID) { this.prescriptionID = prescriptionID; }

    public int getMedicationID() { return medicationID; }
    public void setMedicationID(int medicationID) { this.medicationID = medicationID; }

    public java.sql.Timestamp getPrescriptionDate() { return prescriptionDate; }
    public void setPrescriptionDate(java.sql.Timestamp prescriptionDate) { this.prescriptionDate = prescriptionDate; }

    public int getFrequency() { return frequency; }
    public void setFrequency(int frequency) { this.frequency = frequency; }

    public BigDecimal getDosage() { return dosage; }
    public void setDosage(BigDecimal dosage) { this.dosage = dosage; }

    public int getDoctorID() { return doctorID; }
    public void setDoctorID(int doctorID) { this.doctorID = doctorID; }

    public int getPatientID() { return patientID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }
    
}
