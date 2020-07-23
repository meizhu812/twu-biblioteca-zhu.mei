package com.twu.biblioteca.auth;

public class LoginInput {
    private final String username;
    private final String password;

    public LoginInput(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
