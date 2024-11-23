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


    <%
        String dateTimeStr = request.getParameter("birth_date");
        java.sql.Date birthDate = null;

        if (dateTimeStr != null && !dateTimeStr.isEmpty()) {
            try {
                // Attempt to convert the string to a java.sql.Date
                birthDate = java.sql.Date.valueOf(dateTimeStr);  // This works for "yyyy-MM-dd"
            } catch (IllegalArgumentException e) {
                out.println("Wrong date format. Please use yyyy-MM-dd format.");
            }
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

        String patient_first_name = request.getParameter("patient_first_name") != null ? request.getParameter("patient_first_name") : "Not Provided";
        String patient_last_name = request.getParameter("patient_last_name") != null ? request.getParameter("patient_last_name") : "Not Provided";
        int patient_id = 0;
        if (patient_first_name == null || patient_first_name.isEmpty() || "Not Provided".equals(patient_first_name)
                || patient_last_name == null || patient_last_name.isEmpty() || "Not Provided".equals(patient_last_name)) {
            System.out.println("Patient name not provided.");
            // Handle the missing name case (e.g., set a default value or take other action)
        } else {
            patient_id = PatientDAO.getFromNameBDay(patient_first_name, patient_last_name, birthDate);
            out.println(patient_id);
            out.println(patient_first_name);
            out.println(patient_last_name);
            out.println(birthDate);
            if (patient_id == 0) {
                System.out.println("Patient not found.");
            }
        }

        String doctor_first_name = request.getParameter("doctor_first_name") != null ? request.getParameter("doctor_first_name") : "Not Provided";
        String doctor_last_name = request.getParameter("doctor_last_name") != null ? request.getParameter("doctor_last_name") : "Not Provided";
        int doctor_id = 0;
        if (doctor_first_name == null || doctor_first_name.isEmpty() || "Not Provided".equals(doctor_first_name)
                || doctor_last_name == null || doctor_last_name.isEmpty() || "Not Provided".equals(doctor_last_name)) {
            System.out.println("Doctor name not provided.");
            // Handle the missing name case (e.g., set a default value or take other action)
        } else {
            doctor_id = DoctorsDAO.getFromName(doctor_first_name, doctor_last_name);
            if (doctor_id == 0) {
                System.out.println("Doctor not found.");
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

        String chief = request.getParameter("chief-complaint") != null ? request.getParameter("chief-complaint") : "Not Provided";
        // Store chief in String[] separated by comma
        String[] chief_complaint = chief.split(", ");
        // Count number of chief complaints
        int chief_count = chief_complaint.length;

        String diagnosis = request.getParameter("diagnosis") != null ? request.getParameter("diagnosis") : "Not Provided";
        // Store diagnosis in String[] separated by comma
        String[] medical_diagnosis = diagnosis.split(", ");
        // Count number of medical diagnosis
        int diagnosis_count = medical_diagnosis.length;

    %>

    <%
        if (patient_id == 0 || doctor_id == 0 || birthDate == null 
        || consultDate == null || tempDecimal.compareTo(BigDecimal.ZERO) == 0 || pulse_int == 0 
        || resp_int == 0 || sys_int == 0 || dias_int == 0 
        || spo2_int == 0) {
        
    %>
        <h1>Consultation Failed to Create</h1>
        <p>Missing or invalid data provided.</p>
        <p>Firstname: <%= patient_first_name %></p>
        <p>Lastname: <%= patient_last_name %></p>
        <p>Patient ID: <%= patient_id %></p>
        <p>Birth Date: <%= birthDate %></p>
        <p>Age: <%= age %></p>
        <p>Doctor Firstname: <%= doctor_first_name %></p>
        <p>Doctor Lastname: <%= doctor_last_name %></p>
        <p>Doctor ID: <%= doctor_id %></p>
        <p>Temperature: <%= tempDecimal %></p>
        <p>Pulse: <%= pulse_int %></p>
        <p>Respiratory Rate: <%= resp_int %></p>
        <p>Blood Pressure: <%= sys_int %>/<%= dias_int %></p>
        <p>Oxygen Saturation (SpO2): <%= spo2_int %></p>
    <%

        } else {
            int vitalSignsID = VitalSignsDAO.getHighestVitalSignsID() + 1;
            VitalSigns vitalSigns = new VitalSigns(vitalSignsID, tempDecimal, pulse_int, resp_int, sys_int, dias_int, spo2_int);
            VitalSignsDAO.add(vitalSigns);
            
            // default prescription and lab is 1
            int consult_new = ConsultationDAO.getHighestID() + 1;
            Consultation consultation = new Consultation(consult_new, doctor_id, patient_id, vitalSignsID);
            ConsultationDAO.add(consultation);
            int consult_id_check = (ConsultationDAO.getConsultationID(consultation));
            
            // Create Chief Complaint
            for (int i = 0; i < chief_count; i++) {
                if (ChiefComplaintDAO.exists(chief_complaint[i])) {
                    ChiefComplaint chiefComplaint = ChiefComplaintDAO.getByName(chief_complaint[i]);
                    Consult_ChiefComplaint consult_chiefComplaint = new Consult_ChiefComplaint(consult_id_check, chiefComplaint.getComplaintID());
                    Consult_ChiefComplaintDAO.add(consult_chiefComplaint);
                } else {
                    chief_complaint[i] = "No Chief Complaint found with that name"; // Reset the value
                }
                
            }

            // Create Medical Diagnosis
            for (int i = 0; i < diagnosis_count; i++) {
                if (MedicalDiagnosisDAO.exists(medical_diagnosis[i])) {
                    MedicalDiagnosis medicalDiagnosis = MedicalDiagnosisDAO.getByName(medical_diagnosis[i]);
                    Consult_MedDiagnosis consult_medDiagnosis = new Consult_MedDiagnosis(consultation.getConsultationID(), medicalDiagnosis.getDiagnosisID());
                    Consult_MedDiagnosisDAO.add(consult_medDiagnosis);
                } else {
                    medical_diagnosis[i] = "No Medical Diagnosis found with that name"; // Reset the value
                }
            }

            
    %>
        <h1>Consultation Successfully Created</h1>
        <p>Firstname: <%= patient_first_name %></p>
        <p>Lastname: <%= patient_last_name %></p>
        <p>Patient ID: <%= patient_id %></p>
        <p>Birth Date: <%= birthDate %></p>
        <p>Age: <%= age %></p>
        <p>Doctor Firstname: <%= doctor_first_name %></p>
        <p>Doctor Lastname: <%= doctor_last_name %></p>
        <p>Doctor ID: <%= doctor_id %></p>
        <p>Temperature: <%= tempDecimal %></p>
        <p>Pulse: <%= pulse_int %></p>
        <p>Respiratory Rate: <%= resp_int %></p>
        <p>Blood Pressure: <%= sys_int %>/<%= dias_int %></p>
        <p>Oxygen Saturation (SpO2): <%= spo2_int %></p>

        <h2>Chief Complaints:</h2>
        <ul>
            <% for (String complaint : chief_complaint) { %>
            <li><%= complaint %></li>
            <% } %>
        </ul>

        <h2>Medical Diagnoses:</h2>
        <ul>
            <% for (String show_diagnosis : medical_diagnosis) { %>
            <li><%= show_diagnosis %></li>
            <% } %>
        </ul>

        <!-- Form for Prescription -->
        <form id="prescriptionForm" method="POST" target="_blank">
            <div class="shape text create-4953d79c36fa">
                <div class="text-node-html" id="html-text-node-1a60e743-710c-8078-8005-4953d79c36fa" data-x="1896" data-y="838">
                    <div class="root rich-text root-0" style="display: flex; white-space: break-spaces; align-items: flex-start;" xmlns="http://www.w3.org/1999/xhtml">
                        <div class="paragraph-set root-0-paragraph-set-0">
                            <p class="paragraph root-0-paragraph-set-0-paragraph-0" dir="ltr">
                                <button type="button" 
                                id="prescribe" 
                                name="prescribe" 
                                value="Prescribe" 
                                class="text-node root-0-paragraph-set-0-paragraph-0-text-0" 
                                style="color: black; text-transform: none; line-break: auto; overflow-wrap: initial; white-space: break-spaces; font-size: 18px; text-rendering: geometricPrecision; caret-color: rgba(242, 235, 213, 1); text-decoration: none; --font-id: gfont-noto-sans; --fills: [[ '^ ', '~:fill-color', '#F2EBD5', '~:fill-color-ref-id', '~ua19c5e35-ca12-808b-8005-44469bb3a18a', '~:fill-color-ref-file', '~ua5adc15f-fb38-8092-8005-41b26c440392', '~:fill-opacity', 1]]; letter-spacing: 0px; font-family: 'Noto Sans'; font-style: normal; font-weight: 600;" 
                                onclick="submitForm('prescriptionForm', 'add_prescription.jsp')">Prescribe</button>
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
                                <button type="button" 
                                id="report" 
                                name="report" 
                                value="lab report" 
                                class="text-node root-0-paragraph-set-0-paragraph-0-text-0" 
                                style="color: black; text-transform: none; line-break: auto; overflow-wrap: initial; white-space: break-spaces; font-size: 18px; text-rendering: geometricPrecision; caret-color: rgba(242, 235, 213, 1); text-decoration: none; --font-id: gfont-noto-sans; --fills: [[ '^ ', '~:fill-color', '#F2EBD5', '~:fill-color-ref-id', '~ua19c5e35-ca12-808b-8005-44469bb3a18a', '~:fill-color-ref-file', '~ua5adc15f-fb38-8092-8005-41b26c440392', '~:fill-opacity', 1]]; letter-spacing: 0px; font-family: 'Noto Sans'; font-style: normal; font-weight: 600;" 
                                onclick="submitForm('labReportForm', 'add_labreport.jsp')">Lab Report</button>
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
        }
    %>
</body>
</html>