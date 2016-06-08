package main.java.tasks;

import java.sql.*;

import javafx.event.EventHandler;
import javafx.stage.Stage;

import main.java.users.User;
import main.java.users.UserController;
import main.java.utils.Utilities;


public class TaskController {
    /*
    *  Controller for the task module
    *  This could implement an abstract class
    */

    private TaskView view;
    private User user;
    private Task[][] taskLists = new Task[3][];

    public TaskController(User user) {
        /*
        * Default constructor
        */

        this.view = new TaskView();
        this.user = user;

        // Register event handlers
        this.view.handleCreateTask(handleCreateTask());
        this.view.handleLogout(handleLogout());
    }

    private void createTask(String name) {
        /*
        * Create a task, add it to the list
        */
        Task task;

        if (isValid(name)) {
            System.out.println("Task is valid");
            task = new Task(name);

            // Save task to database
            addTaskToDatabase(task);
            this.view.addTask(task, this.view.tasksTodo);
            this.view.clearNameField();
        }

        else {
            // Input was invalid

            this.view.clearNameField();
            System.out.println("Invalid entry");
        }
    }

    private void logout() {
        System.out.println("Saving task lists...");



        view.hide();
        UserController userController = new UserController();
        userController.start(new Stage());
    }

    private Task addTaskToDatabase(Task task) {

        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection = Utilities.getDatabaseConnection();
            System.out.println("Connection established");
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO tasks (name, userId, listType) VALUES (?, ?, ?);"
            );

            // Bind params & execute query
            preparedStatement.setString(1, task.getName());
            preparedStatement.execute();

            // Close connection
            connection.close();
            return task;
        }
        catch (Exception e) {
            return null;
        }
    }

    EventHandler handleCreateTask() {
        return event -> createTask(this.view.getNameField());
    }

    private EventHandler handleLogout() {
        return event -> logout();
    }

    private boolean isValid(String input) {
        /*
        * Parse string to check if it contains alphanumeric values
        * as well as some special characters
        */

        if (input != null) if (input.matches("[A-Za-z0-9 _.,!]*+")) return true;
        return false;
    }

    public void start(Stage window) {
        /*
        * Wrapper function launches task view's start method
        */

        try {
            this.view.start(window);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}