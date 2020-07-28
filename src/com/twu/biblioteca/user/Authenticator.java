package com.twu.biblioteca.user;

public interface Authenticator {
    User authenticate(LoginInput loginInput) throws InvalidCredential;
}
