package com.twu.biblioteca.library;

import java.util.List;
import java.util.Optional;

public interface Library<C extends Content> {
    List<C> getAllContents();

    Optional<C> checkOutContentByTitle(String title);

    boolean returnContentByTitle(String title);
}
