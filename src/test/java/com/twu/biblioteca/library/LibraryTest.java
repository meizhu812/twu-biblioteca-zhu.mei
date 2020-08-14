package com.twu.biblioteca.library;


import com.twu.biblioteca.DataProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class LibraryTest {
    private Library<Book> bookLibrary;
    private Library<Film> filmLibrary;

    @Before
    public void setUp() {
        bookLibrary = DataProvider.provideBookLibrary();
        filmLibrary = DataProvider.provideFilmLibrary();
    }

    @Test
    public void should_get_all_books() {
        assertEquals(bookLibrary.getAllContents().size(), 4);
    }

    @Test
    public void should_success_check_out_books_in_library() {
        assertSuccessCheckout("The Three-Body Problem", this.bookLibrary);
        assertSuccessCheckout("Foundation", this.bookLibrary);
    }

    @Test
    public void should_fail_check_out_same_book_twice() {
        assertSuccessCheckout("Foundation", this.bookLibrary);
        assertFailedCheckout("Foundation", this.bookLibrary);
    }

    @Test
    public void should_fail_check_out_non_existent_books() {
        assertFailedCheckout("Harry Potter", this.bookLibrary);
        assertFailedCheckout("War and Peace", this.bookLibrary);
    }

    @Test
    public void should_success_return_book_checked_out() {
        assertSuccessCheckout("Foundation", this.bookLibrary);
        assertTrue(bookLibrary.returnContentByTitle("Foundation"));
    }

    @Test
    public void should_success_checkout_book_returned() {
        assertSuccessCheckout("Foundation", this.bookLibrary);
        assertTrue(bookLibrary.returnContentByTitle("Foundation"));
        assertSuccessCheckout("Foundation", this.bookLibrary);
    }

    @Test
    public void should_fail_return_book_twice_but_success_checkout_again() {
        assertSuccessCheckout("Foundation", this.bookLibrary);
        assertTrue(bookLibrary.returnContentByTitle("Foundation"));
        assertFalse(bookLibrary.returnContentByTitle("Foundation"));
        assertSuccessCheckout("Foundation", this.bookLibrary);
    }

    @Test
    public void should_fail_return_non_existent_books() {
        assertFalse(bookLibrary.returnContentByTitle("Harry Potter"));
        assertFalse(bookLibrary.returnContentByTitle("War and Peace"));
    }

    @Test
    public void should_get_all_films() {
        assertEquals(filmLibrary.getAllContents().size(), 3);
    }

    @Test
    public void should_success_check_out_films_in_library() {
        assertSuccessCheckout("Batman Begins", this.filmLibrary);
        assertSuccessCheckout("The Dark Knight", this.filmLibrary);
    }

    @Test
    public void should_fail_check_out_same_film_twice() {
        assertSuccessCheckout("The Dark Knight Rises", this.filmLibrary);
        assertFailedCheckout("The Dark Knight Rises", this.filmLibrary);
    }

    @Test
    public void should_fail_check_out_non_existent_films() {
        assertFailedCheckout("Interstellar", this.filmLibrary);
        assertFailedCheckout("Joker", this.filmLibrary);
    }

    private void assertSuccessCheckout(String title, Library<? extends Content> library) {
        assertEquals(library.checkOutContentByTitle(title)
                .map(Content::getTitle)
                .orElse(""), title);
    }

    private void assertFailedCheckout(String title, Library<? extends Content> library) {
        assertNotEquals(library.checkOutContentByTitle(title)
                .map(Content::getTitle)
                .orElse(""), title);
    }
}
