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
}
