<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="com.source.HospitalDB.Classes.Doctors" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MediBASE: Result of Doctor Prescriptions</title>
</head>
<body>
    <h1>Prescriptions for the Doctor</h1>

    <%
        // Get the doctor's full name from the request parameter
        String doctorFullName = request.getParameter("doctor");

        // Check if the doctorFullName parameter is null or empty
        if (doctorFullName == null || doctorFullName.trim().length() <= 0) {
            out.println("<p>Please provide a valid doctor full name (last name, first name).</p>");
        } else {
            // Check if the full name contains invalid characters (numbers or special characters)
            String regex = "^[a-zA-Z,\\s]+$"; // Allows letters, commas, and spaces
            if (!doctorFullName.matches(regex)) {
                out.println("<p>Invalid doctor name format. Please enter a name containing only letters and commas (e.g., Doe, John).</p>");
            } else {
                // Split the full name into first name and last name
                String[] nameParts = doctorFullName.split(",");
                if (nameParts.length != 2) {
                    out.println("<p>Invalid doctor name format. Please provide the name in 'Lastname, Firstname' format.</p>");
                } else {
                    String doctorLastName = nameParts[0].trim();
                    String doctorFirstName = nameParts[1].trim();

                    // Get the doctors_map from the request or servlet context
                    Map<Integer, Doctors> doctors_map = (Map<Integer, Doctors>) request.getAttribute("doctors_map");

                    // Check if the doctor exists in the map
                    Doctors doctor = null;
                    for (Doctors d : doctors_map.values()) {
                        if (d.getLastname().equalsIgnoreCase(doctorLastName) && d.getFirstname().equalsIgnoreCase(doctorFirstName)) {
                            doctor = d;
                            break;
                        }
                    }

                    if (doctor == null) {
                        out.println("<p>The specified doctor does not exist in the database.</p>");
                    } else {
                        // SQL query with placeholders for dynamic doctor name
                        String query = "SELECT p.prescription_ID, p.prescription_date, p.frequency, p.dosage, " +
                                       "m.generic_name AS medication_generic_name, m.brand_name AS medication_brand_name, " +
                                       "doc.doctor_firstname, doc.doctor_lastname, " +
                                       "pat.patient_firstname, pat.patient_lastname " +
                                       "FROM prescription_record p " +
                                       "JOIN doctors_record doc ON p.doctor_ID = doc.doctor_ID " +
                                       "JOIN medication_record m ON p.medication_ID = m.medication_ID " +
                                       "JOIN patients_record pat ON p.patient_ID = pat.patient_ID " +
                                       "WHERE doc.doctor_lastname = ? AND doc.doctor_firstname = ?";

                        try (Connection conn = DBConnection.getConnection()) {
                            PreparedStatement pstmt = conn.prepareStatement(query);
                            pstmt.setString(1, doctorLastName);  // Set doctor last name
                            pstmt.setString(2, doctorFirstName); // Set doctor first name

                            try (ResultSet rs = pstmt.executeQuery()) {
                                // Process the result set and display the results in a table
                                out.println("<table border='1'>");
                                out.println("<tr><th>Prescription ID</th><th>Prescription Date</th><th>Frequency</th><th>Dosage</th><th>Medication Generic Name</th><th>Medication Brand Name</th><th>Doctor First Name</th><th>Doctor Last Name</th><th>Patient First Name</th><th>Patient Last Name</th></tr>");

                                while (rs.next()) {
                                    out.println("<tr>");
                                    out.println("<td>" + rs.getInt("prescription_ID") + "</td>");
                                    out.println("<td>" + rs.getDate("prescription_date") + "</td>");
                                    out.println("<td>" + rs.getString("frequency") + "</td>");
                                    out.println("<td>" + rs.getString("dosage") + "</td>");
                                    out.println("<td>" + rs.getString("medication_generic_name") + "</td>");
                                    out.println("<td>" + rs.getString("medication_brand_name") + "</td>");
                                    out.println("<td>" + rs.getString("doctor_firstname") + "</td>");
                                    out.println("<td>" + rs.getString("doctor_lastname") + "</td>");
                                    out.println("<td>" + rs.getString("patient_firstname") + "</td>");
                                    out.println("<td>" + rs.getString("patient_lastname") + "</td>");
                                    out.println("</tr>");
                                }
                                out.println("</table>");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                            out.println("<p>Error: " + e.getMessage() + "</p>");
                        }
                    }
                }
            }
        }
    %>
</body>
</html>
