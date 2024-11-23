package com.source.HospitalDB.Classes;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import com.source.HospitalDB.App;

public final class Patient {
    private final int patientId;
    private final String lastname;
    private final String firstname;
    private final int age;
    private final java.sql.Date birthDate;
    private final String sex;
    private final BigDecimal height;
    private final BigDecimal weight;
    private final String religion;
    private final Timestamp dateCreated;

    // Constructor for retrieving from database
    public Patient(int patientId, String lastname, String firstname, java.sql.Date birthDate, String sex, 
                   BigDecimal height, BigDecimal weight, String religion) {
        this.patientId = patientId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.age = calculate_age();
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.religion = religion;
        
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Convert it to LocalDateTime at midnight (00:00:00)
        LocalDateTime localDateTimeAtMidnight = currentDate.atStartOfDay();
        
        // Convert to Timestamp
        this.dateCreated = Timestamp.valueOf(localDateTimeAtMidnight);
    }

    // Constructor for creating a new patient
    public Patient(String lastname, String firstname, java.sql.Date birthDate, String sex, BigDecimal height, BigDecimal weight, String religion) {
        App.inc_count_patient();
        this.patientId = App.get_count_patient();
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.age = calculate_age();
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.religion = religion;
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Convert it to LocalDateTime at midnight (00:00:00)
        LocalDateTime localDateTimeAtMidnight = currentDate.atStartOfDay();
        
        // Convert to Timestamp
        this.dateCreated = Timestamp.valueOf(localDateTimeAtMidnight);
    }

    // Getters (No setters for immutability)
    public int getPatientId() {
        return patientId;
    }

    public String getFirstname() {
        return lastname;
    }

    public String getLastname() {
        return firstname;
    }

    // get full name
    public String getFullName() {
        return firstname + " " + lastname;
    }

    public int getAge() {
        return age;
    }

    public java.sql.Date getBirthDate() {
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
        LocalDate birthDateLocal = birthDate.toLocalDate();
        return Period.between(birthDateLocal, LocalDate.now()).getYears();
    }

}