// Created by Sean on 5/19/2016.
package main.java.tasks;

import main.java.tasks.Task;


public class TaskList {
    /* TaskList model
    * TaskList objects have a taskListId, a title, and an array of task
    * objects. Tasks can be moved from one task list to another.
    */
    private static final int MAX_SIZE = 250;

    // Fields
    private int taskListId;
    private String title;
    private Task[] tasks;

    public TaskList() {
        this.title = "";
        this.tasks = new Task[MAX_SIZE];
    }

    public TaskList(String title) {
        this.title = title;
        this.tasks = new Task[MAX_SIZE];
    }

    public int getTaskListId() {
        return taskListId;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public String getTitle() {
        return title;
    }

    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }

    public void setTaskListId(int taskListId) {
        this.taskListId = taskListId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
