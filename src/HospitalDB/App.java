package HospitalDB;

import java.sql.*;
import java.util.ArrayList;

public class App {

    static final String _DURL  = "";
    static final String _USER  = "";
    static final String _PASS  = "";
    
    private ArrayList<Medical> Medical_Records = new ArrayList<>();
    private ArrayList<Patient> Patient_Records = new ArrayList<>();
    private ArrayList<Doctors> Doctors_Records = new ArrayList<>();

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(_DURL, _USER, _PASS)) {
            System.out.println("Connected.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getters
    public ArrayList<Medical> getMedicalRecords() {
        return Medical_Records;
    }

    public ArrayList<Patient> getPatientRecords() {
        return Patient_Records;
    }

    public ArrayList<Doctors> getDoctorsRecords() {
        return Doctors_Records;
    }

    // Setters
    public void setMedicalRecords(ArrayList<Medical> medicalRecords) {
        this.Medical_Records = medicalRecords;
    }

    public void setPatientRecords(ArrayList<Patient> patientRecords) {
        this.Patient_Records = patientRecords;
    }

    public void setDoctorsRecords(ArrayList<Doctors> doctorsRecords) {
        this.Doctors_Records = doctorsRecords;
    }
}