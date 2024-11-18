package main.java.com.source.HospitalDB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.source.HospitalDB.DBConnection;

import main.java.com.source.HospitalDB.Classes.Consultation;

public class ConsultationDAO {

    public void create(Consultation consultation) throws SQLException {
        String query = "INSERT INTO consultation_record (consultation_ID, prescription_ID, doctor_ID, patient_ID, vital_signs_ID, lab_report_ID, consultation_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, consultation.getConsultationID()); // Primary key
            stmt.setInt(2, consultation.getPrescriptionID());
            stmt.setInt(3, consultation.getDoctorID());
            stmt.setInt(4, consultation.getPatientID());
            stmt.setInt(5, consultation.getVitalSignsID());
            stmt.setInt(6, consultation.getLabReportID());
            stmt.setTimestamp(7, consultation.getConsultationDate());
            stmt.executeUpdate();
        }
    }
    
    public Consultation get(int consultationId) throws SQLException {
        String query = "SELECT * FROM consultation_record WHERE consultation_ID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, consultationId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Consultation(
                        rs.getInt("consultation_ID"),
                        rs.getInt("prescription_ID"),
                        rs.getInt("doctor_ID"),
                        rs.getInt("patient_ID"),
                        rs.getInt("vital_signs_ID"),
                        rs.getInt("lab_report_ID"),
                        rs.getTimestamp("consultation_date")
                    );
                }
            }
        }
        return null;
    }
    
    public List<Consultation> getAll() throws SQLException {
        String query = "SELECT * FROM consultation_record";
        List<Consultation> consultations = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                consultations.add(new Consultation(
                    rs.getInt("consultation_ID"),
                    rs.getInt("prescription_ID"),
                    rs.getInt("doctor_ID"),
                    rs.getInt("patient_ID"),
                    rs.getInt("vital_signs_ID"),
                    rs.getInt("lab_report_ID"),
                    rs.getTimestamp("consultation_date")
                ));
            }
        }
        return consultations;
    }
    
        public void update(Consultation consultation) throws SQLException {
            String query = "UPDATE consultation_record SET prescription_ID = ?, doctor_ID = ?, patient_ID = ?, vital_signs_ID = ?, lab_report_ID = ?, consultation_date = ? WHERE consultation_ID = ?";
            try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, consultation.getPrescriptionID());
                stmt.setInt(2, consultation.getDoctorID());
                stmt.setInt(3, consultation.getPatientID());
                stmt.setInt(4, consultation.getVitalSignsID());
                stmt.setInt(5, consultation.getLabReportID());
                stmt.setTimestamp(6, consultation.getConsultationDate());
                stmt.setInt(7, consultation.getConsultationID()); // Primary key
                stmt.executeUpdate();
            }
        }
    
        public void delete(int consultationId) throws SQLException {
            String query = "DELETE FROM consultation_record WHERE consultation_ID = ?";
            try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, consultationId);
                stmt.executeUpdate();
            }
        }
}
    




