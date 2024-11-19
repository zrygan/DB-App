package main.java.com.source.HospitalDB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.source.HospitalDB.DBConnection;

import main.java.com.source.HospitalDB.Classes.MedicalDiagnosis;

public class MedicalDiagnosisDAO {
    public void addMedicalDiagnosis(MedicalDiagnosis diagnosis) throws SQLException {
        String query = "INSERT INTO medical_diagnosis_record (diagnosis_ID, diagnosis_description) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, diagnosis.getDiagnosisID()); // Primary key
            stmt.setString(2, diagnosis.getDiagnosisDescription());
            stmt.executeUpdate();
        }
    }

    public MedicalDiagnosis getMedicalDiagnosis(int diagnosisId) throws SQLException {
        String query = "SELECT * FROM medical_diagnosis_record WHERE diagnosis_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, diagnosisId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MedicalDiagnosis(
                        rs.getInt("diagnosis_ID"),
                        rs.getString("diagnosis_description")
                    );
                }
            }
        }
        return null;
    }

    public List<MedicalDiagnosis> getAllMedicalDiagnoses() throws SQLException {
        String query = "SELECT * FROM medical_diagnosis_record";
        List<MedicalDiagnosis> diagnoses = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                diagnoses.add(new MedicalDiagnosis(
                    rs.getInt("diagnosis_ID"),
                    rs.getString("diagnosis_description")
                ));
            }
        }
        return diagnoses;
    }

    public void updateMedicalDiagnosis(MedicalDiagnosis diagnosis) throws SQLException {
        String query = "UPDATE medical_diagnosis_record SET diagnosis_description = ? WHERE diagnosis_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, diagnosis.getDiagnosisDescription());
            stmt.setInt(2, diagnosis.getDiagnosisID());
            stmt.executeUpdate();
        }
    }

    public void deleteMedicalDiagnosis(int diagnosisId) throws SQLException {
        String query = "DELETE FROM medical_diagnosis_record WHERE diagnosis_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, diagnosisId);
            stmt.executeUpdate();
        }
    }
}

