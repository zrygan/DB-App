package com.HospitalDB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DoctorsDAO {

    public void addDoctors(Doctors doctors) throws SQLException {
        String query = "INSERT INTO doctors_record (name, specialization, doctorId, status, phoneNumber, email, medicalHierarchy) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, doctors.getName());
            pstmt.setString(2, doctors.getSpecialization());
            pstmt.setString(3, doctors.getDoctorId());
            pstmt.setString(4, doctors.getStatus());
            pstmt.setString(5, doctors.getPhoneNumber());
            pstmt.setString(6, doctors.getEmail());
            pstmt.setString(7, doctors.getMedicalHierarchy());
            pstmt.executeUpdate();
        }
    }

        // Read Doctor by ID
    public Doctors getDoctors(string doctorId) throws SQLException {
        String query = "SELECT * FROM doctors_record WHERE doctorId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, doctorId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Doctors(
                    rs.getString("name"),
                    rs.getString("specialization"),
                    rs.getString("doctorId"),
                    rs.getString("status"), 
                    rs.getString("phoneNumber"),
                    rs.getString("email"),
                    rs.getString("medicalHierarchy")
                    
                );
            }
        }
        return null;
        
    }


        // Update Doctors
    public void updateDoctors(Doctors doctors) throws SQLException {
        String query = "UPDATE doctors_record SET name = ?, specialization = ?, status = ?, phoneNumber = ?, email = ?, medicalHierarchy = ? WHERE doctorId = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, doctors.getName());
            pstmt.setString(2, doctors.getSpecialization());
            pstmt.setString(7, doctors.getMedicalHierarchy());
            pstmt.setString(4, doctors.getStatus());
            pstmt.setString(5, doctors.getPhoneNumber());
            pstmt.setString(6, doctors.getEmail());
            pstmt.setString(3, doctors.getDoctorId());// Primary key to identify the record
            pstmt.executeUpdate();
        }
    }

        // Delete Medication by ID
    public void deleteMedication(int id) throws SQLException {
        String query = "DELETE FROM medication_record WHERE doctorId = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

        // Viewing all doctors in the Doctors' Record with the same Status
    public List<Doctors> getDoctorSameStatus(String doctorSameStatus) throws SQLException {
        List<Doctors> stat = new ArrayList<>();
        String query = "SELECT * FROM doctors_record WHERE status = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, doctorSameStatus); 
            try (ResultSet rs = pstmt.executeQuery()) { 
                while (rs.next()) {
                    Doctors doctors = new Doctors(
                    rs.getString("name"),
                    rs.getString("specialization"),
                    rs.getString("doctorId"),
                    rs.getString("status"), 
                    rs.getString("phoneNumber"),
                    rs.getString("email"),
                    rs.getString("medicalHierarchy")
                    );
                 stat.add(doctors);
                }
            }
        }
        return stat;
    }

            // Viewing all doctors in the Doctors' Record with the same Specialization
    public List<Doctors> getDoctorSameSpec(String doctorSameSpec) throws SQLException {
        List<Doctors> spec = new ArrayList<>();
        String query = "SELECT * FROM doctors_record WHERE specialization = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, doctorSameStatus); 
            try (ResultSet rs = pstmt.executeQuery()) { 
                while (rs.next()) {
                    Doctors doctors = new Doctors(
                    rs.getString("name"),
                    rs.getString("specialization"),
                    rs.getString("doctorId"),
                    rs.getString("status"), 
                    rs.getString("phoneNumber"),
                    rs.getString("email"),
                    rs.getString("medicalHierarchy")
                    );
                 spec.add(doctors);
                }
            }
        }
        return spec;
    }

            // Viewing all doctors in the Doctors' Record with the same Medical Hierarchy
    public List<Doctors> getDoctorSameMH(String doctorSameMH) throws SQLException {
        List<Doctors> med_hi = new ArrayList<>();
        String query = "SELECT * FROM doctors_record WHERE medicalHierarchy = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, doctorSameStatus); 
            try (ResultSet rs = pstmt.executeQuery()) { 
                while (rs.next()) {
                    Doctors doctors = new Doctors(
                    rs.getString("name"),
                    rs.getString("specialization"),
                    rs.getString("doctorId"),
                    rs.getString("status"), 
                    rs.getString("phoneNumber"),
                    rs.getString("email"),
                    rs.getString("medicalHierarchy")
                    );
                 med_hi.add(doctors);
                }
            }
        }
        return med_hi;
    }

}