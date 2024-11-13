package com.source.HospitalDB;

// import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DoctorsDAO {

    public static void addDoctors(Doctors doctors) throws SQLException {
        String query = "INSERT INTO doctors_record (name, specialization, doctor_ID, phoneNumber, email, medicalHierarchy) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, doctors.getName());
            pstmt.setString(2, doctors.getSpecialization());
            pstmt.setString(3, doctors.getDoctorId());
            pstmt.setString(4, doctors.getPhoneNumber());
            pstmt.setString(5, doctors.getEmail());
            pstmt.setString(6, doctors.getMedicalHierarchy());
            pstmt.executeUpdate();
            
            // FIXME: maybe we need to close pstmt and conn
            // conn.close();
            // pstmt.close();
        } catch (SQLException e){
            System.err.println("Error adding doctor " + e.getMessage());
            throw e;
        }
    }

        // Read Doctor by ID
    public Doctors getDoctors(int doctor_ID) throws SQLException {
        String query = "SELECT * FROM doctors_record WHERE doctor_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, doctor_ID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Doctors(
                    rs.getString("name"),
                    rs.getString("specialization"),
                    rs.getString("doctor_ID"),
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
        String query = "UPDATE doctors_record SET name = ?, specialization = ?, phoneNumber = ?, email = ?, medicalHierarchy = ? WHERE doctor_ID = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, doctors.getName());
            pstmt.setString(2, doctors.getSpecialization());
            pstmt.setString(3, doctors.getDoctorId());// Primary key to identify the record
            pstmt.setString(4, doctors.getPhoneNumber());
            pstmt.setString(5, doctors.getEmail());
            pstmt.setString(6, doctors.getMedicalHierarchy());

            pstmt.executeUpdate();
        }
    }

        // Delete Medication by ID
    public void deleteMedication(int id) throws SQLException {
        String query = "DELETE FROM medication_record WHERE doctor_ID = ?";
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
                    rs.getString("doctor_ID"),
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

            pstmt.setString(1, doctorSameSpec); 
            try (ResultSet rs = pstmt.executeQuery()) { 
                while (rs.next()) {
                    Doctors doctors = new Doctors(
                    rs.getString("name"),
                    rs.getString("specialization"),
                    rs.getString("doctor_ID"),
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

            pstmt.setString(1, doctorSameMH); 
            try (ResultSet rs = pstmt.executeQuery()) { 
                while (rs.next()) {
                    Doctors doctors = new Doctors(
                    rs.getString("name"),
                    rs.getString("specialization"),
                    rs.getString("doctor_ID"),
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