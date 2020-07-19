package com.twu.biblioteca;

import java.util.Arrays;

public final class DataProvider {
    private DataProvider() {
    }

    public static Library getLibraryInstance() {
        Book[] books = {
                new Book("The Three-Body Problem", "Cixin Liu", 2014),
                new Book("The Dark Forest", "Cixin Liu", 2016),
                new Book("Death's End", "Cixin Liu", 2014),
                new Book("Foundation", "Isaac Asimov", 2004)};
        return new Library(Arrays.asList(books));
    }
}
