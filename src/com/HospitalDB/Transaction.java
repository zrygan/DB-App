package com.HospitalDB;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {
    












    // [JIMENEZ] TRANSACTIONS

    // Patient Routine Check-up
    public void patientRoutineCheckup(Patient patient, Medical medical,Medication newMed, String newDiagnosis) throws SQLException {
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
    

}
