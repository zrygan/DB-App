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
<%@page import="com.source.HospitalDB.Classes.Consultation" %>
<%@page import="com.source.HospitalDB.DAO.ConsultationDAO" %>
<%@page import="java.time.Year" %>
<%@page import="java.text.ParseException" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="com.source.HospitalDB.Classes.VitalSigns" %>
<%@page import="com.source.HospitalDB.Classes.Prescription" %>
<%@page import="com.source.HospitalDB.DAO.PrescriptionDAO" %>
<%@page import="com.source.HospitalDB.DAO.VitalSignsDAO" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Consultation Result</title>
    </head>
    <body>
        <% 
            String birthStr = request.getParameter("birth_date") != null ? request.getParameter("birth_date") : "Not Provided";
            Date birthDate = null;
            if (birthStr != null && !birthStr.isEmpty()) {
                try {
                    // Convert the String to java.util.Date first
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = formatter.parse(birthStr);

                    // Convert java.util.Date to java.sql.Date
                    birthDate = new Date(utilDate.getTime());
                } catch (ParseException e) {
                    System.out.println("Invalid date format: " + e.getMessage());
                    // Handle the invalid format case
                }
            } else {
                System.out.println("Birth date not provided.");
                // Handle the missing date case (e.g., set a default value or take other action)
            }
            String consultDateStr = request.getParameter("date") != null ? request.getParameter("date") : "Not Provided";
            Date consultDate = null;
            if (consultDateStr != null && !consultDateStr.isEmpty()) {
                try {
                    // Convert the String to java.util.Date first
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date tempDate = formatter.parse(birthStr);

                    // Convert java.util.Date to java.sql.Date
                    consultDate = new Date(tempDate.getTime());
                } catch (ParseException e) {
                    System.out.println("Invalid date format: " + e.getMessage());
                    // Handle the invalid format case
                }
            } else {
                System.out.println("Birth date not provided.");
                // Handle the missing date case (e.g., set a default value or take other action)
            }
            String patient_name = request.getParameter("patient_name") != null ? request.getParameter("patient_name") : "Not Provided";
            // Look for patient ID with the name and birth date
            String doctor_name = request.getParameter("doctor_name") != null ? request.getParameter("doctor_name") : "Not Provided";
            // Look for patient ID and get int
            String temperature = request.getParameter("temperature") != null ? request.getParameter("temperature") : "Not Provided";
            int temp_int = Integer.parseInt(temperature);
            String pulse = request.getParameter("pulse") != null ? request.getParameter("pulse") : "Not Provided";
            int pulse_int = Integer.parseInt(pulse);
            String respiratory_rate = request.getParameter("respiratory_rate") != null ? request.getParameter("respiratory_rate") : "Not Provided";
            int resp_int = Integer.parseInt(respiratory_rate);
            String systolic = request.getParameter("systolic_bp") != null ? request.getParameter("systolic_bp") : "Not Provided";
            int sys_int = Integer.parseInt(systolic);
            String diastolic = request.getParameter("diastolic_bp") != null ? request.getParameter("diastolic_bp") : "Not Provided";
            int dias_int = Integer.parseInt(diastolic);
            String spo2 = request.getParameter("spo2") != null ? request.getParameter("spo2") : "Not Provided";
            int spo2_int = Integer.parseInt(spo2);
            
            VitalSigns vitalSigns = new VitalSigns();
            
            Consultation consultation = new Consultation();
            ConsultationDAO.create(consultation);
        %>
        
        <h1>Consultation Successfully Created</h1>
        <h1>Consultation Failed to Create</h1>
        <p>Name: <%= patient_name %></p>
        <p>Age: <%= age %></p>
        <p>Attending Physician: <%= doctor_name %></p>
    </body>
</html>
