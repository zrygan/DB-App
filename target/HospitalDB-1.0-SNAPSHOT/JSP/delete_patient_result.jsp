<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.source.HospitalDB.DAO.PatientDAO" %>
<%@page import="com.source.HospitalDB.Classes.Patient" %>
<%@page import="com.source.HospitalDB.Transaction" %>
<%@page import="java.sql.Timestamp" %>
<%@page import="java.sql.SQLException" %> <!-- Import SQLException -->
<%@page import="java.math.BigDecimal" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<%@page import="java.text.ParseException" %>
<%@page import="com.source.HospitalDB.WebTools" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Delete Patient Result</title>
</head>
<body>
<%
    String firstName = request.getParameter("first_name");
    String lastName = request.getParameter("last_name");
    String errorMessage = "";
    
    String birthDateStr = request.getParameter("Birth_Date");
    java.sql.Date birthDate = null;
    try {
        birthDate = java.sql.Date.valueOf(birthDateStr);
    } catch (IllegalArgumentException e) {
        errorMessage = "Invalid birth date format.";
    }

    int patientID = -1;
    patientID = PatientDAO.getFromNameBDay(firstName, lastName, birthDate);

    boolean success = false;
    if (patientID == -1) {
        errorMessage = "Patient not found.";
    } else {
        try {
            // Attempt to delete patient record
            Transaction.deletePatientRecord(patientID);
            success = true;
        } catch (SQLException e) {
            errorMessage = "Error deleting patient: " + e.getMessage();
        }
    }

    if (success) {
%>
        <h1>Patient Successfully Deleted</h1>
        <p>First name: <%= firstName %></p>
        <p>Last name: <%= lastName %></p>
        <p>Birth Date: <%= birthDate %></p>
        <p>Patient ID: <%= patientID %></p>
<%
    } else {
%>
        <h1>Failed to Delete Patient</h1>
        <p><%= errorMessage %></p>
        <p>First name: <%= firstName %></p>
        <p>Last name: <%= lastName %></p>
        <p>Birth Date: <%= birthDate %></p>
<%
    }
%>
</body>
</html>
