<%-- 
    Document   : create_patient_result
    Created on : Nov 18, 2024, 2:35:37 PM
    Author     : Windows User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.source.HospitalDB.Classes.Patient" %>
<%@page import="com.source.HospitalDB.Transaction" %>
<%@page import="java.sql.Timestamp" %>
<%@page import="java.math.BigDecimal" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<%@page import="java.text.ParseException" %>
<%@page import="com.source.HospitalDB.WebTools" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create Patient Result</title>
</head>
<body>
<%
    String complete_name = request.getParameter("Patient_Name");
    String[] names = WebTools.splitName(complete_name);

    int age = Integer.parseInt(request.getParameter("Age"));
    String birthDateStr = request.getParameter("Birth_Date");
    String sex = request.getParameter("Sex");
    BigDecimal height = new BigDecimal(request.getParameter("Height"));
    BigDecimal weight = new BigDecimal(request.getParameter("Weight"));
    String religion = request.getParameter("Religion");

    Timestamp birthDate = null;
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(birthDateStr);
        birthDate = new Timestamp(parsedDate.getTime());
    } catch (ParseException e) {
        e.printStackTrace();
    }

    Timestamp dateCreated = new Timestamp(System.currentTimeMillis());

    String firstname = names[1];
    String lastname = names[0];
    Patient patient = new Patient(lastname, firstname, birthDate, sex, height, weight, religion, dateCreated);

    boolean success = Transaction.createPatientRecord(patient);

    if (success) {
%>
        <h1>Patient Successfully Created</h1>
        <p>Complete Name: <%= complete_name %></p>
        <p>Age: <%= age %></p>
        <p>Birth Date: <%= birthDateStr %></p>
        <p>Sex: <%= sex %></p>
        <p>Height: <%= height %></p>
        <p>Weight: <%= weight %></p>
        <p>Religion: <%= religion %></p>
        <p>Date Created: <%= dateCreated %></p>
        <p><a href="index.jsp">Back to Home</a></p>
<%
    } else {
%>
        <h1>Patient Failed to Create</h1>
<%
    }
%>
</body>
</html>