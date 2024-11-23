package com.source.HospitalDB.Classes;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.source.HospitalDB.App;

public class VitalSigns {
    private final int vitalSignsID;
    private final BigDecimal temperature;
    private final int pulse;
    private final int respiratoryRate;
    private final int systolicBP;
    private final int diastolicBP;
    private final int spo2;
    private final Timestamp vitalSignsDate;

    // Constructor to set vital signs, with date at 00:00:00
    public VitalSigns(BigDecimal temperature, int pulse, int respiratoryRate, int systolicBP, int diastolicBP, int spo2) {
        App.inc_count_vitalSigns();
        vitalSignsID = App.get_count_vitalSigns();

        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Convert it to LocalDateTime at midnight (00:00:00)
        LocalDateTime localDateTimeAtMidnight = currentDate.atStartOfDay();
        
        // Convert to Timestamp
        vitalSignsDate = Timestamp.valueOf(localDateTimeAtMidnight);

        this.temperature = temperature;
        this.pulse = pulse;
        this.respiratoryRate = respiratoryRate;
        this.systolicBP = systolicBP;
        this.diastolicBP = diastolicBP;
        this.spo2 = spo2;
    }

    public VitalSigns(int vitalSignsID, BigDecimal temperature, int pulse, int respiratoryRate, int systolicBP, int diastolicBP, int spo2) {
        this.vitalSignsID = vitalSignsID;
        
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Convert it to LocalDateTime at midnight (00:00:00)
        LocalDateTime localDateTimeAtMidnight = currentDate.atStartOfDay();
        
        // Convert to Timestamp
        vitalSignsDate = Timestamp.valueOf(localDateTimeAtMidnight);

        this.temperature = temperature;
        this.pulse = pulse;
        this.respiratoryRate = respiratoryRate;
        this.systolicBP = systolicBP;
        this.diastolicBP = diastolicBP;
        this.spo2 = spo2;
    }

    // Getters and Setters
    public int getVitalSignsID() { return vitalSignsID; }

    public BigDecimal getTemperature() { return temperature; }

    public int getPulse() { return pulse; }

    public int getRespiratoryRate() { return respiratoryRate; }

    public int getSystolicBP() { return systolicBP; }

    public int getDiastolicBP() { return diastolicBP; }

    public int getSpo2() { return spo2; }

    public java.sql.Timestamp getVitalSignsDate() { return vitalSignsDate; }
}