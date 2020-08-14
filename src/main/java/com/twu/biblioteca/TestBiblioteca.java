package com.twu.biblioteca;

import com.twu.biblioteca.library.Book;
import com.twu.biblioteca.library.Film;
import com.twu.biblioteca.library.Library;
import com.twu.biblioteca.user.Authenticator;
import com.twu.biblioteca.user.UserDB;

public final class TestBiblioteca implements Biblioteca {
    private final Library<Book> bookLibrary;
    private final Library<Film> filmLibrary;
    private final Authenticator authenticator;
    private final UserDB userDB;

    public TestBiblioteca(Library<Book> bookLibrary, Library<Film> filmLibrary, Authenticator authenticator, UserDB userDB) {
        this.bookLibrary = bookLibrary;
        this.filmLibrary = filmLibrary;
        this.authenticator = authenticator;
        this.userDB = userDB;
    }

    @Override
    public Library<Book> getBookLibrary() {
        return bookLibrary;
    }

    @Override
    public Library<Film> getFilmLibrary() {
        return filmLibrary;
    }

    @Override
    public Authenticator getAuthenticator() {
        return authenticator;
    }

    @Override
    public UserDB getUserDB() {
        return userDB;
    }
}
