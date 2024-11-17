package main.java.com.source.HospitalDB.Classes;

public class Doctors {

    private final String name;
    private final String specialization;
    private final String doctorId;
    private final String phoneNumber;
    private final String email;

    public Doctors(String doctorId, String name, String specialization, String phoneNumber, String email) {
        this.name = name;
        this.specialization = specialization;
        this.doctorId = doctorId;
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

    public String getDoctorId() {
        return doctorId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}