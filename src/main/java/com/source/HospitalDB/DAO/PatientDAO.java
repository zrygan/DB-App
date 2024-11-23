package com.source.HospitalDB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.source.HospitalDB.Classes.Patient;
import com.source.HospitalDB.DBConnection;

public class PatientDAO {
    // Create a new Patient record
    public static void add(Patient patient) throws SQLException {
        String query = "INSERT INTO patients_record (patient_firstname, patient_firstname, age, birth_date, sex, patient_height,"
                + "patient_weight, religion, date_created)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, patient.getFirstname());
            pstmt.setString(2, patient.getLastname());
            pstmt.setInt(3, patient.getAge());
            pstmt.setTimestamp(4, patient.getBirthDate());
            pstmt.setString(5, patient.getSex());
            pstmt.setBigDecimal(6, patient.getHeight());
            pstmt.setBigDecimal(7, patient.getWeight());
            pstmt.setString(8, patient.getReligion());
            pstmt.setTimestamp(9, patient.getDateCreated());

            pstmt.executeUpdate();
        }
    }

    // Delete a patient record
    public static void del(int id) throws SQLException {
        String query = "DELETE FROM patients_record WHERE patient_ID = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Viewing a patient record of a specific patient
    public static Patient getFromID(int id) throws SQLException {
        String query = "SELECT * FROM patients_record WHERE patient_ID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Patient(
                            rs.getInt("patient_ID"),
                            rs.getString("patient_firstname"),
                            rs.getString("patient_lastname"),
                            rs.getTimestamp("birth_date"),
                            rs.getString("sex"),
                            rs.getBigDecimal("patient_height"),
                            rs.getBigDecimal("patient_weight"),
                            rs.getString("religion"),
                            rs.getTimestamp("date_created"));
                }
            }
        }
        return null;
    }

    // FIXME: no getAll

    // Method to get a comprehensive summary of all medical records and return it as
    // a string
    // FIXME: untested, needs corrections
    public static String getSummary() throws SQLException {
        StringBuilder summary = new StringBuilder();

        // Query for each summary

        // 1. Total number of patients grouped by sex and status.
        String genderGroupQuery = "SELECT sex, status, COUNT(*) AS patient_count "
                + "FROM patients_record "
                + "GROUP BY sex, status "
                + "ORDER BY patient_count DESC";

        // 2. Total number of patients grouped by their respective age group.
        String ageGroupQuery = "SELECT CASE"
                + " WHEN age < 18 THEN '0-18'"
                + " WHEN age BETWEEN 18 AND 29 THEN '19-29'"
                + " WHEN age BETWEEN 30 AND 39 THEN '30-39'"
                + " WHEN age BETWEEN 40 AND 49 THEN '40-49'"
                + " WHEN age BETWEEN 50 AND 59 THEN '50-59'"
                + " WHEN age >= 60 THEN '60 and above'"
                + " END AS age_group, COUNT(*) AS total_patients"
                + " FROM patients_record"
                + " GROUP BY age_group";

        try (Connection conn = DBConnection.getConnection()) {
            // Gender Group
            summary.append("Total patients per sex and status:\n");
            try (PreparedStatement pstmt = conn.prepareStatement(genderGroupQuery);
                    ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String sex = rs.getString("sex");
                    String status = rs.getString("status");
                    int patientCount = rs.getInt("patient_count");
                    summary.append(String.format("%s (%s) - Number of Patients: %d\n", sex, status, patientCount));
                }
            }

            // Age Group
            summary.append("Total patients per age group:\n");
            try (PreparedStatement pstmt = conn.prepareStatement(ageGroupQuery);
                    ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String ageGroup = rs.getString("age_group");
                    int patientCount = rs.getInt("total_patients");
                    summary.append(String.format("Age Group: %s - Number of Patients: %d\n", ageGroup, patientCount));
                }
            }
        }

        return summary.toString();
    }

    public static int getFromNameBDay(String firstname, String lastname, Timestamp birthDate) throws SQLException {
        String query = "SELECT patient_ID FROM patients_record WHERE patient_firstname = ? AND patient_lastname = ? AND birth_date = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setTimestamp(3, birthDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("patient_ID");
                } else {
                    return 0;
                }
            }
        }
    }
}