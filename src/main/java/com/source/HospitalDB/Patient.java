package com.source.HospitalDB;

import java.time.LocalDate;

public class Patient {

    // private ArrayList<Medical> MEDICAL;
    private final String name;
    private int age;
    private final LocalDate birthDate;
    private final String sex;
    private double height;
    private double weight;
    private final String religion;
    private Doctors DOCTOR;
    private String status;

    //FIXME: add patient ID
    public Patient(String name, int age, LocalDate birthDate, String sex, double height, double weight, String religion, Doctors DOCTOR, String status) {
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.religion = religion;
        this.DOCTOR = DOCTOR;
        this.status = status;
    }

    // Getters
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

}