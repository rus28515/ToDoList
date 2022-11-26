package org.example;

import java.util.Objects;

public class Task {
    private String name;
    private int significance;
    private String date;
    private Category category;

    public Task() {
    }

    public Task(String name, int significance) {
        this.name = name;
        this.significance = significance;
    }

    public Task(String name, int significance, String date, Category category) {
        this.name = name;
        this.significance = significance;
        this.date = date;
        this.category = category;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", significance=" + significance +
                ", date='" + date + '\'' +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return getSignificance() == task.getSignificance() && Objects.equals(getName(), task.getName()) && Objects.equals(getDate(), task.getDate()) && getCategory() == task.getCategory();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSignificance(), getDate(), getCategory());
    }
}

enum Category{
    A,B,C
}


