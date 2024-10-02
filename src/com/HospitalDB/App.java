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

        Scanner sc = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(DURL, USER, PASS)) {
            System.out.println("Connected.");
            
            String query;

            System.out.print("Query << ");
            query = sc.nextLine();
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                System.out.println(query + " << " + resultSet.getString("doctor_name") + " : " + resultSet.getString("doctor_specialization"));
            }

            sc.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}