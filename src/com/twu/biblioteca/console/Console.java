package com.twu.biblioteca.console;

import com.twu.biblioteca.auth.Authenticator;
import com.twu.biblioteca.auth.InvalidCredential;
import com.twu.biblioteca.auth.LoginInput;
import com.twu.biblioteca.auth.User;
import com.twu.biblioteca.auth.UserInfo;
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

@SuppressWarnings("unused")
public class Console {
    private final Scanner scanner = new Scanner(System.in);
    private final PrintStream printer = System.out;
    private final Library<Book> bookLibrary;
    private final Library<Film> filmLibrary;
    private final Authenticator authenticator;
    private final Map<String, Option> options;
    private final String optionsPrompt;
    private User currentUser;

    public Console(Library<Book> bookLibrary, Library<Film> filmLibrary, Authenticator authenticator) {
        scanner.useDelimiter("\n");
        this.bookLibrary = bookLibrary;
        this.filmLibrary = filmLibrary;
        this.authenticator = authenticator;
        options = Arrays.stream(getClass().getDeclaredMethods())
                .filter(method -> ConsoleUtil.getMenuItem(method) != null)
                .collect(Collectors.toMap(
                        method -> ConsoleUtil.getMenuItem(method).serial(),
                        ConsoleUtil::methodToOption,
                        (a, b) -> a));
        optionsPrompt = ConsoleUtil.getOptionsPrompt(options);
    }

    public void run() {
        welcome();
        login();
        main();
    }

    private void welcome() {
        printer.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    private void login() {
        while (true) {
            try {
                verifyLogin();
                break;
            } catch (InvalidCredential e) {
                printer.println("Wrong credential.");
            }
        }
    }

    private void verifyLogin() throws InvalidCredential {
        String cardNo = inputWithPrompt("Please enter your card number:");
        String password = inputWithPrompt("Please enter your password:");
        currentUser = authenticator.authenticate(new LoginInput(cardNo, password));
    }

    private String inputWithPrompt(String prompt) {
        printer.println(prompt);
        return scanner.next();
    }

    private void main() {
        while (true) {
            try {
                runOption(inputWithPrompt(optionsPrompt));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                break;
            }
        }
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
                method.invoke(this, inputWithPrompt(prompt));
            }
        }
    }

    /*
    Menu Options
    */

    @MenuItem(serial = "0", desc = "Show User Info", prompt = "")
    private void showUserInfo() {
        UserInfo userInfo = authenticator.getUserInfoByUser(currentUser);
        final String INFO_FORMAT = "- %-16s: %32s\n";
        printer.println("= User Info =");
        printer.printf(INFO_FORMAT, "Card Number", userInfo.getCardNo());
        printer.printf(INFO_FORMAT, "Name", userInfo.getName());
        printer.printf(INFO_FORMAT, "EMail", userInfo.getEmail());
        printer.printf(INFO_FORMAT, "Phone Number", userInfo.getPhoneNo());
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
        filmLibrary.getAllContents()
                .forEach(film -> System.out.printf(
                        ENTRY_FORMAT, film.getTitle(), film.getDirector(), film.getYear(), film.getRating()));
    }

    @MenuItem(serial = "5", desc = "Checkout a film", prompt = "Please enter book title:")
    private void checkoutFilm(String title) {
        if (filmLibrary.checkOutContentByTitle(title).isPresent()) {
            printer.println("Thank you! Enjoy the film!");
        } else {
            printer.println("Sorry, that film is not available.");
        }
    }

    @MenuItem(serial = "Q", desc = "Quit", prompt = "")
    private void quit() {
        printer.println("Thanks for using Biblioteca!");
        System.exit(0);
    }
}
