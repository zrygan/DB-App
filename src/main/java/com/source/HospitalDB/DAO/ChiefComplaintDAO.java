package com.source.HospitalDB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.source.HospitalDB.Classes.ChiefComplaint;
import com.source.HospitalDB.DBConnection;

public class ChiefComplaintDAO {
    public static void addChiefComplaint(ChiefComplaint complaint) throws SQLException {
        String query = "INSERT INTO chief_complaint_record (complaint_description) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, complaint.getComplaintDescription());
            stmt.executeUpdate();
        }
    }

    public static ChiefComplaint getChiefComplaint(int complaintId) throws SQLException {
        String query = "SELECT * FROM chief_complaint_record WHERE complaint_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, complaintId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ChiefComplaint(
                        rs.getString("complaint_description")
                    );
                }
            }
        }
        return null;
    }

    public static List<ChiefComplaint> getAllChiefComplaints() throws SQLException {
        String query = "SELECT * FROM chief_complaint_record";
        List<ChiefComplaint> complaints = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                complaints.add(new ChiefComplaint(
                    rs.getString("complaint_description")
                ));
            }
        }
        return complaints;
    }

    public static void updateChiefComplaint(ChiefComplaint complaint) throws SQLException {
        String query = "UPDATE chief_complaint_record SET complaint_description = ? WHERE complaint_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, complaint.getComplaintDescription());
            stmt.setInt(2, complaint.getComplaintID());
            stmt.executeUpdate();
        }
    }

    public static void deleteChiefComplaint(int complaintId) throws SQLException {
        String query = "DELETE FROM chief_complaint_record WHERE complaint_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, complaintId);
            stmt.executeUpdate();
        }
    }
}
