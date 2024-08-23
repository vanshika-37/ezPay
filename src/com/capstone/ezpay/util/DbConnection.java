package com.capstone.ezpay.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Utility class for managing database connections.
 * 
 * This class provides a method to establish a connection to the Oracle
 * database using credentials and connection details provided in a resource bundle.
 * 
 * @author vanshika
 * @since 2024-08-19
 */
public class DbConnection {

    /**
     * Establishes a connection to the Oracle database using the credentials
     * and connection details from a resource bundle.
     * 
     * @return A Connection object to interact with the database.
     * @throws ClassNotFoundException If the database driver class is not found.
     * @throws SQLException If a database access error occurs.
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // Load database connection details from the resource bundle
        ResourceBundle resourceBundle = ResourceBundle.getBundle("oracle");
        String url = resourceBundle.getString("db.url");
        String username = resourceBundle.getString("db.username");
        String password = resourceBundle.getString("db.password");

        // Establish and return the database connection
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
