package com.source.HospitalDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static final String DURL  = "jdbc:mysql://127.0.0.1:3306/Hospital_DB";
    private static final String USER  = "root";
    private  static final String PASS  = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DURL, USER, PASS);
    }
}
