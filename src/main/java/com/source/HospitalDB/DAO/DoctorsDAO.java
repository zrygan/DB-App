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

    public static int getFromName(String firstname, String lastname) throws SQLException {
        String query = "SELECT doctor_ID FROM doctors_record WHERE doctor_firstname = ? AND doctor_lastname = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("doctor_ID");
                } else {
                    return 0;
                }
            }
        
        }
    }
    
    // get doctor id by phone number
    public static int getDoctorIDByPhoneNumber(String phoneNumber) throws SQLException {
        String query = "SELECT doctor_ID FROM doctors_record WHERE phoneNumber = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, phoneNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("doctor_ID");
                }
            }
        }
        return 0;
    }

    // get doctor id by email
    public static int getDoctorIDByEmail(String email) throws SQLException {
        String query = "SELECT doctor_ID FROM doctors_record WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("doctor_ID");
                }
            }
        }
        return 0;
    }

    // get doctor id by getting all the other attributes
    public static int getDoctorID(String firstname, String lastname, String specialization, String phoneNumber, String email) throws SQLException {
        String query = "SELECT doctor_ID FROM doctors_record WHERE doctor_firstname = ? AND doctor_lastname = ? AND specialization = ? AND phoneNumber = ? AND email = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            stmt.setString(3, specialization);
            stmt.setString(4, phoneNumber);
            stmt.setString(5, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("doctor_ID");
                }
            }
        }
        return 0;
    }



    // return list of doctors with the same specialization
    public static List<Doctors> getDoctorsBySpecialization(String specialization) throws SQLException {
        String query = "SELECT * FROM doctors_record WHERE specialization = ?";
        List<Doctors> doctors = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, specialization);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    doctors.add(new Doctors(
                        rs.getInt("doctor_ID"),
                        rs.getString("doctor_firstname"),
                        rs.getString("doctor_lastname"),
                        rs.getString("specialization"),
                        rs.getString("phoneNumber"),
                        rs.getString("email")
                    ));
                }
            }
        }
        return doctors;
    }

    // get object with id
    public static Doctors getDoctorByID(int doctorID) throws SQLException {
        String query = "SELECT * FROM doctors_record WHERE doctor_ID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, doctorID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Doctors(
                        rs.getInt("doctor_ID"),
                        rs.getString("doctor_firstname"),
                        rs.getString("doctor_lastname"),
                        rs.getString("specialization"),
                        rs.getString("phoneNumber"),
                        rs.getString("email")
                    );
                }
            }
        }
        return null;
    }
}