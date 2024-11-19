package main.java.com.source.HospitalDB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.source.HospitalDB.DBConnection;

import main.java.com.source.HospitalDB.Classes.VitalSigns;

public class VitalSignsDAO {
    public void addVitalSigns(VitalSigns vitalSigns) throws SQLException {
        String query = "INSERT INTO vital_signs_record (vital_signs_ID, temperature, pulse, respiratory_rate, systolic_bp, diastolic_bp, SPO2, vital_signs_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, vitalSigns.getVitalSignsID()); // Primary key
            stmt.setBigDecimal(2, vitalSigns.getTemperature());
            stmt.setInt(3, vitalSigns.getPulse());
            stmt.setInt(4, vitalSigns.getRespiratoryRate());
            stmt.setInt(5, vitalSigns.getSystolicBP());
            stmt.setInt(6, vitalSigns.getDiastolicBP());
            stmt.setInt(7, vitalSigns.getSpo2());
            stmt.setTimestamp(8, vitalSigns.getVitalSignsDate());
            stmt.executeUpdate();
        }
    }

    public VitalSigns getVitalSigns(int vitalSignsId) throws SQLException {
        String query = "SELECT * FROM vital_signs_record WHERE vital_signs_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, vitalSignsId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new VitalSigns(
                        rs.getInt("vital_signs_ID"),
                        rs.getBigDecimal("temperature"),
                        rs.getInt("pulse"),
                        rs.getInt("respiratory_rate"),
                        rs.getInt("systolic_bp"),
                        rs.getInt("diastolic_bp"),
                        rs.getInt("SPO2"),
                        rs.getTimestamp("vital_signs_date")
                    );
                }
            }
        }
        return null;
    }

    public List<VitalSigns> getAllVitalSigns() throws SQLException {
        String query = "SELECT * FROM vital_signs_record";
        List<VitalSigns> vitalSignsList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                vitalSignsList.add(new VitalSigns(
                    rs.getInt("vital_signs_ID"),
                    rs.getBigDecimal("temperature"),
                    rs.getInt("pulse"),
                    rs.getInt("respiratory_rate"),
                    rs.getInt("systolic_bp"),
                    rs.getInt("diastolic_bp"),
                    rs.getInt("SPO2"),
                    rs.getTimestamp("vital_signs_date")
                ));
            }
        }
        return vitalSignsList;
    }

    public void updateVitalSigns(VitalSigns vitalSigns) throws SQLException {
        String query = "UPDATE vital_signs_record SET temperature = ?, pulse = ?, respiratory_rate = ?, systolic_bp = ?, diastolic_bp = ?, SPO2 = ?, vital_signs_date = ? WHERE vital_signs_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBigDecimal(1, vitalSigns.getTemperature());
            stmt.setInt(2, vitalSigns.getPulse());
            stmt.setInt(3, vitalSigns.getRespiratoryRate());
            stmt.setInt(4, vitalSigns.getSystolicBP());
            stmt.setInt(5, vitalSigns.getDiastolicBP());
            stmt.setInt(6, vitalSigns.getSpo2());
            stmt.setTimestamp(7, vitalSigns.getVitalSignsDate());
            stmt.setInt(8, vitalSigns.getVitalSignsID());
            stmt.executeUpdate();
        }
    }

    public void deleteVitalSigns(int vitalSignsId) throws SQLException {
        String query = "DELETE FROM vital_signs_record WHERE vital_signs_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, vitalSignsId);
            stmt.executeUpdate();
        }
    }
}
