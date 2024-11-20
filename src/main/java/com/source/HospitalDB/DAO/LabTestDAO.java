package com.source.HospitalDB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.source.HospitalDB.Classes.LabTest;
import com.source.HospitalDB.DBConnection;

public class LabTestDAO {
    public static void addLabTest(LabTest labTest) throws SQLException {
        String query = "INSERT INTO lab_test_record (test_ID, test_description) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, labTest.getTestID());
            stmt.setString(2, labTest.getTestDescription());
            stmt.executeUpdate();
        }
    }

    public static LabTest getLabTest(int testId) throws SQLException {
        String query = "SELECT * FROM lab_test_record WHERE test_ID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, testId);
            try (java.sql.ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new LabTest(
                        rs.getString("test_description")
                    );
                }
            }
        }
        return null;
    }

    public static List<LabTest> getAllLabTests() throws SQLException {
        String query = "SELECT * FROM lab_test_record";
        List<LabTest> labTests = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
                java.sql.Statement stmt = conn.createStatement();
                java.sql.ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                labTests.add(new LabTest(
                    rs.getString("test_description")
                ));
            }
        }
        return labTests;
    }

    public static void updateLabTest(LabTest labTest) throws SQLException {
        String query = "UPDATE lab_test_record SET test_description = ? WHERE test_ID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, labTest.getTestDescription());
            stmt.setInt(2, labTest.getTestID());
            stmt.executeUpdate();
        }
    }

    public static void delete(int testId) throws SQLException {
        String query = "DELETE FROM lab_test_record WHERE test_ID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, testId);
            stmt.executeUpdate();
        }
    }
}