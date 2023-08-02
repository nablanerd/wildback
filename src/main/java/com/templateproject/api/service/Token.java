package com.templateproject.api.service;

public class Token {

    private Integer userID;
    private String token;

    public Token() {}

    public Token(Integer userID, String token) {
        this.userID = userID;
        this.token = token;
    }


    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
