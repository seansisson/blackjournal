package main.java.users;


import main.java.tasks.TaskList;

public class User {

    private int id;
    private String username;
    private String password;
    private TaskList[] taskLists;

    public User() {
        this.username = "";
        this.password = "";

        this.taskLists[0] = new TaskList("todo", id);
        this.taskLists[1] = new TaskList("in_progress", id);
        this.taskLists[2] = new TaskList("done", id);
    }

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public TaskList[] getTaskLists() {
        return this.taskLists;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTaskLists(TaskList[] taskLists) {
        this.taskLists = taskLists;
    }

    public int length() {
        return this.taskLists.length;
    }
}
