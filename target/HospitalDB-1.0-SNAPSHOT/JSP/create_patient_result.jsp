<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.source.HospitalDB.Classes.Patient" %>
<%@page import="com.source.HospitalDB.DAO.PatientDAO" %>
<%@page import="com.source.HospitalDB.Transaction" %>
<%@page import="java.sql.*" %>
<%@page import="java.math.*" %>
<%@page import="java.text.*" %>
<%@page import="java.util.*" %>
<%@page import="com.source.HospitalDB.WebTools" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create Patient Result</title>
</head>
<body>
<%
    String errorMessage = "";
    String firstName = request.getParameter("first_name");
    String lastName = request.getParameter("last_name");

    if (firstName != null && firstName.trim().isEmpty()) {
        firstName = null;
    }
        
    if (lastName != null && lastName.trim().isEmpty()) {
        lastName = null;
    }
    
    String dateStr = request.getParameter("Birth_Date");
    java.sql.Date birthDate = null;
    try {
        birthDate = java.sql.Date.valueOf(dateStr);
    } catch (IllegalArgumentException e) {
        errorMessage = "Invalid birth date format.";
    }
    
    // Check for valid sex input
    String sex = request.getParameter("Sex");
    if (sex == null || (!sex.equalsIgnoreCase("M") && !sex.equalsIgnoreCase("F"))) {
        errorMessage = "Sex value inputted is not M (Male) or F (Female).";
    }
    
    // Other input processing
    String religion = request.getParameter("Religion");
    BigDecimal height = null, weight = null;
    try {
        height = new BigDecimal(request.getParameter("Height"));
        weight = new BigDecimal(request.getParameter("Weight"));
    } catch (NumberFormatException e) {
        errorMessage = "Height or weight is not in the correct format.";
    }

    Timestamp dateCreated = new Timestamp(System.currentTimeMillis());
    int patient_id = PatientDAO.getHighestPatientID() + 1;
    
    // Check if the patient already exists
    Patient test_patient = PatientDAO.getByName(firstName, lastName);
    if (test_patient != null) {
        errorMessage = "Patient already exists.";
    }

    if (!errorMessage.isEmpty() || firstName == null || lastName == null || birthDate == null) {
        // If there is an error, display it and stop further processing
        out.println("<h1>Error: " + errorMessage + "</h1>");
        out.println("<h1>Enter Valid Inputs</h1>");
    } else {
        // Create new patient if no errors
        Patient new_patient = new Patient(patient_id, firstName, lastName, birthDate, sex, height, weight, religion);
        boolean success = Transaction.createPatientRecord(new_patient);

        if (success) {
            out.println("<h1>Patient Successfully Created</h1>");
            out.println("<p>First name: " + new_patient.getFirstname() + "</p>");
            out.println("<p>Last name: " + new_patient.getLastname() + "</p>");
            out.println("<p>Age: " + new_patient.getAge() + "</p>");
            out.println("<p>Birth Date: " + birthDate + "</p>");
            out.println("<p>Sex: " + sex + "</p>");
            out.println("<p>Height: " + height + "</p>");
            out.println("<p>Weight: " + weight + "</p>");
            out.println("<p>Religion: " + religion + "</p>");
            out.println("<p>Date Created: " + dateCreated + "</p>");
            out.println("<p><a href='index.jsp'>Back to Home</a></p>");
        } else {
            errorMessage = "Error occurred while creating patient record.";
            out.println("<h1>Error: " + errorMessage + "</h1>");
        }
    }
%>    
</body>
</html>
