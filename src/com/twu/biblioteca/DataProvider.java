package com.twu.biblioteca;

import com.twu.biblioteca.library.Book;
import com.twu.biblioteca.library.Library;

import java.util.Arrays;

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
}
