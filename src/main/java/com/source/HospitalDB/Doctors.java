package com.source.HospitalDB;

public class Doctors {

    private final String name;
    private final String specialization;
    private final String doctorId;
    private final String phoneNumber;
    private final String email;
    private final String medicalHierarchy;

    public Doctors(String doctorId, String name, String specialization, String phoneNumber, String email, String medicalHierarchy) {
        this.name = name;
        this.specialization = specialization;
        this.doctorId = doctorId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.medicalHierarchy = medicalHierarchy;
    }

    // Getters for each attribute
    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getMedicalHierarchy() {
        return medicalHierarchy;
    }
}