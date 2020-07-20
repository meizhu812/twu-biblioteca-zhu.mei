package com.twu.biblioteca;

public class Checkout extends Operation<Boolean> {
    public Checkout(Library library) {
        super(library);
    }

    @Override
    Boolean execute(String... input) {
        return library.checkOutBookByTitle(input[0]).isPresent();
    }

    @Override
    void consoleExecute(String... input) {
        if (execute(input)) {
            System.out.println("Thank you! Enjoy the book!");
        } else {
            System.out.println("Sorry, that book is not available.");
        }
    }
}
