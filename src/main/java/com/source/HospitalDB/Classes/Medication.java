package com.source.HospitalDB.Classes;

import com.source.HospitalDB.App;

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
    
    private final int medicationID;
    private final String genericName;
    private final String brandName;

    public Medication(String genericName, String brandName) {
        App.inc_count_medication();
        medicationID = App.get_count_medication();
        this.genericName = genericName;
        this.brandName = brandName;
    }

    
    // Getters and Setters
    public int getMedicationID() { return medicationID; }

    public String getGenericName() { return genericName; }

    public String getBrandName() { return brandName; }

}
