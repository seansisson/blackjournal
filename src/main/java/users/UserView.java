package main.java.users;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class UserView extends Application {

    private Stage window;
    private Scene scene;
    private GridPane grid;
    private Button createUserButton;
    private Button loginButton;
    private Label userNameFieldLabel;
    private Label passwordFieldLabel;
    private TextField userNameField;
    private TextField passwordField;

    public UserView() {

        // Initialize grid
        grid = new GridPane();
        grid.setPadding(new Insets(50, 50, 50, 50));
        grid.setVgap(8);
        grid.setHgap(10);

        // Initialize fields, buttons, & labels
        userNameFieldLabel = new Label("Username: ");
        userNameField = new TextField();
        passwordFieldLabel = new Label("Password: ");
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

        scene = new Scene(grid, 500, 250);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;
        this.window.setTitle("BlackJournal Task Manager");
        this.window.setScene(this.scene);
        this.window.show();
    }

    void hide() {
        this.window.hide();
    }

    String getUserName() {
        return this.userNameField.getText();
    }

    String getPassword() {
        return this.passwordField.getText();
    }

    public void clearText() {
        this.userNameField.clear();
        this.passwordField.clear();
    }

    void handleLogin(EventHandler loginEventHandler) {
        this.loginButton.setOnAction(loginEventHandler);
    }

    void handleCreateUser(EventHandler createUserEventHandler) {
        this.createUserButton.setOnAction(createUserEventHandler);
    }
}
