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

    Stage window;
    private Scene scene;

    private BorderPane mainPane = new BorderPane();
    private GridPane taskListGridPane = new GridPane();
    private GridPane controlPanelGridPane = new GridPane();

    private Button createButton = new Button("Create task");
    private Button logoutButton = new Button("Logout");
    private TextField nameField = new TextField();

    private TableView<Task> todoTable = new TableView<Task>();
    private TableView<Task> inProgressTable = new TableView<Task>();
    private TableView<Task> doneTable = new TableView<Task>();
    private TableColumn todoTableColumn = new TableColumn("To do:");
    private TableColumn inProgressColumn = new TableColumn("In progress:");
    private TableColumn doneColumn = new TableColumn("Done");

    ObservableList<Task> tasksTodo = FXCollections.observableArrayList();
    ObservableList<Task> tasksInProgress = FXCollections.observableArrayList();
    ObservableList<Task> tasksDone = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        window = stage;
        window.setTitle("blackjournal - tasks");

        window.setWidth(800);
        window.setHeight(400);

        todoTable.setEditable(true);

        todoTableColumn = new TableColumn("To do: ");
        todoTableColumn.setMinWidth(200);
        todoTableColumn.setCellValueFactory(
                new PropertyValueFactory("name")
        );
        todoTable.setItems(tasksTodo);
        todoTable.getColumns().add(todoTableColumn);

        inProgressColumn = new TableColumn("In Progress: ");
        inProgressColumn.setMinWidth(200);
        inProgressColumn.setCellValueFactory(
                new PropertyValueFactory("name")
        );
        inProgressTable.setItems(tasksInProgress);
        inProgressTable.getColumns().add(inProgressColumn);

        doneColumn = new TableColumn("Done: ");
        doneColumn.setMinWidth(200);
        doneColumn.setCellValueFactory(
                new PropertyValueFactory("name")
        );
        doneTable.setItems(tasksDone);
        doneTable.getColumns().add(doneColumn);

        // Set move task listeners
        handleDoubleClick(todoTable, tasksTodo, tasksInProgress);
        handleDoubleClick(inProgressTable, tasksInProgress, tasksDone);
        handleDoubleClick(doneTable, tasksDone, null);

        GridPane.setConstraints(todoTable, 0, 0);
        GridPane.setConstraints(inProgressTable, 1, 0);
        GridPane.setConstraints(doneTable, 2, 0);

        taskListGridPane.setHgap(10);
        taskListGridPane.setVgap(10);
        taskListGridPane.setPadding(new Insets(10, 10, 10, 10));
        taskListGridPane.getChildren().addAll(
                todoTable,
                inProgressTable,
                doneTable
        );

        GridPane.setConstraints(nameField, 0, 0);
        GridPane.setConstraints(createButton, 1, 0);
        GridPane.setConstraints(logoutButton, 3, 0);

        nameField.setMinWidth(200);
        controlPanelGridPane.setHgap(10);
        controlPanelGridPane.setVgap(10);
        controlPanelGridPane.setPadding(new Insets(10, 10, 10, 10));
        controlPanelGridPane.getChildren().addAll(
                nameField,
                createButton,
                logoutButton
        );

        mainPane.setCenter(taskListGridPane);
        mainPane.setBottom(controlPanelGridPane);

        scene = new Scene(mainPane, 800, 400);
        window.setScene(scene);
        window.show();
    }

    void addTask(Task task, ObservableList<Task> taskList) {
        taskList.add(task);
    }

    void removeTask(Task task, ObservableList<Task> taskList) {
        taskList.remove(task);
    }

    void moveTask(Task task, ObservableList<Task> source, ObservableList<Task> dest) {
        source.remove(task);
        dest.add(task);
    }

    String getNameField() {
        return this.nameField.getText();
    }

    void clearNameField() {
        this.nameField.clear();
    }

    void handleCreateTask(EventHandler createTaskHandler) {
        this.createButton.setOnAction(createTaskHandler);
    }

    void handleDoubleClick(TableView tableView, ObservableList source, ObservableList dest) {
        tableView.setRowFactory( e -> {
            TableRow<Task> row = new TableRow<Task>();
            row.setOnMouseClicked(event -> {
                Task task = row.getItem();
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    if (dest == null) {
                        removeTask(task, source);
                    } else {
                        moveTask(task, source, dest);
                    }
                }
            });
            return row;
        });
    }

    void handleLogout(EventHandler logoutHandler) {
        this.logoutButton.setOnAction(logoutHandler);
    }

    void hide() {
        // Hide the window

        this.window.hide();
    }
}