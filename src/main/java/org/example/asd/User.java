package org.example.asd;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private String login;
    private String password;
    private ArrayList<Task> listTask;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
        listTask = new ArrayList<>();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addTaskStart (String name, int significance){
        listTask.add(new Task(name,significance));
    }

    public void addTask() {
        Scanner scn = new Scanner(System.in);
        System.out.print("Ведіть назву задачі: ");
        String name = scn.nextLine();
        System.out.print("Ведіть важливість задачі від 1 до 5: ");
        int significance = scn.nextInt();
        listTask.add(new Task(name, significance));
    }

    public void printTask() {
        for (Task t : listTask) {
            System.out.println(t);
        }
    }

    public void printAllImplementationTrue() {
        for (Task t : listTask) {
            if (t.getImplementation() == true)
                System.out.println(t);
        }
    }

    public void printAllImplementationFalse() {
        for (Task t : listTask) {
            if (t.getImplementation() == false)
                System.out.println(t);
        }
    }

    public void printSortTaskSignificance() {
        ArrayList<Task> listCopy = new ArrayList<>(listTask);
        listCopy.stream()
                .sorted((e1, e2) -> e1.getSignificance() - e2.getSignificance())
                .map(e -> e.getImplementation() == false)
                .forEach(System.out::println);
    }

    public void deleteTask() {
        Scanner scn = new Scanner(System.in);
        printTask();
        System.out.println("Укажіть id задачі, яку потрібно видалити");
        int tmp = scn.nextInt();
        for (int i = 0; i <listTask.size() ; i++) {
            if (listTask.get(i).getSignificance()==tmp){
                listTask.remove(i);
                break;
            }
        }
    }
}
