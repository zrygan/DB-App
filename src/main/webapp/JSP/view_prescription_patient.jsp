<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Timestamp, java.sql.Date" %>
<%@ page import="java.util.List, java.util.Date, java.text.SimpleDateFormat" %>
<%@ page import="com.source.HospitalDB.DAO.PrescriptionDAO" %>
<%@ page import="com.source.HospitalDB.DAO.PatientDAO" %>
<%@ page import="com.source.HospitalDB.Classes.Prescription" %>
<%@ page import="com.source.HospitalDB.Classes.Patient" %>
<%@ page import="com.source.HospitalDB.WebTools" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Patient Prescriptions</title>
</head>
<body>
    <h1>Patient Prescriptions</h1>
    <%
        String patientName = request.getParameter("patient_name");
        String birthDateStr = request.getParameter("birth_date");
        if (patientName != null && birthDateStr != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = dateFormat.parse(birthDateStr);
                Timestamp birthDate = new Timestamp(parsedDate.getTime());
                
                String[] nameParts = WebTools.splitName(patientName);
                if (nameParts.length == 2) {
                    String firstName = nameParts[1];
                    String lastName = nameParts[0];
                    
                    int patientId = PatientDAO.getFromNameBDay(firstName, lastName, birthDate);
                    if (patientId != -1) {
                        List<Prescription> prescriptions = PrescriptionDAO.getByPatientId(patientId);
                        if (!prescriptions.isEmpty()) {
    %>
                            <h2>Prescriptions for <%= patientName %> (Birth Date: <%= birthDateStr %>)</h2>
                            <table border="1">
                                <tr>
                                    <th>Prescription ID</th>
                                    <th>Medication ID</th>
                                    <th>Prescription Date</th>
                                    <th>Frequency</th>
                                    <th>Dosage</th>
                                    <th>Doctor ID</th>
                                </tr>
                                <%
                                    for (Prescription prescription : prescriptions) {
                                %>
                                    <tr>
                                        <td><%= prescription.getPrescriptionID() %></td>
                                        <td><%= prescription.getMedicationID() %></td>
                                        <td><%= prescription.getPrescriptionDate() %></td>
                                        <td><%= prescription.getFrequency() %></td>
                                        <td><%= prescription.getDosage() %></td>
                                        <td><%= prescription.getDoctorID() %></td>
                                    </tr>
                                <%
                                    }
                                %>
                            </table>
    <%
                        } else {
    %>
                            <p>No prescriptions found for this patient.</p>
    <%
                        }
                    } else {
    %>
                        <p>Patient not found.</p>
    <%
                    }
                } else {
    %>
                    <p>Invalid patient name format. Please use 'Lastname, Firstname'.</p>
    <%
                }
            } catch (Exception e) {
                e.printStackTrace();
    %>
                <p>Error occurred while retrieving prescriptions: <%= e.getMessage() %></p>
    <%
            }
        } else {
    %>
            <p>Please provide both patient name and birth date.</p>
    <%
        }
    %>
</body>
</html>
