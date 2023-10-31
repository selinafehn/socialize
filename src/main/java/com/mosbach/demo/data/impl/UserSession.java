package com.mosbach.demo.data.impl;


import com.mosbach.demo.data.api.User;

public class UserSession {

    static User sessionuser;

    public UserSession(User u) {
        this.sessionuser = u;
    }

    public static User getSessionuser() {
        return sessionuser;
    }

    public static void setSessionuser(User sessionuser) {
        UserSession.sessionuser = sessionuser;
    }


}

