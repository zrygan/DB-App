<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%@page import="com.source.HospitalDB.DAO.*" %>
<%@page import="com.source.HospitalDB.Classes.*" %>
<%@page import="com.source.HospitalDB.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consultation Records</title>
</head>
<body>
    <h1>Consultation Records</h1>
    <%     
        String date = request.getParameter("date");
        List<Consultation> consultations = ConsultationDAO.getConsultationByDate(date);
            
        if (consultations.isEmpty()) {
            out.println("<p>No consultation records found for the given date and doctor.</p>");
        } else {
            
            for (Consultation consultation : consultations) {
                out.println("<table border='1'>");
                 out.println("<tr><th>Consultation ID</th><th>Patient ID</th><th>Doctor ID</th><th>Date</th></tr>");
                out.println("<tr>");
                int cons_id = consultation.getConsultationID();
                int pat_id = consultation.getPatientID();
                int doc_id = consultation.getDoctorID();
                int vit_id = consultation.getVitalSignsID();
                
                int pres_id = PrescriptionDAO.getIdByConsultation(cons_id);
                int lab_id = LabReportDAO.getLabReportID(cons_id);

                // Retrieve the objects using the IDs
                Prescription prescription = PrescriptionDAO.get(pres_id);
                Patient patient = PatientDAO.getPatient(pat_id);
                Doctors doctor = DoctorsDAO.getDoctorByID(doc_id);
                VitalSigns vitalSigns = VitalSignsDAO.get(vit_id);

                // Print attributes of each object
                out.println("<td>" + cons_id + "</td>");
                out.println("<td>" + pat_id + "</td>");
                out.println("<td>" + doc_id + "</td>");
                out.println("<td>" + consultation.getConsultationDate() + "</td>");
                out.println("</tr>");

                out.println("<tr><td colspan='4'>");
                out.println("<strong>Prescription:</strong><br>");
                List<Prescription> prescriptions = Transaction.getPrescriptionsByConsultationID(cons_id);
                // Go through each prescription and print the details
                for (Prescription prescriptionz : prescriptions) {
                    Medication medication = MedicationDAO.get(prescriptionz.getMedicationID());
                    out.println("Prescription ID: " + prescriptionz.getPrescriptionID() + "<br>");
                    out.println("Medication ID: " + prescriptionz.getMedicationID() + "<br>");
                    out.println("Generic Name: " + medication.getGenericName() + "<br>");
                    out.println("Brand Name: " + medication.getBrandName() + "<br>");
                    out.println("Dosage: " + prescriptionz.getDosage() + "<br>");
                    out.println("Frequency: " + prescriptionz.getFrequency() + "<br>");
                    // place blank line between prescriptions
                    out.println("<br>");
                }

                out.println("<strong>Patient:</strong><br>");
                out.println("ID: " + patient.getPatientId() + "<br>");
                out.println("Name: " + patient.getFullName() + "<br>");
                out.println("Age: " + patient.getAge() + "<br>");
                out.println("Sex: " + patient.getSex() + "<br>");
                out.println("Height: " + patient.getHeight() + "<br>");
                out.println("Weight: " + patient.getWeight() + "<br>");

                out.println("<strong>Doctor:</strong><br>");
                out.println("ID: " + doctor.getDoctorId() + "<br>");
                out.println("Name: " + doctor.getFullName() + "<br>");
                out.println("Specialization: " + doctor.getSpecialization() + "<br>");

                out.println("<strong>Vital Signs:</strong><br>");
                out.println("ID: " + vitalSigns.getVitalSignsID() + "<br>");
                out.println("Temperature: " + vitalSigns.getTemperature() + "<br>");
                out.println("Pulse: " + vitalSigns.getPulse() + "<br>");
                out.println("Respiration: " + vitalSigns.getRespiratoryRate() + "<br>");
                out.println("Blood Pressure: " + vitalSigns.getSystolicBP() + "/" + vitalSigns.getDiastolicBP() + "<br>");
                out.println("SpO2: " + vitalSigns.getSpo2() + "<br>");

                out.println("<strong>Lab Reports:</strong><br>");
                List<LabReport> labReports = Transaction.getLabReportsByConsultationID(cons_id);
                // Go through each lab report and print the details
                for (LabReport labReportItem : labReports) {
                    LabTest labTest = LabTestDAO.get(labReportItem.getTestID());
                    out.println("Lab Report ID: " + labReportItem.getLabReportID() + "<br>");
                    out.println("Lab Test: " + labTest.getTestDescription() + "<br>");
                    out.println("Lab Test ID: " + labTest.getTestID() + "<br>");
                    out.println("<br>");
                }
                out.println("</td></tr>");

                out.println("</table>");
            }
            
        }
    %>
</body>
</html>