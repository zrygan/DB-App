package com.source.HospitalDB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.source.HospitalDB.Classes.Patient;
import com.source.HospitalDB.DBConnection;

public class PatientDAO {
    // Create a new Patient record
    public static void create(Patient patient) throws SQLException {
        String query = "INSERT INTO patients_record (patient_name, age, birth_date, sex, patient_height,"
                     + "patient_weight, religion, doctor_ID, date_created)"
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());
            pstmt.setDate(3, patient.getBirthDate());
            pstmt.setString(4, patient.getSex());
            pstmt.setBigDecimal(5, patient.getHeight());
            pstmt.setBigDecimal(6, patient.getWeight());
            pstmt.setString(7, patient.getReligion());
            pstmt.setInt(8, patient.getDoctor());
            pstmt.setTimestamp(9, patient.getDateCreated());
        
            pstmt.executeUpdate();
        }
        
    }       

    //Update a patient record
    public static void update(Patient patient) throws SQLException {
        String query = "UPDATE patients_record (patient_name, age, birth_date, sex, patient_height,"
                     + "patient_weight, religion, doctor, date_created)"
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());
            pstmt.setDate(3, patient.getBirthDate());
            pstmt.setString(4, patient.getSex());
            pstmt.setBigDecimal(5, patient.getHeight());
            pstmt.setBigDecimal(6, patient.getWeight());
            pstmt.setString(7, patient.getReligion());
            pstmt.setInt(8, patient.getDoctor());
            pstmt.setTimestamp(9, patient.getDateCreated());
        
            pstmt.executeUpdate();
        }
    }

    // Delete a patient record
    public static void delete(int id) throws SQLException {
        String query = "DELETE FROM patient_record WHERE patient_ID = ?";
        
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
        }
    }

    //Viewing a patient record of a specific patient
    public static Patient getPatient(int id) throws SQLException {
        String query = "SELECT * FROM patients_record WHERE patient_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Patient(
                        rs.getString("patient_name"),
                        rs.getDate("birth_date"),
                        rs.getString("sex"),
                        rs.getBigDecimal("patient_height"),
                        rs.getBigDecimal("patient_weight"),
                        rs.getString("religion"),
                        rs.getInt("doctor")
                    );
                }
            }
        }
        return null;
    }


    //Viewing all medical records of patients with the status 'Admitted' or 'Discharged'
    public static List<Patient> status(String status) throws  SQLException{
        String query = "SELECT * FROM patients_record";
        List<Patient> patients = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                patients.add(new Patient(
                    rs.getString("patient_name"),
                    rs.getDate("birth_date"),
                    rs.getString("sex"),
                    rs.getBigDecimal("patient_height"),
                    rs.getBigDecimal("patient_weight"),
                    rs.getString("religion"),
                    rs.getInt("doctor")
                ));
            }
        }
        return patients;
    }

    // Method to get a comprehensive summary of all medical records and return it as a string
    // FIXME: Review this - Jaztin
    public static String getPatientRecordSummary() throws SQLException {
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

