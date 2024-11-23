package com.source.HospitalDB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.source.HospitalDB.DAO.LabTestDAO;
import com.source.HospitalDB.DAO.MedicalDiagnosisDAO;
import com.source.HospitalDB.DAO.PatientDAO;
import com.source.HospitalDB.DAO.PrescriptionDAO;
import com.source.HospitalDB.DAO.VitalSignsDAO;

public class Transaction {

    // Deleting Doctor's Record
    public static boolean deleteDoctor(String firstname, String lastname) throws SQLException {
        int doctorId = -1;
        
        for (Doctors d : App.getDoctorsMap().values()){
            if (d.getFirstname().equals(firstname) && d.getLastname().equals(lastname)){
                doctorId = d.getDoctorId();
                break;
            }
        }
        
        try (Connection conn = DBConnection.getConnection()) {
            // Verify the doctor exists in the database
            if (DoctorsDAO.getFromID(doctorId) == null) {
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
            DoctorsDAO.del(doctorId);
            return true;
        }
    }

    // Creating a Patient record
    public static boolean createPatientRecord(Patient patient) throws SQLException {
        try {
            // Step 1: Verify that a Patient with the same first name and last name already exists
            if (PatientDAO.getByName(patient.getFirstname(), patient.getLastname()) != null) {
                System.out.println("Patient with the same name already exists!");
                return false;
            }

            // Step 2: Record the Patient’s information
            PatientDAO.add(patient);
            return true;
        } catch (SQLException e) {
            System.out.println("Error creating patient record: " + e.getMessage());
            return false;
        }
    }

    
    // Creating a Patient record
    public static boolean createPatientRecord(Patient patient, Doctors doctor, VitalSigns vitalSigns, Consultation consultation, List<Prescription> prescriptions, List<LabReport> labReports, List<ChiefComplaint> chiefComplaints, List<MedicalDiagnosis> medicalDiagnoses) throws SQLException {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // Step 1: Verify that the Patient record does not exist
            if (PatientDAO.getFromID(patient.getPatientId()) != null) {
                System.out.println("Patient record already exists!");
                return false;
            }

            // Step 2: Record the Patient’s information
            PatientDAO.add(patient);

            // Step 3: Ensure the assigned Doctor exists
            if (DoctorsDAO.getFromID(doctor.getDoctorId()) == null){
                System.out.println("Assigned doctor does not exist!");
                return false;
            }

            // Step 4: Record the Patient’s Vital Signs
            VitalSignsDAO.add(vitalSigns);

            // Step 5: Record the Patient’s Prescriptions if any
            if (prescriptions != null) {
                for (Prescription prescription : prescriptions) {
                    PrescriptionDAO.add(prescription);
                }
            }

            // Step 6: Record the Patient’s Lab Reports if any
            if (labReports != null) {
                for (LabReport labReport : labReports) {
                    LabReportDAO.add(labReport);
                }
            }

            // Step 7: Create the first consultation record
            ConsultationDAO.add(consultation);

            // Step 8: Record the Patient’s Chief Complaints if any
            if (chiefComplaints != null) {
                for (ChiefComplaint chiefComplaint : chiefComplaints) {
                    ChiefComplaintDAO.add(chiefComplaint);

                    Consult_ChiefComplaint consultChiefComplaint = new Consult_ChiefComplaint(consultation.getConsultationID(), chiefComplaint.getComplaintID());
                    Consult_ChiefComplaintDAO.add(consultChiefComplaint);
                }
            }

            // Step 9: Record the Patient’s Medical Diagnoses if any
            if (medicalDiagnoses != null) {
                for (MedicalDiagnosis medicalDiagnosis : medicalDiagnoses) {
                    MedicalDiagnosisDAO.add(medicalDiagnosis);

                    Consult_MedDiagnosis consultMedDiagnosis = new Consult_MedDiagnosis(consultation.getConsultationID(), medicalDiagnosis.getDiagnosisID());
                    Consult_MedDiagnosisDAO.add(consultMedDiagnosis);
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

    public static void deletePatientRecord(int patientID) throws SQLException {
        if (PatientDAO.getFromID(patientID) == null) {
            throw new SQLException("Patient record not found for ID: " + patientID);
        }
    
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction
    
            // Step 1: Delete lab report records related to the patient
            String deleteLabReportQuery = 
                "DELETE FROM lab_report_record WHERE consultation_ID IN " +
                "(SELECT consultation_ID FROM consultation_record WHERE patient_ID = ?)";
            try (PreparedStatement stmt = conn.prepareStatement(deleteLabReportQuery)) {
                stmt.setInt(1, patientID);
                stmt.executeUpdate();
            }
    
            // Step 2: Delete prescription records related to the patient
            String deletePrescriptionQuery = 
                "DELETE FROM prescription_record WHERE patient_ID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deletePrescriptionQuery)) {
                stmt.setInt(1, patientID);
                stmt.executeUpdate();
            }
    
            // Step 3: Delete consultation chief complaint records
            String deleteConsultationChiefComplaintQuery = 
                "DELETE FROM consultation_chief_complaint_record WHERE consultation_ID IN " +
                "(SELECT consultation_ID FROM consultation_record WHERE patient_ID = ?)";
            try (PreparedStatement stmt = conn.prepareStatement(deleteConsultationChiefComplaintQuery)) {
                stmt.setInt(1, patientID);
                stmt.executeUpdate();
            }
    
            // Step 4: Delete consultation medical diagnosis records
            String deleteConsultationMedicalDiagnosisQuery = 
                "DELETE FROM consultation_medical_diagnosis_record WHERE consultation_ID IN " +
                "(SELECT consultation_ID FROM consultation_record WHERE patient_ID = ?)";
            try (PreparedStatement stmt = conn.prepareStatement(deleteConsultationMedicalDiagnosisQuery)) {
                stmt.setInt(1, patientID);
                stmt.executeUpdate();
            }
    
            // Step 5: Delete consultation records
            String deleteConsultationsQuery = 
                "DELETE FROM consultation_record WHERE patient_ID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteConsultationsQuery)) {
                stmt.setInt(1, patientID);
                stmt.executeUpdate();
            }
    
            // Step 6: Delete vital signs records
            String deleteVitalSignsQuery = 
                "DELETE FROM vital_signs_record WHERE vital_signs_ID IN " +
                "(SELECT vital_signs_ID FROM consultation_record WHERE patient_ID = ?)";
            try (PreparedStatement stmt = conn.prepareStatement(deleteVitalSignsQuery)) {
                stmt.setInt(1, patientID);
                stmt.executeUpdate();
            }
    
            // Step 7: Delete patient record
            String deletePatientQuery = 
                "DELETE FROM patients_record WHERE patient_ID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deletePatientQuery)) {
                stmt.setInt(1, patientID);
                stmt.executeUpdate();
            }
    
            conn.commit(); // Commit transaction
            System.out.println("Successfully deleted patient with ID: " + patientID);
    
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction in case of error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            throw e; // Re-throw exception after rollback
    
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Restore default auto-commit behavior
                    conn.close(); // Close connection
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }
    
    
    

    // creating a prescription record
    public static void createPrescriptionRecord(int patientId, int doctorId, int medicationID, int frequency, BigDecimal dosage, int consultationID) throws SQLException {
        // Step 1: Verify the Patient exists
        if (PatientDAO.getFromID(patientId) == null) {
            System.out.println("Patient does not exist!");
            return;
        }

        // Step 2: Verify the Doctor exists
        if (DoctorsDAO.getFromID(doctorId) == null) {
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
        Prescription prescription = new Prescription(medicationID, frequency, dosage, doctorId, patientId, consultationID);
        PrescriptionDAO.add(prescription);
    }

    // creating a consultation record
    public static void createConsultationRecord(int prescriptionID, int patientId, int doctorId, int vitalSignsID, int labReportID) throws SQLException {
        // Step 1: Verify the Patient exists
        if (PatientDAO.getFromID(patientId) == null) {
            System.out.println("Patient does not exist!");
            return;
        }

        // Step 2: Verify the Doctor exists
        if (DoctorsDAO.getFromID(doctorId) == null) {
            System.out.println("Doctor does not exist!");
            return;
        }

        // Step 3: Create the Consultation record
        Consultation consultation = new Consultation(doctorId, patientId, vitalSignsID);
        ConsultationDAO.add(consultation);
    }

    // get all the lab test descriptions from a lab report ID
    public static List<String> getLabTestDescriptions(int labReportID) throws SQLException {
        List<Integer> labTestIDs = LabReportDAO.getLabTestID(labReportID);
        List<String> labTestDescriptions = new ArrayList<>();

        for (int labTestID : labTestIDs) {
            labTestDescriptions.add(LabTestDAO.get(labTestID).getTestDescription());
        }
        return labTestDescriptions;
    }

    // return all the prescriptions with the same prescription id
    public static List<Prescription> getPrescriptionsByPrescriptionID(int prescriptionID) throws SQLException {
        List<Prescription> prescriptions = new ArrayList<>();
        
        String query = "SELECT * FROM prescription_record WHERE prescription_ID = ?";
        
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, prescriptionID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Prescription prescription = new Prescription(
                        rs.getInt("medication_ID"),
                        rs.getInt("frequency"),
                        rs.getBigDecimal("dosage"),
                        rs.getInt("doctor_ID"),
                        rs.getInt("patient_ID"),
                        rs.getInt("consultation_ID")
                    );
                    prescriptions.add(prescription);
                }
            }
        }
        
