/* BlackJournal Task Management Application
*
* Author ..... Sean Sisson
* Client ..... Fei Xie, Bin Lin
* Class ...... CS300 - Elements of Software Engineering
* Deadline ... June 2nd, 2016
*
* The BlackJournal Task Management Application is an app which allows users
* to log in, manage their tasks in 3 lists: To Do, In Progress, and Done. Each
* user has a User account on the system, in which they can log in and view their
* 3 personalized task lists. Users can create tasks and move them from one list
* to another. Tasks have a task name, description, and are contained within a
* given task list.
*
*/
package main.java;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import main.java.tasks.Task;
import main.java.tasks.TaskView;
import main.java.users.UserView;


public class Main extends Application {

    private Stage window;
    private TaskView taskView;
    private UserView userView;
    private BorderPane layout;
    private TableView<Task> taskTable;

    public static void main(String args[]) {
        /*
        * All we need to do is run launch() which launches the GUI app.
        * We then override the start() function, in which we create the window
        * from the primary stage (the foundation of the GUI app) and build from
        * there.
        */
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // The primary stage of the application will be referred to as the Window.
        // This we will use to set scenes, which are defined in our view classes.
        window = primaryStage;
        taskView = new TaskView();
        userView = new UserView();

        // Set the title of the window
        window.setTitle("BlackJournal");

        // File menu
        Menu fileMenu = new Menu("_File");

        MenuItem newTaskMenuItem = new MenuItem("New task...");
        newTaskMenuItem.setOnAction(e -> createTask());
        fileMenu.getItems().add(newTaskMenuItem);

        fileMenu.getItems().add(new SeparatorMenuItem());

        MenuItem exitMenuItem = new MenuItem("_Exit");
        exitMenuItem.setOnAction(e -> exit());
        fileMenu.getItems().add(exitMenuItem);

        // Account menu
        Menu accountMenu = new Menu("_Account");

        MenuItem editAccountMenuItem = new MenuItem("Edit account...");
        editAccountMenuItem.setOnAction(e -> editAccount());
        accountMenu.getItems().add(editAccountMenuItem);

        accountMenu.getItems().add(new SeparatorMenuItem());

        MenuItem logoutMenuItem = new MenuItem("Logout");
        logoutMenuItem.setOnAction(e -> logout());
        accountMenu.getItems().add(logoutMenuItem);

        // Menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, accountMenu);

        // Name column
        TableColumn<Task, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Description Column
        TableColumn<Task, String> descriptionColumn= new TableColumn<>("Description");
        descriptionColumn.setMinWidth(100);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        taskTable = new TableView<>();
        taskTable.setItems(getTasks());
        taskTable.getColumns().addAll(nameColumn, descriptionColumn);

        // Create layout
        layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(taskTable);
        Scene scene = new Scene(layout, 400, 300);

        window.setScene(scene);
        window.show();
    }

    public ObservableList<Task> getTasks() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        tasks.add(new Task("10:00: CS300", "Software Engineering Class in FAB"));
        tasks.add(new Task("12:00: PH213", "Particle Physics [?]"));
        tasks.add(new Task("2:00: PH216 LAB", "Physics lab"));
        return tasks;
    }

    public void createTask() {
        window.setScene(taskView.createTaskScene());
    }

    public void exit() {
        window.close();
    }

    public void editAccount() {
        System.out.println("Edit Account...");
    }

    public void logout() {
        window.setScene(userView.userLoginScene());
    }

    public Scene baseTaskScene() {
        /*
        * TODO: Refactor this to show all tasks by default
        * TODO: Add functionality to quickly add/edit/move/delete tasks
        * TODO: Set this to be the main view after the user logs in
        */

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Create task
        Button createButton = new Button("Create");
        createButton.setOnAction(e -> {
            window.setScene(taskView.createTaskScene());
        });
        GridPane.setConstraints(createButton, 0, 0);

        // Display task
        Button displayButton = new Button("Display");
        GridPane.setConstraints(displayButton, 1, 0);

        // Update task
        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> {
            window.setScene(taskView.updateTaskScene());
        });
        GridPane.setConstraints(updateButton, 2, 0);

        // Delete task
        Button deleteButton = new Button("Delete");
        GridPane.setConstraints(deleteButton, 3, 0);

        grid.getChildren().addAll(
                createButton,
                displayButton,
                updateButton,
                deleteButton
        );
        Scene scene = new Scene(grid, 400, 250);
        return scene;
    }

}
