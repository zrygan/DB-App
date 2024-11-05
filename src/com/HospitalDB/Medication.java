package com.HospitalDB;

import java.time.LocalDateTime;

/*
Medication Record
(Generic Name, Brand Name, Date, Frequency, Dosage, Prescribing Doctor, Patient)
Viewing all medications in the Medication Record with the same generic name (e.g., Paracetamol, Ibuprofen, Prednisone).
Viewing all medications in the Medication Record with the same brand name (e.g., Tylenol, Biogesic, Nizoral).

Assigned to Jaztin Jimenez
*/

/**
 * Class for Medication Record
 */
public class Medication{
    
    /**
     * the generic name of the medication
     */
    private String generic;

    /**
     * the brand name of the medication
     */
    private String brand;

    /**
     * the date and time the medication record is recorded
     */
    private LocalDateTime date_time;

    /**
     * the intake frequency of the medication
     * 
     * FIXME: check how it would be called if its gonna be a "Per Day" or "Per Week"
     */
    private int frequency;
    
    /**
     * the dosage of the medication per intake
     */
    private float dosage;

    /**
     * the prescribing doctor of the medication
     */
    private String pres_doc;
    
    /**
     * the patient of the prescribing doctor
     */
    private String patient;

    /**
     * Constructor for Medication class
     * 
     * @param generic the generic name of the medication
     * @param brand the brand name of the medication
     * @param date_time the date and time the medication record is recorded
     * @param frequency the intake frequency of the medication
     * @param dosage the dosage of the medication per intake
     * @param pres_doc the prescribing doctor of the medication
     * @param patient the patient of the prescribing doctor
     */
    public Medication(String generic, String brand, LocalDateTime date_time, int frequency, float dosage, String pres_doc, String patient) {
        this.generic = generic;
        this.brand = brand;
        this.date_time = date_time;
        this.frequency = frequency;
        this.dosage = dosage;
        this.pres_doc = pres_doc;
        this.patient = patient;
    }

    
    // Getters and Setters
    /**
     * Gets the generic name of the medication
     * 
     * @return the generic name
     */
    public String getGeneric() {
        return generic;
    }

    /**
     * Sets the generic name of the medication
     * 
     * @param generic the generic name of the medication
     */
    public void setGeneric(String generic) {
        this.generic = generic;
    }
    
    /**
     * Gets the brand name of the medication
     * 
     * @return the brand name of the medication
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand name of the medication
     * 
     * @param brand the brand name of the medication
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets the date and time of the medication record
     * 
     * @return the date and time of the medication record
     */
    public LocalDateTime getDate_time() {
        return date_time;
    }

    /**
     * Sets the date and time of the medication record
     * 
     * @param date_time the date and time of the medication record
     */
    public void setDate_time(LocalDateTime date_time) {
        this.date_time = date_time;
    }
    
    /**
     * Gets the intake frequency of the medication
     * 
     * @return the intake frequency of the medication
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Sets the intake frequency of the medication
     * 
     * @param frequency the intake frequency of the medication
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /**
     * Get the dosage of the medication per intake 
     * 
     * @return the dosage of the medication per intake 
     */
    public float getDosage() {
        return dosage;
    }

    /**
     * Sets the dosage of the medication per intake
     * 
     * @param dosage the dosage of the medication per intake
     */
    public void setDosage(float dosage) {
        this.dosage = dosage;
    }

    /**
     * Gets the name of the prescribing doctor of the medication record
     * 
     * @return the name of the prescribing doctor of the medication record
     */
    public String getPres_doc() {
        return pres_doc;
    }

    /**
     * Sets the name of the prescribing doctor of the medication record
     * 
     * @param pres_doc the name of the prescribing doctor of the medication record
     */
    public void setPres_doc(String pres_doc) {
        this.pres_doc = pres_doc;
    }

    /**
     * Gets the name of the patient of the medication record
     * 
     * @return the name of the patient of the medication record
     */
    public String getPatient() {
        return patient;
    }

    /**
     * Sets the name of the patient of the medication record
     * 
     * @param patient the name of the patient of the medication record
     */
    public void setPatient(String patient) {
        this.patient = patient;
    }

}
