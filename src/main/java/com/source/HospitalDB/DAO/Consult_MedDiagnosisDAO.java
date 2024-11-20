package com.source.HospitalDB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.source.HospitalDB.Classes.Consult_MedDiagnosis;
import com.source.HospitalDB.DBConnection;

public class Consult_MedDiagnosisDAO {

    public static void addConsultMedDiagnosis(Consult_MedDiagnosis consultMedDiagnosis) throws SQLException {
        String query = "INSERT INTO consultation_medical_diagnosis_record (consultation_ID, diagnosis_ID) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, consultMedDiagnosis.getConsultationID());
            stmt.setInt(2, consultMedDiagnosis.getDiagnosisID());
            stmt.executeUpdate();
        }
    }

    public static List<Consult_MedDiagnosis> getAllConsultMedDiagnoses() throws SQLException {
        String query = "SELECT * FROM consultation_medical_diagnosis_record";
        List<Consult_MedDiagnosis> consultMedDiagnoses = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                consultMedDiagnoses.add(new Consult_MedDiagnosis(
                    rs.getInt("consultation_ID"),
                    rs.getInt("diagnosis_ID")
                ));
            }
        }
        return consultMedDiagnoses;
    }

    public static void updateConsultMedDiagnosis(Consult_MedDiagnosis consultMedDiagnosis) throws SQLException {
        String query = "UPDATE consultation_medical_diagnosis_record SET diagnosis_ID = ? WHERE consultation_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, consultMedDiagnosis.getDiagnosisID());
            stmt.setInt(2, consultMedDiagnosis.getConsultationID());
            stmt.executeUpdate();
        }
    }

    public static void deleteConsultMedDiagnosis(int consultationID, int diagnosisID) throws SQLException {
        String query = "DELETE FROM consultation_medical_diagnosis_record WHERE consultation_ID = ? AND diagnosis_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, consultationID);
            stmt.setInt(2, diagnosisID);
            stmt.executeUpdate();
        }
    }
}