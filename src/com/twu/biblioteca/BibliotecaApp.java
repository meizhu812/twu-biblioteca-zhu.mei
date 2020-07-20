package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {
    private static final Scanner sc = new Scanner(System.in);
    private static final Menu menu = new Menu(DataProvider.getMenuOptions(DataProvider.getLibraryInstance()), sc);

    static {
        sc.useDelimiter("\n");
    }

    public static void main(String[] args) {
        welcome();
        mainLoop();
    }

    private static void welcome() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private static void mainLoop() {
        while (true) {
            menu.prompt();
            try {
                menu.runOption(sc.next());
            } catch (InvalidOption e) {
                System.out.println("Please select a valid option!");
            }
        }
    }

}
