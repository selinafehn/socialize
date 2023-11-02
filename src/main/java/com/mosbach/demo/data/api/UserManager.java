package com.mosbach.demo.data.api;

import com.mosbach.demo.model.auth.SendBackToken;

import java.util.List;

public interface UserManager {

    List<User> readAllUsers();
    User createUser(String userID, String firstName, String lastName, String password, String email, String token, long validUntil);
    void createUserTable();
    User getUserbyEmail(String email);
    SendBackToken logUserIn(String email, String password);
    boolean logUserOff( String token);
    // TODO mal gucken ob man die überhaupt braucht
    String getEmailForToken(String token);


}
