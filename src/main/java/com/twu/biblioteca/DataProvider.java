package com.twu.biblioteca;

import com.twu.biblioteca.library.Book;
import com.twu.biblioteca.library.Film;
import com.twu.biblioteca.library.Library;
import com.twu.biblioteca.library.TestLibrary;
import com.twu.biblioteca.user.Authenticator;
import com.twu.biblioteca.user.TestAuthenticator;
import com.twu.biblioteca.user.TestUserDB;
import com.twu.biblioteca.user.User;
import com.twu.biblioteca.user.UserDB;
import com.twu.biblioteca.user.UserInfo;

import java.util.Arrays;

public final class DataProvider {
    private DataProvider() {
    }

    public static Library<Book> provideBookLibrary() {
        return new TestLibrary<>(Arrays.asList(
                new Book("The Three-Body Problem", "Cixin Liu", 2014),
                new Book("The Dark Forest", "Cixin Liu", 2016),
                new Book("Death's End", "Cixin Liu", 2014),
                new Book("Foundation", "Isaac Asimov", 2004)));
    }

    public static Library<Film> provideFilmLibrary() {
        return new TestLibrary<>(Arrays.asList(
                new Film("Batman Begins", 2005, "Christopher Nolan", 8),
                new Film("The Dark Knight", 2008, "Christopher Nolan", 9),
                new Film("The Dark Knight Rises", 2012, "Christopher Nolan", 8)));
    }

    public static Authenticator provideAuthenticator() {
        return new TestAuthenticator(
                Arrays.asList(
                        new User("001-0001", "passwordA"),
                        new User("001-0002", "passwordB")));
    }

    public static UserDB provideUserDB() {
        return new TestUserDB(Arrays.asList(
                new UserInfo("001-0001", "Amy", "amy@tw.com", "13100000001"),
                new UserInfo("001-0002", "Bob", "bob@tw.com", "13200000002")));
    }

    public static Biblioteca provideBiblioteca() {
        return new TestBiblioteca(
                provideBookLibrary(),
                provideFilmLibrary(),
                provideAuthenticator(),
                provideUserDB()
        );
    }
}
