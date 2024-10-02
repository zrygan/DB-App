package com.HospitalDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class App {

    private static final String DURL  = "jdbc:mysql://127.0.0.1:3306/Hospital_DB";
    private static final String USER  = "root";
    private  static final String PASS  = "password";
    
    public static void main(String[] args) {

        String query = "";

        try (Connection connection = DriverManager.getConnection(DURL, USER, PASS)) {
            System.out.println("Connected.");
            
            System.out.print("Query << ");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}