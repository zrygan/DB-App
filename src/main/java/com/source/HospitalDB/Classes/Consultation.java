package com.source.HospitalDB.Classes;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.source.HospitalDB.App;

public class Consultation {
    private final int consultationID;
    private final int doctorID;
    private final int patientID;
    private final int vitalSignsID;
    private final java.sql.Timestamp consultationDate;

    public Consultation(int doctorID, int patientID, int vitalSignsID) {
        App.inc_count_consultation();
        consultationID = App.get_count_consultation();

        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Convert it to LocalDateTime at midnight (00:00:00)
        LocalDateTime localDateTimeAtMidnight = currentDate.atStartOfDay();
        
        consultationDate = Timestamp.valueOf(localDateTimeAtMidnight);

        this.doctorID = doctorID;
        this.patientID = patientID;
        this.vitalSignsID = vitalSignsID;
    }

    public Consultation(int consultationID, int doctorID, int patientID, int vitalSignsID) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Convert it to LocalDateTime at midnight (00:00:00)
        LocalDateTime localDateTimeAtMidnight = currentDate.atStartOfDay();
        
        consultationDate = Timestamp.valueOf(localDateTimeAtMidnight);

        this.consultationID = consultationID;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.vitalSignsID = vitalSignsID;
    }

    // Getters and Setters
    public int getConsultationID() { return consultationID; }

    public int getDoctorID() { return doctorID; }

    public int getPatientID() { return patientID; }

    public int getVitalSignsID() { return vitalSignsID; }

    public java.sql.Timestamp getConsultationDate() { return consultationDate; }


}