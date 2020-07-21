package com.twu.biblioteca.library;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Library {
    private final Map<String, Book> books;

    public Library(Collection<Book> initBooks) {
        this.books = initBooks.stream().collect(
                Collectors.toMap(Book::getTitle, Function.identity(), (a, b) -> a));
    }

    public List<Book> getAllBooks() {
        return books.values().stream()
                .filter(Book::isIn)
                .collect(Collectors.toList());
    }

    public Optional<Book> checkOutBookByTitle(String title) {
        Optional<Book> optionalBook = Optional.ofNullable(books.getOrDefault(title, null))
                .filter(Book::isIn);
        optionalBook.ifPresent(status -> status.setIn(false));
        return optionalBook;
    }

    public boolean returnBookByTitle(String title) {
        Optional<Book> optionalBook = Optional.ofNullable(books.getOrDefault(title, null))
                .filter(book -> !book.isIn());
        if (optionalBook.isPresent()) {
            optionalBook.get().setIn(true);
            return true;
        } else {
            return false;
        }
    }
}
