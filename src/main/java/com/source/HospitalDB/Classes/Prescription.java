package com.source.HospitalDB.Classes;

import java.math.BigDecimal;

import com.source.HospitalDB.App;

public class Prescription {
    private final int prescriptionID;
    private final int medicationID;
    private final java.sql.Timestamp prescriptionDate;
    private final int frequency;
    private final BigDecimal dosage;
    private final int doctorID;
    private final int patientID;

    public Prescription(int medicationID, int frequency, BigDecimal dosage, int doctorID, int patientID) {
        App.inc_count_prescription();
        prescriptionID = App.get_count_prescription(); 

        prescriptionDate = App.time_now();

        this.medicationID = medicationID;
        this.frequency = frequency;
        this.dosage = dosage;
        this.doctorID = doctorID;
        this.patientID = patientID;
    }

    // Getters and Setters
    public int getPrescriptionID() { return prescriptionID; }

    public int getMedicationID() { return medicationID; }

    public java.sql.Timestamp getPrescriptionDate() { return prescriptionDate; }

    public int getFrequency() { return frequency; }

    public BigDecimal getDosage() { return dosage; }

    public int getDoctorID() { return doctorID; }

    public int getPatientID() { return patientID; }
    
}
