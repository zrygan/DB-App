package com.source.HospitalDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// import com.source.HospitalDB.*;

public class Reports {
    /***********
     * Diagnostic-specific Annual Health Summary Report
     ***********/
    public String DiagnosticSpecificAnnualHealthSummaryReport(Connection conn, String diagnosis, int year) throws SQLException {
    StringBuilder report = new StringBuilder();
    String query = "SELECT p.sex, p.patient_weight, p.patient_height, p.age "
        + "FROM patients_record p "
        + "INNER JOIN consultation_medical_diagnosis_record cmd ON p.patient_ID = cmd.consultation_ID "
        + "INNER JOIN medical_diagnosis_record md ON cmd.diagnosis_ID = md.diagnosis_ID "
        + "WHERE md.diagnosis_description = ? AND YEAR(p.date_created) = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)){
        stmt.setString(1, diagnosis);
        stmt.setInt(2, year);

        try (ResultSet rs = stmt.executeQuery()) {
            int maleCount = 0;
            int femaleCount = 0;
            double totalWeight = 0;
            double totalHeight = 0;
            int totalAge = 0;
            int patientCount = 0;

            while (rs.next()) {
                String sex = rs.getString("sex");
                double weight = rs.getDouble("patient_weight");
                double height = rs.getDouble("patient_height");
                int age = rs.getInt("age");

                if ("Male".equalsIgnoreCase(sex)) {
                    maleCount++;
                } else if ("Female".equalsIgnoreCase(sex)) {
                    femaleCount++;
                }

                totalWeight += weight;
                totalHeight += height;
                totalAge += age;
                patientCount++;
            }

            double avgWeight = patientCount > 0 ? totalWeight / patientCount : 0;
            double avgHeight = patientCount > 0 ? totalHeight / patientCount : 0;
            double avgAge = patientCount > 0 ? (double) totalAge / patientCount : 0;

            report.append("<html>")
                  .append("<head><title>Diagnosis Report</title></head>")
                  .append("<body>")
                  .append("<h1>").append(diagnosis).append(" patients in ").append(year).append("</h1>")
                  .append("<p>There are ").append(maleCount).append(" male patients and ").append(femaleCount).append(" female patients.</p>")
                  .append("<p>The average weight of the diagnosed patients is: ").append(String.format("%.2f", avgWeight)).append(" kg</p>")
                  .append("<p>The average height of the diagnosed patients is: ").append(String.format("%.2f", avgHeight)).append(" cm</p>")
                  .append("<p>The average age of the diagnosed patients is: ").append(String.format("%.2f", avgAge)).append(" years</p>")
                  .append("</body>")
                  .append("</html>");
        }
    }

