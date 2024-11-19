package com.source.HospitalDB.Classes;

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
    
    private int medicationID;
    private String genericName;
    private String brandName;

    public Medication(int medicationID, String genericName, String brandName) {
        this.medicationID = medicationID;
        this.genericName = genericName;
        this.brandName = brandName;
    }

    
    // Getters and Setters
    public int getMedicationID() { return medicationID; }
    public void setMedicationID(int medicationID) { this.medicationID = medicationID; }

    public String getGenericName() { return genericName; }
    public void setGenericName(String genericName) { this.genericName = genericName; }

    public String getBrandName() { return brandName; }
    public void setBrandName(String brandName) { this.brandName = brandName; }

}
