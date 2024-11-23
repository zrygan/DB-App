<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*" %>
<%@ page import="com.source.HospitalDB.DAO.ConsultationDAO" %>
<%@ page import="com.source.HospitalDB.Classes.Consultation" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultations on Specific Date</title>
</head>
<body>
    <h1>Consultations on Specific Date</h1>
    <%
        String date = request.getParameter("date");
        if (date != null && !date.isEmpty()) {
            List<Consultation> consultations = ConsultationDAO.getConsultationByDate(date);
            if (consultations.isEmpty()) {
                out.println("<p>No consultations found for the given date.</p>");
            } else {
                out.println("<table border='1'>");
                out.println("<tr><th>Consultation ID</th><th>Prescription ID</th><th>Doctor ID</th><th>Patient ID</th><th>Vital Signs ID</th><th>Lab Report ID</th><th>Consultation Date</th></tr>");
                for (Consultation consultation : consultations) {
                    out.println("<tr>");
                    out.println("<td>" + consultation.getConsultationID() + "</td>");
                    out.println("<td>" + consultation.getPrescriptionID() + "</td>");
                    out.println("<td>" + consultation.getDoctorID() + "</td>");
                    out.println("<td>" + consultation.getPatientID() + "</td>");
                    out.println("<td>" + consultation.getVitalSignsID() + "</td>");
                    out.println("<td>" + consultation.getLabReportID() + "</td>");
                    out.println("<td>" + consultation.getConsultationDate() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }
        } else {
            out.println("<p>Please provide a date.</p>");
        }
    %>
</body>
</html>