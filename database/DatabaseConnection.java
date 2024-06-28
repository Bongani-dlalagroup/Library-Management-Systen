package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//add your database connection details, the password you set for msql
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Bongz@13591";

    private DatabaseConnection() { }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
