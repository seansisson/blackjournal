package main.java.tasks;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class TaskView extends Application {

    private Stage window;
    private Scene scene;
    private BorderPane layout;
    private GridPane controlGrid;
    private GridPane taskListGrid;
    private Button createButton;
    private Button editButton;
    private Button deleteButton;
    private TextField nameField;
    private TableView todoTable;
    private TableView inProgressTable;
    private TableView doneTable;
    private TableColumn todoTableColumn = new TableColumn("Todo: ");
    private TableColumn inProgressTableColumm = new TableColumn("In progress: ");
    private TableColumn doneTableColumn = new TableColumn("Done: ");
    private final ObservableList<Task> data;

    public TaskView() {

        // Initialize the main layout
        this.layout = new BorderPane();

        // Example data for now
        this.data = FXCollections.observableArrayList(
                new Task("Finish this programming assignment"),
                new Task("Work on KPSU website"),
                new Task("Help Peyton with programming homework friday")
        );

        // Initialize the grid containing the buttons & name field
        controlGrid = new GridPane();

        // Initialize buttons & name field
        createButton = new Button("Create task");
        editButton = new Button("Edit task");
        deleteButton = new Button("Delete Task");
        nameField = new TextField();

        // Initialize the grid containing the task lists
        taskListGrid = new GridPane();

        // Initialize tables and table columns
        todoTable = new TableView<Task>();
        todoTableColumn = new TableColumn("Todo: ");
        inProgressTable = new TableView<Task>();
        inProgressTableColumm = new TableColumn("In progress: ");
        doneTable = new TableView<Task>();
        doneTableColumn = new TableColumn("Done: ");

        // Make name field larger
        nameField.setMinWidth(250);

        // Set table cells
        todoTable.setEditable(true);
        todoTable.setItems(data);
        todoTableColumn.setMinWidth(todoTable.getWidth());
        todoTableColumn.setCellValueFactory(
                new PropertyValueFactory<Task, String>("Name")
        );
        todoTable.getColumns().add(todoTableColumn);
        inProgressTable.setEditable(true);
        inProgressTableColumm.setMinWidth(inProgressTable.getWidth());
        inProgressTableColumm.setCellValueFactory(
                new PropertyValueFactory<Task, String>("Name")
        );
        inProgressTable.getColumns().add(inProgressTableColumm);
        doneTable.setEditable(true);
        doneTableColumn.setMinWidth(doneTable.getWidth());
        doneTableColumn.setCellValueFactory(
                new PropertyValueFactory<Task, String>("Name")
        );
        doneTable.getColumns().add(doneTableColumn);
        taskListGrid.setPadding(new Insets(10, 10, 10, 10));
        taskListGrid.setHgap(10);
        controlGrid.setPadding(new Insets(10, 10, 10, 10));
        controlGrid.setHgap(10);

        // Position tables
        GridPane.setConstraints(todoTable, 0, 0);
        GridPane.setConstraints(inProgressTable, 1, 0);
        GridPane.setConstraints(doneTable, 2, 0);

        // Position controls
        GridPane.setConstraints(nameField, 0, 0);
        GridPane.setConstraints(createButton, 1, 0);
        GridPane.setConstraints(editButton, 2, 0);
        GridPane.setConstraints(deleteButton, 3, 0);

        // Add tables to task list grid
        taskListGrid.getChildren().addAll(
                todoTable,
                inProgressTable,
                doneTable
        );

        // Add controls to button grid
        controlGrid.getChildren().addAll(
                nameField,
                createButton,
                editButton,
                deleteButton
        );

        // Finally add control and task grids to the main layout
        layout.setCenter(taskListGrid);
        layout.setBottom(controlGrid);

        // Set the scene
        scene = new Scene(layout, 800, 400);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;
        this.window.setTitle("BlackJournal Task Manager");
        this.window.setScene(this.scene);
        this.window.show();
    }

    void addToData(Task task) {
        this.data.add(task);
    }

    String getNameField() {
        return nameField.getText();
    }

    void clearNameField() {
        this.nameField.clear();
    }

    void handleCreateTask(EventHandler createTaskHandler) {
        this.createButton.setOnAction(createTaskHandler);
    }

    void handleEditTask(EventHandler createTaskHandler) {
        this.editButton.setOnAction(createTaskHandler);
    }
}
