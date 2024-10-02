package com.HospitalDB;

import java.sql.*;
import java.util.ArrayList;

public class App {

    static final String _DURL  = "jdbc:mysql://localhost:3306/HOSPITAL_DB";
    static final String _USER  = "root";
    static final String _PASS  = "password";
    
    public static void main(String[] args) {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection(_DURL, _USER, _PASS)) {
            System.out.println("Connected.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}