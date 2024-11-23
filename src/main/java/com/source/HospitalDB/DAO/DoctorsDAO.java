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

    public static void add(Doctors doctors) throws SQLException {
        String query = "INSERT INTO doctors_record (doctor_firstname, doctor_lastname, specialization, phoneNumber, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, doctors.getFirstname());
            pstmt.setString(2, doctors.getLastname());
            pstmt.setString(3, doctors.getSpecialization());
            pstmt.setString(4, doctors.getPhoneNumber());
            pstmt.setString(5, doctors.getEmail());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error adding doctor " + e.getMessage());
            throw e;
        }
    }

    // Read Doctor by ID
    public static Doctors getFromID(int doctor_ID) throws SQLException {
        String query = "SELECT * FROM doctors_record WHERE doctor_ID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, doctor_ID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Doctors(
                        rs.getString("doctor_firstname"),
                        rs.getString("doctor_lastname"),
                        rs.getString("specialization"),
                        rs.getString("phoneNumber"),
                        rs.getString("email"));
            }
        }
        return null;

    }

    // Delete Doctor by ID
    public static void del(int id) throws SQLException {
        String query = "DELETE FROM doctors_record WHERE doctor_ID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Viewing all doctors in the Doctors' Record with the same Specialization
    public static List<Doctors> getSpecs(String doctorSameSpec) throws SQLException {
        List<Doctors> spec = new ArrayList<>();
        String query = "SELECT * FROM doctors_record WHERE specialization = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, doctorSameSpec);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Doctors doctors = new Doctors(
                            rs.getString("doctor_firstname"),
                            rs.getString("doctor_lastname"),
                            rs.getString("specialization"),
                            rs.getString("phoneNumber"),
                            rs.getString("email"));
                    spec.add(doctors);
                }
            }
        }
        return spec;
    }

    public static int getFromName(String doctorName) throws SQLException {
        String query = "SELECT doctor_ID FROM doctors_record WHERE doctor_name = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, doctorName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("doctor_ID");
                }
            }
        }
        return 0;
    }

}