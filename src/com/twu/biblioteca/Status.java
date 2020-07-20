package com.twu.biblioteca;

public class Status {
    private final Book book;
    private boolean isIn;

    public Status(Book book) {
        this.book = book;
        isIn = true;
    }

    public boolean isIn() {
        return isIn;
    }

    public void setIn(boolean in) {
        isIn = in;
    }

    public Book getBook() {
        return book;
    }
}
