package com.source.HospitalDB.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.source.HospitalDB.Classes.Patient;
import com.source.HospitalDB.DBConnection;

public class PatientDAO {
    // Create a new Patient record
    public static void add(Patient patient) throws SQLException {
        String query = "INSERT INTO patients_record (patient_firstname, patient_lastname, age, birth_date, sex, patient_height,"
                + "patient_weight, religion, date_created)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, patient.getFirstname());
            pstmt.setString(2, patient.getLastname());
            pstmt.setInt(3, patient.getAge());
            pstmt.setDate(4, patient.getBirthDate());
            pstmt.setString(5, patient.getSex());
            pstmt.setBigDecimal(6, patient.getHeight());
            pstmt.setBigDecimal(7, patient.getWeight());
            pstmt.setString(8, patient.getReligion());
            pstmt.setTimestamp(9, patient.getDateCreated());

            pstmt.executeUpdate();
        }
    }

    // Delete a patient record
    public static void del(int id) throws SQLException {
        String query = "DELETE FROM patients_record WHERE patient_ID = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Viewing a patient record of a specific patient
    public static Patient getFromID(int id) throws SQLException {
        String query = "SELECT * FROM patients_record WHERE patient_ID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Patient(
                            rs.getInt("patient_ID"),
                            rs.getString("patient_firstname"),
                            rs.getString("patient_lastname"),
                            rs.getDate("birth_date"),
                            rs.getString("sex"),
                            rs.getBigDecimal("patient_height"),
                            rs.getBigDecimal("patient_weight"),
                            rs.getString("religion"));
                }
            }
        }
        return null;
    }

    // FIXME: no getAll

    // Method to get a comprehensive summary of all medical records and return it as
    // a string
    // FIXME: untested, needs corrections
    public static String getSummary() throws SQLException {
        StringBuilder summary = new StringBuilder();

        // Define your query with all the statistics, excluding 'status'
        String query = "SELECT " +
            "    COUNT(*) AS total_records, " +                   // Total records
            "    AVG(age) AS avg_age, " +                        // Average age
            "    MIN(age) AS min_age, " +                        // Minimum age
            "    MAX(age) AS max_age, " +                        // Maximum age
            "    AVG(patient_height) AS avg_height, " +          // Average height
            "    MIN(patient_height) AS min_height, " +          // Minimum height
            "    MAX(patient_height) AS max_height, " +          // Maximum height
            "    AVG(patient_weight) AS avg_weight, " +          // Average weight
            "    MIN(patient_weight) AS min_weight, " +          // Minimum weight
            "    MAX(patient_weight) AS max_weight, " +          // Maximum weight
            "    COUNT(DISTINCT sex) AS distinct_sex, " +        // Distinct sex
            "    (SELECT sex FROM patients_record GROUP BY sex ORDER BY COUNT(*) DESC LIMIT 1) AS most_common_sex, " + // Most common sex
            "    COUNT(DISTINCT religion) AS distinct_religion, " +  // Distinct religion
            "    (SELECT religion FROM patients_record GROUP BY religion ORDER BY COUNT(*) DESC LIMIT 1) AS most_common_religion, " + // Most common religion
            "    MIN(date_created) AS earliest_record, " +      // Earliest record created
            "    MAX(date_created) AS latest_record " +         // Latest record created
            "FROM patients_record;";

        try (Connection conn = DBConnection.getConnection()) {
            // Prepare the statement
            try (PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {

                // Process the result set
                if (rs.next()) {
                    // Append the summary based on the query results
                    summary.append("Total records: ").append(rs.getInt("total_records")).append("<br>")
                        .append("Average age: ").append(rs.getDouble("avg_age")).append("<br>")
                        .append("Min age: ").append(rs.getInt("min_age")).append("<br>")
                        .append("Max age: ").append(rs.getInt("max_age")).append("<br>")
                        .append("Average height: ").append(rs.getDouble("avg_height")).append("<br>")
                        .append("Min height: ").append(rs.getDouble("min_height")).append("<br>")
                        .append("Max height: ").append(rs.getDouble("max_height")).append("<br>")
                        .append("Average weight: ").append(rs.getDouble("avg_weight")).append("<br>")
                        .append("Min weight: ").append(rs.getDouble("min_weight")).append("<br>")
                        .append("Max weight: ").append(rs.getDouble("max_weight")).append("<br>")
                        .append("Distinct sexes: ").append(rs.getInt("distinct_sex")).append("<br>")
                        .append("Most common sex: ").append(rs.getString("most_common_sex")).append("<br>")
                        .append("Distinct religions: ").append(rs.getInt("distinct_religion")).append("<br>")
                        .append("Most common religion: ").append(rs.getString("most_common_religion")).append("<br>")
                        .append("Earliest record: ").append(rs.getDate("earliest_record")).append("<br>")
                        .append("Latest record: ").append(rs.getDate("latest_record")).append("<br>");
                }
            }
        }

        return summary.toString();
    }



    public static int getFromNameBDay(String firstname, String lastname, java.sql.Date birthDate) throws SQLException {
        String query = "SELECT patient_ID FROM patients_record WHERE patient_firstname = ? AND patient_lastname = ? AND birth_date = ?";
            try (Connection conn = DBConnection.getConnection();
                    PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, firstname);
                preparedStatement.setString(2, lastname);
                preparedStatement.setDate(3, birthDate);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("patient_ID");
                    } else {
                        return 0;
                    }
                }
        }
    }
    
    public static int getHighestPatientID() throws SQLException {
    String query = "SELECT MAX(patient_ID) AS max_patient_id FROM patients_record";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {
        
        if (rs.next()) {
            return rs.getInt("max_patient_id");
        }
    }

    return 0;
    }
    
    public static Patient getByName(String firstName, String lastName) throws SQLException {
    String query = "SELECT * FROM patients_record WHERE patient_firstname = ? AND patient_lastname = ? LIMIT 1";
    
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                // Assuming you have a constructor or setter to map the ResultSet to a Patient object
                return new Patient(
                            rs.getInt("patient_ID"),
                            rs.getString("patient_firstname"),
                            rs.getString("patient_lastname"),
                            rs.getDate("birth_date"),
                            rs.getString("sex"),
                            rs.getBigDecimal("patient_height"),
                            rs.getBigDecimal("patient_weight"),
                            rs.getString("religion"));
                }
            }
        return null;
    }

    // get by patient id
    public static Patient getPatient(int patientId) throws SQLException {
        String query = "SELECT * FROM patients_record WHERE patient_ID = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Patient(
                            rs.getInt("patient_ID"),
                            rs.getString("patient_firstname"),
                            rs.getString("patient_lastname"),
                            rs.getDate("birth_date"),
                            rs.getString("sex"),
                            rs.getBigDecimal("patient_height"),
                            rs.getBigDecimal("patient_weight"),
                            rs.getString("religion"));
                }
            }
        }
        return null;
    }
}