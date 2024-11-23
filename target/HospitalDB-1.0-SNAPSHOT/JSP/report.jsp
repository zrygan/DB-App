<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="com.source.HospitalDB.Classes.*" %>
<%@page import="com.source.HospitalDB.Reports" %>
<%@page import="com.source.HospitalDB.DAO.*" %>
<%@page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lab Report Result</title>
    </head>
    <body>
    <%
        String originId = request.getParameter("origin_id");
    %>
    
    <%
        if ("patient_record".equals(originId)){
            // Assuming PatientDAO.getSummary() fetches a summary of patient records
            out.println(PatientDAO.getSummary());
        } else if ("Medication_specific_Annual_Patient_Report".equals(originId)){
    %>
        <form method="GET" action="report_annual_med_patient.jsp" target="_blank">
            <label for="medicationName">Brand or Generic Name:</label>
            <input type="text" id="medicationName" name="medicationName" placeholder="Enter Medication Name">
            <input type="submit" value="Search">
        </form>
        <%
            String medicationName = request.getParameter("medicationName");
            request.setAttribute("medicationName", medicationName);
            
            if (medicationName != null && !medicationName.trim().isEmpty()) {
                out.println(Reports.getPatientsForMedication(medicationName));
            }
        %>
    <%    
        } else if ("Daily_TPR".equals(originId)){
    %>
        <form id="dailyTPR" method="GET" action="report_DailyTPR.jsp" target="_blank">
        <div>
            <input type="hidden" id="first_name" name="first_name">
            <input type="hidden" id="last_name" name="last_name">
        </div>
            <label for="patientID">Patient ID:</label>
            <input type="text" id="patient_name" name="patient_name" oninput="splitPatientName()" placeholder="Surname. First Name">

            <input type="text" id="date" name="date" placeholder="Enter Date (YYYY-MM-DD)">
            <input type="submit" value="Generate Report">
        </form>
    <script>
      function splitPatientName() {
        const patientName = document
          .getElementById("patient_name")
          .value.trim(); // Get the full name
        console.log("Full Name:", patientName); // Debug: Log the full name
        const nameParts = patientName.split(","); // Split by comma
        console.log("Name Parts:", nameParts); // Debug: Log the name parts

        if (nameParts.length === 2) {
          // If valid format (Lastname, Firstname) is provided
          document.getElementById("last_name").value = nameParts[0].trim(); // Last name
          document.getElementById("first_name").value = nameParts[1].trim(); // First name
          console.log("First Name:", nameParts[1].trim()); // Debug: Log first name
          console.log("Last Name:", nameParts[0].trim()); // Debug: Log last name
        } else {
          // If invalid format, clear the fields
          document.getElementById("last_name").value = "";
          document.getElementById("first_name").value = "";
        }
      }
    </script>
    <% 
        } else if ("Yearly_Patient_Management_Report".equals(originId)){
    %>
        <form method="GET" action="report_yearly_patient_management.jsp" target="_blank">
            <div>
                <input type="hidden" id="doc_first_name" name="doc_first_name">
                <input type="hidden" id="doc_last_name" name="doc_last_name">
            </div>
            <label for="doctor_name">Doctor ID:</label>
            <input type="text" id="doctor_name" name="doctor_name" oninput="splitDoctorName()" placeholder="Doctor (Surname, First Name)">
            <label for="year">Year:</label>
            <input type="text" id="year" name="year" placeholder="Enter Year">
            <input type="submit" value="Generate Report">
        </form>
        <script>
        function splitDoctorName() {
            const doctorName = document.getElementById("doctor_name").value.trim(); // Get the full name
            console.log("Full Name:", doctorName); // Debug: Log the full name
            const nameParts = doctorName.split(","); // Split by comma
            console.log("Name Parts:", nameParts); // Debug: Log the name parts

            if (nameParts.length === 2) {
                // If valid format (Lastname, Firstname) is provided
                document.getElementById("doc_last_name").value = nameParts[0].trim(); // Last name
                document.getElementById("doc_first_name").value = nameParts[1].trim(); // First name
                console.log("First Name:", nameParts[1].trim()); // Debug: Log first name
                console.log("Last Name:", nameParts[0].trim()); // Debug: Log last name
            } else {
                // If invalid format, clear the fields
                document.getElementById("doc_last_name").value = "";
                document.getElementById("doc_first_name").value = "";
            }
        }
        </script>
    <% 
        } else if ("Diagnostic_specific_Annual_Health_Summary_Report".equals(originId)){
    %>
        <form method="GET" action="report_annual_health_summary.jsp" target="_blank">
            <label for="diagnosis">Diagnosis:</label>
            <input type="text" id="diagnosis" name="diagnosis" placeholder="Enter Diagnosis">
            <label for="year">Year:</label>
            <input type="text" id="year" name="year" placeholder="Enter Year">
            <input type="submit" value="Generate Report">
        </form>

    <% 
        }
    %>
    </body>
</html>