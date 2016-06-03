package main.java.users;

import java.io.Serializable;
import main.java.tasks.Task;


public class User implements Serializable {

    private String userName;
    private String password;
    private Task[] tasks;

    public User() {
        this.userName = "";
        this.password = "";
        this.tasks = new Task[50];
    }

    User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.tasks = null;
    }

    String getUserName() {
        return userName;
    }

    public String getPassword() {

        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new StringBuffer(" Username: ")
                .append(this.userName).toString();
    }
}
