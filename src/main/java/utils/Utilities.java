// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
// @@  DO NOT COMMIT THIS FILE  @@
// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

package main.java.utils;

import java.sql.*;


public final class Utilities {

    // TODO get these from a settings file.
    private static String URL = "jdbc:mysql://localhost:3306/example_db";
    private static String DB_USER = "root";
    private static String DB_PASS = "Haggard2963302.";

    public static Connection getDatabaseConnection() throws SQLException {
        /*
        * Initializes a database connection and returns it to the requested function
        * NOTE: This probably isn't the best way to create connections.
        */

        Connection connection = DriverManager.getConnection(URL, DB_USER, DB_PASS);
        return connection;
    }

    public static void initMysqlDatabase() {
        /*
        * Initialize the MySQL database for use in the application
        */

        try {
            // Load MySQL driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            // Print the error message
            System.out.println("Class not found!" + cnf.getMessage());
        }
    }

    private static void testMysqlDatabase() {
        /*
        * Tests connection to mysql database
        */

        try {
            // MySQL driver needs to be loaded first
            Class.forName("com.mysql.jdbc.Driver");

            // Create connection
            Connection connection = Utilities.getDatabaseConnection();

            // Create and execute query, which will get all tasks from the database
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks;");

            // Print results' name field
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

            // close connections
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        } catch (ClassNotFoundException cnf) {
            System.out.println("Class not found!" + cnf.getMessage());
        }
    }
}