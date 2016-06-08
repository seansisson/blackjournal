package main.java.tasks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.utils.Utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TaskList {

    private int userId;
    private String title;
    private Task[] tasks;

    public TaskList(String title, int userId) {
        this.title = title;
        this.userId = userId;
        this.tasks = new Task[25];
    }

    int getUserId() {
        return this.userId;
    }

    String getTitle() {
        return this.title;
    }

    Task[] getTasks() {
        return this.tasks;
    }

    void setUserId(int userId) {
        this.userId = userId;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void writeTasksToDB() {

        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection = Utilities.getDatabaseConnection();
            for (int i = 0; i > tasks.length; ++i) {
                preparedStatement = connection.prepareStatement(
                        "INSERT INTO tasks (name, userId, listType) VALUES (?, ?, ?);"
                );

                // Bind params & execute query
                preparedStatement.setString(1, tasks[i].getName());
                preparedStatement.execute();

            }
            // Close connection
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
