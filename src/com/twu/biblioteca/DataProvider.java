package com.twu.biblioteca;

import com.twu.biblioteca.console.Checkout;
import com.twu.biblioteca.console.ListAll;
import com.twu.biblioteca.console.Option;
import com.twu.biblioteca.console.Quit;
import com.twu.biblioteca.console.Return;
import com.twu.biblioteca.library.Book;
import com.twu.biblioteca.library.Library;

import java.util.Arrays;
import java.util.List;

public final class DataProvider {
    private DataProvider() {
    }

    public static Library provideLibrary() {
        return new Library(Arrays.asList(
                new Book("The Three-Body Problem", "Cixin Liu", 2014),
                new Book("The Dark Forest", "Cixin Liu", 2016),
                new Book("Death's End", "Cixin Liu", 2014),
                new Book("Foundation", "Isaac Asimov", 2004)));
    }

    public static List<Option<?>> provideOptions(Library library) {
        return Arrays.asList(
                new Option<>("1", "List All Books", null, new ListAll(library)),
                new Option<>("2", "Checkout a book", "Please enter book title:", new Checkout(library)),
                new Option<>("3", "Return a book", "Please enter book title:", new Return(library)),
                new Option<>("Q", "Quit", null, new Quit(library)));
    }
}
