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
        String doctorName = request.getParameter("doctor_name");
        if (doctorName != null && !doctorName.trim().isEmpty()) {
            String[] nameParts = WebTools.splitName(doctorName);
            if (nameParts != null && nameParts.length == 2) {
                String lastName = nameParts[0].trim();
                String firstName = nameParts[1].trim();
                int doctorId = DoctorsDAO.getFromName(firstName, lastName);
                if (doctorId != 0) {
                    Doctors doctor = DoctorsDAO.getFromID(doctorId);
                    if (doctor != null) {
                        List<Doctors> doctorsWithSameSpec = DoctorsDAO.getSpecs(doctor.getSpecialization());
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
                } else {
                    out.println("<p>Doctor not found.</p>");
                }
            } else {
                out.println("<p>Invalid doctor name format.</p>");
            }
        } else {
            out.println("<p>No doctor name provided.</p>");
        }
    %>
</body>
</html>