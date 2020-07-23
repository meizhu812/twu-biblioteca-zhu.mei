package com.twu.biblioteca;

import com.twu.biblioteca.auth.User;
import com.twu.biblioteca.auth.UserInfo;
import com.twu.biblioteca.library.Book;
import com.twu.biblioteca.library.Film;
import com.twu.biblioteca.library.Library;

import java.util.Arrays;
import java.util.List;

public final class DataProvider {
    private DataProvider() {
    }

    public static Library<Book> provideBookLibrary() {
        return new Library<>(Arrays.asList(
                new Book("The Three-Body Problem", "Cixin Liu", 2014),
                new Book("The Dark Forest", "Cixin Liu", 2016),
                new Book("Death's End", "Cixin Liu", 2014),
                new Book("Foundation", "Isaac Asimov", 2004)));
    }

    public static Library<Film> provideFilmLibrary() {
        return new Library<>(Arrays.asList(
                new Film("Batman Begins", 2005, "Christopher Nolan", 8),
                new Film("The Dark Knight", 2008, "Christopher Nolan", 9),
                new Film("The Dark Knight Rises", 2012, "Christopher Nolan", 8)));
    }

    public static List<User> provideUsers() {
        return Arrays.asList(
                new User("001-0001", "passwordA"),
                new User("001-0002", "passwordB"));
    }

    public static List<UserInfo> provideUserInfo() {
        return Arrays.asList(
                new UserInfo("001-0001", "Amy", "amy@tw.com", "13100000001"),
                new UserInfo("001-0002", "Bob", "bob@tw.com", "13200000002"));
    }
}
