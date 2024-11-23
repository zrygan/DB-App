<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.source.HospitalDB.DAO.PatientDAO" %>
<%@ page import="com.source.HospitalDB.DAO.ConsultationDAO" %>
<%@ page import="com.source.HospitalDB.Classes.Consultation" %>
<%@ page import="com.source.HospitalDB.WebTools" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Patient Consultations</title>
</head>
<body>
    <h1>Patient Consultations</h1>
    <%
        String patientName = request.getParameter("patient_name");
        String birthDateStr = request.getParameter("birth_date");
        
        if (patientName != null && birthDateStr != null) {
            try {
                // Convert the birth date string to Timestamp
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedDate = dateFormat.parse(birthDateStr);
                Timestamp birthDate = new Timestamp(parsedDate.getTime());
                
                // Split the patient name into first name and last name
                String[] nameParts = WebTools.splitName(patientName);
                if (nameParts.length == 2) {
                    String firstName = nameParts[1];
                    String lastName = nameParts[0];
                    
                    // Get the patient ID using the first name, last name, and birth date
                    int patientId = PatientDAO.getFromNameBDay(firstName, lastName, birthDate);
                    
                    if (patientId != -1) {
                        // Get the list of consultations for the patient
                        List<Consultation> consultations = ConsultationDAO.getConsultationByPatient(patientId, birthDateStr);
                        
                        if (!consultations.isEmpty()) {
    %>
                            <h2>Consultations for <%= patientName %> (Birth Date: <%= birthDateStr %>)</h2>
                            <table border="1">
                                <tr>
                                    <th>Consultation ID</th>
                                    <th>Prescription ID</th>
                                    <th>Doctor ID</th>
                                    <th>Patient ID</th>
                                    <th>Vital Signs ID</th>
                                    <th>Lab Report ID</th>
                                    <th>Consultation Date</th>
                                </tr>
                                <%
                                    for (Consultation consultation : consultations) {
                                %>
                                    <tr>
                                        <td><%= consultation.getConsultationID() %></td>
                                        <td><%= consultation.getPrescriptionID() %></td>
                                        <td><%= consultation.getDoctorID() %></td>
                                        <td><%= consultation.getPatientID() %></td>
                                        <td><%= consultation.getVitalSignsID() %></td>
                                        <td><%= consultation.getLabReportID() %></td>
                                        <td><%= consultation.getConsultationDate() %></td>
                                    </tr>
                                <%
                                    }
                                %>
                            </table>
    <%
                        } else {
                            out.println("<p>No consultations found for the given patient.</p>");
                        }
                    } else {
                        out.println("<p>Patient not found.</p>");
                    }
                } else {
                    out.println("<p>Invalid patient name format.</p>");
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<p>Error occurred while processing the request.</p>");
            }
        } else {
            out.println("<p>Please provide both patient name and birth date.</p>");
        }
    %>
</body>
</html>