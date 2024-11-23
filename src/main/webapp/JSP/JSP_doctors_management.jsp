<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MediBASE: Result of Patient Management</title>
</head>
<body>
    <%
        String completeName = request.getParameter("doctorName");
        String specialization = request.getParameter("specialization");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String position = request.getParameter("position");

        String action = request.getParameter("action"); // handles multiple buttons

        if ("Create".equals(action)){
            out.println("<p>Create button</p>");
        } else if  ("Delete".equals(action)){
            out.println("<p>Delete button</p>");
        }
    %>
    <h1>Prescriptions of the Patient</h1>


</body>
</html>