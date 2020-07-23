package com.twu.biblioteca.library;

public abstract class Content {
    protected final String title;
    protected boolean isIn;

    public Content(String title) {
        this.title = title;
        this.isIn = true;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIn() {
        return isIn;
    }

    public void setIn(boolean in) {
        isIn = in;
    }
}
