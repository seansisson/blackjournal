package main.java.users;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class UserView {

    public UserView() {}

    public Scene userLoginScene() {
        // Initialize the grid for the layout
        // TODO: Slap this into it's own function
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Initialize and position labels/buttons for username field
        Label userNameFieldLabel = new Label("Username: ");
        TextField userNameField = new TextField();
        GridPane.setConstraints(userNameFieldLabel, 0, 0);
        GridPane.setConstraints(userNameField, 1, 0);

        // Initialize and position labels/buttons for username field
        Label passwordFieldLabel = new Label("Password: ");
        TextField passwordField = new TextField();
        GridPane.setConstraints(passwordFieldLabel, 0, 1);
        GridPane.setConstraints(passwordField, 1, 1);

        // Initialize and position login button
        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 2);

        // Add items to grid
        grid.getChildren().addAll(
                userNameFieldLabel,
                userNameField,
                passwordFieldLabel,
                passwordField,
                loginButton
        );

        Scene scene = new Scene(grid, 400, 250);
        return scene;
    }

    public void userUpdateScene() {}
}
