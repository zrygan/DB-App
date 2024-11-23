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
        String medicationName = request.getParameter("medicationName");
        request.setAttribute("medicationName", medicationName);
        
        if (medicationName != null && !medicationName.trim().isEmpty()) {
            out.println(Reports.getPatientsForMedication(medicationName));
        }
    %>
    </body>
</html>

