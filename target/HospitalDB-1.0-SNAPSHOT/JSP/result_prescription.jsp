<%-- 
    Document   : result_prescription
    Created on : Nov 18, 2024, 2:14:52 PM
    Author     : Windows User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.ParseException" %>
<%@page import="java.util.*" %>
<%@ page import="com.source.HospitalDB.App" %>
<%@page import="com.source.HospitalDB.Classes.*" %>
<%@page import="com.source.HospitalDB.DAO.*" %>
<%@page import="java.math.BigDecimal" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prescription Result</title>
    </head>
    <body>
        <%
            // this is the most recent consultation created
            int consult_id = ConsultationDAO.getHighestID();
            Consultation consultation = ConsultationDAO.get(consult_id);
            int doctor_id = consultation.getDoctorID();
            int patient_id = consultation.getPatientID();

            String brand1 = request.getParameter("brand1");
            String brand2 = request.getParameter("brand2");
            String brand3 = request.getParameter("brand3");
            String brand4 = request.getParameter("brand4");
            String brand5 = request.getParameter("brand5");

            String dosage1Str = request.getParameter("dosage1");
            String dosage2Str = request.getParameter("dosage2");
            String dosage3Str = request.getParameter("dosage3");
            String dosage4Str = request.getParameter("dosage4");
            String dosage5Str = request.getParameter("dosage5");

            BigDecimal dosage1 = (dosage1Str != null && !dosage1Str.isEmpty()) ? new BigDecimal(dosage1Str) : BigDecimal.ZERO;
            BigDecimal dosage2 = (dosage2Str != null && !dosage2Str.isEmpty()) ? new BigDecimal(dosage2Str) : BigDecimal.ZERO;
            BigDecimal dosage3 = (dosage3Str != null && !dosage3Str.isEmpty()) ? new BigDecimal(dosage3Str) : BigDecimal.ZERO;
            BigDecimal dosage4 = (dosage4Str != null && !dosage4Str.isEmpty()) ? new BigDecimal(dosage4Str) : BigDecimal.ZERO;
            BigDecimal dosage5 = (dosage5Str != null && !dosage5Str.isEmpty()) ? new BigDecimal(dosage5Str) : BigDecimal.ZERO;

            String freq1Str = request.getParameter("freq1");
            String freq2Str = request.getParameter("freq2");
            String freq3Str = request.getParameter("freq3");
            String freq4Str = request.getParameter("freq4");
            String freq5Str = request.getParameter("freq5");

            int freq1 = (freq1Str != null && !freq1Str.isEmpty()) ? Integer.parseInt(freq1Str) : 0;
            int freq2 = (freq2Str != null && !freq2Str.isEmpty()) ? Integer.parseInt(freq2Str) : 0;
            int freq3 = (freq3Str != null && !freq3Str.isEmpty()) ? Integer.parseInt(freq3Str) : 0;
            int freq4 = (freq4Str != null && !freq4Str.isEmpty()) ? Integer.parseInt(freq4Str) : 0;
            int freq5 = (freq5Str != null && !freq5Str.isEmpty()) ? Integer.parseInt(freq5Str) : 0; 

            String[] brands = {brand1, brand2, brand3, brand4, brand5};
            BigDecimal[] dosages = {dosage1, dosage2, dosage3, dosage4, dosage5};
            int[] freqs = {freq1, freq2, freq3, freq4, freq5};
            
            out.println("<h2>Prescription Details</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>Brand</th><th>Dosage</th><th>Frequency</th></tr>");
            
            // VERIFY the BRAND exists in the DB
            Set<String> uniqueBrands = new HashSet<>();
            for (int i = 0; i < brands.length; i++) {
                if (brands[i] != null && !brands[i].isEmpty() && !uniqueBrands.contains(brands[i])) {
                    uniqueBrands.add(brands[i]);
                    out.println("<tr>");
                    if (MedicationDAO.brandNameExists(brands[i])) {
                        out.println("<td>" + brands[i] + "</td>");
                        out.println("<td>" + dosages[i] + "</td>");
                        out.println("<td>" + freqs[i] + "</td>");
                    } else {
                        brands[i] = "-1";
                    }
                    out.println("</tr>");
                } else {
                    brands[i] = "-1";
                }
            }
            out.println("</table>");

            // create the prescription
            // connect prescription to consultation
            for (int i = 0; i < brands.length; i++) {
                if (!brands[i].equals("-1") && freqs[i] > 0 && dosages[i].compareTo(BigDecimal.ZERO) > 0) {
                    Prescription prescription;
                    int med_id = MedicationDAO.getFromBrandName(brands[i]);
                        prescription = new Prescription(med_id, freqs[i], dosages[i], doctor_id, patient_id, consult_id);
                        PrescriptionDAO.add(prescription);
                }
            }
            
            // Write successful or unsuccessful message
            out.println("<br>");
            out.println("<h3>Prescription added successfully!</h3>");
            
        %>
    </body>
</html>