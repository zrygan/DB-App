package com.source.HospitalDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.source.HospitalDB.Classes.Doctors;
import com.source.HospitalDB.Classes.Medication;
import com.source.HospitalDB.Classes.Patient;
import com.source.HospitalDB.DAO.MedicationDAO;

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

    // Deleting Doctor's Record
public boolean deleteDoctor(String doctorId) throws SQLException {
    try (Connection conn = DBConnection.getConnection()) {

        // Verify the doctor exists in the database
        String doctorExistQuery = "SELECT COUNT(*) FROM doctors_record WHERE doctor_ID = ?";
        try (PreparedStatement doctorExistStmt = conn.prepareStatement(doctorExistQuery)) {
            doctorExistStmt.setString(1, doctorId);
            ResultSet doctorExistRs = doctorExistStmt.executeQuery();
            if (!doctorExistRs.next() || doctorExistRs.getInt(1) == 0) {
                System.out.println("Doctor does not exist.");
                return false;
            }
        }

        // Check if the doctor has at least one consultation
        String consultationCheckQuery = "SELECT COUNT(*) FROM consultations_record WHERE doctor_ID = ?";
        try (PreparedStatement consultationCheckStmt = conn.prepareStatement(consultationCheckQuery)) {
            consultationCheckStmt.setString(1, doctorId);
            ResultSet consultationCheckRs = consultationCheckStmt.executeQuery();
            if (consultationCheckRs.next() && consultationCheckRs.getInt(1) > 0) {
                System.out.println("Doctor has consultations and cannot be deleted.");
                return false;
            }
        }

        // Delete the doctor if no consultations
        String deleteDoctorQuery = "DELETE FROM doctors_record WHERE doctor_ID = ?";
        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteDoctorQuery)) {
            deleteStmt.setString(1, doctorId);
            int rowsDeleted = deleteStmt.executeUpdate();
            return rowsDeleted > 0;
        }
    }
}
// Deleting Doctor's Record
public boolean deleteDoctor(String doctorId) throws SQLException {
    try (Connection conn = DBConnection.getConnection()) {

        // Verify the doctor exists in the database
        String doctorExistQuery = "SELECT COUNT(*) FROM doctors_record WHERE doctor_ID = ?";
        try (PreparedStatement doctorExistStmt = conn.prepareStatement(doctorExistQuery)) {
            doctorExistStmt.setString(1, doctorId);
            ResultSet doctorExistRs = doctorExistStmt.executeQuery();
            if (!doctorExistRs.next() || doctorExistRs.getInt(1) == 0) {
                System.out.println("Doctor does not exist.");
                return false;
            }
        }

        // Check if the doctor has at least one consultation
        String consultationCheckQuery = "SELECT COUNT(*) FROM consultations_record WHERE doctor_ID = ?";
        try (PreparedStatement consultationCheckStmt = conn.prepareStatement(consultationCheckQuery)) {
            consultationCheckStmt.setString(1, doctorId);
            ResultSet consultationCheckRs = consultationCheckStmt.executeQuery();
            if (consultationCheckRs.next() && consultationCheckRs.getInt(1) > 0) {
                System.out.println("Doctor has consultations and cannot be deleted.");
                return false;
            }
        }

        // Delete the doctor if no consultations
        String deleteDoctorQuery = "DELETE FROM doctors_record WHERE doctor_ID = ?";
        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteDoctorQuery)) {
            deleteStmt.setString(1, doctorId);
            int rowsDeleted = deleteStmt.executeUpdate();
            return rowsDeleted > 0;
        }
    }
}


