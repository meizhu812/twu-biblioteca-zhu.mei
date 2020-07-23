package com.twu.biblioteca;

import com.twu.biblioteca.auth.Authenticator;
import com.twu.biblioteca.console.Console;

public class BibliotecaApp {
    public static void main(String[] args) {
        Console console = new Console(DataProvider.provideBookLibrary(), DataProvider.provideFilmLibrary(),new Authenticator(DataProvider.provideUsers()));
        console.run();
    }
}
