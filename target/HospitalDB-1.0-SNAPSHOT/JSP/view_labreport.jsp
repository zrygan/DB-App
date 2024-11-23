<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="com.source.HospitalDB.DAO.LabReportDAO" %>
<%@page import="com.source.HospitalDB.DAO.LabTestDAO" %>
<%@page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Lab Report</title>
    </head>
    <body>
        <h1>Lab Report Details</h1>
        <%
            int labReportID = Integer.parseInt(request.getParameter("labReportID"));
            List<Integer> labTestIDs = LabReportDAO.getLabTestID(labReportID);
            List<String> labTestDescriptions = new ArrayList<>();
            
            for (int labTestID : labTestIDs) {
                labTestDescriptions.add(LabTestDAO.getTestDescription(labTestID));
            }
        %>
        <table border="1">
            <tr>
                <th>Lab Test ID</th>
                <th>Lab Test Description</th>
            </tr>
            <%
                for (int i = 0; i < labTestIDs.size(); i++) {
            %>
            <tr>
                <td><%= labTestIDs.get(i) %></td>
                <td><%= labTestDescriptions.get(i) %></td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>