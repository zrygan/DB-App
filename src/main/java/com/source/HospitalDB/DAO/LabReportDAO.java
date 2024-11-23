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
    public static void add(LabReport labReport) throws SQLException {
        String query = "INSERT INTO lab_report_record (test_ID) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, labReport.getTestID());
            stmt.executeUpdate();
        }
    }

    public static LabReport get(int labReportId) throws SQLException {
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

    public static List<LabReport> getAll() throws SQLException {
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

    public static void del(int labReportId) throws SQLException {
        String query = "DELETE FROM lab_report_record WHERE lab_report_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, labReportId);
            stmt.executeUpdate();
        }
    }

    // get array of lab test ID with the same labreportID
    public static List<Integer> getLabTestID(int labReportId) throws SQLException {
        String query = "SELECT test_ID FROM lab_report_record WHERE lab_report_ID = ?";
        List<Integer> labTestIDs = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, labReportId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    labTestIDs.add(rs.getInt("test_ID"));
                }
            }
        }
        return labTestIDs;
    }
}
