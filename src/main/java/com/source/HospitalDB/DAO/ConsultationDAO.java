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
        String query = "INSERT INTO consultation_record (doctor_ID, patient_ID, vital_signs_ID, consultation_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) { 
            stmt.setInt(1, consultation.getDoctorID());
            stmt.setInt(2, consultation.getPatientID());
            stmt.setInt(3, consultation.getVitalSignsID());
            stmt.setTimestamp(4, consultation.getConsultationDate());
            stmt.executeUpdate();
        }
    }

    // updaate consultation record
    public static void update(Consultation consultation) throws SQLException {
        String query = "UPDATE consultation_record SET doctor_ID = ?, patient_ID = ?, vital_signs_ID = ?, consultation_date = ? WHERE consultation_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, consultation.getDoctorID());
            stmt.setInt(2, consultation.getPatientID());
            stmt.setInt(3, consultation.getVitalSignsID());
            stmt.setTimestamp(4, consultation.getConsultationDate());
            stmt.setInt(5, consultation.getConsultationID());
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
                        rs.getInt("doctor_ID"),
                        rs.getInt("patient_ID"),
                        rs.getInt("vital_signs_ID")
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
                    rs.getInt("doctor_ID"),
                    rs.getInt("patient_ID"),
                    rs.getInt("vital_signs_ID")
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

    // return list of consultations from the date of consultation
    public static List<Consultation> getConsultationByDate(String consultationDate) throws SQLException {
        String query = "SELECT * FROM consultation_record WHERE consultation_date = ?";
        List<Consultation> consultations = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, consultationDate);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    consultations.add(new Consultation(
                        rs.getInt("consultation_ID"),
                        rs.getInt("doctor_ID"),
                        rs.getInt("patient_ID"),
                        rs.getInt("vital_signs_ID")
                    ));
                }
            }
        }
        return consultations;
    }

    // return list of consultations from the patient ID and their birth date
    public static List<Consultation> getConsultationByPatient(int patientId) throws SQLException {
        String query = "SELECT * FROM consultation_record WHERE patient_ID = ?";
        List<Consultation> consultations = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    consultations.add(new Consultation(
                        rs.getInt("consultation_ID"),
                        rs.getInt("doctor_ID"),
                        rs.getInt("patient_ID"),
                        rs.getInt("vital_signs_ID")
                    ));
                }
            }
        }
        return consultations;
    }

    // get doctor id of a consultation with doctor id
    public static int getDoctorID(int consultationId) throws SQLException {
        String query = "SELECT doctor_ID FROM consultation_record WHERE doctor_ID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, consultationId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("doctor_ID");
                }
            }
        }
        return 0;
    }

    public static int getHighestID() throws SQLException {
        String query = "SELECT MAX(consultation_ID) AS max_consultation_id FROM consultation_record";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt("max_consultation_id");
            }
        }
    
        return 0;
    }

    // get consult ID through object directly
    public static int getConsultationID(Consultation consultation) throws SQLException {
        String query = "SELECT consultation_ID FROM consultation_record WHERE doctor_ID = ? AND patient_ID = ? AND vital_signs_ID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, consultation.getDoctorID());
            stmt.setInt(2, consultation.getPatientID());
            stmt.setInt(3, consultation.getVitalSignsID());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("consultation_ID");
                }
            }
        }
        return 0;
    }
}