package com.twu.biblioteca.user;

public class LoginInput {
    private final String cardNo;
    private final String password;

    public LoginInput(String cardNo, String password) {
        this.cardNo = cardNo;
        this.password = password;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getPassword() {
        return password;
    }
}
