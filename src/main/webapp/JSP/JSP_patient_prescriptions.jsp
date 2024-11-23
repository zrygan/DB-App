<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="com.source.HospitalDB.Classes.Doctors" %>
<%@ page import="com.source.HospitalDB.WebTools"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MediBASE: Result of Patient Prescriptions</title>
</head>
<body>
    <h1>Prescriptions of the Patient</h1>

    <%
    String patientName = request.getParameter("patient_name");
    String birthDate = request.getParameter("birth_date");
    String sex = request.getParameter("sex");
    String doctor = request.getParameter("doctor");

    String[] name = splitName(patientName);

    if (name != null){
        // Split the patient_name into Lastname and Firstname
        String patientLastname = name[0];
        String patientFirstname = name[1];
        String query = "SELECT pr.prescription_ID, mr.generic_name, mr.brand_name, pr.dosage, pr.frequency, pr.prescription_date, dr.doctor_firstname, dr.doctor_lastname "
                       + "FROM prescription_record pr "
                       + "JOIN medication_record mr ON pr.medication_ID = mr.medication_ID "
                       + "JOIN doctors_record dr ON pr.doctor_ID = dr.doctor_ID "
                       + "JOIN patients_record prnt ON prnt.patient_ID = pr.patient_ID "
                       + "WHERE prnt.patient_firstname = ? AND prnt.birth_date = ? AND prnt.sex = ?";

        // check if doctor is provided
        String[] doctor_name = splitName(doctor);

        if (name == null){
            // usual operation
        } else{
            // doctor is not null, append to SQL
            query += "AND "
        }
    }

    // check if the patient exists

    

    // check if a doctor (name) is provided 
    %>
</body>
</head>
