package com.source.HospitalDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static final String HOST_NAME = "127.0.0.1";
    private static final String PORT = "3306";
    private static final String DURL  = "jdbc:mysql://" + HOST_NAME + ":" + PORT + "/hospital_db";
    private static final String USER  = "root";
    private  static final String PASS  = "password";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DURL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
    }
}