    return report.toString();
}

    /********** 
     * Medication-specific Annual Patient Report 
     ***********/
    public String getPatientsForMedication(Connection conn, String medicationName) throws SQLException {
        StringBuilder report = new StringBuilder();
        String query = "SELECT p.patient_firstname, p.patient_lastname, p.age, p.sex, md.diagnosis_description "
                     + "FROM patients_record p "
                     + "INNER JOIN prescription_record pr ON p.patient_ID = pr.patient_ID "
                     + "INNER JOIN medication_record m ON pr.medication_ID = m.medication_ID "
                     + "INNER JOIN consultation_medical_diagnosis_record cmd ON p.patient_ID = cmd.consultation_ID "
                     + "INNER JOIN medical_diagnosis_record md ON cmd.diagnosis_ID = md.diagnosis_ID "
                     + "WHERE m.generic_name = ? OR m.brand_name = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, medicationName);
            stmt.setString(2, medicationName);

            try (ResultSet rs = stmt.executeQuery()) {
                report.append("The patients administered with ").append(medicationName).append(" are:\n\n");

                while (rs.next()) {
                    String firstName = rs.getString("patient_firstname");
                    String lastName = rs.getString("patient_lastname");
                    int age = rs.getInt("age");
                    String sex = rs.getString("sex");
                    String diagnosis = rs.getString("diagnosis_description");

                    report.append(firstName).append(" ").append(lastName)
                          .append(" (").append(age).append(", ").append(sex.charAt(0)).append("): ")
                          .append(diagnosis).append("\n");
                }
            }
        }

        return report.toString();
    }
    

    /***********
     * DailyTPR Report
     ***********/
    public String generateDailyTPRReport(Connection conn, int patientID) throws SQLException {
        StringBuilder report = new StringBuilder();
        
        // Query to fetch patient details and TPR records
        String query = "SELECT p.patient_firstname, p.patient_lastname, p.age, p.sex, "
            + "p.patient_height, p.patient_weight, v.temperature, v.pulse, v.respiratory_rate, v.vital_signs_date "
            + "FROM patients_record p "
            + "INNER JOIN consultation_record c ON p.patient_ID = c.patient_ID "
            + "INNER JOIN vital_signs_record v ON c.vital_signs_ID = v.vital_signs_ID "
            + "WHERE p.patient_ID = ? AND v.vital_signs_date >= CURDATE() - INTERVAL 7 DAY "
            + "ORDER BY v.vital_signs_date DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, patientID);
    
            try (ResultSet rs = stmt.executeQuery()) {
                String firstName = "";
                String lastName = "";
                int age = 0;
                String sex = "";
                double height = 0;
                double weight = 0;
                double totalTemp = 0;
                int totalPulse = 0;
                int totalRespRate = 0;
                int dayCount = 0;
                
                report.append("<html>")
                      .append("<head><title>Daily TPR Monitoring Report</title></head>")
                      .append("<body>")
                      .append("<h1>Daily TPR Monitoring Sheet</h1>");
                
                while (rs.next()) {
                    // Extract patient basic details
                    if (dayCount == 0) {
                        firstName = rs.getString("patient_firstname");
                        lastName = rs.getString("patient_lastname");
                        age = rs.getInt("age");
                        sex = rs.getString("sex");
                        height = rs.getDouble("patient_height");
                        weight = rs.getDouble("patient_weight");
    
                        // Add patient summary
                        report.append("<p><strong>Patient:</strong> ").append(firstName).append(" ").append(lastName).append(" (Age: ").append(age).append(", Sex: ").append(sex).append(")</p>")
                              .append("<p><strong>Height:</strong> ").append(height).append(" cm, <strong>Weight:</strong> ").append(weight).append(" kg</p>");
                    }
    
                    // Collect TPR data
                    double temperature = rs.getDouble("temperature");
                    int pulse = rs.getInt("pulse");
                    int respRate = rs.getInt("respiratory_rate");
                    String date = rs.getString("vital_signs_date");
    
                    totalTemp += temperature;
                    totalPulse += pulse;
                    totalRespRate += respRate;
                    dayCount++;
    
                    // Display current TPR
                    report.append("<p><strong>Date:</strong> ").append(date).append("<br>")
                          .append("<strong>Temperature:</strong> ").append(temperature).append(" °C, ")
                          .append("<strong>Pulse:</strong> ").append(pulse).append(", ")
                          .append("<strong>Respiratory Rate:</strong> ").append(respRate).append("</p>");
                }
    
                // Calculate averages
                if (dayCount > 0) {
                    double avgTemp = totalTemp / dayCount;
                    double avgPulse = totalPulse / dayCount;
                    double avgRespRate = totalRespRate / dayCount;
    
                    // Display averages
                    report.append("<h3>Summary for the Past 7 Days</h3>")
                          .append("<p><strong>Average Temperature:</strong> ").append(String.format("%.2f", avgTemp)).append(" °C</p>")
                          .append("<p><strong>Average Pulse:</strong> ").append(avgPulse).append("</p>")
                          .append("<p><strong>Average Respiratory Rate:</strong> ").append(avgRespRate).append("</p>");
                }
    
                report.append("</body>")
                      .append("</html>");
            }
        }
    
        return report.toString();
    }
    


    /**********
     * Yearly Patient Management Report
     ***********/
    public StringBuilder YearlyPatientManagementReport(Connection conn, int doctorID, int year) throws SQLException {
        StringBuilder report = new StringBuilder();
        String query = "SELECT p.name AS patient_name, c.diagnosis AS medical_diagnosis"
                    + "FROM consultations_record c"
                    + "JOIN patients_record p ON c.patient_ID = p.patient_ID"
                    + "WHERE c.doctor_ID = ? AND YEAR(c.consultation_date) = ?"
                    + "ORDER BY p.name";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Set parameters for the doctor ID and year
            pstmt.setInt(1, doctorID);
            pstmt.setInt(2, year);

            try (ResultSet rs = pstmt.executeQuery()) {
                // Add report header
                report.append("Yearly Patient Management Report\n");
                report.append("Doctor ID: ").append(doctorID).append("\n");
                report.append("Year: ").append(year).append("\n");
                report.append("\nPatients Handled:\n");

                int patientCount = 0; // To keep track of the number of patients

                // Process the result set
                while (rs.next()) {
                    String patientName = rs.getString("patient_name");
                    String diagnosis = rs.getString("medical_diagnosis");

                    report.append("- ").append(patientName).append(" (").append(diagnosis).append(")\n");
                    patientCount++;
                }

                // Add a summary of the number of patients
                if (patientCount > 0) {
                    report.append("\nTotal Patients Handled: ").append(patientCount);
                } else {
                    report.append("No patients were handled by this doctor in ").append(year).append(".");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error generating Yearly Patient Management Report: " + e.getMessage(), e);
        }

        return report;
    }
}
