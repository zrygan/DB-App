package com.source.HospitalDB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.source.HospitalDB.Classes.LabTest;
import com.source.HospitalDB.DBConnection;

public class LabTestDAO {
    public static void add(LabTest labTest) throws SQLException {
        String query = "INSERT INTO lab_test_record (test_description) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, labTest.getTestDescription());
            stmt.executeUpdate();
        }
    }

    public static LabTest get(int testId) throws SQLException {
        String query = "SELECT * FROM lab_test_record WHERE test_ID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, testId);
            try (java.sql.ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new LabTest(
                        rs.getInt("test_ID"),
                        rs.getString("test_description")
                    );
                }
            }
        }
        return null;
    }

    public static List<LabTest> getAll() throws SQLException {
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

    public static void del(int testId) throws SQLException {
        String query = "DELETE FROM lab_test_record WHERE test_ID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, testId);
            stmt.executeUpdate();
        }
    }

    // return description of a test ID
    public static String getTestDescription(int testId) throws SQLException {
        String query = "SELECT test_description FROM lab_test_record WHERE test_ID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, testId);
            try (java.sql.ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("test_description");
                }
            }
        }
        return null;
    }
    
    public static int getByDescription(String testDescription) throws SQLException {
        String query = "SELECT test_ID FROM lab_test_record WHERE test_description = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, testDescription);
            try (java.sql.ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("test_ID");
                }
            }
        }
        return -1; // Return -1 if no matching test description is found
    }
}