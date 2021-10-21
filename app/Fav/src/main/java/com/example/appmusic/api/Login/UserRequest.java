package com.example.appmusic.api.Login;

import java.io.Serializable;

public class UserRequest implements Serializable {
    private String account;
    private String password;

    public UserRequest() {
    }

    public UserRequest(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
