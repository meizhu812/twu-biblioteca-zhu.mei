package com.twu.biblioteca.console;

import com.twu.biblioteca.library.Library;

public class Quit extends Operation<Boolean> {
    public Quit(Library library) {
        super(library);
    }

    @Override
    Boolean execute(String... input) {
        /* For actions before exiting
         */
        return true;
    }

    @Override
    public void consoleExecute(String... input) {
        if (execute()) {
            System.out.println("Thanks for using Biblioteca!");
        }
        System.exit(0);
    }
}

