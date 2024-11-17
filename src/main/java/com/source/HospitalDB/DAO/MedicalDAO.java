package main.java.com.source.HospitalDB.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import main.java.com.source.HospitalDB.Classes.Medical;

public class MedicalDAO {
    // Create a new medical record
    public void create(Medical medical) throws SQLException {
        
        String query = "INSERT INTO medical_record (patient_ID, chief_complaint, reason_for_admission, medical_diagnosis, "
                     + "temperature, pulse, BP_systolic, BP_diastolic, spo2, date_time) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, medical.getPatient_id());
            pstmt.setString(2, medical.getChiefComplaint());
            pstmt.setString(3, medical.getReasonForAdmission());
            pstmt.setString(4, medical.getMedicalDiagnosis());
            pstmt.setBigDecimal(5, BigDecimal.valueOf(medical.getTemperature()));
            pstmt.setInt(6, medical.getPulse());
            pstmt.setInt(7, medical.getBPSystolic());
            pstmt.setInt(8, medical.getBPDiastolic());
            pstmt.setInt(9, medical.getSpo2());
            pstmt.setObject(10, medical.getDateTime());

            pstmt.executeUpdate();
        }
    }
    
    public void update(Medical medical) throws SQLException {
        String query = "UPDATE medical_record SET patient_ID = ?, chief_complaint = ?, reason_for_admission = ?, medical_diagnosis = ?, temperature = ?, pulse = ?, BP_systolic = ?, BP_diastolic = ?, spo2 = ?, date_time = ? WHERE record_ID = ?"; // Use record_ID to identify the record to update
    
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)){
    
            pstmt.setInt(1, medical.getPatient_id());
            pstmt.setString(2, medical.getChiefComplaint());
            pstmt.setString(3, medical.getReasonForAdmission());
            pstmt.setString(4, medical.getMedicalDiagnosis());
            pstmt.setBigDecimal(5, BigDecimal.valueOf(medical.getTemperature()));
            pstmt.setInt(6, medical.getPulse());
            pstmt.setInt(7, medical.getBPSystolic());
            pstmt.setInt(8, medical.getBPDiastolic());
            pstmt.setInt(9, medical.getSpo2());
            pstmt.setObject(10, medical.getDateTime());
            pstmt.setInt(11, medical.getRecord_id()); 
    
            pstmt.executeUpdate();
        }
    }
    
    // delete medical record
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM medical_record WHERE record_ID = ?";
        
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
        }
    }

    // view medical record from name
    public Medical getMedical(String name) throws SQLException {
        String query = "SELECT m.record_ID, m.patient_ID, m.chief_complaint, m.reason_for_admission, "
                    + "m.medical_diagnosis, m.temperature, m.pulse, m.BP_systolic, m.BP_diastolic, "
                    + "m.spo2, m.date_time "
                    + "FROM medical_record m "
                    + "JOIN patients_record p ON m.patient_ID = p.patient_ID "
                    + "WHERE p.name = ?";  

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int recordID = rs.getInt("record_ID");
                    int patientID = rs.getInt("patient_ID");
                    String chiefComplaint = rs.getString("chief_complaint");
                    String reasonForAdmission = rs.getString("reason_for_admission");
                    String medicalDiagnosis = rs.getString("medical_diagnosis");
                    float temperature = rs.getFloat("temperature");
                    int pulse = rs.getInt("pulse");
                    int bpSystolic = rs.getInt("BP_systolic");
                    int bpDiastolic = rs.getInt("BP_diastolic");
                    int spo2 = rs.getInt("spo2");
                    LocalDateTime dateTime = rs.getObject("date_time", LocalDateTime.class);
    
                    // Create and return the Medical object
                    return new Medical(recordID, patientID, chiefComplaint, reasonForAdmission, medicalDiagnosis, temperature, pulse, bpSystolic, bpDiastolic, spo2, dateTime);
                } 
                return null;
            }
        } 
    }

    // all pateitns for a diagnosis
    public List<String> getPatientsByDiagnosis(String diagnosis) throws SQLException {
        List<String> patientNames = new ArrayList<>();
        
        String query = "SELECT p.name "
                    + "FROM medical_record m "
                    + "JOIN patients_record p ON m.patient_ID = p.patient_ID "
                    + "WHERE m.medical_diagnosis = ?";  // Filter by the diagnosis

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, diagnosis);  
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    patientNames.add(rs.getString("name"));
                }
            }
        } 
        return patientNames;
    }

    // Method to get a comprehensive summary of all medical records and return it as a string
    public String getMedicalRecordSummary() throws SQLException {
        StringBuilder summary = new StringBuilder();
        
        // query for each summary
        // some java string formatting to make it easier to see
        String chiefComplaintQuery = "SELECT chief_complaint, COUNT(*) AS patient_count "
                                   + "FROM medical_record "
                                   + "GROUP BY chief_complaint "
                                   + "ORDER BY patient_count DESC";  

        String diagnosisQuery = "SELECT medical_diagnosis, COUNT(*) AS diagnosis_count "
                               + "FROM medical_record "
                               + "GROUP BY medical_diagnosis "
                               + "ORDER BY diagnosis_count DESC";  

        String ageGroupQuery = "SELECT CASE "
                             + "WHEN TIMESTAMPDIFF(YEAR, p.date_of_birth, CURDATE()) < 18 THEN 'Under 18' "
                             + "WHEN TIMESTAMPDIFF(YEAR, p.date_of_birth, CURDATE()) BETWEEN 18 AND 34 THEN '18-34' "
                             + "WHEN TIMESTAMPDIFF(YEAR, p.date_of_birth, CURDATE()) BETWEEN 35 AND 49 THEN '35-49' "
                             + "WHEN TIMESTAMPDIFF(YEAR, p.date_of_birth, CURDATE()) BETWEEN 50 AND 64 THEN '50-64' "
                             + "WHEN TIMESTAMPDIFF(YEAR, p.date_of_birth, CURDATE()) >= 65 THEN '65+' "
                             + "END AS age_group, COUNT(*) AS patient_count "
                             + "FROM medical_record m "
                             + "JOIN patients_record p ON m.patient_ID = p.patient_ID "
                             + "GROUP BY age_group";

        String averagesQuery = "SELECT AVG(m.temperature) AS avg_temperature, "
                             + "AVG(m.pulse) AS avg_pulse, "
                             + "AVG(m.BP_systolic) AS avg_systolic, "
                             + "AVG(m.BP_diastolic) AS avg_diastolic "
                             + "FROM medical_record m";

        String genderDiagnosisQuery = "SELECT p.gender, m.medical_diagnosis, COUNT(*) AS diagnosis_count "
                                     + "FROM medical_record m "
                                     + "JOIN patients_record p ON m.patient_ID = p.patient_ID "
                                     + "GROUP BY p.gender, m.medical_diagnosis "
                                     + "ORDER BY diagnosis_count DESC";

        String admissionCountQuery = "SELECT YEAR(m.date_time) AS year, MONTH(m.date_time) AS month, COUNT(*) AS patient_count "
                                    + "FROM medical_record m "
                                    + "GROUP BY YEAR(m.date_time), MONTH(m.date_time) "
                                    + "ORDER BY year DESC, month DESC";

        String specificDiagnosisPercentageQuery = "SELECT COUNT(*) AS diagnosis_count "
                                                 + "FROM medical_record "
                                                 + "WHERE medical_diagnosis = 'Pneumonia'";

        String repeatPatientsQuery = "SELECT patient_ID, COUNT(*) AS admission_count "
                                    + "FROM medical_record "
                                    + "GROUP BY patient_ID "
                                    + "HAVING COUNT(*) > 1";

        try (Connection conn = DBConnection.getConnection()) {
            // chief complaints
            summary.append("Summary of Chief Complaints:\n");
            try (PreparedStatement pstmt = conn.prepareStatement(chiefComplaintQuery);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String chiefComplaint = rs.getString("chief_complaint");
                    int patientCount = rs.getInt("patient_count");
                    summary.append(String.format("Chief Complaint: %s - Number of Patients: %d\n", chiefComplaint, patientCount));
                }
            }

            // medical diagnoses 
            summary.append("\nSummary of Medical Diagnoses:\n");
            try (PreparedStatement pstmt = conn.prepareStatement(diagnosisQuery);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String diagnosis = rs.getString("medical_diagnosis");
                    int diagnosisCount = rs.getInt("diagnosis_count");
                    summary.append(String.format("Diagnosis: %s - Frequency: %d\n", diagnosis, diagnosisCount));
                }
            }

            // age groups
            summary.append("\nSummary of Patients by Age Group:\n");
            try (PreparedStatement pstmt = conn.prepareStatement(ageGroupQuery);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String ageGroup = rs.getString("age_group");
                    int patientCount = rs.getInt("patient_count");
                    summary.append(String.format("Age Group: %s - Number of Patients: %d\n", ageGroup, patientCount));
                }
            }

            // averages
            summary.append("\nAverage Medical Statistics:\n");
            try (PreparedStatement pstmt = conn.prepareStatement(averagesQuery);
                 ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    double avgTemperature = rs.getDouble("avg_temperature");
                    double avgPulse = rs.getDouble("avg_pulse");
                    double avgSystolicBP = rs.getDouble("avg_systolic");
                    double avgDiastolicBP = rs.getDouble("avg_diastolic");
                    summary.append(String.format("Avg Temperature: %.2f °C\n", avgTemperature));
                    summary.append(String.format("Avg Pulse: %.2f bpm\n", avgPulse));
                    summary.append(String.format("Avg Systolic BP: %.2f mmHg\n", avgSystolicBP));
                    summary.append(String.format("Avg Diastolic BP: %.2f mmHg\n", avgDiastolicBP));
                }
            }

            // most common diagnoses
            summary.append("\nMost Common Medical Diagnoses by Gender:\n");
            try (PreparedStatement pstmt = conn.prepareStatement(genderDiagnosisQuery);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String gender = rs.getString("gender");
                    String diagnosis = rs.getString("medical_diagnosis");
                    int diagnosisCount = rs.getInt("diagnosis_count");
                    summary.append(String.format("Gender: %s | Diagnosis: %s - Frequency: %d\n", gender, diagnosis, diagnosisCount));
                }
            }

            // admission/year
            summary.append("\nNumber of Admissions per Month/Year:\n");
            try (PreparedStatement pstmt = conn.prepareStatement(admissionCountQuery);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int year = rs.getInt("year");
                    int month = rs.getInt("month");
                    int patientCount = rs.getInt("patient_count");
                    summary.append(String.format("Year: %d, Month: %d - Admissions: %d\n", year, month, patientCount));
                }
            }

            // % of Patients with Specific Diagnosis
            summary.append("\nPercentage of Patients with 'Pneumonia':\n");
            try (PreparedStatement pstmt = conn.prepareStatement(specificDiagnosisPercentageQuery);
                 ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int pneumoniaCount = rs.getInt("diagnosis_count");

                    // Get total number of patients in the system
                    String totalPatientsQuery = "SELECT COUNT(*) AS total_count FROM medical_record";
                    try (PreparedStatement totalPstmt = conn.prepareStatement(totalPatientsQuery);
                         ResultSet totalRs = totalPstmt.executeQuery()) {
                        if (totalRs.next()) {
                            int totalCount = totalRs.getInt("total_count");
                            double percentage = (pneumoniaCount / (double) totalCount) * 100;
                            summary.append(String.format("Pneumonia Patients: %d (%.2f%% of total patients)\n", pneumoniaCount, percentage));
                        }
                    }
                }
            }

            // repeating  patients 
            summary.append("\nRepeat Patients (Admitted more than once):\n");
            try (PreparedStatement pstmt = conn.prepareStatement(repeatPatientsQuery);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int patientID = rs.getInt("patient_ID");
                    int admissionCount = rs.getInt("admission_count");
                    summary.append(String.format("Patient ID: %d - Admissions: %d\n", patientID, admissionCount));
                }
            }
        }

        return summary.toString();  
    }

    // SOME HELPY FUNCTIONS
    public boolean patientExists(int patientId, Connection conn) throws SQLException {
        String query = "SELECT COUNT(*) FROM patients_record WHERE patient_ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, patientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public boolean isPatientAdmitted(int patientId, Connection conn) throws SQLException {
        String query = "SELECT status FROM patients_record WHERE patient_ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, patientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String status = rs.getString("status");
                    return "admitted".equalsIgnoreCase(status);  
                }
            }
        }
        return false;  
    }

    public String getExistingDiagnosis(int patientId, Connection conn) throws SQLException {
        String query = "SELECT medical_diagnosis FROM medical_record WHERE patient_ID = ? ORDER BY date_time DESC LIMIT 1";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, patientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("medical_diagnosis");
                }
            }
        }
        return null;  // No existing diagnosis
    }

    public String getExistingReason(int patientId, Connection conn) throws SQLException {
        String query = "SELECT reason_for_admission FROM medical_record WHERE patient_ID = ? ORDER BY date_time DESC LIMIT 1";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, patientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("reason_for_admission");
                }
            }
        }
        return null;  // No existing reason for admission
    }

    public void updateDiagnosis(int patientId, String diagnosis, Connection conn) throws SQLException {
        String query = "UPDATE medical_record SET medical_diagnosis = ? WHERE patient_ID = ? ORDER BY date_time DESC LIMIT 1";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, diagnosis);
            pstmt.setInt(2, patientId);
            pstmt.executeUpdate();
            System.out.println("Diagnosis updated for Patient ID: " + patientId);
        }
    }

    public void updateReasonForAdmission(int patientId, String reason, Connection conn) throws SQLException {
        String query = "UPDATE medical_record SET reason_for_admission = ? WHERE patient_ID = ? ORDER BY date_time DESC LIMIT 1";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, reason);
            pstmt.setInt(2, patientId);
            pstmt.executeUpdate();
            System.out.println("Reason for admission updated for Patient ID: " + patientId);
        }
    }

    public boolean deleteMedicalRecord(int recordId, int patientId, Connection conn) throws SQLException {
        // First, check if the medical record exists for the given patient
        String checkQuery = "SELECT COUNT(*) FROM medical_record WHERE record_ID = ? AND patient_ID = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, recordId);
            checkStmt.setInt(2, patientId);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    // If the record exists, delete it
                    String deleteQuery = "DELETE FROM medical_record WHERE record_ID = ? AND patient_ID = ?";
                    try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
                        deleteStmt.setInt(1, recordId);
                        deleteStmt.setInt(2, patientId);
                        int rowsAffected = deleteStmt.executeUpdate();
                        return rowsAffected > 0;  // Return true if the deletion was successful
                    }
                } else {
                    System.out.println("Medical record not found for Patient ID: " + patientId);
                    return false;  // No medical record found for the given record ID and patient ID
                }
            }
        }
    }
}