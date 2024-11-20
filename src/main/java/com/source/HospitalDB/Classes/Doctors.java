package com.source.HospitalDB.Classes;

import com.source.HospitalDB.App;

public class Doctors {

    private final String name;
    private final String specialization;
    private final int doctorId;
    private final String phoneNumber;
    private final String email;

    public Doctors(String name, String specialization, String phoneNumber, String email) {
        App.inc_count_doctors();
        doctorId = App.get_count_doctors();
        
        this.name = name;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters for each attribute
    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}