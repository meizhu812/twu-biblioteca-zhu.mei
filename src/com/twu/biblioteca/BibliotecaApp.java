package com.twu.biblioteca;

import com.twu.biblioteca.console.Console;

public class BibliotecaApp {
    public static void main(String[] args) {
        Console console = new Console(DataProvider.provideBookLibrary());
        console.run();
    }
}
