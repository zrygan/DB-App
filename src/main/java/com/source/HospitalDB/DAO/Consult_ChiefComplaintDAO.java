package com.source.HospitalDB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.source.HospitalDB.Classes.Consult_ChiefComplaint;
import com.source.HospitalDB.DBConnection;

public class Consult_ChiefComplaintDAO {

    public static void addConsultChiefComplaint(Consult_ChiefComplaint consultChiefComplaint) throws SQLException {
        String query = "INSERT INTO consultation_chief_complaint_record (consultation_ID, complaint_ID) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, consultChiefComplaint.getConsultationID());
            stmt.setInt(2, consultChiefComplaint.getComplaintID());
            stmt.executeUpdate();
        }
    }

    public static List<Consult_ChiefComplaint> getAllConsultChiefComplaints() throws SQLException {
        String query = "SELECT * FROM consultation_chief_complaint_record";
        List<Consult_ChiefComplaint> consultChiefComplaints = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                consultChiefComplaints.add(new Consult_ChiefComplaint(
                    rs.getInt("consultation_ID"),
                    rs.getInt("complaint_ID")
                ));
            }
        }
        return consultChiefComplaints;
    }

    public static void updateConsultChiefComplaint(Consult_ChiefComplaint consultChiefComplaint) throws SQLException {
        String query = "UPDATE consultation_chief_complaint_record SET complaint_ID = ? WHERE consultation_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, consultChiefComplaint.getComplaintID());
            stmt.setInt(2, consultChiefComplaint.getConsultationID());
            stmt.executeUpdate();
        }
    }

    public static void deleteConsultChiefComplaint(int consultationID, int complaintID) throws SQLException {
        String query = "DELETE FROM consultation_chief_complaint_record WHERE consultation_ID = ? AND complaint_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, consultationID);
            stmt.setInt(2, complaintID);
            stmt.executeUpdate();
        }
    }
}