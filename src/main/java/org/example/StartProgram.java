package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StartProgram {

    private User user;

    private ArrayList<String> otherTask = new ArrayList<>();

    public  void startMenu() {
        Scanner scn = new Scanner(System.in);
        System.out.println("1 - Регистрация нового пользователя");
        System.out.println("2 - Авторизация пользователя");
        int pic = scn.nextInt();
        if (pic == 1) {
            register();
        } else if (pic == 2) {
            logging();
        }
    }

    public  void logging() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Ведіть ім'я користувача");
        String log = scn.nextLine();
        System.out.println("Ведіть пароль користувача");
        String pass = scn.nextLine();
        if (checkLogging(log, pass)) {
            startLogingUser(log, pass);
        } else {
            System.out.println("Не вірно ведено логін або пароль");
            startMenu();
        }
    }

    private void startLogingUser(String log, String pass) {
        this.user = new User(log,pass);

        String read;
        try (InputStream inputStream = new FileInputStream("task.txt")) {
            byte[] buf = new byte[inputStream.available()];
            int count = inputStream.read(buf);
            read = new String(buf, 0, count);
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
       String[] arrTask = read.split("\n");
        for (int i = 0; i <arrTask.length ; i++) {
            String[] tmp = arrTask[i].split(":");
            if (tmp[0].equals(log)){
                user.addTaskStart(tmp[1],Integer.parseInt(tmp[2]));
            }
        }
        otherTask(log);
        startUserMenu();
    }

    public void startUserMenu (){
        Scanner scn = new Scanner(System.in);
        System.out.println("1. Отображение списка всех не выполненых задач");
        System.out.println("2. Отображение списка всех выполненных задач");
        System.out.println("3. Добавление новой задачи");
        System.out.println("4. Отметка про выполнение задания");
        System.out.println("5. Удаление задачи");
        System.out.println("6. Выход");
        int pic = scn.nextInt();
        switch (pic) {
            case 1:
                user.printAllImplementationFalse();
                break;
            case 2:
                user.printAllImplementationTrue();
                break;
            case 3:
               user.addTask();
               break;
            case 4:
                user.implementationIsTrueInTask();
                break;
            case 5:
                user.deleteTask();
                break;
            case 6:
                user.exit();
                writeOtherTask();
                startMenu();
                break;
            default:
        }
        startUserMenu();
    }

    private void writeOtherTask() {
        try (OutputStream outputStream = new FileOutputStream("task.txt",true)) {

            for (String message: otherTask){
                message+="\n";
                outputStream.write(message.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void otherTask(String log) {
        String read;
        try (InputStream inputStream = new FileInputStream("task.txt")) {
            byte[] buf = new byte[inputStream.available()];
            int count = inputStream.read(buf);
            read = new String(buf, 0, count);
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        String[] arrTask = read.split("\n");

        for (int i = 0; i <arrTask.length ; i++) {
            int a = arrTask[i].indexOf(':');
            if (!(arrTask[i].contains(log))){
                otherTask.add(arrTask[i]);
            }
        }
    }

    private  boolean checkLogging(String log, String pass) {
        boolean c = false;
        String[] arrUser = readBaseUser();
        for (int i = 0; i < arrUser.length; i++) {
            String[] tmpUser = arrUser[i].split(":");
            if (tmpUser[0].equals(log)) {
                if (tmpUser[1].equals(pass)) {
                    c = true;
                    break;
                }
                break;
            }
        }
        return c;
    }

    private  String[] readBaseUser() {
        String read;

        try (InputStream inputStream = new FileInputStream("user.txt")) {
            byte[] buf = new byte[inputStream.available()];
            int count = inputStream.read(buf);
            read = new String(buf, 0, count);
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return read.split("\n");
    }

    private  void register() {
        Scanner scn = new Scanner(System.in);
        System.out.print("Ведіть ім'я нового користувача: ");
        String login = scn.nextLine();
        if (check(login)) {
            System.out.println("Такий користувач в базі існує");
        } else {
            addNewUser(login);
        }
        startMenu();

    }

    private  void addNewUser(String login) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Ведіть пароль для користувача");
        String password = scn.nextLine();
        try (OutputStream outputStream = new FileOutputStream("user.txt", true)) {
            String message = "\n" + login + ":" + password;
            outputStream.write(message.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private  boolean check(String login) {
        boolean c = false;
        String[] arrUser = readBaseUser();
        for (int i = 0; i < arrUser.length; i++) {
            String[] tmpUser = arrUser[i].split(":");
            if (tmpUser[0].equals(login)) {
                c = true;
                break;
            }
        }
        return c;
    }

}
