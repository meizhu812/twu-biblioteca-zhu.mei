package com.twu.biblioteca;

public abstract class Operation<R> {
    protected final Library library;

    public Operation(Library library) {
        this.library = library;
    }

    abstract R execute(String... input);

    abstract void consoleExecute(String... input);
}
