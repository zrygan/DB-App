<%-- 
    Document   : result_labreport
    Created on : Nov 18, 2024, 2:14:52 PM
    Author     : Windows User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.ParseException" %>
<%@page import="com.source.HospitalDB.Classes.LabReport" %>
<%@page import="com.source.HospitalDB.Classes.LabTest" %>
<%@page import="com.source.HospitalDB.DAO.LabReportDAO" %>
<%@page import="com.source.HospitalDB.DAO.LabTestDAO" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lab Report Result</title>
    </head>
    <body>
        <% /* FIXME: Fix this */
            String patient_id = request.getParameter("patient_id");
            String lab_test_id = request.getParameter("lab_test_id");
            String lab_report_date = request.getParameter("lab_report_date");
            String lab_report = request.getParameter("lab_report");
            String lab_report_id = request.getParameter("lab_report_id");
            
            LabReport lr = new LabReport();
            lr.setPatient_id(patient_id);
            lr.setLab_test_id(lab_test_id);
            lr.setLab_report_date(lab_report_date);
            lr.setLab_report(lab_report);
            lr.setLab_report_id(lab_report_id);
            
            LabReportDAO lrd = new LabReportDAO();
            boolean result = lrd.createLabReport(lr);
            
            if(result){
        %>
            <h1>Lab Report Successfully Created</h1>
        <%    
            } else {
        %>
        <h1>Lab Report Failed to Create</h1>
        <%
            }
        %>
    </body>