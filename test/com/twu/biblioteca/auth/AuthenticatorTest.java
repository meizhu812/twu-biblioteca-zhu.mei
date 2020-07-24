package com.twu.biblioteca.auth;

import com.twu.biblioteca.DataProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthenticatorTest {
    private Authenticator authenticator;

    @Before
    public void setUp() {
        authenticator = new TestAuthenticator(DataProvider.provideUsers(), DataProvider.provideUserInfo());
    }

    @Test
    public void should_success_login_with_correct_username_and_password() throws InvalidCredential {
        LoginInput loginInputA = new LoginInput("001-0001", "passwordA");
        LoginInput loginInputB = new LoginInput("001-0002", "passwordB");
        assertNotNull(authenticator.authenticate(loginInputA));
        assertNotNull(authenticator.authenticate(loginInputB));
    }

    @Test(expected = InvalidCredential.class)
    public void should_success_login_with_incorrect_password() throws InvalidCredential {
        LoginInput loginInputA = new LoginInput("001-0001", "passwordB");
        assertNull(authenticator.authenticate(loginInputA));
    }

    @Test(expected = InvalidCredential.class)
    public void should_success_login_with_incorrect_username() throws InvalidCredential {
        LoginInput loginInputB = new LoginInput("001-0003", "passwordB");
        assertNull(authenticator.authenticate(loginInputB));
    }

    @Test
    public void should_fetch_user_info_of_user() throws InvalidCredential {
        User amy = authenticator.authenticate(new LoginInput("001-0001", "passwordA"));
        assertEquals("Amy", authenticator.getUserInfoByUser(amy).getName());
    }
}
