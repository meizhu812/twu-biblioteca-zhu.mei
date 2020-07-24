package com.twu.biblioteca;

import com.twu.biblioteca.auth.TestAuthenticator;
import com.twu.biblioteca.console.Console;

public class BibliotecaApp {
    public static void main(String[] args) {
        Console console = new Console(DataProvider.provideBookLibrary(), DataProvider.provideFilmLibrary(), new TestAuthenticator(DataProvider.provideUsers(), DataProvider.provideUserInfo()));
        console.run();
    }
}
