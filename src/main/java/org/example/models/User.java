package org.example.models;

import java.util.List;

public class User {
    private String name;

    private List<Tasks> tasks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }

    public User(String name, List<Tasks> tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}

