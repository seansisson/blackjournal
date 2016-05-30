// Created by Sean on 5/19/2016.
package main.java.users;

import main.java.tasks.TaskList;


public class User {
    /* User model
    * Contains information describing a user of the system. Each user has 3
    * task lists by default: To Do, In Progress, and Done.
    */

    // Fields
    private String userName;
    private String password;
    private TaskList[] taskLists;

    // Methods
    public User() {
        this.userName = "";
        this.password = "";
        initTaskLists();
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        initTaskLists();
    }

    private void initTaskLists() {
        this.taskLists[0] = new TaskList("To Do");
        this.taskLists[1] = new TaskList("In Progress");
        this.taskLists[2] = new TaskList("Done");
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public TaskList[] getTaskLists() {
        return taskLists;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTaskLists(TaskList[] taskLists) {
        this.taskLists = taskLists;
    }


}
