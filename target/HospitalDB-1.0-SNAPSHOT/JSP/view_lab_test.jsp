<%@ page import="com.source.HospitalDB.DAO.LabTestDAO" %>
<%@ page import="com.source.HospitalDB.Classes.LabTest" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html>
<head>
    <title>View Lab Tests</title>
</head>
<body>
    <h1>Lab Tests</h1>
    <table border="1">
        <tr>
            <th>Test ID</th>
            <th>Test Description</th>
        </tr>
        <%
            try {
                List<LabTest> labTests = LabTestDAO.getAll();
                for (LabTest labTest : labTests) {
        %>
                    <tr>
                        <td><%= labTest.getTestID() %></td>
                        <td><%= labTest.getTestDescription() %></td>
                    </tr>
        <%
                }
            } catch (SQLException e) {
                out.println("<tr><td colspan='2'>Error: " + e.getMessage() + "</td></tr>");
            }
        %>
    </table>
</body>
</html>