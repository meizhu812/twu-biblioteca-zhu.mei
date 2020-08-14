package com.twu.biblioteca.user;

import com.twu.biblioteca.DataProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserDBTest {
    private Authenticator authenticator;
    private UserDB userDB;

    @Before
    public void setUp() {
        authenticator = DataProvider.provideAuthenticator();
        userDB = DataProvider.provideUserDB();
    }

    @Test
    public void should_fetch_user_info_of_user() throws InvalidCredential {
        User amy = authenticator.authenticate(new LoginInput("001-0001", "passwordA"));
        assertEquals("Amy", userDB.getUserInfoByUser(amy).getName());
    }
}
