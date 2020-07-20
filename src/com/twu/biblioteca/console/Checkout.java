package com.twu.biblioteca.console;

import com.twu.biblioteca.library.Library;

public class Checkout extends Operation<Boolean> {
    public Checkout(Library library) {
        super(library);
    }

    @Override
    Boolean execute(String... input) {
        return library.checkOutBookByTitle(input[0]).isPresent();
    }

    @Override
    public void consoleExecute(String... input) {
        if (execute(input)) {
            System.out.println("Thank you! Enjoy the book!");
        } else {
            System.out.println("Sorry, that book is not available.");
        }
    }
}
