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
    public static void add(ChiefComplaint complaint) throws SQLException {
        String query = "INSERT INTO chief_complaint_record (complaint_description) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, complaint.getComplaintDescription());
            stmt.executeUpdate();
        }
    }

    public static ChiefComplaint get(int complaintId) throws SQLException {
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

    public static List<ChiefComplaint> getAll() throws SQLException {
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

    public static void del(int complaintId) throws SQLException {
        String query = "DELETE FROM chief_complaint_record WHERE complaint_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, complaintId);
            stmt.executeUpdate();
        }
    }

    // get highest id of complaint
    public static int getHighestID() throws SQLException {
        String query = "SELECT MAX(complaint_ID) FROM chief_complaint_record";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
    // return booleaan if the chief complain already exists
    public static boolean exists(String complaint) throws SQLException {
        String query = "SELECT * FROM chief_complaint_record WHERE complaint_description = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, complaint);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    // get by name
    public static ChiefComplaint getByName(String complaint) throws SQLException {
        String query = "SELECT * FROM chief_complaint_record WHERE complaint_description = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, complaint);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ChiefComplaint(
                        rs.getInt("complaint_ID"),
                        rs.getString("complaint_description")
                    );
                }
            }
        }
        return null;
    }
}
