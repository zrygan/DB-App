<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%@page import="com.source.HospitalDB.DAO.ConsultationDAO" %>
<%@page import="com.source.HospitalDB.Classes.Consultation" %>
<%@page import="com.source.HospitalDB.WebTools" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consultation Records</title>
</head>
<body>
    <h1>Consultation Records</h1>
    <% 
        String date = request.getParameter("date");
        String doctor = request.getParameter("doctor");
        
        if (date != null && doctor != null) {
            String[] doctorName = WebTools.splitName(doctor);
            String firstName = doctorName[1];
            String lastName = doctorName[0];
            
            List<Consultation> consultations = ConsultationDAO.getConsultationsByDateAndDoctor(date, firstName, lastName);
            
            if (consultations.isEmpty()) {
                out.println("<p>No consultation records found for the given date and doctor.</p>");
            } else {
                out.println("<table border='1'>");
                out.println("<tr><th>Consultation ID</th><th>Patient ID</th><th>Doctor ID</th><th>Date</th></tr>");
                for (Consultation consultation : consultations) {
                    out.println("<tr>");
                    out.println("<td>" + consultation.getConsultationID() + "</td>");
                    out.println("<td>" + consultation.getPatientID() + "</td>");
                    out.println("<td>" + consultation.getDoctorID() + "</td>");
                    out.println("<td>" + consultation.getConsultationDate() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }
        } else {
            out.println("<p>Please provide both date and doctor name.</p>");
        }
    %>
</body>
</html>