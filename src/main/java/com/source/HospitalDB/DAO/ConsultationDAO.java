package com.source.HospitalDB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.source.HospitalDB.Classes.Consultation;
import com.source.HospitalDB.DBConnection;

public class ConsultationDAO {

    public static void add(Consultation consultation) throws SQLException {
        String query = "INSERT INTO consultation_record (prescription_ID, doctor_ID, patient_ID, vital_signs_ID, lab_report_ID, consultation_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) { 
            stmt.setInt(1, consultation.getPrescriptionID());
            stmt.setInt(2, consultation.getDoctorID());
            stmt.setInt(3, consultation.getPatientID());
            stmt.setInt(4, consultation.getVitalSignsID());
            stmt.setInt(5, consultation.getLabReportID());
            stmt.setTimestamp(6, consultation.getConsultationDate());
            stmt.executeUpdate();
        }
    }
    
    public static Consultation get(int consultationId) throws SQLException {
        String query = "SELECT * FROM consultation_record WHERE consultation_ID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, consultationId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Consultation(
                        rs.getInt("prescription_ID"),
                        rs.getInt("doctor_ID"),
                        rs.getInt("patient_ID"),
                        rs.getInt("vital_signs_ID"),
                        rs.getInt("lab_report_ID")
                    );
                }
            }
        }
        return null;
    }
    
    public static List<Consultation> getAll() throws SQLException {
        String query = "SELECT * FROM consultation_record";
        List<Consultation> consultations = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                consultations.add(new Consultation(
                    rs.getInt("prescription_ID"),
                    rs.getInt("doctor_ID"),
                    rs.getInt("patient_ID"),
                    rs.getInt("vital_signs_ID"),
                    rs.getInt("lab_report_ID")
                ));
            }
        }
        return consultations;
    }

    public static void del(int consultationId) throws SQLException {
        String query = "DELETE FROM consultation_record WHERE consultation_ID = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, consultationId);
            stmt.executeUpdate();
        }
    }
}
    




