package main.java.tasks;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class TaskView {

    public TaskView() {}

    public Scene createTaskScene() {
        // init grid
        // TODO: Slap this into it's own function
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // init name field/label
        Label nameFieldLabel = new Label("Task: ");
        TextField nameField = new TextField();
        GridPane.setConstraints(nameFieldLabel, 0, 0);
        GridPane.setConstraints(nameField, 1, 0);

        // init description field/label
        Label descriptionFieldLabel = new Label("Description: ");
        TextField descriptionField = new TextField();
        GridPane.setConstraints(descriptionFieldLabel, 0, 1);
        GridPane.setConstraints(descriptionField, 1, 1);

        // Init button
        Button createButton = new Button("Create");
        GridPane.setConstraints(createButton, 1, 2);

        // Update and return the grid
        grid.getChildren().addAll(
                nameFieldLabel,
                nameField,
                descriptionFieldLabel,
                descriptionField,
                createButton
        );

        Scene scene = new Scene(grid, 400, 250);
        return scene;
    }

    public Scene updateTaskScene() {
        // init grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // init name field/label
        Label nameFieldLabel = new Label("Update task name: ");
        TextField nameField = new TextField();
        GridPane.setConstraints(nameFieldLabel, 0, 0);
        GridPane.setConstraints(nameField, 1, 0);

        // init description field/label
        Label descriptionFieldLabel = new Label("Update task description: ");
        TextField descriptionField = new TextField();
        GridPane.setConstraints(descriptionFieldLabel, 0, 1);
        GridPane.setConstraints(descriptionField, 1, 1);

        // Init button
        Button updateButton = new Button("Create");
        GridPane.setConstraints(updateButton, 1, 2);

        // Update and return the grid
        grid.getChildren().addAll(
                nameFieldLabel,
                nameField,
                descriptionFieldLabel,
                descriptionField,
                updateButton
        );

        Scene scene = new Scene(grid, 400, 250);
        return scene;
    }

    public void deleteTaskScene() {
        //TODO: This should just be a button on the main view
    }
}
