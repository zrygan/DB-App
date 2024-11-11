package com.HospitalDB;

import java.time.LocalDateTime;

/**
 * Class for Medical Report By Zhean Ganituen
 */
public class Medical {

    /**
     * the record id
     */
    private final int record_id;

    /**
     * the patient id
     */
    private final int patient_id; 

    /**
     * the chief complaint of the patient. Example: "difficulty breathing"
     */
    private final String chief_complaint;

    /**
     * the reason for admission of the patient, the possible diagoses from the
     * chief complaint. Example: "Asthma, Allergy"
     */
    private final String reason_for_admission;

    /**
     * the final diagnosis of the patient given by the doctor. Example:
     * "Allergic Asthma"
     */
    private String medical_diagnosis;

    /**
     * the patients temperature.
     */
    private float temperature;

    /**
     * the patients pulse or heart beats per minute
     */
    private int pulse;

    /**
     * the patient's systolic pressure. This is the numerator in the blood
     * pressure reading. Example: "130/80", 130 is the BP_systolic
     */
    private int BP_systolic;

    /**
     * the patient's diastolic pressure. This is the denominator in the blood
     * pressure reading. Example: "130/80", 80 is the BP_diastolic
     */
    private int BP_diastolic;

    /**
     * the amount of oxygen in the patient's blood. From 0 to 100
     */
    private int spo2;

    /**
     * date_time the date and time the Medical Record is recorded.
     */
    private LocalDateTime date_time; // FIXME: USE java.sql.Timestamp instead to be able to use SQL methods

    /**
     * Constructor for Medical class. This is also the record creating method.
     *
     * @param DATABASE the database instance
     * @param PATIENT the patient who "owns" this medical record
     * @param chief_complaint the chief complaint of the patient
     * @param reason_for_admission the reason for admission of the patient
     * @param medical_diagnosis the final diagnosis of the patient
     * @param temperature the patient's temperature
     * @param pulse the patient's pulse or heart beats per minute
     * @param BP_systolic the patient's systolic pressure
     * @param BP_diastolic the patient's diastolic pressure
     * @param spo2 the amount of oxygen in the patient's blood
     * @param date_time the date and time the Medical Record is recorded
     */
    public Medical(int record_id, int patient_id, String chief_complaint, String reason_for_admission, String medical_diagnosis, float temperature, int pulse, int BP_systolic, int BP_diastolic, int spo2, LocalDateTime date_time) {
        
        // FIXME: removed checker if the patient does exist (zry)

        this.record_id = record_id;
        this.patient_id = patient_id;
        this.chief_complaint = (chief_complaint != null) ? chief_complaint : "Not Provided"; 
        this.reason_for_admission = (reason_for_admission != null) ? reason_for_admission : "Not Provided";
        this.medical_diagnosis = (medical_diagnosis != null) ? medical_diagnosis : "Not Provided";
        this.temperature = temperature;
        this.pulse = pulse;
        this.BP_systolic = BP_systolic;
        this.BP_diastolic = BP_diastolic;
        this.spo2 = spo2;
        this.date_time = date_time;


        // FIXME: Removed this for now
        // check if the reason for admission and diagnosis is NULL
        // if it is then set it to the previous RFA and Diagnosis of the patient
        // otherwise, set a different one
        // if (reason_for_admission == null) {
        //     the most recent (or previous medical record of the patient) is the last one
        //     scary single liner
        //     this.reason_for_admission = PATIENT.getMEDICAL().get(PATIENT.getMEDICAL().size() - 1).getReasonForAdmission();
        // } else {
        //     this.reason_for_admission = reason_for_admission;
        // }

        // if (medical_diagnosis == null) {
        //     this.medical_diagnosis = PATIENT.getMEDICAL().get(PATIENT.getMEDICAL().size() - 1).getMedicalDiagnosis();
        // } else {
        //     this.medical_diagnosis = medical_diagnosis;
        // }

        //FIXME: removed adding this medicaal record to the list of records of the patient
        // is this even necessary tho? doesnt SQL verify that already?
    }

    // Getters
    
    public int getRecord_id(){
        return record_id;

    }

    public int getPatient_id(){
        return patient_id;
    }

    /**
     * Gets chief complaint.
     *
     * @return the chief complaint
     */
    public String getChiefComplaint() {
        return chief_complaint;
    }

    /**
     * Gets the reason for admission.
     *
     * @return the reason for admission
     */
    public String getReasonForAdmission() {
        return reason_for_admission;
    }

    /**
     * Gets the medical diagnosis.
     *
     * @return the medical diagnosis
     */
    public String getMedicalDiagnosis() {
        return medical_diagnosis;
    }

    /**
     * Gets the temperature.
     *
     * @return the temperature
     */
    public float getTemperature() {
        return temperature;
    }

    /**
     * Gets the pulse.
     *
     * @return the pulse
     */
    public int getPulse() {
        return pulse;
    }

    /**
     * Gets the systolic blood pressure.
     *
     * @return the systolic blood pressure
     */
    public int getBPSystolic() {
        return BP_systolic;
    }

    /**
     * Gets the diastolic blood pressure.
     *
     * @return the diastolic blood pressure
     */
    public int getBPDiastolic() {
        return BP_diastolic;
    }

    /**
     * Gets the SpO2 level.
     *
     * @return the SpO2 level
     */
    public int getSpo2() {
        return spo2;
    }

    /**
     * Gets the date and time.
     *
     * @return the date and time
     */
    public LocalDateTime getDateTime() {
        return date_time;
    }

    // Setters
    /**
     * Sets the medical diagnosis.
     *
     * @param medical_diagnosis the medical diagnosis to set
     */
    public void setMedicalDiagnosis(String medical_diagnosis) {
        this.medical_diagnosis = medical_diagnosis;
    }

    /**
     * Sets the temperature.
     *
     * @param temperature the temperature to set
     */
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    /**
     * Sets the pulse.
     *
     * @param pulse the pulse to set
     */
    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    /**
     * Sets the systolic blood pressure.
     *
     * @param BP_systolic the systolic blood pressure to set
     */
    public void setBPSystolic(int BP_systolic) {
        this.BP_systolic = BP_systolic;
    }

    /**
     * Sets the diastolic blood pressure.
     *
     * @param BP_diastolic the diastolic blood pressure to set
     */
    public void setBPDiastolic(int BP_diastolic) {
        this.BP_diastolic = BP_diastolic;
    }

    /**
     * Sets the SpO2 level.
     *
     * @param spo2 the SpO2 level to set
     */
    public void setSpo2(int spo2) {
        this.spo2 = spo2;
    }

    /**
     * Sets the date and time.
     *
     * @param date_time the date and time to set
     */
    public void setDateTime(LocalDateTime date_time) {
        this.date_time = date_time;
    }
}