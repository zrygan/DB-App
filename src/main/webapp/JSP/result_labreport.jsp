<%-- 
    Document   : result_labreport
    Created on : Nov 18, 2024, 2:14:52 PM
    Author     : Windows User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="com.source.HospitalDB.Classes.LabReport" %>
<%@page import="com.source.HospitalDB.Classes.LabTest" %>
<%@page import="com.source.HospitalDB.DAO.LabReportDAO" %>
<%@page import="com.source.HospitalDB.DAO.LabTestDAO" %>
<%@page import="com.source.HospitalDB.DAO.ConsultationDAO" %>
<%@page import="com.source.HospitalDB.Classes.Consultation" %>
<%@page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lab Report Result</title>
    </head>
    <body>
        <%
            int consult_id = Integer.parseInt(request.getParameter("consult_id"));
            int doctor_id = Integer.parseInt(request.getParameter("doctor_id"));
            int patient_id = Integer.parseInt(request.getParameter("patient_id"));

            String[] lab_test_names = {
                request.getParameter("laboratory_name"),
                request.getParameter("laboratory_name2"),
                request.getParameter("laboratory_name3"),
                request.getParameter("laboratory_name4"),
                request.getParameter("laboratory_name5")
            };

            int[] lab_test_ids = new int[5];
            for (int i = 0; i < lab_test_names.length; i++) {
                if (lab_test_names[i] != null && !lab_test_names[i].isEmpty()) {
                    lab_test_ids[i] = LabTestDAO.getFromName(lab_test_names[i]);
                } else {
                    lab_test_ids[i] = -1; // Invalid ID for empty or null lab test name
                }
            }

            int validLabReportCount = 0;

            for (int i = 0; i < lab_test_ids.length; i++) {
                if (lab_test_ids[i] != -1) {
                    validLabReportCount++;
                }
            }

            if (validLabReportCount > 0) {
                int firstLabReportId = -1;

                for (int i = 0; i < lab_test_ids.length; i++) {
                    if (lab_test_ids[i] != -1) {
                        LabReport labReport;
                        if (firstLabReportId == -1) {
                            labReport = new LabReport(lab_test_ids[i]);
                            firstLabReportId = labReport.getLabReportID();
                            LabReportDAO.add(labReport);
                        } else {
                            LabReport labReport_new = new LabReport(firstLabReportId, lab_test_ids[i]);
                            LabReportDAO.add(labReport_new);
                        }
                    }
                }

                Consultation consultation = ConsultationDAO.get(consult_id);
                consultation.setLabReportID(firstLabReportId);
                ConsultationDAO.update(consultation);
        %>
                <h1>Lab Report Successfully Created</h1>
                <table>
                    <tr>
                        <th>Lab Test Name</th>
                    </tr>
                    <%
                        for (int i = 0; i < lab_test_ids.length; i++) {
                            if (lab_test_ids[i] != -1) {
                    %>
                                <tr>
                                    <td><%= lab_test_names[i] %></td>
                                </tr>
                    <%
                            }
                        }
                    %>
                </table>
        <%
            } else {
        %>
                <h1>No Valid Lab Reports Provided</h1>
                <p>Lab report must have at least one valid lab test name.</p>
        <%
            }
        %>
    </body>
</html>