package com.source.HospitalDB.Classes;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.source.HospitalDB.App;

public class Prescription {
    private final int prescriptionID;
    private final int medicationID;
    private final java.sql.Timestamp prescriptionDate;
    private final int frequency;
    private final BigDecimal dosage;
    private final int doctorID;
    private final int patientID;
    private final int consultationID;

    public Prescription(int medicationID, int frequency, BigDecimal dosage, int doctorID, int patientID, int consultationID) {
        App.inc_count_prescription();
        this.prescriptionID = App.get_count_prescription(); 

        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Convert it to LocalDateTime at midnight (00:00:00)
        LocalDateTime localDateTimeAtMidnight = currentDate.atStartOfDay();
        
        // Convert to Timestamp
        this.prescriptionDate = Timestamp.valueOf(localDateTimeAtMidnight);

        this.medicationID = medicationID;
        this.frequency = frequency;
        this.dosage = dosage;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.consultationID = consultationID;
    }

    public Prescription(int prescriptionID, int medicationID, int frequency, BigDecimal dosage, int doctorID, int patientID, int consultationID) {
        this.prescriptionID = prescriptionID;
        this.medicationID = medicationID;
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Convert it to LocalDateTime at midnight (00:00:00)
        LocalDateTime localDateTimeAtMidnight = currentDate.atStartOfDay();
         
        // Convert to Timestamp
        this.prescriptionDate = Timestamp.valueOf(localDateTimeAtMidnight);
        this.frequency = frequency;
        this.dosage = dosage;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.consultationID = consultationID;
    }   

    // Getters and Setters
    public int getPrescriptionID() { return prescriptionID; }

    public int getMedicationID() { return medicationID; }

    public java.sql.Timestamp getPrescriptionDate() { return prescriptionDate; }

    public int getFrequency() { return frequency; }

    public BigDecimal getDosage() { return dosage; }

    public int getDoctorID() { return doctorID; }

    public int getPatientID() { return patientID; }
    
    public int getConsultationID() { return consultationID; }
    
}