<%-- 
    Document   : create_consultation _result
    Created on : Nov 18, 2024, 2:53:20 PM
    Author     : Windows User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.source.HospitalDB.DAO.DoctorsDAO" %>
<%@page import="com.source.HospitalDB.Classes.Doctors" %><
<%@page import="java.math.BigDecimal" %>
<%@page import="java.sql.Date" %>
<%@page import="java.sql.Timestamp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Consultation Result</title>
    </head>
    <body>
        <% 
            String patient_name = request.getParameter("patient_name") != null ? request.getParameter("patient_name") : "Not Provided";
            int age = 19; // FIXME: change this (age = year.now() - birthdate.year()
            String doctor_name = request.getParameter("doctor_name") != null ? request.getParameter("doctor_name") : "Not Provided";
            Doctors doctor = new Doctors(88888888, "This is a new person", "Ohayo", "(123) 356-5678", "yarnie@x.com");
            DoctorsDAO.create(doctor);
        %>
        
        <h1>Consultation Successfully Created</h1>
        <h1>Consultation Failed to Create</h1>
        <p>Name: <%= patient_name %></p>
        <p>Age: <%= age %></p>
        <p>Attending Physician: <%= doctor_name %></p>
    </body>
</html>
