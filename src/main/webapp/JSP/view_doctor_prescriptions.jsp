<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="com.source.HospitalDB.DAO.PrescriptionDAO" %>
<%@ page import="com.source.HospitalDB.Classes.Prescription" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctor's Prescriptions</title>
</head>
<body>
    <h1>Prescriptions for the Doctor</h1>
    <%
        String doctorName = request.getParameter("doctor_name");
        if (doctorName == null || doctorName.trim().isEmpty()) {
            out.println("<p>Please provide a valid doctor name.</p>");
        } else {
            String[] nameParts = WebTools.splitName(doctorName);
            if (nameParts.length != 2) {
                out.println("<p>Invalid doctor name format. Please provide the name in 'Lastname, Firstname' format.</p>");
            } else {
                String doctorLastName = nameParts[0];
                String doctorFirstName = nameParts[1];
                int doctorId = DoctorsDAO.getFromName(doctorFirstName, doctorLastName);
                if (doctorId == 0) {
                    out.println("<p>The specified doctor does not exist in the database.</p>");
                } else {
                    List<Prescription> prescriptions = PrescriptionDAO.getPrescriptionsByDoctor(doctorId);
                    if (prescriptions.isEmpty()) {
                        out.println("<p>No prescriptions found for this doctor.</p>");
                    } else {
                        %>
                        <table border="1">
                            <tr>
                                <th>Prescription ID</th>
                                <th>Medication ID</th>
                                <th>Prescription Date</th>
                                <th>Frequency</th>
                                <th>Dosage</th>
                                <th>Patient ID</th>
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
                                    <td><%= prescription.getPatientID() %></td>
                                </tr>
                                <%
                            }
                            %>
                        </table>
                        <%
                    }
                }
            }
        }
    %>
</body>
</html>