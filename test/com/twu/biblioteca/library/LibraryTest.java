package com.twu.biblioteca.library;


import com.twu.biblioteca.DataProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class LibraryTest {
    private Library<Book> bookLibrary;

    @Before
    public void setUp() {
        bookLibrary = DataProvider.provideBookLibrary();
    }

    @Test
    public void should_get_all_books() {
        assertEquals(bookLibrary.getAllContents().size(), 4);
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
        assertTrue(bookLibrary.returnContentByTitle("Foundation"));
    }

    @Test
    public void should_success_checkout_book_returned() {
        assertSuccessCheckout("Foundation");
        assertTrue(bookLibrary.returnContentByTitle("Foundation"));
        assertSuccessCheckout("Foundation");
    }

    @Test
    public void should_fail_return_book_twice_but_success_checkout_again() {
        assertSuccessCheckout("Foundation");
        assertTrue(bookLibrary.returnContentByTitle("Foundation"));
        assertFalse(bookLibrary.returnContentByTitle("Foundation"));
        assertSuccessCheckout("Foundation");
    }
    @Test

    public void should_fail_return_non_existent_books() {
        assertFalse(bookLibrary.returnContentByTitle("Harry Potter"));
        assertFalse(bookLibrary.returnContentByTitle("War and Peace"));
    }

    private void assertSuccessCheckout(String title) {
        assertEquals(bookLibrary.checkOutContentByTitle(title)
                .map(Content::getTitle)
                .orElse(""), title);
    }

    private void assertFailedCheckout(String title) {
        assertNotEquals(bookLibrary.checkOutContentByTitle(title)
                .map(Content::getTitle)
                .orElse(""), title);
    }
}
