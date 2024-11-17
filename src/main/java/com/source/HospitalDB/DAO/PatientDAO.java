package main.java.com.source.HospitalDB.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import main.java.com.source.HospitalDB.Classes.Doctors;
import main.java.com.source.HospitalDB.Classes.Patient;

public class PatientDAO {
    // Create a new Patient record
    public void createPatient(Patient patient, Connection conn) throws SQLException {
        String query = "INSERT INTO patient_record (patient_ID, name, age, birth_date, sex, height"
                     + "weight, religion, DOCTOR, status, created_at)"
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, patient.getPatientId());
            pstmt.setString(2, patient.getName());
            pstmt.setInt(3, patient.getAge());
            pstmt.setDate(4, java.sql.Date.valueOf(patient.getBirthDate()));
            pstmt.setString(5, patient.getSex());
            pstmt.setDouble(6, patient.getHeight());
            pstmt.setDouble(7, patient.getWeight());
            pstmt.setString(8, patient.getReligion());
            //pstmt.setInt(9, patient.getDoctor().getDoctorId()); //Fix me: determine what is doctor's data type
            pstmt.setString(10, patient.getStatus());
            pstmt.setDate(11, java.sql.Date.valueOf(patient.getCreatedAt().toLocalDate()));

            pstmt.executeUpdate();
        }
    }

    //Update a patient record
    public void updatePatient(Patient patient, Connection conn) throws SQLException {
        String query = "UPDATE patient_record (patient_ID, name, age, birth_date, sex, height"
                     + "weight, religion, DOCTOR, status, created_at)"
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, patient.getPatientId());
            pstmt.setString(2, patient.getName());
            pstmt.setInt(3, patient.getAge());
            pstmt.setDate(4, java.sql.Date.valueOf(patient.getBirthDate()));
            pstmt.setString(5, patient.getSex());
            pstmt.setDouble(6, patient.getHeight());
            pstmt.setDouble(7, patient.getWeight());
            pstmt.setString(8, patient.getReligion());
            //pstmt.setInt(9, patient.getDoctor().getDoctorId()); //Fix me: determine what is doctor's data type
            pstmt.setString(10, patient.getStatus());
            pstmt.setDate(11, java.sql.Date.valueOf(patient.getCreatedAt().toLocalDate()));

            pstmt.executeUpdate();
        }
    }

    // Delete a patient record
    public void deletePatient(int id) throws SQLException {
        String query = "DELETE FROM patient_record WHERE patient_ID = ?";
        
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
        }
    }

    //Viewing a patient record of a specific patient
    public Patient getPatient(String name) throws SQLException {
        String query = "SELECT patient_ID, age, birthDate, sex, height, weight, religion, DOCTOR, status, createdAt, updatedAt"
                    + "FROM patient_record "
                    + "WHERE name = ?";  

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int patientID = rs.getInt("patient_ID");
                    int age = rs.getInt("age");
                    LocalDate birthdate = rs.getDate("birthdate").toLocalDate();
                    String sex = rs.getString("sex");
                    double height = rs.getDouble("height");
                    double weight = rs.getInt("weight");
                    String religion = rs.getString("religion");
                    Doctors DOCTOR = (Doctors) rs.getObject("DOCTOR");
                    String status = rs.getString("status");
                    LocalDateTime createdAt = rs.getObject("createdAt", LocalDateTime.class);
                    LocalDateTime updatedAt = rs.getObject("updatedAt", LocalDateTime.class);
                    // Create and return the Patient object
                    return new Patient(patientID, name, age, birthdate, sex, height, weight, religion, DOCTOR, status, createdAt, updatedAt);
                } 
                return null;
            }
        } 
    }

    //Viewing all medical records of patients with the status 'Admitted' or 'Discharged'
    public Patient status(String status) throws  SQLException{
        String query = "SELECT patient_ID, name, age, birthDate, sex, height, weight, religion, DOCTOR, createdAt, updatedAt"
                    + "FROM patient_record "
                    + "WHERE status = ?";  

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, status);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    int patientID = rs.getInt("patient_ID");
                    int age = rs.getInt("age");
                    LocalDate birthdate = rs.getDate("birthdate").toLocalDate();
                    String sex = rs.getString("sex");
                    double height = rs.getDouble("height");
                    double weight = rs.getInt("weight");
                    String religion = rs.getString("religion");
                    Doctors DOCTOR = (Doctors) rs.getObject("DOCTOR");
                    LocalDateTime createdAt = rs.getObject("createdAt", LocalDateTime.class);
                    LocalDateTime updatedAt = rs.getObject("updatedAt", LocalDateTime.class);
                    // Create and return the Patient object
                    return new Patient(patientID, name, age, birthdate, sex, height, weight, religion, DOCTOR, status, createdAt, updatedAt);
                } 
                return null;
            }
        } 
    }

    // Method to get a comprehensive summary of all medical records and return it as a string
    public String getPatientRecordSummary() throws SQLException {
        StringBuilder summary = new StringBuilder();
        
        // Query for each summary

        // 1. Total number of patients grouped by sex and status.
        String genderGroupQuery = "SELECT sex, status COUNT(*) AS patient_count "
                                   + "FROM patient_record "
                                   + "GROUP BY sex, status "
                                   + "ORDER BY patient_count DESC";

        // 2. Total number of patients grouped by their respecive age group.
        String ageGroupQuery = "SELECT CASE" 
                                + "WHEN Age < 18 THEN '0-18'"
                                + "WHEN Age BETWEEN 18 AND 29 THEN '19-29'"
                                + "WHEN Age BETWEEN 30 AND 39 THEN '30-39'"
                                + "WHEN Age BETWEEN 40 AND 49 THEN '40-49'"
                                + "WHEN Age BETWEEN 50 AND 59 THEN '50-59'"
                                + "WHEN Age >= 60 THEN '60 and above'"
                                + "END AS age_group, COUNT(*) AS total_patients"
                                + "FROM patient_record"
                                + "GROUP BY age_group";


        try (Connection conn = DBConnection.getConnection()) {
            // Gender Group
            summary.append("Total patients per sex and status:\n");
            try (PreparedStatement pstmt = conn.prepareStatement(genderGroupQuery);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String sex = rs.getString("sex");
                    int patientCount = rs.getInt("patient_count");
                    summary.append(String.format("%s - Number of Patients: %d\n", sex, patientCount));
                }
            }
        }

        try (Connection conn = DBConnection.getConnection()) {
            // Age Group
            summary.append("Total patients per age group:\n");
            try (PreparedStatement pstmt = conn.prepareStatement(ageGroupQuery);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String ageGroup = rs.getString("age_group");
                    int patientCount = rs.getInt("patient_count");
                    summary.append(String.format("Age Group: %s - Number of Patients: %d\n", ageGroup, patientCount));
                }
            }
        }

        return summary.toString();
    }
}

