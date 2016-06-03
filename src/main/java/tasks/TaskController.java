package main.java.tasks;

import javafx.event.Event;
import javafx.event.EventHandler;

import java.io.*;


public class TaskController {

    private Task model;
    private TaskView view;

    public TaskController(Task taskModel, TaskView taskView) {

        this.model = taskModel;
        this.view = taskView;

        this.view.handleCreateTask(createTaskEventHandler());
        this.view.handleEditTask(editTaskEventHandler());
    }

    private EventHandler createTaskEventHandler() {

        return new EventHandler() {

            @Override
            public void handle(Event event) {
                String name = view.getNameField();
                Task task = new Task(name);

                System.out.println("Created task: " + name);

                view.addToData(task);
                view.clearNameField();
            }
        };
    }

    private EventHandler editTaskEventHandler () {

        return new EventHandler() {

            @Override
            public void handle(Event event) {
                String name = view.getNameField();

            }
        };
    }

    private void serializeTask(String name) {

        Task task = new Task(name);

        try {
            FileOutputStream f = new FileOutputStream("task.ser");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(task);
            System.out.println("Task object written to file");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Task deserializeTask() {

        Task task;

        try {
            FileInputStream f = new FileInputStream("task.ser");
            ObjectInputStream o = new ObjectInputStream(f);
            task = (Task) o.readObject();
            o.close();
            System.out.println("Id: " + task.getId());
            System.out.println("Name: " + task.getName());

            return task;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isValid(String input) {

        // Parse string to check if it contains alphanumeric values
        // as well as some special characters
        return input.matches("[A-Za-z0-9 _.,!]*");
    }
}
