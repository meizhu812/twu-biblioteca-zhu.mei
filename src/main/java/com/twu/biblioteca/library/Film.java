package com.twu.biblioteca.library;

public class Film extends Content{
    private final int year;
    private final String director;
    private int rating;

    public Film(String title, int year, String director, int rating) {
        super(title);
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public int getRating() {
        return rating;
    }
}
