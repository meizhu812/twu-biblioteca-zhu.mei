package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;

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

    public static List<Option<?>> getMenuOptions(){
        Library library = getLibraryInstance();
        return Arrays.asList(new Option<>("1","List All Books",null, new ListAll(library)));
    }
}
