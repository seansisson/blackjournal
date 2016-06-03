/* BlackJournal Task Management Application
*
* Author ..... Sean Sisson
* Client ..... Fei Xie, Bin Lin
* Class ...... CS300 - Elements of Software Engineering
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import main.java.tasks.Task;
import main.java.tasks.TaskController;
import main.java.tasks.TaskView;
import main.java.users.User;
import main.java.users.UserView;
import main.java.users.UserController;


public class Main extends Application {

    private Stage window;

    public static void main(String args[]) {
        // Launch GUI application

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        User user = new User();
        UserView userView = new UserView();
        UserController userController = new UserController(user, userView);

        window = stage;
        userView.start(window);
    }
}