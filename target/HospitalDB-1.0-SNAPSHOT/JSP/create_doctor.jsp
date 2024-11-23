<%@ page import="java.sql.SQLException" %>
<%@ page import="com.source.HospitalDB.Classes.Doctors" %>
<%@ page import="com.source.HospitalDB.DAO.DoctorsDAO" %>
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
    if (firstname == null || firstname.isEmpty() ||
        lastname == null || lastname.isEmpty() ||
        specialization == null || specialization.isEmpty() ||
        phoneNumber == null || phoneNumber.isEmpty() ||
        email == null || email.isEmpty()) {
        out.println("<p>All fields are required!</p>");
    } else {
        // Create a new Doctors object
        Doctors newDoctor = new Doctors(firstname, lastname, specialization, phoneNumber, email);

        // Add the new doctor to the database
        int success = 0;
        try {
            // check first if the doctor already exists with name and phone number and email
            if (DoctorsDAO.getFromName(firstname, lastname) != 0 || DoctorsDAO.getDoctorIDByPhoneNumber(phoneNumber) != 0 || DoctorsDAO.getDoctorIDByEmail(email) != 0) {
                out.println("<p>Doctor already exists with the same name or phone number or email!</p>");
            } else {
                success = 1;
                DoctorsDAO.add(newDoctor);
                out.println("<p>Doctor added successfully!</p>");
            }
        } catch (SQLException e) {
            out.println("<p>Error adding doctor: " + e.getMessage() + "</p>");
        }

        // display the created doctor if success
        if (success == 1) {
            out.println("<p>Doctor Details:</p>");
            out.println("<p>First Name: " + firstname + "</p>");
            out.println("<p>Last Name: " + lastname + "</p>");
            out.println("<p>Specialization: " + specialization + "</p>");
            out.println("<p>Phone Number: " + phoneNumber + "</p>");
            out.println("<p>Email: " + email + "</p>");
        } else {
            out.println("<p>Doctor not added!</p>");
        }
    }
%>
