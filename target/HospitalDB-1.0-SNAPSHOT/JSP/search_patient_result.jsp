<%@ page import="com.source.HospitalDB.App" %>
<%@ page import="com.source.HospitalDB.DAO.PatientDAO" %>
<%@ page import="com.source.HospitalDB.Classes.Patient" %>
<%@ page import="com.source.HospitalDB.WebTools" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search Patient Result</title>
</head>
<body>
<%
    String complete_name = request.getParameter("patient_name");
    if (complete_name == null || complete_name.isEmpty()) {
        out.println("<h1>Error: Patient name is missing.</h1>");
        return;
    }

    String[] names = WebTools.splitName(complete_name);
    String firstName = names.length > 1 ? names[1] : "";
    String lastName = names.length > 0 ? names[0] : "";

    Timestamp birthDate = null;
    try {
        birthDate = Timestamp.valueOf(request.getParameter("Birth_Date"));
    } catch (IllegalArgumentException e) {
        e.printStackTrace();
    }

    Patient patient = null;
    try {
        int patient_id = PatientDAO.getFromNameBDay(firstName, lastName, birthDate);
        if (App.getPatientMap().containsKey(patient_id)){
            patient = App.getPatientMap().get(patient_id);
        } else {
            patient = PatientDAO.getFromID(patient_id);
            if (patient != null) {
                App.getPatientMap().put(patient_id, patient);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    if (patient != null) {
%>
        <h1>Patient Found</h1>
        <p>Complete Name: <%= complete_name %></p>
        <p>Patient ID: <%= patient.getPatientId() %></p>
        <p>Age: <%= patient.getAge() %></p>
        <p>Birth Date: <%= patient.getBirthDate() %></p>
        <p>Sex: <%= patient.getSex() %></p>
        <p>Height: <%= patient.getHeight() %></p>
        <p>Weight: <%= patient.getWeight() %></p>
        <p>Religion: <%= patient.getReligion() %></p>
        <p>Date Created: <%= patient.getDateCreated() %></p>
<%
    } else {
%>
        <h1>Patient Not Found</h1>
<%
    }
%>
</body>
</html>