package com.source.HospitalDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.source.HospitalDB.Classes.ChiefComplaint;
import com.source.HospitalDB.Classes.Consultation;
import com.source.HospitalDB.Classes.MedicalDiagnosis;
import com.source.HospitalDB.Classes.Patient;
import com.source.HospitalDB.Classes.Prescription;
import com.source.HospitalDB.Classes.VitalSigns;
import com.source.HospitalDB.DAO.ChiefComplaintDAO;
import com.source.HospitalDB.DAO.Consult_ChiefComplaintDAO;
import com.source.HospitalDB.DAO.Consult_MedDiagnosisDAO;
import com.source.HospitalDB.DAO.ConsultationDAO;
import com.source.HospitalDB.DAO.DoctorsDAO;
import com.source.HospitalDB.DAO.MedicalDiagnosisDAO;
import com.source.HospitalDB.DAO.PatientDAO;
import com.source.HospitalDB.DAO.VitalSignsDAO;

public class Transaction {
    // Deleting Doctor's Record
    // FIXME: doctor_ID should be of type int not of type String
    public boolean deleteDoctor(int doctorId) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {

            // Verify the doctor exists in the database
            String doctorExistQuery = "SELECT COUNT(*) FROM doctors_record WHERE doctor_ID = ?";
            try (PreparedStatement doctorExistStmt = conn.prepareStatement(doctorExistQuery)) {
                doctorExistStmt.setInt(1, doctorId);
                ResultSet doctorExistRs = doctorExistStmt.executeQuery();
                if (!doctorExistRs.next() || doctorExistRs.getInt(1) == 0) {
                    System.out.println("Doctor does not exist.");
                    return false;
                }
            }

            // Check if the doctor has at least one consultation
            String consultationCheckQuery = "SELECT COUNT(*) FROM consultations_record WHERE doctor_ID = ?";
            try (PreparedStatement consultationCheckStmt = conn.prepareStatement(consultationCheckQuery)) {
                consultationCheckStmt.setInt(1, doctorId);
                ResultSet consultationCheckRs = consultationCheckStmt.executeQuery();
                if (consultationCheckRs.next() && consultationCheckRs.getInt(1) > 0) {
                    System.out.println("Doctor has consultations and cannot be deleted.");
                    return false;
                }
            }

            // Delete the doctor if no consultations
            DoctorsDAO.delete(doctorId);
            return true;
        }
    }

    // Creating a Patient record
    // NO prescription
    // NO lab
    public void createPatientRecord(Patient patient, VitalSigns vitalSigns, Consultation consultation, ChiefComplaint[] chiefComplaints, MedicalDiagnosis[] medicalDiagnoses) throws SQLException {
        try (Connection conn = DBConnection.getConnection();){
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
            PatientDAO.create(patient);

            // Step 3: Ensure the assigned Doctor exists
            String checkDoctorQuery = "SELECT COUNT(*) FROM doctors_record WHERE doctor_ID = ?";
            try (PreparedStatement checkDoctorStmt = conn.prepareStatement(checkDoctorQuery)) {
                checkDoctorStmt.setInt(1, patient.getDoctor());
                ResultSet rs = checkDoctorStmt.executeQuery();
                if (!rs.next() || rs.getInt(1) == 0) {
                    System.out.println("Assigned doctor does not exist!");
                    return;
                }
            }

            // Step 4: Record the Patient’s Vital Signs
            VitalSignsDAO.addVitalSigns(vitalSigns);

            // Step 5: Create the first consultation record
            // assume for now that prescription and lab_report is null
            ConsultationDAO.create(consultation);

            // Adding chief complaints
            for (ChiefComplaint chiefComplaint : chiefComplaints) {
                ChiefComplaintDAO.addChiefComplaint(chiefComplaint);
                Consult_ChiefComplaintDAO.addConsultChiefComplaint(consultation.getConsultationID(), chiefComplaint.getComplaintID());
            }

            // Adding medical diagnosis
            for (MedicalDiagnosis medicalDiagnosis : medicalDiagnoses) {
                MedicalDiagnosisDAO.addMedicalDiagnosis(medicalDiagnosis);
                Consult_MedDiagnosisDAO.addConsultMedDiagnosis(consultation.getConsultationID(), medicalDiagnosis.getDiagnosisID());
            }
        }
    }

    // Creating a Patient record
    // YES prescription
    // NO lab
    public void createPatientRecord(Patient patient, VitalSigns vitalSigns, Prescription prescription){
        
    }

    // Deleting a patient record
    public void deletePatientRecord(int patientID) throws SQLException {

        String checkPatientExistsQuery = "SELECT COUNT(*) FROM patients_record WHERE patient_ID = ?";
        String deleteMedicationRecordsQuery = "DELETE FROM medication_record WHERE patient_ID = ?";
        String deleteMedicalRecordsQuery = "DELETE FROM medical_records WHERE patient_ID = ?";
        String deletePatientRecordQuery = "DELETE FROM patients_record WHERE patient_ID = ?";

        // Step 1: Verify that the patient record to delete exists
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement checkStmt = conn.prepareStatement(checkPatientExistsQuery)) {
            checkStmt.setInt(1, patientID);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    throw new SQLException("Patient record not found for ID: " + patientID);
                }
            }
        }

        // Step 2-4: Use a transaction to ensure all deletes are executed atomically
        try {Connection conn = DBConnection.getConnection();
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
            Connection conn = DBConnection.getConnection();
            conn.rollback(); // Rollback transaction if any operation fails
            throw e;
        } finally {
            Connection conn = DBConnection.getConnection();
            conn.setAutoCommit(true); // Restore default commit behavior
        }
    }

    // creating a prescription record
    public void createPrescriptionRecord(int patientId, String doctorId, String medication, String frequency,
            String dosage) throws SQLException {
        // Step 1: Verify the Patient exists
        String checkPatientQuery = "SELECT COUNT(*) FROM patients_record WHERE patient_ID = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement checkPatientStmt = conn.prepareStatement(checkPatientQuery)) {
            checkPatientStmt.setInt(1, patientId);
            ResultSet rs = checkPatientStmt.executeQuery();
            if (!rs.next() || rs.getInt(1) == 0) {
                System.out.println("Patient does not exist!");
                return;
            }
        }

        // Step 2: Verify the Doctor exists
        String checkDoctorQuery = "SELECT COUNT(*) FROM doctors_record WHERE doctor_ID = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement checkDoctorStmt = conn.prepareStatement(checkDoctorQuery)) {
            checkDoctorStmt.setString(1, doctorId);
            ResultSet rs = checkDoctorStmt.executeQuery();
            if (!rs.next() || rs.getInt(1) == 0) {
                System.out.println("Doctor does not exist!");
                return;
            }
        }

        // Step 3: Verify Medication exists
        String checkMedicationQuery = "SELECT COUNT(*) FROM medication_record WHERE medication_name = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement checkMedicationStmt = conn.prepareStatement(checkMedicationQuery)) {
            checkMedicationStmt.setString(1, medication);
            ResultSet rs = checkMedicationStmt.executeQuery();
            if (!rs.next() || rs.getInt(1) == 0) {
                System.out.println("Medication does not exist!");
                return;
            }
        }

        // Step 4: Create Prescription Record
        String createPrescriptionQuery = "INSERT INTO prescriptions_record (patient_ID, doctor_ID, medication, frequency, dosage) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement prescriptionStmt = conn.prepareStatement(createPrescriptionQuery)) {
            prescriptionStmt.setInt(1, patientId);
            prescriptionStmt.setString(2, doctorId);
            prescriptionStmt.setString(3, medication);
            prescriptionStmt.setString(4, frequency);
            prescriptionStmt.setString(5, dosage);
            prescriptionStmt.executeUpdate();
            System.out.println("Prescription record created successfully!");
        }
    }

    // creating a consultation record
    public void createConsultationRecord(int patientId, String doctorId, String chiefComplaint, String diagnosis) throws SQLException {
        // Step 1: Verify the Patient exists
        String checkPatientQuery = "SELECT COUNT(*) FROM patients_record WHERE patient_ID = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement checkPatientStmt = conn.prepareStatement(checkPatientQuery)) {
            checkPatientStmt.setInt(1, patientId);
            ResultSet rs = checkPatientStmt.executeQuery();
            if (!rs.next() || rs.getInt(1) == 0) {
                System.out.println("Patient does not exist!");
                return;
            }
        }

        // Step 2: Verify the Doctor exists
        String checkDoctorQuery = "SELECT COUNT(*) FROM doctors_record WHERE doctor_ID = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement checkDoctorStmt = conn.prepareStatement(checkDoctorQuery)) {
            checkDoctorStmt.setString(1, doctorId);
            ResultSet rs = checkDoctorStmt.executeQuery();
            if (!rs.next() || rs.getInt(1) == 0) {
                System.out.println("Doctor does not exist!");
                return;
            }
        }

        // Step 3: Record Consultation Details
        String insertConsultationQuery = "INSERT INTO consultations_record (patient_ID, doctor_ID, consultation_date, chief_complaint, diagnosis) VALUES (?, ?, NOW(), ?, ?)";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement consultStmt = conn.prepareStatement(insertConsultationQuery)) {
            consultStmt.setInt(1, patientId);
            consultStmt.setString(2, doctorId);
            consultStmt.setString(3, chiefComplaint);
            consultStmt.setString(4, diagnosis);
            consultStmt.executeUpdate();
            System.out.println("Consultation record created successfully!");
        }
    }

}
