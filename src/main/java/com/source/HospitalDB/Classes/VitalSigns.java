package com.source.HospitalDB.Classes;

import java.math.BigDecimal;

public class VitalSigns {
    private final int vitalSignsID;
    private final BigDecimal temperature;
    private final int pulse;
    private final int respiratoryRate;
    private final int systolicBP;
    private final int diastolicBP;
    private final int spo2;
    private final java.sql.Timestamp vitalSignsDate;

    public VitalSigns(int vitalSignsID, BigDecimal temperature, int pulse, int respiratoryRate, int systolicBP, int diastolicBP, int spo2, java.sql.Timestamp vitalSignsDate) {
        this.vitalSignsID = vitalSignsID;
        this.temperature = temperature;
        this.pulse = pulse;
        this.respiratoryRate = respiratoryRate;
        this.systolicBP = systolicBP;
        this.diastolicBP = diastolicBP;
        this.spo2 = spo2;
        this.vitalSignsDate = vitalSignsDate;
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