// Creating a Patient record
public void createPatientRecord(Patient patient, Connection conn) throws SQLException {
    try {
        // Step 1: Verify that the Patient record does not exist
        String checkPatientQuery = "SELECT COUNT(*) FROM patients_record WHERE patient_ID = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkPatientQuery)) {
            checkStmt.setInt(1, patient.getPatientId());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Patient record already exists!");
                return;
            }
        }

        // Step 2: Record the Patient’s information
        String insertPatientQuery = "INSERT INTO patients_record (patient_ID, name, age, birth_date, sex, height, weight, religion, doctor_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStmt = conn.prepareStatement(insertPatientQuery)) {
            insertStmt.setInt(1, patient.getPatientId());
            insertStmt.setString(2, patient.getName());
            insertStmt.setInt(3, patient.getAge());
            insertStmt.setDate(4, java.sql.Date.valueOf(patient.getBirthDate()));
            insertStmt.setString(5, patient.getSex());
            insertStmt.setDouble(6, patient.getHeight());
            insertStmt.setDouble(7, patient.getWeight());
            insertStmt.setString(8, patient.getReligion());
            insertStmt.setString(9, patient.getAssignedDoctorId());
            insertStmt.executeUpdate();
        }

        // Step 3: Ensure the assigned Doctor exists
        String checkDoctorQuery = "SELECT COUNT(*) FROM doctors_record WHERE doctor_ID = ?";
        try (PreparedStatement checkDoctorStmt = conn.prepareStatement(checkDoctorQuery)) {
            checkDoctorStmt.setString(1, patient.getAssignedDoctorId());
            ResultSet rs = checkDoctorStmt.executeQuery();
            if (!rs.next() || rs.getInt(1) == 0) {
                System.out.println("Assigned doctor does not exist!");
                return;
            }
        }

        // Step 4: Create the first consultation record
        String createConsultationQuery = "INSERT INTO consultations_record (patient_ID, doctor_ID, consultation_date, chief_complaint) VALUES (?, ?, NOW(), ?)";
        try (PreparedStatement consultStmt = conn.prepareStatement(createConsultationQuery)) {
            consultStmt.setInt(1, patient.getPatientId());
            consultStmt.setString(2, patient.getAssignedDoctorId());
            consultStmt.setString(3, "First Consultation");
            consultStmt.executeUpdate();
        }

        System.out.println("Patient record created successfully!");

    } catch (SQLException e) {
        throw new SQLException("Error creating patient record: " + e.getMessage(), e);
    }
}


