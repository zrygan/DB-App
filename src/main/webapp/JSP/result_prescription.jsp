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
<%@page import="com.source.HospitalDB.DAO.ConsultationDAO" %>
<%@page import="com.source.HospitalDB.Classes.Consultation" %>
<%@page import="com.source.HospitalDB.DAO.PatientDAO" %>
<%@page import="com.source.HospitalDB.Classes.Patient" %>
<%@page import="com.source.HospitalDB.DAO.DoctorDAO" %>
<%@page import="com.source.HospitalDB.Classes.Doctor" %>
<%@page import="com.source.HospitalDB.Classes.Medicaation" %>
<%@page import="com.source.HospitalDB.DAO.MedicationDAO" %>
<%@page import="java.math.BigDecimal" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prescription Result</title>
    </head>
    <body>
        <%
            int consult_id = Integer.parseInt(request.getParameter("consult_id"));
            int doctor_id = Integer.parseInt(request.getParameter("doctor_id"));
            int patient_id = Integer.parseInt(request.getParameter("patient_id"));

            String[] generic_names = {
                request.getParameter("generic_name"),
                request.getParameter("generic_name2"),
                request.getParameter("generic_name3"),
                request.getParameter("generic_name4"),
                request.getParameter("generic_name5")
            };

            int[] generic_ids = new int[5];
            for (int i = 0; i < generic_names.length; i++) {
                if (generic_names[i] != null && !generic_names[i].isEmpty()) {
                    generic_ids[i] = MedicationDAO.getFromGenericName(generic_names[i]);
                } else {
                    generic_ids[i] = -1; // Invalid ID for empty or null generic name
                }
            }

            int[] frequencies = new int[5];
            for (int i = 0; i < frequencies.length; i++) {
                try {
                    frequencies[i] = Integer.parseInt(request.getParameter("frequency" + (i == 0 ? "" : (i + 1))));
                } catch (NumberFormatException e) {
                    frequencies[i] = -1; // Invalid frequency for non-numeric input
                }
            }

            BigDecimal[] dosages = new BigDecimal[5];
            for (int i = 0; i < dosages.length; i++) {
                try {
                    dosages[i] = new BigDecimal(request.getParameter("dosage" + (i == 0 ? "" : (i + 1))));
                } catch (NumberFormatException e) {
                    dosages[i] = BigDecimal.ZERO; // Invalid dosage for non-numeric input
                }
            }

            int validPrescriptionCount = 0;

            for (int i = 0; i < generic_ids.length; i++) {
                if (generic_ids[i] != -1 && frequencies[i] > 0 && dosages[i].compareTo(BigDecimal.ZERO) > 0) {
                    validPrescriptionCount++;
                }
            }

            if (validPrescriptionCount > 0) {
                for (int i = 0; i < generic_ids.length; i++) {
                    if (generic_ids[i] != -1 && frequencies[i] > 0 && dosages[i].compareTo(BigDecimal.ZERO) > 0) {
                        Prescription prescription = new Prescription(generic_ids[i], frequencies[i], dosages[i], doctor_id, patient_id);
                        PrescriptionDAO.add(prescription);
                    }
                }
            int firstPrescriptionId = -1;

            for (int i = 0; i < generic_ids.length; i++) {
                if (generic_ids[i] != -1 && frequencies[i] > 0 && dosages[i].compareTo(BigDecimal.ZERO) > 0) {
                    Prescription prescription;
                    if (firstPrescriptionId == -1) {
                        prescription = new Prescription(generic_ids[i], frequencies[i], dosages[i], doctor_id, patient_id);
                        firstPrescriptionId = prescription.getId();
                        PrescriptionDAO.add(prescription);
                    } else {
                        Prescription prescription_new = new Prescription(firstPrescriptionId, generic_ids[i], frequencies[i], dosages[i], doctor_id, patient_id);
                        PrescriptionDAO.add(prescription_new, firstPrescriptionId);
                    }
                }
            }
            
                Consultation consultation = ConsultationDAO.get(consult_id);
                consultation.setPrescriptionID(firstPrescriptionId);
                ConsultationDAO.update(consultation);

        %>
                <h1>Prescription Successfully Created</h1>
                <table>
                    <tr>
                        <th>Generic Name</th>
                        <th>Frequency</th>
                        <th>Dosage</th>
                    </tr>
                    <%
                        for (int i = 0; i < generic_ids.length; i++) {
                            if (generic_ids[i] != -1 && frequencies[i] > 0 && dosages[i].compareTo(BigDecimal.ZERO) > 0) {
                    %>
                                <tr>
                                    <td><%= generic_names[i] %></td>
                                    <td><%= frequencies[i] %></td>
                                    <td><%= dosages[i] %></td>
                                </tr>
                    <%
                            }
                        }
                    %>
                </table>
        <%
            } else {
        %>
                <h1>No Valid Prescriptions Provided</h1>
                <p>Prescription must have at least one valid medication with a frequency greater than 0 and a dosage greater than 0.</p>
        <%
            }
        %>
    </body>
</html>