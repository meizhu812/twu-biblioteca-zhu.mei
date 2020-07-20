package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LibraryTestNew {
    private LibraryNew library;

    @Before
    public void setUp() {
        library = DataProvider.provideLibraryNew();
    }

    @Test
    public void should_get_all_books() {
        assertEquals(library.getAllBooks().size(), 4);
    }

    @Test
    public void should_success_check_out_books_in_library() {
        assertSuccessCheckout("The Three-Body Problem");
        assertSuccessCheckout("Foundation");
    }

    @Test
    public void should_fail_check_out_twice() {
        assertSuccessCheckout("Foundation");
        assertFailedCheckout("Foundation");
    }

    @Test
    public void should_fail_check_out_non_existent_books() {
        assertFailedCheckout("Harry Potter");
        assertFailedCheckout("War and Peace");
    }

    @Test
    public void should_success_return_book_checked_out() {
        assertSuccessCheckout("Foundation");
        assertTrue(library.returnBookByTitle("Foundation"));
    }

    @Test
    public void should_success_checkout_book_returned() {
        assertSuccessCheckout("Foundation");
        assertTrue(library.returnBookByTitle("Foundation"));
        assertSuccessCheckout("Foundation");
    }

    @Test
    public void should_fail_return_book_twice_but_success_checkout_again() {
        assertSuccessCheckout("Foundation");
        assertTrue(library.returnBookByTitle("Foundation"));
        assertFalse(library.returnBookByTitle("Foundation"));
        assertSuccessCheckout("Foundation");
    }
    @Test

    public void should_fail_return_non_existent_books() {
        assertFalse(library.returnBookByTitle("Harry Potter"));
        assertFalse(library.returnBookByTitle("War and Peace"));
    }

    private void assertSuccessCheckout(String title) {
        assertEquals(library.checkOutBookByTitle(title)
                .map(Book::getTitle)
                .orElse(""), title);
    }

    private void assertFailedCheckout(String title) {
        assertNotEquals(library.checkOutBookByTitle(title)
                .map(Book::getTitle)
                .orElse(""), title);
    }
}
