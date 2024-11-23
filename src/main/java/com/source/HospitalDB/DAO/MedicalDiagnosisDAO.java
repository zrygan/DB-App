package com.source.HospitalDB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.source.HospitalDB.Classes.MedicalDiagnosis;
import com.source.HospitalDB.DBConnection;

public class MedicalDiagnosisDAO {
    public static void add(MedicalDiagnosis diagnosis) throws SQLException {
        String query = "INSERT INTO medical_diagnosis_record (diagnosis_description) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, diagnosis.getDiagnosisDescription());
            stmt.executeUpdate();
        }
    }

    public static MedicalDiagnosis get(int diagnosisId) throws SQLException {
        String query = "SELECT * FROM medical_diagnosis_record WHERE diagnosis_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, diagnosisId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MedicalDiagnosis(
                        rs.getString("diagnosis_description")
                    );
                }
            }
        }
        return null;
    }

    public static List<MedicalDiagnosis> getAll() throws SQLException {
        String query = "SELECT * FROM medical_diagnosis_record";
        List<MedicalDiagnosis> diagnoses = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                diagnoses.add(new MedicalDiagnosis(
                    rs.getString("diagnosis_description")
                ));
            }
        }
        return diagnoses;
    }

    public static void del(int diagnosisId) throws SQLException {
        String query = "DELETE FROM medical_diagnosis_record WHERE diagnosis_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, diagnosisId);
            stmt.executeUpdate();
        }
    }

    // check if it exists boolean by diagnosis string
    public static boolean exists(String diagnosis) throws SQLException {
        String query = "SELECT * FROM medical_diagnosis_record WHERE diagnosis_description = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, diagnosis);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    // get medical diagnosis by name
    public static MedicalDiagnosis getByName(String diagnosis) throws SQLException {
        String query = "SELECT * FROM medical_diagnosis_record WHERE diagnosis_description = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, diagnosis);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MedicalDiagnosis(
                        rs.getInt("diagnosis_ID"),
                        rs.getString("diagnosis_description"));
                }
            }
        }
        return null;
    }
}

