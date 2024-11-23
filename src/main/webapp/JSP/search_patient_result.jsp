<%-- 
    Document   : search_patient_result
    Created on : Nov 23, 2024, 2:35:37 PM
    Author     : Windows User
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.source.HospitalDB.DAO.PatientDAO" %>
<%@ page import="com.source.HospitalDB.Classes.Patient" %>
<%@ page import="com.source.HospitalDB.WebTools" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search Patient Result</title>
</head>
<body>
<%
    String complete_name = request.getParameter("Patient_Name");
    String[] names = WebTools.splitName(complete_name);
    String firstName = names[0];
    String lastName = names[1];
    
    Timestamp birthDate = null;
    try {
        birthDate = Timestamp.valueOf(request.getParameter("Birth_Date"));
    } catch (IllegalArgumentException e) {
        e.printStackTrace();
    }

    Patient patient = null;
    try {
        patient = PatientDAO.getFromNameBDay(firstName, lastName, birthDate);
    } catch (SQLException e) {
        e.printStackTrace();
    }

    if (patient != null) {
%>
        <h1>Patient Found</h1>
        <p>Complete Name: <%= complete_name %></p>
        <p>Patient ID: <%= patient.getPatientId() %></p>
        <p>Age: <%= patient.getAge() %></p>
        <p>Birth Date: <%= patient.getBirthDate() %></p>
        <p>Sex: <%= patient.getSex() %></p>
        <p>Height: <%= patient.getHeight() %></p>
        <p>Weight: <%= patient.getWeight() %></p>
        <p>Religion: <%= patient.getReligion() %></p>
        <p>Date Created: <%= patient.getDateCreated() %></p>
        <p><a href="index.jsp">Back to Home</a></p>
<%
    } else {
%>
        <h1>Patient Not Found</h1>
        <p>Complete Name: <%= complete_name %></p>
        <p>Birth Date: <%= request.getParameter("Birth_Date") %></p>
        <p><a href="index.jsp">Back to Home</a></p>
<%
    }
%>
</body>
</html>