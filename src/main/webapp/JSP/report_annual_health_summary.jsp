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
    <body>        <%
            String diagnosis = request.getParameter("diagnosis");
            String yearStr = request.getParameter("year");
            int year = -1;
            if (yearStr != null) {
                try {
                    year = Integer.parseInt(yearStr);
                } catch (NumberFormatException e) {
                    out.println("Invalid Year format. Please enter a numeric value.");
                }
            }
            if (diagnosis != null && year != -1) {
                out.println(Reports.DiagnosticSpecificAnnualHealthSummaryReport(diagnosis, year));
            } else {
                out.println("Please enter both Diagnosis and Year.");
            }
        %>
    </body>
</html>