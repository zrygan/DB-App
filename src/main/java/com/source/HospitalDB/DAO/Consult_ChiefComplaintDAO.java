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

    public static void add(Consult_ChiefComplaint consultChiefComplaint) throws SQLException {
        String query = "INSERT INTO consultation_chief_complaint_record (consultation_ID, complaint_ID) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, consultChiefComplaint.getConsultationID());
            stmt.setInt(2, consultChiefComplaint.getComplaintID());
            stmt.executeUpdate();
        }
    }

    public static Consult_ChiefComplaint get(int consultation_ID, int complaint_ID) throws SQLException {
        String query = "SELECT * FROM consultation_chief_complaint_record WHERE consultation_ID = ? AND complaint_ID = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setInt(1, consultation_ID);
                stmt.setInt(2, complaint_ID);
                try (ResultSet rs = stmt.executeQuery()){
                    if (rs.next()){
                        return new Consult_ChiefComplaint(
                            rs.getInt("consultation_ID"),
                            rs.getInt("complaint_ID")
                        );
                    }
                }
        }

        return null;
    }

    public static List<Consult_ChiefComplaint> getAll() throws SQLException {
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

    public static void del(int consultation_ID, int complaint_ID) throws SQLException {
        String query = "DELETE FROM consultation_chief_complaint_record WHERE consultation_ID = ? AND complaint_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, consultation_ID);
                stmt.setInt(2, complaint_ID);
                stmt.executeUpdate();
        }
    }
}