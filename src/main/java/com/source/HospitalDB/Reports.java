package com.source.HospitalDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reports {

    /**
     * Diagnostic-specific Annual Health Summary Report
     *
     * @param diagnosis the diagnosis description
     * @param year      the year for the report
     * @return the generated report as a String
     * @throws SQLException if a database access error occurs
     */
    public static String DiagnosticSpecificAnnualHealthSummaryReport(String diagnosis, int year) throws SQLException {
        // StringBuilder for report content
        StringBuilder report = new StringBuilder();

        // Corrected query to include proper joins
        String query = "SELECT p.sex, p.patient_weight, p.patient_height, p.age "
                + "FROM patients_record p "
                + "INNER JOIN consultation_record c ON p.patient_ID = c.patient_ID "
                + "INNER JOIN consultation_medical_diagnosis_record cmd ON c.consultation_ID = cmd.consultation_ID "
                + "INNER JOIN medical_diagnosis_record md ON cmd.diagnosis_ID = md.diagnosis_ID "
                + "WHERE md.diagnosis_description = ? AND YEAR(c.consultation_date) = ?";

        // Initialize database resources
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set query parameters
            stmt.setString(1, diagnosis);
            stmt.setInt(2, year);

            // Execute query and process results
            try (ResultSet rs = stmt.executeQuery()) {
                int maleCount = 0, femaleCount = 0, totalAge = 0, patientCount = 0;
                double totalWeight = 0.0, totalHeight = 0.0;

                while (rs.next()) {
                    // Retrieve data
                    String sex = rs.getString("sex");
                    double weight = rs.getDouble("patient_weight");
                    double height = rs.getDouble("patient_height");
                    int age = rs.getInt("age");

                    // Update counts and totals
                    if ("M".equalsIgnoreCase(sex)) maleCount++;
                    if ("F".equalsIgnoreCase(sex)) femaleCount++;
                    totalWeight += weight;
                    totalHeight += height;
                    totalAge += age;
                    patientCount++;
                }

                // Generate report based on results
                if (patientCount > 0) {
                    double avgWeight = totalWeight / patientCount;
                    double avgHeight = totalHeight / patientCount;
                    double avgAge = (double) totalAge / patientCount;

                    report.append("<title>Diagnosis Report</title>")
                            .append("<h1>").append(diagnosis).append(" patients in ").append(year).append("</h1>")
                            .append("<p>There are ").append(maleCount).append(" male patients and ")
                            .append(femaleCount).append(" female patients.</p>")
                            .append("<p>The average weight of the diagnosed patients is: ")
                            .append(String.format("%.2f", avgWeight)).append(" kg</p>")
                            .append("<p>The average height of the diagnosed patients is: ")
                            .append(String.format("%.2f", avgHeight)).append(" cm</p>")
                            .append("<p>The average age of the diagnosed patients is: ")
                            .append(String.format("%.2f", avgAge)).append(" years</p>");
                } else {
                    report.append("<title>Diagnosis Report</title>")
                            .append("<h1>No Data Available</h1>")
                            .append("<p>No patients with the diagnosis '").append(diagnosis)
                            .append("' were found in the year ").append(year).append(".</p>");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error generating health summary report: " + e.getMessage(), e);
        }

        return report.toString();
    }


        /**
     * Medication-specific Annual Patient Report
     * @param medicationName the name of the medication
     * @return the generated report as a String
     * @throws SQLException if a database access error occurs
     */
    public static String getPatientsForMedication(String medicationName) throws SQLException {
        StringBuilder report = new StringBuilder();
        String query = "SELECT p.patient_firstname, p.patient_lastname, p.age, p.sex, md.diagnosis_description, pr.consultation_ID, YEAR(c.consultation_date) AS consultation_year "
                + "FROM patients_record p "
                + "INNER JOIN prescription_record pr ON p.patient_ID = pr.patient_ID "
                + "INNER JOIN medication_record m ON pr.medication_ID = m.medication_ID "
                + "INNER JOIN consultation_medical_diagnosis_record cmd ON pr.consultation_ID = cmd.consultation_ID "
                + "INNER JOIN medical_diagnosis_record md ON cmd.diagnosis_ID = md.diagnosis_ID "
                + "INNER JOIN consultation_record c ON pr.consultation_ID = c.consultation_ID "
                + "WHERE m.generic_name = ? OR m.brand_name = ?";

        try (Connection conn = DBConnection.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, medicationName);
            stmt.setString(2, medicationName);

            try (ResultSet rs = stmt.executeQuery()) {
                report.append("<h2>The patients administered with ").append(medicationName).append(" are:</h2>\n");
                report.append("<table border='1'>\n");
                report.append("<tr><th>First Name</th><th>Last Name</th><th>Age</th><th>Sex</th><th>Diagnosis</th><th>Consultation ID</th><th>Consultation Year</th></tr>\n");

                while (rs.next()) {
                    String firstName = rs.getString("patient_firstname");
                    String lastName = rs.getString("patient_lastname");
                    int age = rs.getInt("age");
                    String sex = rs.getString("sex");
                    String diagnosis = rs.getString("diagnosis_description");
                    int consultationID = rs.getInt("consultation_ID");
                    int consultationYear = rs.getInt("consultation_year");

                    report.append("<tr>")
                        .append("<td>").append(firstName).append("</td>")
                        .append("<td>").append(lastName).append("</td>")
                        .append("<td>").append(age).append("</td>")
                        .append("<td>").append(sex.charAt(0)).append("</td>")
                        .append("<td>").append(diagnosis).append("</td>")
                        .append("<td>").append(consultationID).append("</td>")
                        .append("<td>").append(consultationYear).append("</td>")
                        .append("</tr>\n");
                }
                report.append("</table>\n");
            }
        }

        return report.toString();
    }

    /**
     * Daily TPR Report
     *
     * @param patientID the ID of the patient
     * @return the generated report as a String
     * @throws SQLException if a database access error occurs
     */
    public static String generateDailyTPRReport(int patientID) throws SQLException {
        StringBuilder report = new StringBuilder();

        // Query to fetch patient details and TPR records
        String query = "SELECT p.patient_firstname, p.patient_lastname, p.age, p.sex, "
                + "p.patient_height, p.patient_weight, v.temperature, v.pulse, v.respiratory_rate, v.vital_signs_date "
                + "FROM patients_record p "
                + "INNER JOIN consultation_record c ON p.patient_ID = c.patient_ID "
                + "INNER JOIN vital_signs_record v ON c.vital_signs_ID = v.vital_signs_ID "
                + "WHERE p.patient_ID = ? AND v.vital_signs_date >= CURDATE() - INTERVAL 7 DAY "
                + "ORDER BY v.vital_signs_date DESC";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

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
                        report.append("<p><strong>Patient:</strong> ").append(firstName).append(" ").append(lastName)
                                .append(" (Age: ").append(age).append(", Sex: ").append(sex).append(")</p>")
                                .append("<p><strong>Height:</strong> ").append(height).append(" cm, <strong>Weight:</strong> ")
                                .append(weight).append(" kg</p>");
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
                            .append("<p><strong>Average Temperature:</strong> ").append(String.format("%.2f", avgTemp))
                            .append(" °C</p>")
                            .append("<p><strong>Average Pulse:</strong> ").append(avgPulse).append("</p>")
                            .append("<p><strong>Average Respiratory Rate:</strong> ").append(avgRespRate).append("</p>");
                }

                report.append("</body></html>");
            }
        }

        return report.toString();
    }

    /**
     * Yearly Patient Management Report
     *
     * @param doctorID the ID of the doctor
     * @param year     the year for the report
     * @return the generated report as a String
     * @throws SQLException if a database access error occurs
     */
    public static String YearlyPatientManagementReport(int doctorID, int year) throws SQLException {
        // StringBuilder for report content
        StringBuilder report = new StringBuilder();

        // Corrected SQL query
        String query = "SELECT p.patient_firstname, md.diagnosis_description AS medical_diagnosis "
                + "FROM consultation_record c "
                + "JOIN patients_record p ON c.patient_ID = p.patient_ID "
                + "JOIN consultation_medical_diagnosis_record cmd ON c.consultation_ID = cmd.consultation_ID "
                + "JOIN medical_diagnosis_record md ON cmd.diagnosis_ID = md.diagnosis_ID "
                + "WHERE c.doctor_ID = ? AND YEAR(c.consultation_date) = ? "
                + "ORDER BY p.patient_firstname";

        // Initialize database resources
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set parameters for the doctor ID and year
            pstmt.setInt(1, doctorID);
            pstmt.setInt(2, year);

            // Execute the query and process results
            try (ResultSet rs = pstmt.executeQuery()) {
                // Add report header
                report.append("<html>")
                    .append("<head><title>Yearly Patient Management Report</title></head>")
                    .append("<body>")
                    .append("<h1>Yearly Patient Management Report</h1>")
                    .append("<p><strong>Doctor ID:</strong> ").append(doctorID).append("</p>")
                    .append("<p><strong>Year:</strong> ").append(year).append("</p>")
                    .append("<h2>Patients Handled:</h2>")
                    .append("<ul>");

                int patientCount = 0; // To keep track of the number of patients

                // Process the result set
                while (rs.next()) {
                    String patientName = rs.getString("patient_firstname");
                    String diagnosis = rs.getString("medical_diagnosis");

                    // Handle null diagnosis
                    if (diagnosis == null) {
                        diagnosis = "No diagnosis recorded";
                    }

                    // Append patient and diagnosis to the report
                    report.append("<li>").append(patientName).append(" (").append(diagnosis).append(")</li>");
                    patientCount++;
                }

                report.append("</ul>");

                // Add a summary of the number of patients
                if (patientCount > 0) {
                    report.append("<p><strong>Total Patients Handled:</strong> ").append(patientCount).append("</p>");
                } else {
                    report.append("<p>No patients were handled by this doctor in ").append(year).append(".</p>");
                }

                report.append("</body></html>");
            }
        } catch (SQLException e) {
            // Provide detailed error message and rethrow the exception
            throw new SQLException("Error generating Yearly Patient Management Report for Doctor ID " 
                                    + doctorID + " in year " + year + ": " + e.getMessage(), e);
        }

        return report.toString();
    }
}
