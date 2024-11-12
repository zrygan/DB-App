package com.HospitalDB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PatientJAO {
    // Create a new Patient record
    public void createPatient(Patient patient, Connection conn) throws SQLException {
        String query = "INSERT INTO patient_record (patient_ID, name, age, birth_date, sex, height"
                     + "weight, religion, doctor, status, created_at)"
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
                     + "weight, religion, doctor, status, created_at)"
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

    // delete medical record
    public void deletePatient(int id) throws SQLException {
        String query = "DELETE FROM patient_record WHERE patient_ID = ?";
        
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
        }
    }
}
