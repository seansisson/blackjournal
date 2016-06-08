package main.java.users;

import java.sql.*;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import main.java.tasks.TaskController;
import main.java.utils.Utilities;


public class UserController {
    /*
    *  Controller for the user account module
    *  This could implement an abstract class
    */

    private UserView view;
    private User user;

    public UserController() {
        /*
        * Default constructor
        */

        this.view = new UserView();
        this.user = null;

        // Add event handlers
        this.view.handleLogin(loginEventHandler());
        this.view.handleCreateUser(createUserEventHandler());
    }

    private void login(String username, String password) {
        /*
        * Primary login function
        */

        // If the username and password are valid, try retrieving the user
        if (isValid(username) && isValid(password)) {
            this.user = getUserFromDB(username, password);
        }

        // If we successfully retrieved a user, hide current window and open the task lists
        if (this.user != null) {
            this.view.hide();
            TaskController taskController = new TaskController(this.user);
            taskController.start(new Stage());
        }

        // Either the username and password were invalid, or we did not get a user.
        // Clear the user text and throw an error message on the screen
        else {
            this.view.clearText();
            System.out.println("Username or password invalid");
        }
    }

    private void create(String username, String password) {
        /*
        * Creates a user and adds them to the database
        */

        // If the username and password are valid, try retrieving the user
        if (isValid(username) && isValid(password)) {
            User newUser = new User(username, password);
            addUserToDB(newUser);
        }

        else {
            this.view.clearText();
            System.out.println("Invalid username or password");
        }
    }

    private void addUserToDB(User user) {
        /*
        * Adds a user to the database
        */

        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection = Utilities.getDatabaseConnection();
            System.out.println("Connection established");
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (username, password) VALUES (?, ?);"
            );

            // Bind params & execute query
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();

            // Close connection
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private User getUserFromDB(String username, String password) {
        /*
        * Retrieves a user from the database, given username and password strings
        */

        String u, p;
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet results;

        try {
            // Get database connection & construct a query
            // Note: This is definitely not the most efficient way to do this
            connection = Utilities.getDatabaseConnection();
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE username=? AND password=?;"
            );

            // Bind parameters to statement & execute query
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            results = preparedStatement.executeQuery();

            // Iterate through results until we find a match
            while (results.next()) {
                u = results.getString("username");
                p = results.getString("password");

                // If we find a user that matches our input,
                // close the connection and return a new user
                if (u.equals(username) && p.equals(password)) {
                    connection.close();

                    return new User(u, p);
                }
            } return null;
        }
        catch (SQLException e) {
            return null;
        }
    }

    private EventHandler loginEventHandler() {
        // Handle the event when a user tries to log in

        return event -> login(view.getUsernameInput(), view.getPasswordInput());
    }

    private EventHandler createUserEventHandler() {
        // Handle the event where a user tries to create a user

        return event -> create(view.getUsernameInput(), view.getPasswordInput());
    }

    private boolean isValid(String input) {
        /*
        * Parses the string passed in and returns true if it contains
        * only alphanumeric values (required for login)
        */

        if (input != null) if (input.matches("^[a-zA-Z0-9]*$")) return true;
        return false;
    }

    public void start(Stage stage) {
        /*
        * Wrapper function launches user view's start method.
        */

        try {
            this.view.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
