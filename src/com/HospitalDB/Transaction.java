package com.HospitalDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction {

    // ******************************    
    // ****** [GANITUEN] TRANSACTIONS
    // ******************************

    // Patient Admission
    public void patientAdmission(Patient patient, Medicl medical) throws SQLException{
        Connection conn = null;
        try{
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // Step 1: Verify if the patient is an existing patient

            // Step 2: Update status to Admitted

            // Step 3: Verify if the assigned doctor exists
        } catch (SQLException e) { // copied from jaztins mwehehe (change if any changes occer there)
            if (conn != null) {
                try {
                    conn.close();  
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();  
                }
            }
         }
    }

    // Create a medical record
    // Create a new medical record for a patient
    public void createMedicalRecord(Medical medical, Connection conn) throws SQLException {
        // USES quite ALL the helpy functions in MedicalDAO

        MedicalDAO medicalDAO = new MedicalDAO();


        // VERIFICATION STEPS
        // verify the patient exists
        if (!medicalDAO.patientExists(medical.getPatient_id(), conn)) {
            System.out.println("Patient does not exist!");
            return;
        }

        // verify the patient is currently admitted
        if (!medicalDAO.isPatientAdmitted(medical.getPatient_id(), conn)) {
            System.out.println("Patient is not admitted to the hospital!");
            return;
        }
        

        // UPDATING DIAGNISES AND REASON FOR ADMISSION
        String existingDiagnosis = medicalDAO.getExistingDiagnosis(medical.getPatient_id(), conn);
        String existingReason = medicalDAO.getExistingReason(medical.getPatient_id(), conn);

        // update the diagnosis and reason, if needed only
        if (existingDiagnosis == null || !existingDiagnosis.equals(medical.getMedicalDiagnosis())) {
            medicalDAO.updateDiagnosis(medical.getPatient_id(), medical.getMedicalDiagnosis(), conn);
        }
        if (existingReason == null || !existingReason.equals(medical.getReasonForAdmission())) {
            medicalDAO.updateReasonForAdmission(medical.getPatient_id(), medical.getReasonForAdmission(), conn);
        }

        medicalDAO.create(medical);  
        System.out.println("New medical record created successfully for Patient ID: " + medical.getPatient_id());
    }

    // Deleting medical record
    public void deleteMedicalRecord(int recordId, int patientId, Connection conn) throws SQLException {
        MedicalDAO medicalDAO = new MedicalDAO();

        // verify if the patient exists
        if (!medicalDAO.patientExists(patientId, conn)) {
            System.out.println("Patient does not exist!");
            return;
        }

        // verify that the patient is not admitted
        // (status = 'discharged')
        if (medicalDAO.isPatientAdmitted(patientId, conn)) {
            System.out.println("Patient is still admitted! Cannot delete medical record.");
            return;
        }

        // deleting the medical record
        if (medicalDAO.deleteMedicalRecord(recordId, patientId, conn)) {
            System.out.println("Medical record deleted successfully for Patient ID: " + patientId);
        } else {
            System.out.println("Failed to delete medical record.");
        }
    }

    // ******************************
    // ******* [JIMENEZ] TRANSACTIONS
    // ******************************
    // Patient Routine Check-up
    public void patientRoutineCheckup(Patient patient, Medical medical, Medication newMed, String newDiagnosis) throws SQLException {
        Connection conn = null;  
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);  // Start a transaction

            // Step 1: Update patient info if needed
            // PatientDAO patientDAO = new PatientDAO();
            // patientDAO.updatePatient(patient); // Assuming updated patient info is passed in the parameter

            // Step 2: Add the new medical record
            MedicationDAO medicalRecordDAO = new MedicationDAO();
            medicalRecordDAO.addMedication(newMed);

            // Step 3 & 4: Update the doctor and medication if the diagnosis has changed
            if (!newDiagnosis.equals(medical.getMedicalDiagnosis())) {
            //    patientDAO.updateDiagnosisAndDoctor(patient.getId(), newDiagnosis);
                MedicationDAO medicationDAO = new MedicationDAO();
            //    medicationDAO.updateMedicationByDiagnosis(patient.getId(), newDiagnosis); // Assuming this updates based on diagnosis
            }

            conn.commit(); // Commit the transaction if all operations succeed
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.close();  // Close connection in finally block
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();  // Log close errors
                }
            }
         }
    }

    // Creating a Medication Record
    




    // ******************************    
    // ****** [BARLAAN] TRANSACTIONS
    // ******************************

    
}
