package com.twu.biblioteca;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MenuTest {
    private Menu menu;

    @Before
    public void setUp() {
        menu = new Menu(DataProvider.getMenuOptions(DataProvider.getLibraryInstance()));
    }

    @Test
    public void should_show_proper_output_when_listing_all_books() {
        menu.runOption("1");
    }

    @Test(expected = InvalidOption.class)
    public void should_throw_invalid_option_with_invalid_serial_2() throws InvalidOption {
        menu.runOption("2");
    }

    @Test(expected = InvalidOption.class)
    public void should_throw_invalid_option_with_invalid_serial_a() throws InvalidOption {
        menu.runOption("a");
    }

    @Test(expected = InvalidOption.class)
    public void should_throw_invalid_option_with_invalid_serial_01() throws InvalidOption {
        menu.runOption("01");
    }
}
