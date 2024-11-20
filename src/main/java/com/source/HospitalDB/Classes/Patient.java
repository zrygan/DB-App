package com.source.HospitalDB.Classes;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;

import com.source.HospitalDB.App;

public final class Patient {
    private final int patientId;
    private final String name;
    private final int age;
    private final Timestamp birthDate;
    private final String sex;
    private final BigDecimal height;
    private final BigDecimal weight;
    private final String religion;
    private final Timestamp dateCreated;

    // Constructor
    public Patient(String name, Timestamp birthDate, String sex, 
                   BigDecimal height, BigDecimal weight, String religion) {
        App.inc_count_patient();
        patientId = App.get_count_patient();
        dateCreated = App.time_now();
        this.name = name;
        this.birthDate = birthDate;
        this.age = calculate_age();
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.religion = religion;
    }

    // Constructor for retrieving from database
    public Patient(int patientId, String name, Timestamp birthDate, String sex, 
                   BigDecimal height, BigDecimal weight, String religion, Timestamp dateCreated) {
        this.patientId = patientId;
        this.name = name;
        this.birthDate = birthDate;
        this.age = calculate_age();
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.religion = religion;
        this.dateCreated = dateCreated;
    }

    // Getters (No setters for immutability)
    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public String getSex() {
        return sex;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public String getReligion() {
        return religion;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    // Calculate age based on birthDate
    private int calculate_age() {
        LocalDate birthDateLocal = birthDate.toLocalDateTime().toLocalDate();
        return Period.between(birthDateLocal, LocalDate.now()).getYears();
    }
}