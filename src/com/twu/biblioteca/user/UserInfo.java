package com.twu.biblioteca.user;

public class UserInfo {
    private String cardNo;
    private String name;
    private String email;
    private String phoneNo;

    public UserInfo(String cardNo, String name, String email, String phoneNo) {
        this.cardNo = cardNo;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
