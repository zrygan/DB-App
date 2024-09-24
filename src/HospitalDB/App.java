package HospitalDB;

import java.sql.*;

/**
 * App class, contains main function
 */
public class App {

    /**
     *   
     */
    static final String _DURL  = "";
    static final String _USER  = "";
    static final String _PASS  = "";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(_DURL, _USER, _PASS)) {
            System.out.println("DB App");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
