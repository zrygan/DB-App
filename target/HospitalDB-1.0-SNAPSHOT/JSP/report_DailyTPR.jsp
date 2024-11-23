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
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String birth_date_str = request.getParameter("date");
        java.sql.Date birth_date = null;
        if (birth_date_str != null && !birth_date_str.isEmpty()) {
            try {
            birth_date = java.sql.Date.valueOf(birth_date_str);
            } catch (IllegalArgumentException e) {
            out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
        
        if (first_name != null && last_name != null && birth_date != null) {
            int patient_id = PatientDAO.getFromNameBDay(first_name, last_name, birth_date);
            if (patient_id == 0) {
                out.println("Patient not found.");
            } else {
                out.println(Reports.generateDailyTPRReport(patient_id));
            }
        } else {
            out.println("Please enter a valid inputs.");
        }
%>

    </body>
</html>
