<%-- 
    Document   : create_consultation _result
    Created on : Nov 18, 2024, 2:53:20 PM
    Author     : Windows User
--%>

<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%@page import="com.source.HospitalDB.App" %>
<%@page import="com.source.HospitalDB.DAO.*" %>
<%@page import="com.source.HospitalDB.Classes.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.source.HospitalDB.App" %>
<%@ page import="com.source.HospitalDB.WebTools" %>
<%@ page import="java.text.ParseException" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create Consultation Result</title>
</head>
<body>
    <!-- Form for Prescription -->
    <form id="prescriptionForm" method="POST" target="_blank">
        <div class="shape text create-4953d79c36fa">
            <div class="text-node-html" id="html-text-node-1a60e743-710c-8078-8005-4953d79c36fa" data-x="1896" data-y="838">
                <div class="root rich-text root-0" style="display: flex; white-space: break-spaces; align-items: flex-start;" xmlns="http://www.w3.org/1999/xhtml">
                    <div class="paragraph-set root-0-paragraph-set-0">
                        <p class="paragraph root-0-paragraph-set-0-paragraph-0" dir="ltr">
                            <button type="button" id="prescribe" name="prescribe" value="Prescribe" class="text-node root-0-paragraph-set-0-paragraph-0-text-0" style="color: rgba(242, 235, 213, 1); text-transform: none; line-break: auto; overflow-wrap: initial; white-space: break-spaces; font-size: 18px; text-rendering: geometricPrecision; caret-color: rgba(242, 235, 213, 1); text-decoration: none; --font-id: gfont-noto-sans; --fills: [[ '^ ', '~:fill-color', '#F2EBD5', '~:fill-color-ref-id', '~ua19c5e35-ca12-808b-8005-44469bb3a18a', '~:fill-color-ref-file', '~ua5adc15f-fb38-8092-8005-41b26c440392', '~:fill-opacity', 1]]; letter-spacing: 0px; font-family: 'Noto Sans'; font-style: normal; font-weight: 600;" onclick="submitForm('prescriptionForm', 'add_prescription.jsp')">Prescribe</button>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <!-- Form for Lab Report -->
    <form id="labReportForm" method="POST" target="_blank">
        <div class="shape text create-4953d79c36fa">
            <div class="text-node-html" id="html-text-node-1a60e743-710c-8078-8005-4953d79c36fa" data-x="1896" data-y="838">
                <div class="root rich-text root-0" style="display: flex; white-space: break-spaces; align-items: flex-start;" xmlns="http://www.w3.org/1999/xhtml">
                    <div class="paragraph-set root-0-paragraph-set-0">
                        <p class="paragraph root-0-paragraph-set-0-paragraph-0" dir="ltr">
                            <button type="button" id="report" name="report" value="lab report" class="text-node root-0-paragraph-set-0-paragraph-0-text-0" style="color: rgba(242, 235, 213, 1); text-transform: none; line-break: auto; overflow-wrap: initial; white-space: break-spaces; font-size: 18px; text-rendering: geometricPrecision; caret-color: rgba(242, 235, 213, 1); text-decoration: none; --font-id: gfont-noto-sans; --fills: [[ '^ ', '~:fill-color', '#F2EBD5', '~:fill-color-ref-id', '~ua19c5e35-ca12-808b-8005-44469bb3a18a', '~:fill-color-ref-file', '~ua5adc15f-fb38-8092-8005-41b26c440392', '~:fill-opacity', 1]]; letter-spacing: 0px; font-family: 'Noto Sans'; font-style: normal; font-weight: 600;" onclick="submitForm('labReportForm', 'add_labreport.jsp')">Lab Report</button>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <script>
        function submitForm(formId, action) {
            const form = document.getElementById(formId);
            form.action = action;
            form.target = '_blank'; // Open in a new tab
            form.submit();
        }
    </script>

    <%
        String birthStr = request.getParameter("birth_date") != null ? request.getParameter("birth_date") : "Not Provided";
        Timestamp birthDate = null;
        if (birthStr != null && !birthStr.isEmpty() && !"Not Provided".equals(birthStr)) {
            try {
                // Convert the String to java.util.Date first
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = formatter.parse(birthStr);

                // Convert java.util.Date to java.sql.Timestamp
                birthDate = new Timestamp(utilDate.getTime());
            } catch (ParseException e) {
                System.out.println("Invalid date format: " + e.getMessage());
                // Handle the invalid format case
            }
        } else {
            System.out.println("Birth date not provided.");
            // Handle the missing date case (e.g., set a default value or take other action)
        }

        Timestamp consultDate = App.time_now();

        int age = -1; // Default value in case of error
        if (birthDate != null && consultDate != null) {
            Calendar birthCal = Calendar.getInstance();
            Calendar consultCal = Calendar.getInstance();

            birthCal.setTime(birthDate);
            consultCal.setTime(consultDate);

            int birthYear = birthCal.get(Calendar.YEAR);
            int consultYear = consultCal.get(Calendar.YEAR);

            age = consultYear - birthYear;

            // Check if the birth date hasn't occurred yet in the consult year
            int birthMonth = birthCal.get(Calendar.MONTH);
            int birthDay = birthCal.get(Calendar.DAY_OF_MONTH);
            int consultMonth = consultCal.get(Calendar.MONTH);
            int consultDay = consultCal.get(Calendar.DAY_OF_MONTH);

            if (consultMonth < birthMonth || (consultMonth == birthMonth && consultDay < birthDay)) {
                age--; // Adjust age if the birthday hasn't occurred yet
            }
        }

        String patient_name = request.getParameter("patient_name") != null ? request.getParameter("patient_name") : "Not Provided";
        String[] patient_parts = WebTools.splitName(patient_name);
        int patient_id = 0;
        for (Patient p : App.getPatientMap().values()){
            if (p.getFirstname().equals(patient_parts[1]) && p.getLastname().equals(patient_parts[0]) && p.getBirthDate().equals(birthDate)){
                patient_id = p.getPatientId();
            }
        }

        String doctor_name = request.getParameter("doctor_name") != null ? request.getParameter("doctor_name") : "Not Provided";
        String[] doctor_parts = WebTools.splitName(doctor_name);
        int doctor_id = 0;
        for (Doctors p : App.getDoctorsMap().values()){
            if (p.getFirstname().equals(doctor_parts[1]) && p.getLastname().equals(doctor_parts[0])){
                doctor_id = p.getDoctorId();
            }
        }

        String temperature = request.getParameter("temperature") != null ? request.getParameter("temperature") : "0";
        BigDecimal tempDecimal = BigDecimal.ZERO;
        try {
            tempDecimal = new BigDecimal(temperature);
        } catch (NumberFormatException e) {
            System.out.println("Invalid temperature format: " + e.getMessage());
        }

        String pulse = request.getParameter("pulse") != null ? request.getParameter("pulse") : "0";
        int pulse_int = 0;
        try {
            pulse_int = Integer.parseInt(pulse);
        } catch (NumberFormatException e) {
            System.out.println("Invalid pulse format: " + e.getMessage());
        }

        String respiratory_rate = request.getParameter("respiratory_rate") != null ? request.getParameter("respiratory_rate") : "0";
        int resp_int = 0;
        try {
            resp_int = Integer.parseInt(respiratory_rate);
        } catch (NumberFormatException e) {
            System.out.println("Invalid respiratory rate format: " + e.getMessage());
        }

        String systolic = request.getParameter("systolic_bp") != null ? request.getParameter("systolic_bp") : "0";
        int sys_int = 0;
        try {
            sys_int = Integer.parseInt(systolic);
        } catch (NumberFormatException e) {
            System.out.println("Invalid systolic BP format: " + e.getMessage());
        }

        String diastolic = request.getParameter("diastolic_bp") != null ? request.getParameter("diastolic_bp") : "0";
        int dias_int = 0;
        try {
            dias_int = Integer.parseInt(diastolic);
        } catch (NumberFormatException e) {
            System.out.println("Invalid diastolic BP format: " + e.getMessage());
        }

        String spo2 = request.getParameter("spo2") != null ? request.getParameter("spo2") : "0";
        int spo2_int = 0;
        try {
            spo2_int = Integer.parseInt(spo2);
        } catch (NumberFormatException e) {
            System.out.println("Invalid SpO2 format: " + e.getMessage());
        }
    %>

    <%
        if (patient_id == 0 || doctor_id == 0 || birthDate == null 
        || consultDate == null || tempDecimal.compareTo(BigDecimal.ZERO) == 0 || pulse_int == 0 
        || resp_int == 0 || sys_int == 0 || dias_int == 0 
        || spo2_int == 0) {
    %>
        <h1>Consultation Failed to Create</h1>
        <p>Missing or invalid data provided.</p>
        <p>Firstname: <%= patient_parts[1] %></p>
        <p>Lastname: <%= patient_parts[0] %></p>
        <p>Patient ID: <%= patient_id %></p>
        <p>Birth Date: <%= birthDate %></p>
        <p>Age: <%= age %></p>
        <p>Doctor Firstname: <%= doctor_parts[1] %></p>
        <p>Doctor Lastname: <%= doctor_parts[0] %></p>
        <p>Doctor ID: <%= doctor_id %></p>
        <p>Temperature: <%= tempDecimal %></p>
        <p>Pulse: <%= pulse_int %></p>
        <p>Respiratory Rate: <%= resp_int %></p>
        <p>Blood Pressure: <%= sys_int %>/<%= dias_int %></p>
        <p>Oxygen Saturation (SpO2): <%= spo2_int %></p>
    <%
        } else {
            VitalSigns vitalSigns = new VitalSigns(tempDecimal, pulse_int, resp_int, sys_int, dias_int, spo2_int);
            VitalSignsDAO.add(vitalSigns);
            
            Consultation consultation = new Consultation(0, doctor_id, patient_id, vitalSigns.getVitalSignsID(), 0);
            ConsultationDAO.add(consultation);

            int consult_id = consultation.getConsultationID();
            request.setAttribute("consult_id", consult_id);
            request.setAttribute("patient_id", patient_id);
            request.setAttribute("doctor_id", doctor_id);
    %>
        <h1>Consultation Successfully Created</h1>
        <p>Name: <%= patient_name %></p>
        <p>Age: <%= age %></p>
        <p>Attending Physician: <%= doctor_name %></p>
        <p>Temperature: <%= tempDecimal %></p>
        <p>Pulse: <%= pulse_int %></p>
        <p>Respiratory Rate: <%= resp_int %></p>
        <p>Blood Pressure: <%= sys_int %>/<%= dias_int %></p>
        <p>Oxygen Saturation (SpO2): <%= spo2_int %></p>
    <%
        }
    %>
</body>
</html>