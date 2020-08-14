package com.twu.biblioteca;

import com.twu.biblioteca.library.Book;
import com.twu.biblioteca.library.Film;
import com.twu.biblioteca.library.Library;
import com.twu.biblioteca.user.Authenticator;
import com.twu.biblioteca.user.UserDB;

public interface Biblioteca {
    Library<Book> getBookLibrary();

    Library<Film> getFilmLibrary();

    Authenticator getAuthenticator();

    UserDB getUserDB();
}
