<%-- 
    Document   : delete_patient_result
    Created on : Nov 23, 2024, 2:35:37 PM
    Author     : Windows User
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.source.HospitalDB.Transaction" %>
<%@ page import="com.source.HospitalDB.DAO.PatientDAO" %>
<%@ page import="com.source.HospitalDB.Classes.Patient" %>
<%@ page import="java.sql.Timestamp" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Delete Patient Result</title>
</head>
<body>
<%
    String complete_name = request.getParameter("Patient_Name");
    String[] names = WebTools.splitName(complete_name);
    String firstName = names[1];
    String lastName = names[0];

    int patientID = -1;
    try {
        int patientID = PatientDAO.getFromNameBDay(firstName, lastName, Timestamp.valueOf(request.getParameter("Birth_Date")));
    } catch (SQLException e) {
        e.printStackTrace();
    }

    boolean success = false;
    if (patientID != -1) {
        try {
            Transaction.deletePatientRecord(patientID);
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    if (success) {
%>
        <h1>Patient Successfully Deleted</h1>
        <p>Complete Name: <%= complete_name %></p>
        <p>Patient ID: <%= patientID %></p>
<%
    } else {
%>
        <h1>Failed to Delete Patient</h1>
        <p>Complete Name: <%= complete_name %></p>
        <p>Patient ID: <%= patientID %></p>
<%
    }
%>
</body>
</html>