package com.HospitalDB;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicationDAO {
    // Create a new Medication Record
    public void addMedication(Medication medication) throws SQLException {
        String query = "INSERT INTO medication_record (medication_ID, generic_name, brand_name, date_time, frequency, dosage, doctor_ID, patient_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, medication.getMed_ID());
            pstmt.setString(2, medication.getGeneric());
            pstmt.setString(3, medication.getBrand());
            pstmt.setObject(4, medication.getDate_time()); // FIXME: Note this might break not sure yet tho hehe
            pstmt.setString(5, medication.getFrequency());
            pstmt.setFloat(6, medication.getDosage());
            pstmt.setInt(7, medication.getPres_doc());
            pstmt.setInt(8, medication.getPatient());
            pstmt.executeUpdate();
        }
    }

    // Read Patient by ID
    public Medication getMedication(int med_id) throws SQLException {
        String query = "SELECT * FROM medication_record WHERE medication_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, med_id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Medication(
                    rs.getInt("medication_ID"),
                    rs.getString("generic_name"),
                    rs.getString("brand_name"),
                    rs.getTimestamp("date_time"), 
                    rs.getString("frequency"),
                    rs.getFloat("dosage"),
                    rs.getInt("doctor_ID"),
                    rs.getInt("patient_ID")
                );
            }
        }
        return null;
    }

    // Update Medication
    public void updateMedication(Medication medication) throws SQLException {
        String query = "UPDATE medication_record SET medication_ID = ?, generic_name = ?, brand_name = ?, date_time = ?, frequency = ?, dosage = ?, doctor_ID = ?, doctorId = ?, patient_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, medication.getMed_ID());
            pstmt.setString(2, medication.getGeneric());
            pstmt.setString(3, medication.getBrand());
            pstmt.setObject(4, medication.getDate_time());
            pstmt.setString(5, medication.getFrequency());
            pstmt.setFloat(6, medication.getDosage());
            pstmt.setInt(7, medication.getPres_doc());
            pstmt.setInt(8, medication.getPatient());
            pstmt.executeUpdate();
        }
    }

    // Delete Medication by ID
    public void deletePatient(int id) throws SQLException {
        String query = "DELETE FROM medication_record WHERE medication_ID = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Viewing all medications in the Medication Record with the same generic name
    public List<Medication> getMedGenericList(String generic_name) throws SQLException {
        List<Medication> meds = new ArrayList<>();
        String query = "SELECT * FROM medication_record WHERE generic_name = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);  
            ResultSet rs = pstmt.executeQuery()) {
                pstmt.setString(1, generic_name); //FIXME: NOT SURE KUNG IF THIS IS HOW IT WORKS LETS TEST IT OUT FIRST
                pstmt.executeUpdate();
                while (rs.next()) {
                    Medication medication = new Medication(
                        rs.getInt("medication_ID"),
                        rs.getString("generic_name"),
                        rs.getString("brand_name"),
                        rs.getTimestamp("date_time"), 
                        rs.getString("frequency"),
                        rs.getFloat("dosage"),
                        rs.getInt("doctor_ID"),
                        rs.getInt("patient_ID")
                    );
                    meds.add(medication);
                }
            }
        return meds;
    }

    // Viewing all medications in the Medication Record with the same brand name (e.g., Tylenol, Biogesic, Nizoral).
    public List<Medication> getMedBrandList(String brand_name) throws SQLException {
        List<Medication> meds = new ArrayList<>();
        String query = "SELECT * FROM medication_record WHERE generic_name = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);  
            ResultSet rs = pstmt.executeQuery()) {
                pstmt.setString(1, brand_name); //FIXME: NOT SURE KUNG IF THIS IS HOW IT WORKS LETS TEST IT OUT FIRST
                pstmt.executeUpdate();
                while (rs.next()) {
                    Medication medication = new Medication(
                        rs.getInt("medication_ID"),
                        rs.getString("generic_name"),
                        rs.getString("brand_name"),
                        rs.getTimestamp("date_time"), 
                        rs.getString("frequency"),
                        rs.getFloat("dosage"),
                        rs.getInt("doctor_ID"),
                        rs.getInt("patient_ID")
                    );
                    meds.add(medication);
                }
            }
        return meds;
    }
}