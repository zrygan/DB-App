package main.java.com.source.HospitalDB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PatientJAO {
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


}

