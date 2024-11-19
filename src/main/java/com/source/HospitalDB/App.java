package com.source.HospitalDB;

import java.sql.SQLException;

import com.source.HospitalDB.Classes.Doctors;
import com.source.HospitalDB.DAO.DoctorsDAO;

public class App {
    
    public static void main(String[] args) throws SQLException{
        Doctors doctor = new Doctors("12345", "Max", "Physical Therapist", "(234) 123-1234", "max@zhean.com");
        DoctorsDAO.create(doctor);
        
    }
}