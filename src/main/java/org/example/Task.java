package org.example;

import java.util.Objects;

public class Task{

    private static int count = 0;
    private int id = count;
    private String name;
    private int significance;

    private boolean implementation;


    public Task(String name, int significance) {
        this.name = name;
        this.significance = significance;
        id = ++count;
        implementation = false;
    }

    public Task(int id, String name, int significance, boolean implementation) {
        this.id = id;
        this.name = name;
        this.significance = significance;
        this.implementation = implementation;
        count=id;
    }

    public int getId() {
        return id;
    }

    public boolean getImplementation() {
        return implementation;
    }

    public void setImplementation(boolean implementation) {
        this.implementation = implementation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSignificance() {
        return significance;
    }

    public void setSignificance(int significance) {
        this.significance = significance;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", significance=" + significance +
                ", implementation=" + implementation +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return getSignificance() == task.getSignificance() && Objects.equals(getName(), task.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSignificance());
    }

}
