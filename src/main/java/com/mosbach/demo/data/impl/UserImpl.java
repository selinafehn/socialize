package com.mosbach.demo.data.impl;

import com.mosbach.demo.data.api.User;

import java.util.UUID;

public class UserImpl implements User {
    private String ID;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String token;
    private int validUntil;

    public UserImpl(String ID, String firstname, String lastname, String password, String email, String token, int validUntil) {
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.token = token;
        this.validUntil = validUntil;
    }

    public String getUserID() {
        return ID;
    }

    public void setUserID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getToken() {
        return token;
    }


    public void setToken(String token) {
        this.token = token;
    }

    public int getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(int validUntil) {
        this.validUntil = validUntil;
    }
}
