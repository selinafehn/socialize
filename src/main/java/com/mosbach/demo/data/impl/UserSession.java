package com.mosbach.demo.data.impl;

import com.mosbach.demo.model.auth.User;

public class UserSession {

    static User sessionuser;

    public static User getSessionuser() {
        return sessionuser;
    }

    public static void setSessionuser(User sessionuser) {
        UserSession.sessionuser = sessionuser;
    }
}

