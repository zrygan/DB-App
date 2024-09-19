package HospitalDB;

import java.time.LocalDateTime;

/**
 * Class for Medical Report
 */
public class Medical {

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
    private int temperature;

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
    private LocalDateTime date_time;

    /**
     * Constructor for Medical class.
     *
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
    public Medical(String chief_complaint, String reason_for_admission, String medical_diagnosis, int temperature, int pulse, int BP_systolic, int BP_diastolic, int spo2, LocalDateTime date_time) {
        this.chief_complaint = chief_complaint;
        this.reason_for_admission = reason_for_admission;
        this.medical_diagnosis = medical_diagnosis;
        this.temperature = temperature;
        this.pulse = pulse;
        this.BP_systolic = BP_systolic;
        this.BP_diastolic = BP_diastolic;
        this.spo2 = spo2;
        this.date_time = date_time;
    }

    // Getters
    /**
     * Gets the chief complaint.
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
    public int getTemperature() {
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
    public void setTemperature(int temperature) {
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

    /**
     * MedicalRecord_create creates a medical record
     *
     * FIXME: This might need to be a static method
     */
    public void create() {

    }

    /**
     * updates an already existing medical record
     *
     * FIXME: This might need to be a static method
     */
    public void update() {

    }

    /**
     * deletes a medical record
     *
     * FIXME: This might need to be a static method
     */
    public void delete() {

    }

    /**
     * to view the medical record for a specific patient.
     */
    public void viewPatientRecord() {

    }

    /**
     * to view the names of all patients for a specific diagnosis
     */
    public void viewPatientsForDiagnosis() {

    }

    /**
     * to view the summary medical record for all patients. Example the number
     * of patients diagnosed with the "Common Cold"
     */
    public void viewSummary() {

    }
}
