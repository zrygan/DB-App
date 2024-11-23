<%-- 
    Document   : delete_patient_result
    Created on : Nov 23, 2024, 2:35:37 PM
    Author     : Windows User
--%>

<%@ page import="java.sql.*, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.source.HospitalDB.Transaction" %>
<%@ page import="com.source.HospitalDB.DAO.MedicationDAO" %>
<%@ page import="com.source.HospitalDB.Classes.Medication" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <title>Brand Name Medicines</title>
</head>
<body>
    <h1>Brand Name Medicines</h1>
    <%
        String genericName = request.getParameter("generic_name");
        if (genericName != null && !genericName.isEmpty()) {
            List<Medication> meds = MedicationDAO.getAllGeneric(genericName);
            if (meds != null && !meds.isEmpty()) {
                out.println("<ul>");
                for (Medication med : meds) {
                    out.println("<li>Generic Name: " + med.getGenericName() + ", Brand Name: " + med.getBrandName() + "</li>");
                }
                out.println("</ul>");
            } else {
                out.println("No medications found for the provided generic name.");
            }
        } else {
            out.println("No generic name provided.");
        }
    %>
</body>
</html>