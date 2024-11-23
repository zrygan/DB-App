<%@ page import="com.source.HospitalDB.App" %>
<%@ page import="com.source.HospitalDB.DAO.PatientDAO" %>
<%@ page import="com.source.HospitalDB.Classes.Patient" %>
<%@ page import="com.source.HospitalDB.WebTools" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search Patient Result</title>
</head>
<body>
<%
    // Retrieve the first name and last name from the request parameters
    String firstName = request.getParameter("first_name");
    String lastName = request.getParameter("last_name");
    
    if (firstName != null && firstName.trim().isEmpty()) {
        firstName = null;
    }
        
    if (lastName != null && lastName.trim().isEmpty()) {
        lastName = null;
    }
        
    // Retrieve the month, day, and year from the request parameters
    String dateTimeStr = request.getParameter("Birth_Date") + " 00:00:00";
    java.sql.Date birthDate = null;
    try {
        birthDate = java.sql.Date.valueOf(request.getParameter("Birth_Date"));
    } catch (IllegalArgumentException e) {
        e.printStackTrace();
    }
    


    Patient patient = null;
    int patient_id = 0;
    if (firstName == null || lastName == null || birthDate == null) {
        out.println("<h1>Invalid Input</h1>");
        out.println("<p>Please provide valid first name, last name, and birth date.</p>");
    } else {

        try {
            // Query the database to get patient details based on the names and birthdate
            patient_id = PatientDAO.getFromNameBDay(firstName, lastName, birthDate);
            if (patient_id != 0) {
                patient = PatientDAO.getFromID(patient_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Display the patient details if found
    if (patient != null) {
%>
        <h1>Patient Found</h1>
        <p>First Name: <%= firstName %></p>
        <p>Last Name: <%= lastName %></p>
        <p>Patient ID: <%= patient.getPatientId() %></p>
        <p>Age: <%= patient.getAge() %></p>
        <p>Birth Date: <%= patient.getBirthDate() %></p>
        <p>Sex: <%= patient.getSex() %></p>
        <p>Height: <%= patient.getHeight() %></p>
        <p>Weight: <%= patient.getWeight() %></p>
        <p>Religion: <%= patient.getReligion() %></p>
        <p>Date Created: <%= patient.getDateCreated() %></p>
<%
    } else {
%>
        <h1>Patient Not Found</h1>
        <p>First Name: <%= firstName %></p>
        <p>Last Name: <%= lastName %></p>
        <p>Birth Date: <%= dateTimeStr %></p>
        <p>Patient ID: <%= patient_id %></p>
<%
    }
%>
</body>
</html>
