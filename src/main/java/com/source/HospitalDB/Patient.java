package com.source.HospitalDB;

public class Patient {

    // private ArrayList<Medical> MEDICAL;
    private final int patient_ID;
    private final String name;
    private int age;
    private final java.sql.Date birthDate;
    private final String sex;
    private double height;
    private double weight;
    private final String religion;
    private int doctor;
    private java.sql.Timestamp dateCreated;

    public Patient(int patient_ID, String name, int age, java.sql.Date birthDate, String sex, double height, double weight, String religion, int doctor, java.sql.Timestamp dateCreated) {
        this.patient_ID = patient_ID;
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.religion = religion;
        this.doctor = doctor;
        this.dateCreated = dateCreated;
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

    public java.sql.Date getBirthDate() {
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

    public int getDoctor() {
        return doctor;
    }

    public java.sql.Timestamp getDateCreated() {
        return dateCreated;
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

    public void setDoctor(int doctor) {
        this.doctor = doctor;
    }

    public void setDateCreated(java.sql.Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
}