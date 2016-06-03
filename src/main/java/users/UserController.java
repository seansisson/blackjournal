package main.java.users;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import main.java.tasks.Task;
import main.java.tasks.TaskController;
import main.java.tasks.TaskView;


public class UserController {

    private UserView view;
    private User model;

    public UserController(User userModel, UserView userView) {

        this.view = userView;
        this.model = userModel;

        // Add event handlers
        this.view.handleLogin(loginEventHandler());
        this.view.handleCreateUser(createUserEventHandler());
    }

    private EventHandler loginEventHandler() {
        return new EventHandler() {
            @Override
            public void handle(Event event) {
                String name = view.getUserName();
                String password = view.getPassword();
                User user = new User(name, password);
                System.out.println("Logging in " + name);
                // find user, if users match, take user to task scene

                try {
                    view.hide();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Task task = new Task();
                TaskView taskView = new TaskView();
                TaskController taskController = new TaskController(task, taskView);
                try {
                    taskView.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private EventHandler createUserEventHandler() {
        return new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("create user");
            }
        };
    }

    public void serializeUser(String username, String password) {

        User user = new User(username, password);

        try {
            FileOutputStream f = new FileOutputStream("users.ser");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(user);
            System.out.println("User object written to file");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private User deserializeUser() {

        User user;

        try {
            FileInputStream f = new FileInputStream("task.ser");
            ObjectInputStream o = new ObjectInputStream(f);
            user = (User) o.readObject();
            o.close();
            System.out.println("Username: " + user.getUserName());

            return user;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isValid(String input) {

        // Parse string to check if it contains only alphanumeric values
        if (input == null) {
            return false;
        }

        return input.matches("^[a-zA-Z0-9]*$");
    }

    public void getDatabaseConnection() throws SQLException {
        try {
            Class.forName("com.jdbc.mysql.Driver");
            System.out.println("It worked!");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: unable to load driver class");
        }
    }
}
