<%-- 
    Document   : result_labreport
    Created on : Nov 18, 2024, 2:14:52 PM
    Author     : Windows User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="com.source.HospitalDB.Classes.*" %>
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
            // this is the most recent consultation created
            int consult_id = ConsultationDAO.getHighestID();
            Consultation consultation = ConsultationDAO.get(consult_id);

            String[] lab_test_names = {
                request.getParameter("labtest1"),
                request.getParameter("labtest2"),
                request.getParameter("labtest3"),
                request.getParameter("labtest4"),
                request.getParameter("labtest5")
            };

            int[] lab_test_ids = new int[5];
            for (int i = 0; i < lab_test_names.length; i++) {
                if (lab_test_names[i] != null && !lab_test_names[i].isEmpty()) {
                    lab_test_ids[i] = LabTestDAO.getByDescription(lab_test_names[i]);
                } else {
                    lab_test_ids[i] = -1; // Invalid ID for empty or null lab test name
                }
            }
            // Check for duplicates and make only one valid
            Set<Integer> uniqueLabTestIds = new HashSet<>();
            for (int i = 0; i < lab_test_ids.length; i++) {
                if (lab_test_ids[i] != -1) {
                    if (uniqueLabTestIds.contains(lab_test_ids[i])) {
                        lab_test_ids[i] = -1; // Mark as invalid if duplicate
                    } else {
                        uniqueLabTestIds.add(lab_test_ids[i]);
                    }
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

                            labReport = new LabReport(lab_test_ids[i], consult_id);
                            firstLabReportId = labReport.getLabReportID();
                            LabReportDAO.add(labReport);

                    }
                }
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