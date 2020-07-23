package com.twu.biblioteca.auth;

import com.twu.biblioteca.DataProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AuthenticatorTest {
    private Authenticator authenticator;

    @Before
    public void setUp() {
        authenticator = new Authenticator(DataProvider.provideUsers());
    }

    @Test
    public void should_success_login_with_correct_username_and_password() {
        LoginInput loginInputA = new LoginInput("001-0001", "passwordA");
        LoginInput loginInputB = new LoginInput("001-0002", "passwordB");
        assertNotNull(authenticator.authenticate(loginInputA).orElse(null));
        assertNotNull(authenticator.authenticate(loginInputB).orElse(null));
    }

    @Test
    public void should_success_login_with_incorrect_username_or_password() {
        LoginInput loginInputA = new LoginInput("001-0001", "passwordB");
        LoginInput loginInputB = new LoginInput("001-0003", "passwordB");
        assertNull(authenticator.authenticate(loginInputA).orElse(null));
        assertNull(authenticator.authenticate(loginInputB).orElse(null));
    }
}
