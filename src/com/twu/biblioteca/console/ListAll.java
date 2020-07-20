package com.twu.biblioteca.console;

import com.twu.biblioteca.library.Book;
import com.twu.biblioteca.library.Library;

import java.util.List;

public class ListAll extends Operation<List<Book>> {
    public ListAll(Library library) {
        super(library);
    }

    private static final String ENTRY_FORMAT = "- %-32s | %-16s | %16s\n";

    @Override
    List<Book> execute(String... input) {
        return library.getAllBooks();
    }

    @Override
    public void consoleExecute(String... input) {
        System.out.println("= Listing all books in Biblioteca:");
        System.out.printf(ENTRY_FORMAT,"-TITLE-","-AUTHOR-","-PUBLISH YEAR-");
        execute().forEach(book -> System.out.printf(ENTRY_FORMAT, book.getTitle(), book.getAuthor(), book.getPubYear()));
    }
}
