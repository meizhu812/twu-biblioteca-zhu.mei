package com.twu.biblioteca;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class LibraryNew {
    private final Map<String, Status> statusMap;

    public LibraryNew(Collection<Book> initBooks) {
        this.statusMap = initBooks.stream().collect(
                Collectors.toMap(Book::getTitle, Status::new, (a, b) -> a));
    }

    public List<Book> getAllBooks() {
        return statusMap.values().stream()
                .map(Status::getBook)
                .collect(Collectors.toList());
    }

    public Optional<Book> checkOutBookByTitle(String title) {
        Optional<Status> optionalStatus = Optional.ofNullable(statusMap.getOrDefault(title, null))
                .filter(Status::isIn);
        optionalStatus.ifPresent(status -> status.setIn(false));
        return optionalStatus.map(Status::getBook);
    }

    public boolean returnBookByTitle(String title) {
        Optional<Status> optionalStatus = Optional.ofNullable(statusMap.getOrDefault(title, null))
                .filter(status -> !status.isIn());
        if (optionalStatus.isPresent()) {
            optionalStatus.get().setIn(true);
            return true;
        } else {
            return false;
        }
    }
}
