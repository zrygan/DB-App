package com.source.HospitalDB;

import java.sql.Timestamp;

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
     * the id of the medication
     */
    private int med_ID;

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
    private Timestamp date_time;

    /**
     * the intake frequency of the medication
     */
    private String frequency;
    
    /**
     * the dosage of the medication per intake
     */
    private float dosage;

    /**
     * the prescribing doctor of the medication
     */
    private int pres_doc;
    
    /**
     * the patient of the prescribing doctor
     */
    private int patient;

    /**
     * Constructor for Medication class
     * 
     * @param database the database instance
     * @param generic the generic name of the medication
     * @param brand the brand name of the medication
     * @param date_time the date and time the medication record is recorded
     * @param frequency the intake frequency of the medication
     * @param dosage the dosage of the medication per intake
     * @param pres_doc the prescribing doctor of the medication
     * @param patient the patient of the prescribing doctor
     */
    public Medication(int med_ID, String generic, String brand, Timestamp date_time, String frequency, float dosage, int pres_doc, int patient) {
        this.med_ID = med_ID;
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
     * Gets the medication ID
     * 
     * @return the medication ID
     */
    public int getMed_ID() {
        return med_ID;
    }

    /**
     * Sets the medication ID
     * 
     * @param med_ID the medication ID
     */
    public void setMed_ID(int med_ID) {
        this.med_ID = med_ID;
    }

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
    public Timestamp getDate_time() {
        return date_time;
    }

    /**
     * Sets the date and time of the medication record
     * 
     * @param date_time the date and time of the medication record
     */
    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }
    
    /**
     * Gets the intake frequency of the medication
     * 
     * @return the intake frequency of the medication
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * Sets the intake frequency of the medication
     * 
     * @param frequency the intake frequency of the medication
     */
    public void setFrequency(String frequency) {
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
    public int getPres_doc() {
        return pres_doc;
    }

    /**
     * Sets the name of the prescribing doctor of the medication record
     * 
     * @param pres_doc the name of the prescribing doctor of the medication record
     */
    public void setPres_doc(int pres_doc) {
        this.pres_doc = pres_doc;
    }

    /**
     * Gets the name of the patient of the medication record
     * 
     * @return the name of the patient of the medication record
     */
    public int getPatient() {
        return patient;
    }

    /**
     * Sets the name of the patient of the medication record
     * 
     * @param patient the name of the patient of the medication record
     */
    public void setPatient(int patient) {
        this.patient = patient;
    }

}
