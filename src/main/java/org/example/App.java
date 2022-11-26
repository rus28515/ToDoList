package org.example;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class App {


    private static void register() {
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

    private static void addNewUser(String login) {
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

    private static String[] readBaseUser() {
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

    private static boolean check(String login) {
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

    public static void startMenu() {
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

    public static void logging() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Ведіть ім'я користувача");
        String log = scn.nextLine();
        System.out.println("Ведіть пароль користувача");
        String pass = scn.nextLine();
        if (checkLogging(log, pass)) {
            userMenu(log);
        } else {
            System.out.println("Не вірно ведено логін або пароль");
        }

    }

    private static boolean checkLogging(String log, String pass) {
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

    public static void userMenu(String log) {



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
            case 2:
            case 3:
                addNewTask(log);
                break;
            case 4:
            case 5:
            case 6:
                startMenu();
                break;
            default:
        }
    }

    private static void addNewTask(String log) {
        Task task = new Task();
        Scanner scn = new Scanner(System.in);
        System.out.println("Ведіть назву задачі");
        String name = scn.nextLine();
        task.setName(name);
        System.out.println("Ведіть важливість задачі від 1 до 5");
        int sig = scn.nextInt();
        task.setSignificance(sig);
        try (OutputStream outputStream = new FileOutputStream("task.txt", true)) {
            String message = "\n"+log+":"+name+":"+sig ;
            outputStream.write(message.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {

        startMenu();

    }
}
