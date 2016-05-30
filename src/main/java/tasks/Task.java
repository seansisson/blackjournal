package main.java.tasks;


public class Task {
    /* Task model
    * Individual tasks for the user to read and update. Tasks have an ID, a name,
    * and a description.
    */

    // Fields
    private String name;
    private String description;

    // Methods
    public Task() {
        this.name = "";
        this.description = "";
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
