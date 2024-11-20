package com.source.HospitalDB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.source.HospitalDB.Classes.Prescription;
import com.source.HospitalDB.DBConnection;

public class PrescriptionDAO {
    public static void addPrescription(Prescription prescription) throws SQLException {
        String query = "INSERT INTO prescription_record (prescription_ID, medication_ID, prescription_date, frequency, dosage, doctor_ID, patient_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, prescription.getPrescriptionID());
            stmt.setInt(2, prescription.getMedicationID());
            stmt.setTimestamp(3, prescription.getPrescriptionDate());
            stmt.setInt(4, prescription.getFrequency());
            stmt.setBigDecimal(5, prescription.getDosage());
            stmt.setInt(6, prescription.getDoctorID());
            stmt.setInt(7, prescription.getPatientID());
            stmt.executeUpdate();
        }
    }

    public static Prescription getPrescription(int prescriptionId) throws SQLException {
        String query = "SELECT * FROM prescription_record WHERE prescription_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, prescriptionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Prescription(
                        rs.getInt("medication_ID"),
                        rs.getInt("frequency"),
                        rs.getBigDecimal("dosage"),
                        rs.getInt("doctor_ID"),
                        rs.getInt("patient_ID")
                    );
                }
            }
        }
        return null;
    }

    public static List<Prescription> getAllPrescriptions() throws SQLException {
        String query = "SELECT * FROM prescription_record";
        List<Prescription> prescriptions = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                prescriptions.add(new Prescription(
                    rs.getInt("medication_ID"),
                    rs.getInt("frequency"),
                    rs.getBigDecimal("dosage"),
                    rs.getInt("doctor_ID"),
                    rs.getInt("patient_ID")
                ));
            }
        }
        return prescriptions;
    }

    public static void updatePrescription(Prescription prescription) throws SQLException {
        String query = "UPDATE prescription_record SET medication_ID = ?, prescription_date = ?, frequency = ?, dosage = ?, doctor_ID = ?, patient_ID = ? WHERE prescription_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, prescription.getMedicationID());
            stmt.setTimestamp(2, prescription.getPrescriptionDate());
            stmt.setInt(3, prescription.getFrequency());
            stmt.setBigDecimal(4, prescription.getDosage());
            stmt.setInt(5, prescription.getDoctorID());
            stmt.setInt(6, prescription.getPatientID());
            stmt.setInt(7, prescription.getPrescriptionID());
            stmt.executeUpdate();
        }
    }

    public static void deletePrescription(int prescriptionId) throws SQLException {
        String query = "DELETE FROM prescription_record WHERE prescription_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, prescriptionId);
            stmt.executeUpdate();
        }
    }   
}

