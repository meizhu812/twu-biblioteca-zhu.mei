package com.twu.biblioteca.console;

import com.twu.biblioteca.library.Book;
import com.twu.biblioteca.library.Film;
import com.twu.biblioteca.library.Library;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Console {
    private final Scanner scanner = new Scanner(System.in);
    private final PrintStream printer = System.out;
    private final Library<Book> bookLibrary;
    private final Library<Film> filmLibrary;
    private final Map<String, Option> options;

    public Console(Library<Book> bookLibrary, Library<Film> filmLibrary) {
        scanner.useDelimiter("\n");
        this.bookLibrary = bookLibrary;
        this.filmLibrary = filmLibrary;
        options = Arrays.stream(getClass().getDeclaredMethods())
                .filter(method -> method.getAnnotation(MenuItem.class) != null)
                .collect(Collectors.toMap(
                        method -> getAnnotation(method).serial(),
                        this::methodToOption,
                        (a, b) -> a));
    }

    public void run() {
        welcome();
        while (true) {
            showOptions();
            try {
                runOption(scanner.next());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private Option methodToOption(Method method) {
        MenuItem annotation = method.getAnnotation(MenuItem.class);
        return new Option(annotation.serial(), annotation.desc(), annotation.prompt(), method);
    }

    private MenuItem getAnnotation(Method method) {
        return method.getAnnotation(MenuItem.class);
    }

    private void welcome() {
        printer.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    private void showOptions() {
        printer.println("Please select an option:");
        options.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> printer.printf("%2s - %s\n", entry.getKey(), entry.getValue().getDescription()));
    }

    private void runOption(String serial) throws InvocationTargetException, IllegalAccessException {
        Optional<Option> optionalOption = Optional.ofNullable(options.getOrDefault(serial, null));
        if (!optionalOption.isPresent()) {
            System.out.println("Please select a valid option!");
        } else {
            Option option = optionalOption.get();
            Method method = option.getMethod();
            String prompt = option.getInputPrompt();
            if (prompt.equals("")) {
                method.invoke(this);
            } else {
                printer.println(prompt);
                method.invoke(this, scanner.next());
            }
        }
    }

    @MenuItem(serial = "1", desc = "List All Books", prompt = "")
    private void listAllBooks() {
        final String ENTRY_FORMAT = "- %-32s | %-16s | %16s\n";
        printer.println("= Listing all books in Biblioteca:");
        printer.printf(ENTRY_FORMAT, "-TITLE-", "-AUTHOR-", "-PUBLISH YEAR-");
        bookLibrary.getAllContents().forEach(book -> System.out.printf(ENTRY_FORMAT, book.getTitle(), book.getAuthor(), book.getPubYear()));
    }

    @MenuItem(serial = "2", desc = "Checkout a book", prompt = "Please enter book title:")
    private void checkoutBook(String title) {
        if (bookLibrary.checkOutContentByTitle(title).isPresent()) {
            printer.println("Thank you! Enjoy the book!");
        } else {
            printer.println("Sorry, that book is not available.");
        }
    }

    @MenuItem(serial = "3", desc = "Return a book", prompt = "Please enter book title:")
    private void returnBook(String title) {
        if (bookLibrary.returnContentByTitle(title)) {
            printer.println("Thank you for returning the book!");
        } else {
            printer.println("That is not a valid book to return.");
        }
    }

    @MenuItem(serial = "4", desc = "List All Films", prompt = "")
    private void listAllFilms() {
        final String ENTRY_FORMAT = "- %-32s | %-32s | %-8s |%-16s\n";
        printer.println("= Listing all films in Biblioteca:");
        printer.printf(ENTRY_FORMAT, "-TITLE-", "-Director-", "-YEAR-", "-RATING-");
        filmLibrary.getAllContents().forEach(film -> System.out.printf(ENTRY_FORMAT, film.getTitle(), film.getDirector(), film.getYear(), film.getRating()));
    }

    @MenuItem(serial = "Q", desc = "Quit", prompt = "")
    private void quit() {
        printer.println("Thanks for using Biblioteca!");
        System.exit(0);
    }
}
