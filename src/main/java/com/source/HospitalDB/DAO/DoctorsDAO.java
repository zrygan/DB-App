package com.source.HospitalDB.DAO;

// import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.source.HospitalDB.Classes.Doctors;
import com.source.HospitalDB.DBConnection;

public class DoctorsDAO {

    public static void create(Doctors doctors) throws SQLException {
        String query = "INSERT INTO doctors_record (doctor_ID, doctor_name, specialization, phoneNumber, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, doctors.getDoctorId());
            pstmt.setString(2, doctors.getName());
            pstmt.setString(3, doctors.getSpecialization());
            pstmt.setString(4, doctors.getPhoneNumber());
            pstmt.setString(5, doctors.getEmail());
            pstmt.executeUpdate();

        } catch (SQLException e){
            System.err.println("Error adding doctor " + e.getMessage());
            throw e;
        }
    }

        // Read Doctor by ID
    public static Doctors get(int doctor_ID) throws SQLException {
        String query = "SELECT * FROM doctors_record WHERE doctor_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, doctor_ID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Doctors(
                    rs.getString("doctor_name"),
                    rs.getString("specialization"),
                    rs.getString("phoneNumber"),
                    rs.getString("email")            
                );
            }
        }
        return null;
        
    }

        // Update Doctors
    public static void update(Doctors doctors) throws SQLException {
        String query = "UPDATE doctors_record SET doctor_name = ?, specialization = ?, phoneNumber = ?, email = ?, WHERE doctor_ID = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, doctors.getName());
            pstmt.setString(2, doctors.getSpecialization());
            pstmt.setString(3, doctors.getPhoneNumber());
            pstmt.setString(4, doctors.getEmail());
            pstmt.setInt(5, doctors.getDoctorId());

            pstmt.executeUpdate();
        }
    }

        // Delete Doctor by ID
    public static void delete(int id) throws SQLException {
        String query = "DELETE FROM doctors_record WHERE doctor_ID = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Viewing all doctors in the Doctors' Record with the same Specialization
    public static List<Doctors> getDoctorSameSpec(String doctorSameSpec) throws SQLException {
        List<Doctors> spec = new ArrayList<>();
        String query = "SELECT * FROM doctors_record WHERE specialization = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, doctorSameSpec); 
            try (ResultSet rs = pstmt.executeQuery()) { 
                while (rs.next()) {
                    Doctors doctors = new Doctors(
                        rs.getString("doctor_name"),
                        rs.getString("specialization"),
                        rs.getString("phoneNumber"),
                        rs.getString("email")    
                    );
                 spec.add(doctors);
                }
            }
        }
        return spec;
    }

}