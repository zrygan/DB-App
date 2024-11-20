package com.source.HospitalDB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.source.HospitalDB.Classes.ChiefComplaint;
import com.source.HospitalDB.Classes.Consult_ChiefComplaint;
import com.source.HospitalDB.Classes.Consult_MedDiagnosis;
import com.source.HospitalDB.Classes.Consultation;
import com.source.HospitalDB.Classes.Doctors;
import com.source.HospitalDB.Classes.LabReport;
import com.source.HospitalDB.Classes.MedicalDiagnosis;
import com.source.HospitalDB.Classes.Patient;
import com.source.HospitalDB.Classes.Prescription;
import com.source.HospitalDB.Classes.VitalSigns;
import com.source.HospitalDB.DAO.ChiefComplaintDAO;
import com.source.HospitalDB.DAO.Consult_ChiefComplaintDAO;
import com.source.HospitalDB.DAO.Consult_MedDiagnosisDAO;
import com.source.HospitalDB.DAO.ConsultationDAO;
import com.source.HospitalDB.DAO.DoctorsDAO;
import com.source.HospitalDB.DAO.LabReportDAO;
import com.source.HospitalDB.DAO.MedicalDiagnosisDAO;
import com.source.HospitalDB.DAO.PatientDAO;
import com.source.HospitalDB.DAO.PrescriptionDAO;
import com.source.HospitalDB.DAO.VitalSignsDAO;

public class Transaction {

    // Deleting Doctor's Record
    public static boolean deleteDoctor(int doctorId) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            // Verify the doctor exists in the database
            if (DoctorsDAO.get(doctorId) == null) {
                System.out.println("Doctor does not exist.");
                return false;
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
    public static boolean createPatientRecord(Patient patient, Doctors doctor, VitalSigns vitalSigns, Consultation consultation, List<Prescription> prescriptions, List<LabReport> labReports, List<ChiefComplaint> chiefComplaints, List<MedicalDiagnosis> medicalDiagnoses) throws SQLException {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // Step 1: Verify that the Patient record does not exist
            if (PatientDAO.getPatient(patient.getPatientId()) != null) {
                System.out.println("Patient record already exists!");
                return false;
            }

            // Step 2: Record the Patient’s information
            PatientDAO.create(patient);

            // Step 3: Ensure the assigned Doctor exists
            if (DoctorsDAO.get(doctor.getDoctorId()) == null){
                System.out.println("Assigned doctor does not exist!");
                return false;
            }

            // Step 4: Record the Patient’s Vital Signs
            VitalSignsDAO.addVitalSigns(vitalSigns);

            // Step 5: Record the Patient’s Prescriptions if any
            if (prescriptions != null) {
                for (Prescription prescription : prescriptions) {
                    PrescriptionDAO.addPrescription(prescription);
                }
            }

            // Step 6: Record the Patient’s Lab Reports if any
            if (labReports != null) {
                for (LabReport labReport : labReports) {
                    LabReportDAO.addLabReport(labReport);
                }
            }

            // Step 7: Create the first consultation record
            ConsultationDAO.create(consultation);

            // Step 8: Record the Patient’s Chief Complaints if any
            if (chiefComplaints != null) {
                for (ChiefComplaint chiefComplaint : chiefComplaints) {
                    ChiefComplaintDAO.addChiefComplaint(chiefComplaint);

                    Consult_ChiefComplaint consultChiefComplaint = new Consult_ChiefComplaint(consultation.getConsultationID(), chiefComplaint.getComplaintID());
                    Consult_ChiefComplaintDAO.addConsultChiefComplaint(consultChiefComplaint);
                }
            }

            // Step 9: Record the Patient’s Medical Diagnoses if any
            if (medicalDiagnoses != null) {
                for (MedicalDiagnosis medicalDiagnosis : medicalDiagnoses) {
                    MedicalDiagnosisDAO.addMedicalDiagnosis(medicalDiagnosis);

                    Consult_MedDiagnosis consultMedDiagnosis = new Consult_MedDiagnosis(consultation.getConsultationID(), medicalDiagnosis.getDiagnosisID());
                    Consult_MedDiagnosisDAO.addConsultMedDiagnosis(consultMedDiagnosis);
                }
            }

            

            conn.commit(); // Commit transaction if all operations succeed
            
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Rollback transaction if any operation fails
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true); // Restore default commit behavior
                conn.close();
            }
        }
    }

    // Deleting a patient record
    public static void deletePatientRecord(int patientID) throws SQLException {
        // Step 1: Verify that the patient record to delete exists
        if (PatientDAO.getPatient(patientID) == null) {
            throw new SQLException("Patient record not found for ID: " + patientID);
        }

        // Step 2-4: Use a transaction to ensure all deletes are executed atomically
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            // Step 2: Delete all Medication Records of the patient
            String deleteMedicationRecordsQuery = "DELETE FROM medication_record WHERE patient_ID = ?";
            try (PreparedStatement deleteMedStmt = conn.prepareStatement(deleteMedicationRecordsQuery)) {
                deleteMedStmt.setInt(1, patientID);
                deleteMedStmt.executeUpdate();
            }

            // Step 3: Delete all Medical Records of the patient
            String deleteMedicalRecordsQuery = "DELETE FROM medical_records WHERE patient_ID = ?";
            try (PreparedStatement deleteMedicalStmt = conn.prepareStatement(deleteMedicalRecordsQuery)) {
                deleteMedicalStmt.setInt(1, patientID);
                deleteMedicalStmt.executeUpdate();
            }

            // Step 4: Delete the Patient Record of the patient
            PatientDAO.delete(patientID);

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
    public static void createPrescriptionRecord(int patientId, int doctorId, int medicationID, int frequency, BigDecimal dosage) throws SQLException {
        // Step 1: Verify the Patient exists
        if (PatientDAO.getPatient(patientId) == null) {
            System.out.println("Patient does not exist!");
            return;
        }

        // Step 2: Verify the Doctor exists
        if (DoctorsDAO.get(doctorId) == null) {
            System.out.println("Doctor does not exist!");
            return;
        }

        // Step 3: Verify Medication exists
        String checkMedicationQuery = "SELECT COUNT(*) FROM medication_record WHERE medication_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkMedicationStmt = conn.prepareStatement(checkMedicationQuery)) {
            checkMedicationStmt.setInt(1, medicationID);
            ResultSet rs = checkMedicationStmt.executeQuery();
            if (!rs.next() || rs.getInt(1) == 0) {
                System.out.println("Medication does not exist!");
                return;
            }
        }

        // Step 4: Create the Prescription record
        Prescription prescription = new Prescription(medicationID, frequency, dosage, doctorId, patientId);
        PrescriptionDAO.addPrescription(prescription);
    }

    // creating a consultation record
    public static void createConsultationRecord(int prescriptionID, int patientId, int doctorId, int vitalSignsID, int labReportID) throws SQLException {
        // Step 1: Verify the Patient exists
        if (PatientDAO.getPatient(patientId) == null) {
            System.out.println("Patient does not exist!");
            return;
        }

        // Step 2: Verify the Doctor exists
        if (DoctorsDAO.get(doctorId) == null) {
            System.out.println("Doctor does not exist!");
            return;
        }

        // Step 3: Create the Consultation record
        Consultation consultation = new Consultation(prescriptionID, doctorId, patientId, vitalSignsID, labReportID);
        ConsultationDAO.create(consultation);
    }
}