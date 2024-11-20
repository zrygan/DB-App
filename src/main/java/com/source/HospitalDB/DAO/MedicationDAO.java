package com.source.HospitalDB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.source.HospitalDB.Classes.Medication;
import com.source.HospitalDB.DBConnection;

public class MedicationDAO {
    // Create a new Medication Record
    public static void create(Medication medication) throws SQLException {
        String query = "INSERT INTO medication_record (generic_name, brand_name) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, medication.getGenericName());
            pstmt.setString(2, medication.getBrandName());
            pstmt.executeUpdate();
        }
    }

    // Read Patient by ID
    public static Medication get(int med_id) throws SQLException {
        String query = "SELECT * FROM medication_record WHERE medication_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, med_id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Medication(
                    rs.getString("generic_name"),
                    rs.getString("brand_name")
                );
            }
        }
        return null;
    }

    // Update Medication
    public static void update(Medication record) throws SQLException {
        String query = "INSERT INTO medication_record (generic_name, brand_name) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, record.getGenericName());
            pstmt.setString(2, record.getBrandName());
            pstmt.executeUpdate();
        }
    }
    
    // Delete Medication by ID
    public static void delete(int id) throws SQLException {
        String query = "DELETE FROM medication_record WHERE medication_ID = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Viewing all medications with the same generic name
    public static List<Medication> getMedGenericList(String generic_name) throws SQLException {
        List<Medication> meds = new ArrayList<>();
        String query = "SELECT * FROM medication_record WHERE generic_name = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, generic_name); // Setting the parameter
            try (ResultSet rs = pstmt.executeQuery()) { 
                while (rs.next()) {
                    Medication medication = new Medication(
                        rs.getString("generic_name"),
                        rs.getString("brand_name")
                    );
                    meds.add(medication);
                }
            }
        }
        return meds;
    }

    // Viewing all medications with the same brand name
    public static List<Medication> getMedBrandList(String brand_name) throws SQLException {
        List<Medication> meds = new ArrayList<>();
        String query = "SELECT * FROM medication_record WHERE brand_name = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, brand_name); // Setting the parameter
            try (ResultSet rs = pstmt.executeQuery()) { 
                while (rs.next()) {
                    Medication medication = new Medication(
                        rs.getString("generic_name"),
                        rs.getString("brand_name")
                    );
                    meds.add(medication);
                }
            }
        }
        return meds;
    }

}
