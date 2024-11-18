package com.source.HospitalDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import main.java.com.source.HospitalDB.Classes.Medical;
import main.java.com.source.HospitalDB.Classes.Patient;

public class Reports {
    // ***************************
    // ****************** GANITUEN
    // ***************************

    // "MAIN" FUNCTION
    public static String DailyTPR(int patientId, Connection conn) throws SQLException {
        // step 1: get basic patient information
        Patient patient = getPatientInfo(patientId, conn);

        // step 2: get  the patient's medical records (Temperature, Pulse, Respiratory Rate) for the last 7 days
        List<Medical> medicalRecords = getMedicalRecords(patientId, conn);

        // check if the patient or records were not found
        if (patient == null || medicalRecords.isEmpty()) {
            return "No medical records found for patient ID: " + patientId;
        }

        // step 3: get the current (latest) TPR data
        Medical currentRecord = medicalRecords.get(0);
        float currentTemperature = currentRecord.getTemperature();
        int currentPulse = currentRecord.getPulse();
        int currentRespiratoryRate = currentRecord.getPulse();  // assuming pulse is used as placeholder for Respiratory Rate
        
        // step 4: calculate average TPR for the past 7 days
        double avgTemperature = calculateAverageTemperature(medicalRecords);
        double avgPulse = calculateAveragePulse(medicalRecords);
        double avgRespiratoryRate = calculateAverageRespiratoryRate(medicalRecords);

        // step 5: format and generate the report string
        DecimalFormat df = new DecimalFormat("#.##");
        String report = generateReport(patient, currentTemperature, currentPulse, currentRespiratoryRate,
                                       avgTemperature, avgPulse, avgRespiratoryRate, df);

        return report;
    }

    // HELPER FUNCTIONS
    // getter of patient details from the DB
    private static Patient getPatientInfo(int patientId, Connection conn) throws SQLException {
        String query = "SELECT name, age, sex, height, weight, religion, status FROM patients_record WHERE patient_ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, patientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Patient(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDate("birthDate").toLocalDate(),
                        rs.getString("sex"),
                        rs.getDouble("height"),
                        rs.getDouble("weight"),
                        rs.getString("religion"),
                        null,  // assuming Doctor information is set elsewhere
                                      // FIXME: I don't know what to do here yet @jazjimenez need help here
                        rs.getString("status")
                    );
                }
            }
        }
        return null;  
    }

    // get the last 7 days of medical records for the patient
    private static List<Medical> getMedicalRecords(int patientId, Connection conn) throws SQLException {
        String query = "SELECT record_ID, temperature, pulse, respiratory_rate, date_time " +
                       "FROM medical_record WHERE patient_ID = ? AND date_time >= CURDATE() - INTERVAL 7 DAY " +
                       "ORDER BY date_time DESC";
        List<Medical> medicalRecords = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, patientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    medicalRecords.add(new Medical(
                        rs.getInt("record_ID"),
                        patientId,
                        null,  
                        null,
                        null,
                        rs.getFloat("temperature"),
                        rs.getInt("pulse"),
                        0,  
                        0,  
                        0,  
                        rs.getTimestamp("date_time").toLocalDateTime()
                    ));
                }
            }
        }
        return medicalRecords;
    }

    // average temperature from the last 7 days
    private static double calculateAverageTemperature(List<Medical> medicalRecords) {
        return medicalRecords.stream()
                             .mapToDouble(Medical::getTemperature)
                             .average()
                             .orElse(0.0);
    }

    // average pulse from the last 7 days
    private static double calculateAveragePulse(List<Medical> medicalRecords) {
        return medicalRecords.stream()
                             .mapToInt(Medical::getPulse)
                             .average()
                             .orElse(0.0);
    }

    // average rr  from the last 7 days
    private static double calculateAverageRespiratoryRate(List<Medical> medicalRecords) {
        return medicalRecords.stream()
                             .mapToInt(Medical::getPulse)  // Assuming pulse as a proxy for respiratory rate
                             .average()
                             .orElse(0.0);
    }

    // report as a string 
    private static String generateReport(Patient patient, float currentTemperature, int currentPulse, int currentRespiratoryRate,
                                     double avgTemperature, double avgPulse, double avgRespiratoryRate, DecimalFormat df) {
        StringBuilder report = new StringBuilder();

        // Header: Patient Information and Current TPR
        report.append("Daily TPR Monitoring Sheet\n")
            .append("Date: ").append(LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM d, yyyy"))).append("\n")
            .append("Patient: ").append(patient.getName()).append("\n")
            .append("Age: ").append(patient.getAge()).append(", Sex: ").append(patient.getSex()).append("\n")
            .append("Height: ").append(patient.getHeight()).append(" cm, Weight: ").append(patient.getWeight()).append(" kg\n")
            .append("\n")
            .append("Current Data—\n")
            .append("Temperature: ").append(currentTemperature).append(" °C, ")
            .append("Pulse: ").append(currentPulse).append(", ")
            .append("Respiratory Rate: ").append(currentRespiratoryRate).append("\n")
            .append("\n")
            .append("Average for the past 7 days—\n")
            .append("Temperature: ").append(df.format(avgTemperature)).append(" °C, ")
            .append("Pulse: ").append(df.format(avgPulse)).append(", ")
            .append("Respiratory Rate: ").append(df.format(avgRespiratoryRate)).append("\n");

        return report.toString();
    }
}
