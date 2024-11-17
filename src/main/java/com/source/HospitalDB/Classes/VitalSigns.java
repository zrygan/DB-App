package main.java.com.source.HospitalDB.Classes;

public class VitalSigns {
    private int vitalSignsID;
    private double temperature;
    private int pulse;
    private int respiratoryRate;
    private int systolicBP;
    private int diastolicBP;
    private int spo2;
    private java.sql.Timestamp vitalSignsDate;

    public VitalSigns(int vitalSignsID, double temperature, int pulse, int respiratoryRate, int systolicBP, int diastolicBP, int spo2, java.sql.Timestamp vitalSignsDate) {
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
    public void setVitalSignsID(int vitalSignsID) { this.vitalSignsID = vitalSignsID; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public int getPulse() { return pulse; }
    public void setPulse(int pulse) { this.pulse = pulse; }

    public int getRespiratoryRate() { return respiratoryRate; }
    public void setRespiratoryRate(int respiratoryRate) { this.respiratoryRate = respiratoryRate; }

    public int getSystolicBP() { return systolicBP; }
    public void setSystolicBP(int systolicBP) { this.systolicBP = systolicBP; }

    public int getDiastolicBP() { return diastolicBP; }
    public void setDiastolicBP(int diastolicBP) { this.diastolicBP = diastolicBP; }

    public int getSpo2() { return spo2; }
    public void setSpo2(int spo2) { this.spo2 = spo2; }

    public java.sql.Timestamp getVitalSignsDate() { return vitalSignsDate; }
    public void setVitalSignsDate(java.sql.Timestamp vitalSignsDate) { this.vitalSignsDate = vitalSignsDate; }

}
