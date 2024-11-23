<%@ page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ page import="java.sql.*, java.util.*" %> 
<%@ page import="com.source.HospitalDB.Classes.*" %>
<%@ page import="com.source.HospitalDB.DAO.*" %>
<%@ page import="com.source.HospitalDB.*" %> 

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>MediBASE: Result of Prescriptions by Doctor</title>
  </head>
  <body>
    <%
      String first_name = request.getParameter("doc_first_name");
      String last_name = request.getParameter("doc_last_name");

      if (first_name != null && last_name != null) {
          // Print out the doctor's prescriptions
          int doc_id = DoctorsDAO.getFromName(first_name, last_name);
          if (doc_id != 0) {
              List<Prescription> prescriptions = PrescriptionDAO.getPrescriptionsByDoctor(doc_id); 
              
                  %>
                  <table border="1">
                  <tr>
                    <th>Prescription ID</th>
                    <th>Medication ID</th>
                    <th>Prescription Date</th>
                    <th>Frequency</th>
                    <th>Dosage</th>
                    <th>Doctor ID</th>
                    <th>Patient ID</th>
                    <th>Consultation ID</th>
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
                        <td><%= DoctorsDAO.getDoctorByID(prescription.getDoctorID()).getFullName() %></td>
                        <td><%= PatientDAO.getPatient(prescription.getPatientID()).getFullName() %></td>
                        <td><%= prescription.getConsultationID() %></td>
                      </tr>
                  <%
                    }
                   %>
                      </table>
        <%
          } else {
              out.println("Doctor not found.");
          }
      } else {
          out.println("Please enter a valid doctor name.");
      }
    %>
  </body>
</html>

