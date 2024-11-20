package com.source.HospitalDB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.source.HospitalDB.Classes.LabReport;
import com.source.HospitalDB.DBConnection;

public class LabReportDAO {
    public static void addLabReport(LabReport labReport) throws SQLException {
        String query = "INSERT INTO lab_report_record (lab_report_ID, test_ID) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, labReport.getLabReportID()); // Primary key
            stmt.setInt(2, labReport.getTestID());
            stmt.executeUpdate();
        }
    }

    public static LabReport getLabReport(int labReportId) throws SQLException {
        String query = "SELECT * FROM lab_report_record WHERE lab_report_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, labReportId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new LabReport(
                        rs.getInt("test_ID")
                    );
                }
            }
        }
        return null;
    }

    public static List<LabReport> getAllLabReports() throws SQLException {
        String query = "SELECT * FROM lab_report_record";
        List<LabReport> labReports = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                labReports.add(new LabReport(
                    rs.getInt("test_ID")
                ));
            }
        }
        return labReports;
    }

    public static void updateLabReport(LabReport labReport) throws SQLException {
        String query = "UPDATE lab_report_record SET test_ID = ? WHERE lab_report_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, labReport.getTestID());
            stmt.setInt(2, labReport.getLabReportID());
            stmt.executeUpdate();
        }
    }

    public static void deleteLabReport(int labReportId) throws SQLException {
        String query = "DELETE FROM lab_report_record WHERE lab_report_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, labReportId);
            stmt.executeUpdate();
        }
    }
}
