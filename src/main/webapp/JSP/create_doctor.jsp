<%@ page import="java.sql.SQLException" %>
<%@ page import="com.source.HospitalDB.Classes.Doctors" %>
<%@ page import="com.source.HospitalDB.DAO.DoctorsDAO" %>
<%
    // Retrieve form parameters from the request
    String firstname = request.getParameter("doctorName");
    String lastname = request.getParameter("doctorLastName");
    String specialization = request.getParameter("specialization");
    String phoneNumber = request.getParameter("phoneNumber");
    String email = request.getParameter("email");

    // Create a new Doctors object
    Doctors newDoctor = new Doctors(firstname, lastname, specialization, phoneNumber, email);

    // Add the new doctor to the database
    try {
        DoctorsDAO.add(newDoctor);
        out.println("<p>Doctor added successfully!</p>");
    } catch (SQLException e) {
        out.println("<p>Error adding doctor: " + e.getMessage() + "</p>");
    }
%>
<%
    // Display the created doctor details if the form was submitted
    if (request.getMethod().equalsIgnoreCase("POST")) {
        out.println("<h2>Created Doctor Details:</h2>");
        out.println("<p>First Name: " + firstname + "</p>");
        out.println("<p>Last Name: " + lastname + "</p>");
        out.println("<p>Specialization: " + specialization + "</p>");
        out.println("<p>Phone Number: " + phoneNumber + "</p>");
        out.println("<p>Email: " + email + "</p>");
    }
%>










