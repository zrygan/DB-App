<%-- 
    Document   : view_vital_date
    Created on : Nov 23, 2024, 2:35:37 PM
    Author     : Windows User
--%>

<%@ page import="java.sql.*, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.source.HospitalDB.DAO.VitalSignsDAO" %>
<%@ page import="com.source.HospitalDB.Classes.VitalSigns" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
    <title>Vital Signs Records</title>
</head>
<body>
    <h1>Vital Signs Records</h1>
    <%
        String dateStr = request.getParameter("date");
        if (dateStr != null && !dateStr.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = dateFormat.parse(dateStr);
                Timestamp timestamp = new Timestamp(parsedDate.getTime());

                String timestampStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
                List<VitalSigns> vitalSignsList = VitalSignsDAO.getVitalSignsByDate(timestampStr);
                
                if (vitalSignsList != null && !vitalSignsList.isEmpty()) {
                    out.println("<ul>");
                    for (VitalSigns vitalSigns : vitalSignsList) {
                        out.println("<li>Temperature: " + vitalSigns.getTemperature() + 
                                    ", Pulse: " + vitalSigns.getPulse() + 
                                    ", Respiratory Rate: " + vitalSigns.getRespiratoryRate() + 
                                    ", Systolic BP: " + vitalSigns.getSystolicBP() + 
                                    ", Diastolic BP: " + vitalSigns.getDiastolicBP() + 
                                    ", SPO2: " + vitalSigns.getSpo2() + 
                                    ", Date: " + vitalSigns.getVitalSignsDate() + "</li>");
                    }
                    out.println("</ul>");
                } else {
                    out.println("No vital signs records found for the provided date.");
                }
            } catch (Exception e) {
                out.println("Error parsing date: " + e.getMessage());
            }
        } else {
            out.println("No date provided.");
        }
    %>
</body>
</html>