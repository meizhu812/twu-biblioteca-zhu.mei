package com.twu.biblioteca.auth;

public interface Authenticator {
    User authenticate(LoginInput loginInput) throws InvalidCredential;

    UserInfo getUserInfoByUser(User user);
}
