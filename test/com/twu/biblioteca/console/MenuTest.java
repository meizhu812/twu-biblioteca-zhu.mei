package com.twu.biblioteca.console;

import com.twu.biblioteca.DataProvider;
import org.junit.Before;

import org.junit.Test;

import java.util.Scanner;

public class MenuTest {
    private Menu menu;

    @Before
    public void setUp() {
        menu = new Menu(DataProvider.provideOptions(DataProvider.provideLibrary()), new Scanner(System.in));
    }

    @Test
    public void should_show_proper_output_when_listing_all_books() throws InvalidOption {
        menu.runOption("1");
    }

    @Test(expected = InvalidOption.class)
    public void should_throw_invalid_option_with_invalid_serial_4() throws InvalidOption {
        menu.runOption("4");
    }

    @Test(expected = InvalidOption.class)
    public void should_throw_invalid_option_with_invalid_serial_a() throws InvalidOption {
        menu.runOption("a");
    }

    @Test(expected = InvalidOption.class)
    public void should_throw_invalid_option_with_invalid_serial_01() throws InvalidOption {
        menu.runOption("01");
    }

    @Test
    public void should_show_proper_output_when_checking_out_books() throws InvalidOption {
        String inputs = "Foundation\nThe Dark Forest\nFoundation\nWar and Peace\n";
        Scanner scanner = new Scanner(inputs);
        scanner.useDelimiter("\n");
        menu.setScanner(scanner);
        menu.runOption("2");
        menu.runOption("2");
        menu.runOption("2");
        menu.runOption("2");
    }

    @Test
    public void should_show_proper_output_when_returning_books() throws InvalidOption {
        String inputs
                = "Foundation\n"
                + "Foundation\n"
                + "Foundation\n"
                + "Foundation\n"
                + "The Dart Forest\n";
        Scanner scanner = new Scanner(inputs);
        scanner.useDelimiter("\n");
        menu.setScanner(scanner);
        menu.runOption("2");
        menu.runOption("3");
        menu.runOption("3");
        menu.runOption("2");
        menu.runOption("3");
    }
}
