package main.java.users;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class UserView extends Application {
    /*
    * View class for the user module
    * this could implement an abstract view class
    */

    private Stage window;
    private final Scene scene;
    private final GridPane grid;
    private final Button createUserButton;
    private final Button loginButton;
    private final Label userNameFieldLabel;
    private final Label passwordFieldLabel;
    private final TextField userNameField;
    private final PasswordField passwordField;

    public UserView() {
        /*
        * Constructor
        */

        // Initialize grid
        grid = new GridPane();
        grid.setPadding(new Insets(50, 50, 50, 50));
        grid.setVgap(8);
        grid.setHgap(10);

        // Initialize fields, buttons, & labels
        userNameFieldLabel = new Label("Username: ");
        passwordFieldLabel = new Label("Password: ");
        userNameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Log in");
        createUserButton = new Button("Create User");

        // Set positions of components
        GridPane.setConstraints(userNameFieldLabel, 0, 0);
        GridPane.setConstraints(userNameField, 1, 0);
        GridPane.setConstraints(passwordFieldLabel, 0, 1);
        GridPane.setConstraints(passwordField, 1, 1);
        GridPane.setConstraints(loginButton, 1, 2);
        GridPane.setConstraints(createUserButton, 1, 3);

        // Add components to grid
        grid.getChildren().addAll(
                userNameFieldLabel,
                userNameField,
                passwordFieldLabel,
                passwordField,
                loginButton,
                createUserButton
        );

        // Finally, initialize scene
        scene = new Scene(grid, 500, 250);
    }

    @Override
    public void start(Stage window) throws Exception {
        /*
        * Main method for the user GUI
        */
        this.window = window;
        window.setTitle("blackjournal - log in");
        window.setScene(this.scene);
        window.show();
    }

    public void clearText() {
        // Reset text in the user login form

        this.userNameField.clear();
        this.passwordField.clear();
    }

    void hide() {
        // Hide the window

        this.window.hide();
    }

    void handleLogin(EventHandler loginEventHandler) {
        // Set login event handler for login button

        this.loginButton.setOnAction(loginEventHandler);
    }

    void handleCreateUser(EventHandler createUserEventHandler) {
        // Set create user event handler for create user button

        this.createUserButton.setOnAction(createUserEventHandler);
    }

    String getUsernameInput() {
        return this.userNameField.getText();
    }

    String getPasswordInput() {
        return this.passwordField.getText();
    }
}
