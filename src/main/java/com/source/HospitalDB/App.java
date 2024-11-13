package com.source.HospitalDB;

import java.sql.SQLException;

public class App {
    
    public static void main(String[] args) {
        Doctors zhean = new Doctors("12345678", "Zhean", "Cardiologist", "0917222", "zhean@hospital.com", "Senior Consultant");
         
        try {
            DoctorsDAO.addDoctors(zhean);
        } catch (SQLException e) {
            System.err.println("Error adding doctor" + e.getMessage()); 
        }
    }
}