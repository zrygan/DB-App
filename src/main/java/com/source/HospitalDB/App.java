package com.source.HospitalDB;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;

import com.source.HospitalDB.Classes.ChiefComplaint;
import com.source.HospitalDB.Classes.Consultation;
import com.source.HospitalDB.Classes.Doctors;
import com.source.HospitalDB.Classes.LabReport;
import com.source.HospitalDB.Classes.LabTest;
import com.source.HospitalDB.Classes.MedicalDiagnosis;
import com.source.HospitalDB.Classes.Medication;
import com.source.HospitalDB.Classes.Patient;
import com.source.HospitalDB.Classes.Prescription;
import com.source.HospitalDB.Classes.VitalSigns;

public class App {
    public static void main(String[] args) throws SQLException{
        
    }
    
    /************
     * App global variables
     * Contains the HashMaps
     * The count variables
     ************/ 
    
    // the getters and setters for these are at the bottom (since they might not be used)
    private static HashMap<Integer, Patient> patient_map = new HashMap<>();
    private static HashMap<Integer, Doctors> doctors_map = new HashMap<>();
    private static HashMap<Integer, Medication> medication_map = new HashMap<>();
    private static HashMap<Integer, LabTest> labTest_map = new HashMap<>();
    private static HashMap<Integer, MedicalDiagnosis> medicalDiagnosis_map = new HashMap<>();
    private static HashMap<Integer, ChiefComplaint> chiefComplaint_map = new HashMap<>();
    private static HashMap<Integer, Consultation> consultation_map = new HashMap<>();
    private static HashMap<Integer, Prescription> prescription_map = new HashMap<>();
    private static HashMap<Integer, VitalSigns> vitalSigns_map = new HashMap<>();
    private static HashMap<Integer, LabReport> labReport_map = new HashMap<>();

    public static void add_patient(int id, Patient p){ patient_map.put(id, p); }
    
    public static void add_doctors(int id, Doctors p){ doctors_map.put(id, p); }
    
    public static void add_medication(int id, Medication p){ medication_map.put(id, p); }
    
    public static void add_labTests(int id, LabTest p){ labTest_map.put(id, p); }
    
    public static void add_medicalDiagnosis(int id, MedicalDiagnosis p){ medicalDiagnosis_map.put(id, p); }

    public static void add_chiefComplaint(int id, ChiefComplaint p){ chiefComplaint_map.put(id, p); }

    public static void add_consultation(int id, Consultation p){ consultation_map.put(id, p); }
    
    public static void add_prescription(int id, Prescription p){ prescription_map.put(id, p); }

    public static void add_vitalSigns(int id, VitalSigns p){ vitalSigns_map.put(id, p); }
    
    public static void add_labReport(int id, LabReport p){ labReport_map.put(id, p); }
    
    private static int count_patient = 0;
    private static int count_doctors = 0;
    private static int count_medication = 0;
    private static int count_labTests = 0;
    private static int count_chiefComplaint = 0;
    private static int count_consult_medDiagnosis = 0;
    private static int count_consultation = 0;
    private static int count_prescription = 0;
    private static int count_vitalSigns = 0;
    private static int count_labReport = 0;

    // getters
    public static int get_count_patient(){ return count_patient; }
    
    public static int get_count_doctors(){ return count_doctors; }

    public static int get_count_medication(){ return count_medication; }

    public static int get_count_labTests(){ return count_labTests; }

    public static int get_count_chiefComplaint() { return count_chiefComplaint; }

    public static int get_count_consult_medDiagnosis() { return count_consult_medDiagnosis; }

    public static int get_count_consultation() { return count_consultation; }

    public static int get_count_prescription() { return count_prescription; }

    public static int get_count_vitalSigns() { return count_vitalSigns; }

    public static int get_count_labReport() { return count_labReport; }

    // incrementers (setters)
    public static void inc_count_patient(){ count_patient++; }
    
    public static void inc_count_doctors(){ count_doctors++; }
    
    public static void inc_count_medication(){ count_medication++; }

    public static void inc_count_labTests(){ count_labTests++; }

    public static void inc_count_chiefComplaint() { count_chiefComplaint++; }

    public static void inc_count_consult_medDiagnosis() { count_consult_medDiagnosis++; }

    public static void inc_count_consultation() { count_consultation++; }

    public static void inc_count_prescription() { count_prescription++; }

    public static void inc_count_vitalSigns() { count_vitalSigns++; }

    public static void inc_count_labReport() { count_labReport++; }
   
    public static Timestamp time_now(){
        Instant instant = Instant.now();
        return Timestamp.from(instant);
    }

    // FIXME: create a function in App that will create the HospitalDB
    // without running it on SQL Workbench
    // also do the same for the sample data (see make_sample_data())
    public static void make_sql(){  
        // create the database and tables
        
    }

    public static void make_sample_data(){
        // create the INSERT INTO ___
    }
    
    /************
     * Helper functions in the JSP
     ************/

    /************
     * Getter and setter of each Record
     ************/
    public static HashMap<Integer, Patient> getPatientMap() {
        return patient_map;
    }

    public static HashMap<Integer, Doctors> getDoctorsMap() {
        return doctors_map;
    }

    public static HashMap<Integer, Medication> getMedicationMap() {
        return medication_map;
    }

    public static HashMap<Integer, LabTest> getLabTestMap() {
        return labTest_map;
    }

    public static HashMap<Integer, MedicalDiagnosis> getMedicalDiagnosisMap() {
        return medicalDiagnosis_map;
    }

    public static HashMap<Integer, ChiefComplaint> getChiefComplaintMap() {
        return chiefComplaint_map;
    }

    public static HashMap<Integer, Consultation> getConsultationMap() {
        return consultation_map;
    }

    public static HashMap<Integer, Prescription> getPrescriptionMap() {
        return prescription_map;
    }

    public static HashMap<Integer, VitalSigns> getVitalSignsMap() {
        return vitalSigns_map;
    }

    public static HashMap<Integer, LabReport> getLabReportMap() {
        return labReport_map;
    }

    public static void setPatientMap(HashMap<Integer, Patient> map) {
        patient_map = map;
    }

    public static void setDoctorsMap(HashMap<Integer, Doctors> map) {
        doctors_map = map;
    }

    public static void setMedicationMap(HashMap<Integer, Medication> map) {
        medication_map = map;
    }

    public static void setLabTestMap(HashMap<Integer, LabTest> map) {
        labTest_map = map;
    }

    public static void setMedicalDiagnosisMap(HashMap<Integer, MedicalDiagnosis> map) {
        medicalDiagnosis_map = map;
    }

    public static void setChiefComplaintMap(HashMap<Integer, ChiefComplaint> map) {
        chiefComplaint_map = map;
    }

    public static void setConsultationMap(HashMap<Integer, Consultation> map) {
        consultation_map = map;
    }

    public static void setPrescriptionMap(HashMap<Integer, Prescription> map) {
        prescription_map = map;
    }

    public static void setVitalSignsMap(HashMap<Integer, VitalSigns> map) {
        vitalSigns_map = map;
    }

    public static void setLabReportMap(HashMap<Integer, LabReport> map) {
        labReport_map = map;
    }
}