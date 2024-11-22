<%-- 
    Document   : create_consultation _result
    Created on : Nov 18, 2024, 2:53:20 PM
    Author     : Windows User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.source.HospitalDB.DAO.DoctorsDAO" %>
<%@page import="com.source.HospitalDB.Classes.Doctors" %><
<%@page import="com.source.HospitalDB.DAO.DoctorsDAO" %>
<%@page import="java.math.BigDecimal" %>
<%@page import="java.sql.Date" %>
<%@page import="java.sql.Timestamp" %>
<%@page import="com.source.HospitalDB.Classes.Consultation" %>
<%@page import="com.source.HospitalDB.DAO.ConsultationDAO" %>
<%@page import="java.time.Year" %>
<%@page import="java.text.ParseException" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.*" %>
<%@page import="com.source.HospitalDB.Classes.VitalSigns" %>
<%@page import="com.source.HospitalDB.Classes.Prescription" %>
<%@page import="com.source.HospitalDB.DAO.PrescriptionDAO" %>
<%@page import="com.source.HospitalDB.DAO.VitalSignsDAO" %>
<%@page import="com.source.HospitalDB.DAO.PatientDao" %>
<%@page import="com.source.HospitalDB.Classes.Patient" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.source.HospitalDB.App" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Consultation Result</title>   
    </head>
    <body>
        <form id="myForm" method="POST">
            <div class="shape text create-4953d79c36fa">
              <div
                class="text-node-html"
                id="html-text-node-1a60e743-710c-8078-8005-4953d79c36fa"
                data-x="1896"
                data-y="838"
              >
                <div
                  class="root rich-text root-0"
                  style="
                    display: flex;
                    white-space: break-spaces;
                    align-items: flex-start;
                  "
                  xmlns="http://www.w3.org/1999/xhtml"
                >
                  <div class="paragraph-set root-0-paragraph-set-0">
                    <p
                      class="paragraph root-0-paragraph-set-0-paragraph-0"
                      dir="ltr"
                    >
                      <button
                        type="button"
                        id="prescribe"
                        name="prescribe"
                        value="Prescribe"
                        class="text-node root-0-paragraph-set-0-paragraph-0-text-0"
                        style="
                          color: rgba(242, 235, 213, 1);
                          text-transform: none;
                          line-break: auto;
                          overflow-wrap: initial;
                          white-space: break-spaces;
                          font-size: 18px;
                          text-rendering: geometricPrecision;
                          caret-color: rgba(242, 235, 213, 1);
                          text-decoration: none;
                          --font-id: gfont-noto-sans;
                          --fills: [[ '^ ', '~:fill-color', '#F2EBD5',
                            '~:fill-color-ref-id',
                            '~ua19c5e35-ca12-808b-8005-44469bb3a18a',
                            '~:fill-color-ref-file',
                            '~ua5adc15f-fb38-8092-8005-41b26c440392',
                            '~:fill-opacity', 1]];
                          letter-spacing: 0px;
                          font-family: 'Noto Sans';
                          font-style: normal;
                          font-weight: 600;
                        "
                        onclick="submitForm('add_prescription.jsp')">Prescribe</button>
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <jsp:forward page="add_prescription.jsp" /> <!-- FIXME: Careful idk how this works -->
        </form>
        <form action="JSP/add_labreport.jsp" method="post">
            <div class="shape text create-4953d79c36fa">
              <div
                class="text-node-html"
                id="html-text-node-1a60e743-710c-8078-8005-4953d79c36fa"
                data-x="1896"
                data-y="838"
              >
                <div
                  class="root rich-text root-0"
                  style="
                    display: flex;
                    white-space: break-spaces;
                    align-items: flex-start;
                  "
                  xmlns="http://www.w3.org/1999/xhtml"
                >
                  <div class="paragraph-set root-0-paragraph-set-0">
                    <p
                      class="paragraph root-0-paragraph-set-0-paragraph-0"
                      dir="ltr"
                    >
                      <button
                        type="button"
                        id="report"
                        name="report"
                        value="lab report"
                        class="text-node root-0-paragraph-set-0-paragraph-0-text-0"
                        style="
                          color: rgba(242, 235, 213, 1);
                          text-transform: none;
                          line-break: auto;
                          overflow-wrap: initial;
                          white-space: break-spaces;
                          font-size: 18px;
                          text-rendering: geometricPrecision;
                          caret-color: rgba(242, 235, 213, 1);
                          text-decoration: none;
                          --font-id: gfont-noto-sans;
                          --fills: [[ '^ ', '~:fill-color', '#F2EBD5',
                            '~:fill-color-ref-id',
                            '~ua19c5e35-ca12-808b-8005-44469bb3a18a',
                            '~:fill-color-ref-file',
                            '~ua5adc15f-fb38-8092-8005-41b26c440392',
                            '~:fill-opacity', 1]];
                          letter-spacing: 0px;
                          font-family: 'Noto Sans';
                          font-style: normal;
                          font-weight: 600;
                        "
                        onclick="submitForm('add_labreport.jsp')">Lab Report</button>
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <jsp:forward page="add_labreport.jsp" /> <!-- FIXME: Careful idk how this works -->
        </form>
        <% 
        String birthStr = request.getParameter("birth_date") != null ? request.getParameter("birth_date") : "Not Provided";
        Timestamp birthDate = null;
        if (birthStr != null && !birthStr.isEmpty()) {
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
        int patient_id = PatientDao.getFromNameBDay(patient_name, birthDate);

        String doctor_name = request.getParameter("doctor_name") != null ? request.getParameter("doctor_name") : "Not Provided";
        int doctor_id = DoctorsDAO.getFromName(doctor_name);

        String temperature = request.getParameter("temperature") != null ? request.getParameter("temperature") : "0";
        BigDecimal tempDecimal = new BigDecimal(temperature);

        String pulse = request.getParameter("pulse") != null ? request.getParameter("pulse") : "Not Provided";
        int pulse_int = Integer.parseInt(pulse);

        String respiratory_rate = request.getParameter("respiratory_rate") != null ? request.getParameter("respiratory_rate") : "Not Provided";
        int resp_int = Integer.parseInt(respiratory_rate);

        String systolic = request.getParameter("systolic_bp") != null ? request.getParameter("systolic_bp") : "Not Provided";
        int sys_int = Integer.parseInt(systolic);

        String diastolic = request.getParameter("diastolic_bp") != null ? request.getParameter("diastolic_bp") : "Not Provided";
        int dias_int = Integer.parseInt(diastolic);

        String spo2 = request.getParameter("spo2") != null ? request.getParameter("spo2") : "Not Provided";
        int spo2_int = Integer.parseInt(spo2);
    
        %>
        
        <% 
        if (patient_id == 0 || doctor_id == 0 || birthDate == null || consultDate == null || temperature == null || pulse == null || respiratory_rate == null || systolic == null || diastolic == null || spo2 == null) { 
        %>
            <h1>Consultation Failed to Create</h1>
        <%
        } else {
            VitalSigns vitalSigns = new VitalSigns(tempDecimal, pulse_int, resp_int, sys_int, dias_int, spo2_int);
            VitalSignsDAO.create(vitalSigns);
            
            Consultation consultation = new Consultation(null, doctor_id, patient_id, vitalSigns, null);
            ConsultationDAO.add(consultation);

            int consult_id = consultation.getConsultationID();
            request.setAttribute("consult_id", consult_id);
        %>
        <h1>Consultation Successfully Created</h1>
        <p>Name: <%= patient_name %></p>
        <p>Age: <%= age %></p>
        <p>Attending Physician: <%= doctor_name %></p>
        <p>Temperature: <%= temperature %></p>
        <p>Pulse: <%= pulse %></p>
        <p>Respiratory Rate: <%= respiratory_rate %></p>
        <p>Blood Pressure: <%= systolic %>/<%= diastolic %></p>
        <p>Oxygen Saturation (SpO2): <%= spo2 %></p>


        <%
        }
        %>
    </body>
    
</html>
