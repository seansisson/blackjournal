package main.java.tasks;


import javafx.beans.property.SimpleStringProperty;

public class Task {

    private SimpleStringProperty name;

    public Task(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