// Creating an Advanced Patient record
public void createAdvancePatientRecord(Patient patient, Connection conn) throws SQLException {
    createPatientRecord(patient, conn); // Reuse the basic patient creation logic

    // Step 5: Create and Assign a Prescription and Lab Record
    String createPrescriptionQuery = "INSERT INTO prescriptions_record (patient_ID, doctor_ID, medication, frequency, dosage) VALUES (?, ?, ?, ?, ?)";
    String createLabRecordQuery = "INSERT INTO lab_records (patient_ID, doctor_ID, lab_test, result) VALUES (?, ?, ?, ?)";

    try (PreparedStatement prescriptionStmt = conn.prepareStatement(createPrescriptionQuery);
         PreparedStatement labStmt = conn.prepareStatement(createLabRecordQuery)) {
        
        // Create Prescription
        prescriptionStmt.setInt(1, patient.getPatientId());
        prescriptionStmt.setString(2, patient.getAssignedDoctorId());
        prescriptionStmt.setString(3, "Paracetamol");
        prescriptionStmt.setString(4, "Twice a day");
        prescriptionStmt.setString(5, "500mg");
        prescriptionStmt.executeUpdate();

        // Create Lab Record
        labStmt.setInt(1, patient.getPatientId());
        labStmt.setString(2, patient.getAssignedDoctorId());
        labStmt.setString(3, "Blood Test");
        labStmt.setString(4, "Pending");
        labStmt.executeUpdate();

        System.out.println("Advanced Patient record created successfully!");
    }
}


    // Deleting a patient record
    public void deletePatientRecord(Connection conn, int patientID) throws SQLException {

        String checkPatientExistsQuery = "SELECT COUNT(*) FROM patients_record WHERE patient_ID = ?";
        String deleteMedicationRecordsQuery = "DELETE FROM medication_record WHERE patient_ID = ?";
        //String deleteMedicalRecordsQuery = "DELETE FROM medical_records WHERE patient_ID = ?"; 
        String deletePatientRecordQuery = "DELETE FROM patients_record WHERE patient_ID = ?";
    
        // Step 1: Verify that the patient record to delete exists
        try (PreparedStatement checkStmt = conn.prepareStatement(checkPatientExistsQuery)) {
            checkStmt.setInt(1, patientID);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    throw new SQLException("Patient record not found for ID: " + patientID);
                }
            }
        }
    
        // Step 2-4: Use a transaction to ensure all deletes are executed atomically
        try {
            conn.setAutoCommit(false); // Start transaction
    
            // Step 2: Delete all Medication Records of the patient
            try (PreparedStatement deleteMedStmt = conn.prepareStatement(deleteMedicationRecordsQuery)) {
                deleteMedStmt.setInt(1, patientID);
                deleteMedStmt.executeUpdate();
            }
    
            // Step 3: Delete all Medical Records of the patient
            try (PreparedStatement deleteMedicalStmt = conn.prepareStatement(deleteMedicalRecordsQuery)) {
                deleteMedicalStmt.setInt(1, patientID);
                deleteMedicalStmt.executeUpdate();
            }
    
            // Step 4: Delete the Patient Record of the patient
            try (PreparedStatement deletePatientStmt = conn.prepareStatement(deletePatientRecordQuery)) {
                deletePatientStmt.setInt(1, patientID);
                deletePatientStmt.executeUpdate();
            }
    
            conn.commit(); // Commit transaction if all operations succeed
        } catch (SQLException e) {
            conn.rollback(); // Rollback transaction if any operation fails
            throw e;
        } finally {
            conn.setAutoCommit(true); // Restore default commit behavior
        }
    }
    

    //creating a prescription record
    public void createPrescriptionRecord(int patientId, String doctorId, String medication, String frequency, String dosage, Connection conn) throws SQLException {
    // Step 1: Verify the Patient exists
    String checkPatientQuery = "SELECT COUNT(*) FROM patients_record WHERE patient_ID = ?";
    try (PreparedStatement checkPatientStmt = conn.prepareStatement(checkPatientQuery)) {
        checkPatientStmt.setInt(1, patientId);
        ResultSet rs = checkPatientStmt.executeQuery();
        if (!rs.next() || rs.getInt(1) == 0) {
            System.out.println("Patient does not exist!");
            return;
        }
    }

    // Step 2: Verify the Doctor exists
    String checkDoctorQuery = "SELECT COUNT(*) FROM doctors_record WHERE doctor_ID = ?";
    try (PreparedStatement checkDoctorStmt = conn.prepareStatement(checkDoctorQuery)) {
        checkDoctorStmt.setString(1, doctorId);
        ResultSet rs = checkDoctorStmt.executeQuery();
        if (!rs.next() || rs.getInt(1) == 0) {
            System.out.println("Doctor does not exist!");
            return;
        }
    }

    // Step 3: Verify Medication exists
    String checkMedicationQuery = "SELECT COUNT(*) FROM medication_record WHERE medication_name = ?";
    try (PreparedStatement checkMedicationStmt = conn.prepareStatement(checkMedicationQuery)) {
        checkMedicationStmt.setString(1, medication);
        ResultSet rs = checkMedicationStmt.executeQuery();
        if (!rs.next() || rs.getInt(1) == 0) {
            System.out.println("Medication does not exist!");
            return;
        }
    }

    // Step 4: Create Prescription Record
    String createPrescriptionQuery = "INSERT INTO prescriptions_record (patient_ID, doctor_ID, medication, frequency, dosage) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement prescriptionStmt = conn.prepareStatement(createPrescriptionQuery)) {
        prescriptionStmt.setInt(1, patientId);
        prescriptionStmt.setString(2, doctorId);
        prescriptionStmt.setString(3, medication);
        prescriptionStmt.setString(4, frequency);
        prescriptionStmt.setString(5, dosage);
        prescriptionStmt.executeUpdate();
        System.out.println("Prescription record created successfully!");
    }
}

    //creating a consultation record
    public void createConsultationRecord(int patientId, String doctorId, String chiefComplaint, String diagnosis, Connection conn) throws SQLException {
    // Step 1: Verify the Patient exists
    String checkPatientQuery = "SELECT COUNT(*) FROM patients_record WHERE patient_ID = ?";
    try (PreparedStatement checkPatientStmt = conn.prepareStatement(checkPatientQuery)) {
        checkPatientStmt.setInt(1, patientId);
        ResultSet rs = checkPatientStmt.executeQuery();
        if (!rs.next() || rs.getInt(1) == 0) {
            System.out.println("Patient does not exist!");
            return;
        }
    }

    // Step 2: Verify the Doctor exists
    String checkDoctorQuery = "SELECT COUNT(*) FROM doctors_record WHERE doctor_ID = ?";
    try (PreparedStatement checkDoctorStmt = conn.prepareStatement(checkDoctorQuery)) {
        checkDoctorStmt.setString(1, doctorId);
        ResultSet rs = checkDoctorStmt.executeQuery();
        if (!rs.next() || rs.getInt(1) == 0) {
            System.out.println("Doctor does not exist!");
            return;
        }
    }

    // Step 3: Record Consultation Details
    String insertConsultationQuery = "INSERT INTO consultations_record (patient_ID, doctor_ID, consultation_date, chief_complaint, diagnosis) VALUES (?, ?, NOW(), ?, ?)";
    try (PreparedStatement consultStmt = conn.prepareStatement(insertConsultationQuery)) {
        consultStmt.setInt(1, patientId);
        consultStmt.setString(2, doctorId);
        consultStmt.setString(3, chiefComplaint);
        consultStmt.setString(4, diagnosis);
        consultStmt.executeUpdate();
        System.out.println("Consultation record created successfully!");
    }
}

}
