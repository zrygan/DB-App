<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.source.HospitalDB.DAO.*" %>
<%@ page import="com.source.HospitalDB.WebTools" %>
<%@ page import="com.source.HospitalDB.Classes.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Doctor Result</title>
</head>
<body>
    <h1>Delete Doctor Result</h1>
    <%
        // Retrieve form parameters from the request
        String firstname = request.getParameter("first_name");
        String lastname = request.getParameter("last_name");
        String specialization = request.getParameter("specialization");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        // Validate email format
        if (!email.endsWith("@medibase.com")) {
            out.println("<p>Email must end with @medibase.com!</p>");
            email = null;
        }

        // Validate phone number format
        String phonePattern = "\\(\\d{3}\\) \\d{3}-\\d{3}";
        if (!phoneNumber.matches(phonePattern)) {
            out.println("<p>Phone number must be in the format (XXX) XXX-XXX!</p>");
            phoneNumber = null;
        }

        // Check if any of the inputs are empty
        if (firstname == null || firstname.isEmpty() || lastname == null || lastname.isEmpty() ||
            specialization == null || specialization.isEmpty() || phoneNumber == null || phoneNumber.isEmpty() ||
            email == null || email.isEmpty()) {
            out.println("<p>Please provide all required information.</p>");
        } else {
            // Check if the doctor already exists with name, phone number, and email
            if (DoctorsDAO.getFromName(firstname, lastname) != 0 || DoctorsDAO.getDoctorIDByPhoneNumber(phoneNumber) != 0 || DoctorsDAO.getDoctorIDByEmail(email) != 0) {
                try {
                    int doctorId = DoctorsDAO.getDoctorID(firstname, lastname, specialization, phoneNumber, email);

                    // Check if doctor id is in any of the consultation records, if it is, do not delete
                    if (ConsultationDAO.getDoctorID(doctorId) != 0) {
                        out.println("<p>Doctor cannot be deleted because they are in consultation records.</p>");
                    } else {
                        DoctorsDAO.del(doctorId);
                        out.println("<p>Doctor successfully deleted.</p>");
                        out.println("<p>Doctor Name: " + lastname + ", " + firstname + "</p>");
                        out.println("<p>Doctor ID: " + doctorId + "</p>");
                    }
                } catch (SQLException e) {
                    out.println("<p>Error deleting doctor: " + e.getMessage() + "</p>");
                }
            } else {
                out.println("<p>Please provide a valid doctor name.</p>");
            }
        }
    %>
</body>
</html>