        return prescriptions;
    }

    // return list of lab reports with the same lab report id
    public static List<LabReport> getLabReportsByLabReportID(int labReportID) throws SQLException {
        List<LabReport> labReports = new ArrayList<>();
        
        String query = "SELECT * FROM lab_report_record WHERE lab_report_ID = ?";
        
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, labReportID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    LabReport labReport = new LabReport(
                        rs.getInt("lab_report_ID"),
                        rs.getInt("lab_report_ID"),
                        rs.getInt("test_ID"));
                    labReports.add(labReport);
                }
            }
        }
        
        return labReports;
    }

    // return list of prescriptions with the same consultation id
    public static List<Prescription> getPrescriptionsByConsultationID(int consultationID) throws SQLException {
        List<Prescription> prescriptions = new ArrayList<>();
        
        String query = "SELECT * FROM prescription_record WHERE consultation_ID = ?";
        
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, consultationID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Prescription prescription = new Prescription(
                        rs.getInt("prescription_ID"),
                        rs.getInt("medication_ID"),
                        rs.getInt("frequency"),
                        rs.getBigDecimal("dosage"),
                        rs.getInt("doctor_ID"),
                        rs.getInt("patient_ID"),
                        rs.getInt("consultation_ID")
                    );
                    prescriptions.add(prescription);
                }
            }
        }
        
        return prescriptions;
    }

    // get list of labreports by consultation id
    public static List<LabReport> getLabReportsByConsultationID(int consultationID) throws SQLException {
        List<LabReport> labReports = new ArrayList<>();
        
        String query = "SELECT * FROM lab_report_record WHERE consultation_ID = ?";
        
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, consultationID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    LabReport labReport = new LabReport(
                        rs.getInt("lab_report_ID"),
                        rs.getInt("test_ID"),
                        rs.getInt("consultation_ID"));
                    labReports.add(labReport);
                }
            }
        }
        
        return labReports;
    }
}