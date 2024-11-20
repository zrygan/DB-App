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
    public static void addMedicalDiagnosis(MedicalDiagnosis diagnosis) throws SQLException {
        String query = "INSERT INTO medical_diagnosis_record (diagnosis_description) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, diagnosis.getDiagnosisDescription());
            stmt.executeUpdate();
        }
    }

    public static MedicalDiagnosis getMedicalDiagnosis(int diagnosisId) throws SQLException {
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

    public static List<MedicalDiagnosis> getAllMedicalDiagnoses() throws SQLException {
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

    public static void updateMedicalDiagnosis(MedicalDiagnosis diagnosis) throws SQLException {
        String query = "UPDATE medical_diagnosis_record SET diagnosis_description = ? WHERE diagnosis_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, diagnosis.getDiagnosisDescription());
            stmt.setInt(2, diagnosis.getDiagnosisID());
            stmt.executeUpdate();
        }
    }

    public static void deleteMedicalDiagnosis(int diagnosisId) throws SQLException {
        String query = "DELETE FROM medical_diagnosis_record WHERE diagnosis_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, diagnosisId);
            stmt.executeUpdate();
        }
    }
}

