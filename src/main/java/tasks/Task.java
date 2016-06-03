package main.java.tasks;


import java.io.Serializable;

public class Task implements Serializable {
    /* Task model
    * Individual tasks for the user to read and update. Tasks have an ID, a name,
    * and a description.
    */

    private int id;
    private String name;

    public Task() {
        this.name = "";
    }

    public Task(String name) {
        this.name = name;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringBuffer(" Id: ")
                .append(this.id)
                .append(" Name: ")
                .append(this.name).toString();
    }

}
