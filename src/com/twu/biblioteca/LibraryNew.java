package com.twu.biblioteca;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class LibraryNew {
    private final Map<String, Book> titleMap;
    private Map<Book, Boolean> statusMap;

    public LibraryNew(Collection<Book> initBooks) {
        this.titleMap = initBooks.stream().collect(
                Collectors.toMap(Book::getTitle, Function.identity(), (a, b) -> a));
        init();
    }

    public void init() {
        this.statusMap = this.titleMap.values().stream().collect(
                Collectors.toMap(Function.identity(), book -> true, (a, b) -> a));
    }

    public List<Book> getAllBooks() {
        return statusMap.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public Optional<Book> checkOutBookByTitle(String title) {
        Optional<Book> bookOptional = Optional.ofNullable(titleMap.getOrDefault(title, null))
                .filter(statusMap::get);
        bookOptional.ifPresent(book -> statusMap.put(book, false));
        return bookOptional;
    }

    public boolean returnBookByTitle(String title) {
        Optional<Book> bookOptional = Optional.ofNullable(titleMap.getOrDefault(title, null))
                .filter(book -> !statusMap.get(book));
        if (bookOptional.isPresent()) {
            statusMap.put(bookOptional.get(), true);
            return true;
        } else {
            return false;
        }
    }
}