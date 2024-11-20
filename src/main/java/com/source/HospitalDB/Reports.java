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
    public StringBuilder YearlyPatientManagementReport(Connection conn, int doctorID, int year) throws SQLException { //Fix me: Add necessary parameters
        StringBuilder report = null; // tempvalue, can be deleted
        // add methods here

        return report;
    } 
}
