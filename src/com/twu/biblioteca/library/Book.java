package com.twu.biblioteca.library;

public final class Book extends Content {
    private final String author;
    private final int pubYear;

    public Book(String title, String author, int pubYear) {
        super(title);
        this.author = author;
        this.pubYear = pubYear;
    }

    public String getAuthor() {
        return author;
    }

    public int getPubYear() {
        return pubYear;
    }

}
