package com.twu.biblioteca.library;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Library<C extends Content> {
    private final Map<String, C> contents;

    public Library(Collection<C> initContents) {
        this.contents = initContents.stream().collect(
                Collectors.toMap(C::getTitle, Function.identity(), (a, b) -> a));
    }

    public List<C> getAllContents() {
        return contents.values().stream()
                .filter(C::isIn)
                .collect(Collectors.toList());
    }

    public Optional<C> checkOutContentByTitle(String title) {
        Optional<C> optionalBook = Optional.ofNullable(contents.getOrDefault(title, null))
                .filter(C::isIn);
        optionalBook.ifPresent(status -> status.setIn(false));
        return optionalBook;
    }

    public boolean returnContentByTitle(String title) {
        Optional<C> optionalContent = Optional.ofNullable(contents.getOrDefault(title, null))
                .filter(content -> !content.isIn());
        if (optionalContent.isPresent()) {
            optionalContent.get().setIn(true);
            return true;
        } else {
            return false;
        }
    }
}
