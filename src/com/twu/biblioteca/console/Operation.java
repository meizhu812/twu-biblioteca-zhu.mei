package com.twu.biblioteca.console;

import com.twu.biblioteca.library.Library;

public abstract class Operation<R> {
    protected final Library library;

    public Operation(Library library) {
        this.library = library;
    }

    abstract R execute(String... input);

    public abstract void consoleExecute(String... input);
}
