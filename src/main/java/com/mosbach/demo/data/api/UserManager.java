package com.mosbach.demo.data.api;

import java.util.List;

public interface UserManager {

    List<User> readAllUsers();
    User createUser(String userID, String firstName, String lastName, String password, String email, String token, int validuntil);
    void createUserTable();
    User logUserIn(String email, String password);
    User logUserOff(String email, String token);
    String getEmailForToken(String token);

}
