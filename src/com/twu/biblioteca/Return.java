package com.twu.biblioteca;

public class Return extends Operation<Boolean> {
    public Return(Library library) {
        super(library);
    }

    @Override
    Boolean execute(String... input) {
        return library.returnBookByTitle(input[0]);
    }

    @Override
    void consoleExecute(String... input) {
        if (execute(input)) {
            System.out.println("Thank you for returning the book!");
        } else {
            System.out.println("That is not a valid book to return.");
        }
    }
}
