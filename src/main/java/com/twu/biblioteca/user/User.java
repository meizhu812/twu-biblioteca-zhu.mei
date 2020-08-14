package com.twu.biblioteca.user;

public class User {
    private String cardNo;
    private String password;

    public User(String cardNo, String password) {
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
