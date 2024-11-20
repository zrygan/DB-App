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
    //The patient’s basic information and daily temperature, pulse, and respiratory rate data. Furthermore, a summary of these data for the past 7 days
    public static StringBuilder DailyTPR(Connection conn, int patientID) throws SQLException {
        StringBuilder report = null;
        
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
