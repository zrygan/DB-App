<%-- 
    Document   : result_prescription
    Created on : Nov 18, 2024, 2:14:52 PM
    Author     : Windows User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.ParseException" %>
<%@page import="java.util.*" %>
<%@ page import="com.source.HospitalDB.App" %>
<%@page import="com.source.HospitalDB.Classes.Prescription" %>
<%@page import="com.source.HospitalDB.DAO.PrescriptionDAO" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prescription Result</title>
    </head>
    <body>
        <%
            String patient_id = request.getParameter("patient_id");
            String doctor_id = request.getParameter("doctor_id");
            String prescription_date = request.getParameter("prescription_date");
            String prescription = request.getParameter("prescription");
            String prescription_id = request.getParameter("prescription_id");
            
            Prescription p = new Prescription();
            p.setPatient_id(patient_id);
            p.setDoctor_id(doctor_id);
            p.setPrescription_date(prescription_date);
            p.setPrescription(prescription);
            p.setPrescription_id(prescription_id);
            
            PrescriptionDAO pd = new PrescriptionDAO();
            boolean result = pd.createPrescription(p);
            
            if(result){
        %>
            <h1>Prescription Successfully Created</h1>
        <%    
            } else {
        %>
        <h1>Prescription Failed to Create</h1>
        <%
            }
        %>
    </body>
</html>