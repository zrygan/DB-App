package com.source.HospitalDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.source.HospitalDB.Classes.Doctors;
import main.java.com.source.HospitalDB.Classes.Medical;
import main.java.com.source.HospitalDB.Classes.Medication;
import main.java.com.source.HospitalDB.Classes.Patient;
import main.java.com.source.HospitalDB.DAO.MedicalDAO;
import main.java.com.source.HospitalDB.DAO.MedicationDAO;

public class Transaction {

    // ******************************    
    // ****** [GANITUEN] TRANSACTIONS
    // ******************************

    // Patient Admission
    public void patientAdmission(Patient patient, Medical medical) throws SQLException{
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

    //Doctor's Prescription
    public boolean createPrescription(String doctorId, String patientId, String medicationDetails) throws SQLException {
    try (Connection conn = DBConnection.getConnection()) {
        
        // Verify patient exists and is assigned to the correct doctor
        String patientDoctorCheckQuery = "SELECT COUNT(*) FROM patients_record WHERE patient_ID = ? AND doctor_ID = ?";
        try (PreparedStatement patientDoctorStmt = conn.prepareStatement(patientDoctorCheckQuery)) {
            patientDoctorStmt.setString(1, patientId);
            patientDoctorStmt.setString(2, doctorId);
            ResultSet patientDoctorRs = patientDoctorStmt.executeQuery();
            if (!patientDoctorRs.next() || patientDoctorRs.getInt(1) == 0) {
                System.out.println("Patient is not assigned to this doctor.");
                return false;
            }
        }

        // Verify doctor exists in doctors_record
        String doctorCheckQuery = "SELECT COUNT(*) FROM doctors_record WHERE doctor_ID = ?";
        try (PreparedStatement doctorCheckStmt = conn.prepareStatement(doctorCheckQuery)) {
            doctorCheckStmt.setString(1, doctorId);
            ResultSet doctorCheckRs = doctorCheckStmt.executeQuery();
            if (!doctorCheckRs.next() || doctorCheckRs.getInt(1) == 0) {
                System.out.println("Doctor does not exist.");
                return false;
            }
        }

        // All checks passed, create new medication record
        String insertMedicationQuery = "INSERT INTO medication_record (doctor_ID, patient_ID, medication_details) VALUES (?, ?, ?)";
        try (PreparedStatement insertStmt = conn.prepareStatement(insertMedicationQuery)) {
            insertStmt.setString(1, doctorId);
            insertStmt.setString(2, patientId);
            insertStmt.setString(3, medicationDetails);
            int rowsInserted = insertStmt.executeUpdate();
            return rowsInserted > 0;
        }
    }
}

    //Creating Doctor's Record
    public boolean createDoctor(Doctors doctor) throws SQLException {
    try (Connection conn = DBConnection.getConnection()) {
        
        // Check for duplicates by doctor ID or email
        String duplicateCheckQuery = "SELECT COUNT(*) FROM doctors_record WHERE doctor_ID = ? OR email = ?";
        try (PreparedStatement duplicateCheckStmt = conn.prepareStatement(duplicateCheckQuery)) {
            duplicateCheckStmt.setString(1, doctor.getDoctorId());
            duplicateCheckStmt.setString(2, doctor.getEmail());
            ResultSet duplicateCheckRs = duplicateCheckStmt.executeQuery();
            if (duplicateCheckRs.next() && duplicateCheckRs.getInt(1) > 0) {
                System.out.println("Doctor already exists with this ID or email.");
                return false;
            }
        }

        // Validate email format
        if (!doctor.getEmail().matches("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,}$")) {
            System.out.println("Invalid email format.");
            return false;
        }

        // Validate phone number format
        if (!doctor.getPhoneNumber().matches("\\(\\d{3}\\) \\d{3}-\\d{4}")) {
            System.out.println("Invalid phone number format.");
            return false;
        }

        // Insert new doctor record
        String insertDoctorQuery = "INSERT INTO doctors_record (name, specialization, doctor_ID, status, phoneNumber, email, medicalHierarchy) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStmt = conn.prepareStatement(insertDoctorQuery)) {
            insertStmt.setString(1, doctor.getName());
            insertStmt.setString(2, doctor.getSpecialization());
            insertStmt.setString(3, doctor.getDoctorId());
            insertStmt.setString(4, doctor.getPhoneNumber());
            insertStmt.setString(5, doctor.getEmail());
            insertStmt.setString(6, doctor.getMedicalHierarchy());

            int rowsInserted = insertStmt.executeUpdate();
            return rowsInserted > 0;
        }
    }
}
    //Deleting Doctor's Record
    public boolean deleteDoctor(String doctorId) throws SQLException {
    try (Connection conn = DBConnection.getConnection()) {

        // Verify doctor exists
        String doctorExistQuery = "SELECT COUNT(*) FROM doctors_record WHERE doctor_ID = ?";
        try (PreparedStatement doctorExistStmt = conn.prepareStatement(doctorExistQuery)) {
            doctorExistStmt.setString(1, doctorId);
            ResultSet doctorExistRs = doctorExistStmt.executeQuery();
            if (!doctorExistRs.next() || doctorExistRs.getInt(1) == 0) {
                System.out.println("Doctor does not exist.");
                return false;
            }
        }

        // Check if doctor has active patients
        String activePatientCheckQuery = "SELECT COUNT(*) FROM patients_record WHERE doctor_ID = ? AND status = 'Admitted'";
        try (PreparedStatement activePatientCheckStmt = conn.prepareStatement(activePatientCheckQuery)) {
            activePatientCheckStmt.setString(1, doctorId);
            ResultSet activePatientCheckRs = activePatientCheckStmt.executeQuery();
            if (activePatientCheckRs.next() && activePatientCheckRs.getInt(1) > 0) {
                System.out.println("Doctor has active patients and cannot be deleted.");
                return false;
            }
        }

        // Delete doctor record if no active patients
        String deleteDoctorQuery = "DELETE FROM doctors_record WHERE doctor_ID = ?";
        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteDoctorQuery)) {
            deleteStmt.setString(1, doctorId);
            int rowsDeleted = deleteStmt.executeUpdate();
            return rowsDeleted > 0;
        }
    }
}


}
