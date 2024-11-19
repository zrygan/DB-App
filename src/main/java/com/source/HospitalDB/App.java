package com.source.HospitalDB;

import java.sql.SQLException;

import com.source.HospitalDB.Classes.Patient;
import com.source.HospitalDB.Classes.Doctors;
import com.source.HospitalDB.DAO.PatientDAO;
import com.source.HospitalDB.DAO.DoctorsDAO;
import java.math.BigDecimal;

public class App {
    
    public static void main(String[] args) throws SQLException{
        java.sql.Date birthDate = java.sql.Date.valueOf("2004-10-01"); 
        
        Doctors doctor = new Doctors(9999, "Ririn", "Valorant Pro Gamer", "(234) 234-2345", "ririn@zhean.com");
        
        Patient patient = new Patient(1, "John Doe", birthDate, "Male", 
                                      new BigDecimal("180.5"), new BigDecimal("75.2"),
                                      "Christianity", 9999);
        DoctorsDAO.create(doctor);
        PatientDAO.create(patient);
    }
}