package com.source.HospitalDB;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Patient {

    // private ArrayList<Medical> MEDICAL;
    private final int patient_ID;
    private final String name;
    private int age;
    private final LocalDate birthDate;
    private final String sex;
    private double height;
    private double weight;
    private final String religion;
    private Doctors DOCTOR;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //FIXME: add patient ID
    public Patient(int patient_ID, String name, int age, LocalDate birthDate, String sex, double height, double weight, String religion, Doctors DOCTOR, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.patient_ID = patient_ID;
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.religion = religion;
        this.DOCTOR = DOCTOR;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters
    public int getPatientId(){
        return patient_ID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getSex() {
        return sex;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getReligion() {
        return religion;
    }

    public Doctors getDoctor() {
        return DOCTOR;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    // Setters
    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDoctor(Doctors DOCTOR) {
        this.DOCTOR = DOCTOR;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}