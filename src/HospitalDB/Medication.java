package HospitalDB;

import java.time.LocalDateTime;

/*
Medication Record
(Generic Name, Brand Name, Date, Frequency, Dosage, Prescribing Doctor, Patient)
Viewing all medications in the Medication Record with the same generic name (e.g., Paracetamol, Ibuprofen, Prednisone).
Viewing all medications in the Medication Record with the same brand name (e.g., Tylenol, Biogesic, Nizoral).

Assigned to Jaztin Jimenez
*/

/*
 * Class for Medication Record
 */
public class Medication{
    
    private String generic;

    private String brand;

    private LocalDateTime date_time;

    private int frequency;
    
    private float dosage;

    private String pres_doc;
    
    private String patient;

    public Medication(String generic, String brand, LocalDateTime date, int frequency, float dosage, String pres_doc, String patient) {
        this.generic = generic;
        this.brand = brand;
        this.date_time = date;
        this.frequency = frequency;
        this.dosage = dosage;
        this.pres_doc = pres_doc;
        this.patient = patient;
    }

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }
    
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    public LocalDateTime getDate_time() {
        return date_time;
    }

    public void setDate_time(LocalDateTime date_time) {
        this.date_time = date_time;
    }
    
    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public float getDosage() {
        return dosage;
    }

    public void setDosage(float dosage) {
        this.dosage = dosage;
    }

    public String getPres_doc() {
        return pres_doc;
    }

    public void setPres_doc(String pres_doc) {
        this.pres_doc = pres_doc;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

}
