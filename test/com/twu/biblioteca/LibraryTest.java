package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LibraryTest {
    private Library library;

    @Before
    public void setUp() {
        library = DataProvider.getLibraryInstance();
    }

    @Test
    public void should_get_all_books() {
        assertEquals(library.getAllBooks().size(), 4);
    }
}
