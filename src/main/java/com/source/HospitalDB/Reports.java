package com.source.HospitalDB;

import java.sql.Connection;
import java.sql.SQLException;

// import com.source.HospitalDB.*;

public class Reports {
    /***********
     * Diagnostic-specific Annual Health Summary Report
     ***********/
    public StringBuilder DiagnosticSpecificAnnualHealthSummaryReport(Connection conn, String diagnosis) throws SQLException { //Fix me: Add necessary parameters
        StringBuilder report = null; // tempvalue, can be deleted
        // add methods here

        return report;
    } 
    /********** 
     * Medication-specific Annual Patient Report 
     ***********/
    public StringBuilder MedicationSpecificAnnualPatientReport(Connection conn, String medication_name) throws SQLException { //Fix me: Add necessary parameters
        // medication_name can be the BRAND name or the GENERIC name
        StringBuilder report = null; // tempvalue, can be deleted
        // add methods here

        return report;
    } 

/***********
 * DailyTPR Report
 ***********/
public static StringBuilder DailyTPR(Connection conn, int patientID) throws SQLException {
    StringBuilder report = new StringBuilder();

    // Query to retrieve patient's basic information
    String patientInfoQuery = """
        SELECT name, age, sex, height, weight 
        FROM patients_record 
        WHERE patient_ID = ?
    """;

    // Query to retrieve current TPR and past 7 days data
    String tprDataQuery = """
        SELECT tpr_date, temperature, pulse, respiratory_rate 
        FROM tpr_record 
        WHERE patient_ID = ? 
        ORDER BY tpr_date DESC 
        LIMIT 7
    """;

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
    String query = """
        SELECT p.name AS patient_name, c.diagnosis AS medical_diagnosis
        FROM consultations_record c
        JOIN patients_record p ON c.patient_ID = p.patient_ID
        WHERE c.doctor_ID = ? AND YEAR(c.consultation_date) = ?
        ORDER BY p.name
    """;

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
