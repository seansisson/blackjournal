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
import javafx.stage.Stage;

import main.java.users.UserController;
import main.java.utils.Utilities;


public class Main extends Application {

    public static void main(String args[]) {
        /*
        * Main program method
        * Loads MySQL and launches the application
        */

        Utilities.initMysqlDatabase();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        /*
        * Main GUI method
        */

        UserController userController = new UserController();
        userController.start(stage);
    }
}