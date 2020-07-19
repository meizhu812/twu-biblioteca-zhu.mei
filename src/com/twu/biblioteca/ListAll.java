package com.twu.biblioteca;

import java.util.List;

public class ListAll extends Operation<List<Book>> {
    public ListAll(Library library) {
        super(library);
    }

    @Override
    List<Book> execute(String... input) {
        return library.getAllBooks();
    }

    @Override
    void consoleExecute(String... input) {
        System.out.println("Listing all books in Biblioteca:");
        System.out.println("Title | Author | Publish Year");
        execute().forEach(book -> System.out.printf("%s | %s | %d\n", book.getTitle(), book.getAuthor(), book.getPubYear()));
    }
}
