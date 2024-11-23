<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.source.HospitalDB.DAO.DoctorsDAO" %>
<%@page import="com.source.HospitalDB.Classes.Doctors" %>
<%@page import="com.source.HospitalDB.WebTools" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Doctors with Same Specialization</title>
</head>
<body>
    <h1>Doctors with Same Specialization</h1>
    <%
        String specialization = request.getParameter("specialization");
        if (specialization != null) {
            List<Doctors> doctorsWithSameSpec = DoctorsDAO.getSpecs(specialization);
                if (!doctorsWithSameSpec.isEmpty()) {
                    %>
                    <table border="1">
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Specialization</th>
                            <th>Phone Number</th>
                            <th>Email</th>
                        </tr>
                        <%
                        for (Doctors d : doctorsWithSameSpec) {
                            %>
                            <tr>
                                <td><%= d.getFirstname() %></td>
                                <td><%= d.getLastname() %></td>
                                <td><%= d.getSpecialization() %></td>
                                <td><%= d.getPhoneNumber() %></td>
                                <td><%= d.getEmail() %></td>
                            </tr>
                            <%
                        }
                        %>
                    </table>
                    <%
                } else {
                    out.println("<p>No doctors found with the same specialization.</p>");
                }
        } else {
                out.println("<p>Doctor not found.</p>");
        }
%>
</body>
</html>