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
    public StringBuilder DiagnosticSpecificAnnualHealthSummaryReport(Connection conn, String diagnosis, int year) throws SQLException {
    StringBuilder report = new StringBuilder();
    String query = "SELECT p.Patient_ID, p.Name, p.Age, p.Sex, p.Religion,"
                + "m.Reason_for_Admission, m.Pulse, m.Respiratory_Rate,"
                + "m.Blood_Pressure, m.SPO2, m.Date_Taken"
                + "FROM Medical m"
                + "INNER JOIN Patient p ON m.Patient_ID = p.Patient_ID"
                + "INNER JOIN Medical_Chief_Complaint mc ON m.Medical_ID = mc.Medical_ID"
                + "WHERE mc.Medical_Diagnosis = ? AND YEAR(m.Date_Taken) = ?;";

    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, diagnosis);
        stmt.setInt(2, year);

        try (ResultSet rs = stmt.executeQuery()) {
            report.append("Diagnostic-Specific Annual Health Summary Report\n");
            report.append("Diagnosis: ").append(diagnosis).append("\nYear: ").append(year).append("\n\n");

            while (rs.next()) {
                report.append("Patient ID: ").append(rs.getInt("Patient_ID")).append("\n");
                report.append("Name: ").append(rs.getString("Name")).append("\n");
                report.append("Age: ").append(rs.getInt("Age")).append("\n");
                report.append("Sex: ").append(rs.getString("Sex")).append("\n");
                report.append("Religion: ").append(rs.getString("Religion")).append("\n");
                report.append("Reason for Admission: ").append(rs.getString("Reason_for_Admission")).append("\n");
                report.append("Pulse: ").append(rs.getInt("Pulse")).append("\n");
                report.append("Respiratory Rate: ").append(rs.getInt("Respiratory_Rate")).append("\n");
                report.append("Blood Pressure: ").append(rs.getString("Blood_Pressure")).append("\n");
                report.append("SPO2: ").append(rs.getInt("SPO2")).append("\n");
                report.append("Date Taken: ").append(rs.getDate("Date_Taken")).append("\n");
                report.append("--------------------------\n");
            }
        }
    }
    return report;
}

    /********** 
     * Medication-specific Annual Patient Report 
     ***********/
    public StringBuilder MedicationSpecificAnnualPatientReport(Connection conn, String medicationName, int year) throws SQLException {
        StringBuilder report = new StringBuilder();
        String query = "SELECT p.Patient_ID, p.Name, p.Age, p.Sex, p.Religion,"
                    + "pr.Date_Given, pr.Frequency, pr.Dosage, d.Name AS Doctor_Name"
                    + "FROM Prescription pr"
                    + "INNER JOIN Patient p ON pr.Patient_ID = p.Patient_ID"
                    + "INNER JOIN Doctor d ON pr.Doctor_ID = d.Doctor_ID"
                    + "INNER JOIN Medication m ON pr.Medication_ID = m.Medication_ID"
                    + "WHERE (m.Generic_Name = ? OR m.Brand_Name = ?) AND YEAR(pr.Date_Given) = ?";
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, medicationName);
            stmt.setString(2, medicationName);
            stmt.setInt(3, year);
    
            try (ResultSet rs = stmt.executeQuery()) {
                report.append("Medication-Specific Annual Patient Report\n");
                report.append("Medication: ").append(medicationName).append("\nYear: ").append(year).append("\n\n");
    
                while (rs.next()) {
                    report.append("Patient ID: ").append(rs.getInt("Patient_ID")).append("\n");
                    report.append("Name: ").append(rs.getString("Name")).append("\n");
                    report.append("Age: ").append(rs.getInt("Age")).append("\n");
                    report.append("Sex: ").append(rs.getString("Sex")).append("\n");
                    report.append("Religion: ").append(rs.getString("Religion")).append("\n");
                    report.append("Date Given: ").append(rs.getDate("Date_Given")).append("\n");
                    report.append("Frequency: ").append(rs.getInt("Frequency")).append("\n");
                    report.append("Dosage: ").append(rs.getBigDecimal("Dosage")).append("\n");
                    report.append("Prescribed by Doctor: ").append(rs.getString("Doctor_Name")).append("\n");
                    report.append("--------------------------\n");
                }
            }
        }
        return report;
    }
    

/***********
 * DailyTPR Report
 ***********/
public static StringBuilder DailyTPR(Connection conn, int patientID) throws SQLException {
    StringBuilder report = new StringBuilder();

    // Query to retrieve patient's basic information
    String patientInfoQuery = "SELECT name, age, sex, height, weight"
                            + "FROM patients_record" 
                            + "WHERE patient_ID = ?";

    // Query to retrieve current TPR and past 7 days data
    String tprDataQuery = "SELECT tpr_date, temperature, pulse, respiratory_rate"
                        + "FROM tpr_record" 
                        + "WHERE patient_ID = ?"
                        + "ORDER BY tpr_date DESC" 
                        + "LIMIT 7";

    try {
        // Step 1: Retrieve Patient's Basic Information
        try (PreparedStatement patientInfoStmt = conn.prepareStatement(patientInfoQuery)) {
            patientInfoStmt.setInt(1, patientID);
            try (ResultSet rs = patientInfoStmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String sex = rs.getString("sex");
                    double height = rs.getDouble("height");
                    double weight = rs.getDouble("weight");

                    report.append("Daily TPR Monitoring Sheet\n");
                    report.append(String.format("Patient: %s. Age: %d, Sex: %s, Height: %.1f cm, Weight: %.1f kg.\n", 
                            name, age, sex, height, weight));
                } else {
                    report.append("Patient not found.\n");
                    return report;
                }
            }
        }

        // Step 2: Retrieve TPR Data for the Past 7 Days
        double totalTemp = 0.0, totalPulse = 0, totalRespRate = 0;
        int dataCount = 0;

        try (PreparedStatement tprDataStmt = conn.prepareStatement(tprDataQuery)) {
            tprDataStmt.setInt(1, patientID);
            try (ResultSet rs = tprDataStmt.executeQuery()) {
                report.append("\nCurrent—\n");
                while (rs.next()) {
                    double temperature = rs.getDouble("temperature");
                    int pulse = rs.getInt("pulse");
                    int respiratoryRate = rs.getInt("respiratory_rate");
                    java.sql.Date tprDate = rs.getDate("tpr_date");

                    // Add current day's data
                    if (dataCount == 0) {
                        report.append(String.format("Date: %s, Temperature: %.1f C, Pulse: %d, Respiratory Rate: %d.\n",
                                tprDate, temperature, pulse, respiratoryRate));
                    }

                    // Accumulate data for averaging
                    totalTemp += temperature;
                    totalPulse += pulse;
                    totalRespRate += respiratoryRate;
                    dataCount++;
                }
            }
        }

        // Step 3: Calculate and Append Averages for the Past 7 Days
        if (dataCount > 0) {
            double avgTemp = totalTemp / dataCount;
            double avgPulse = totalPulse / dataCount;
            double avgRespRate = totalRespRate / dataCount;

            report.append(String.format("\nAverage for the past %d days—\n", dataCount));
            report.append(String.format("Temperature: %.1f C, Pulse: %.1f, Respiratory Rate: %.1f.\n", 
                    avgTemp, avgPulse, avgRespRate));
        } else {
            report.append("\nNo TPR data available for the past 7 days.\n");
        }

    } catch (SQLException e) {
        throw new SQLException("Error generating Daily TPR report: " + e.getMessage(), e);
    }

    return report;
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
