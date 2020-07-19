package com.twu.biblioteca;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Library {
    private final Map<String, Book> titleMap;
    private Map<Book, Boolean> statusMap;

    public Library(Collection<Book> initBooks) {
        this.titleMap = initBooks.stream()
                .collect(Collectors.toMap(Book::getTitle, Function.identity(), (a, b) -> a));
        init();
    }

    public void init() {
        this.statusMap = this.titleMap.values().stream()
                .collect(Collectors.toMap(Function.identity(), book -> true, (a, b) -> a));
    }

    public Map<Book, Boolean> getStatusMap() {
        return statusMap;
    }

    public List<Book> getAllBooks() {
        return statusMap.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
