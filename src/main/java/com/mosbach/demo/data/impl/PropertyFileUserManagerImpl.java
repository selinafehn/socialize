package com.mosbach.demo.data.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.mosbach.demo.data.api.User;
import com.mosbach.demo.data.api.UserManager;
import com.mosbach.demo.model.auth.SendBackToken;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PropertyFileUserManagerImpl implements UserManager{

    String userPropertyFile;

    //Singleton implementierung
    static PropertyFileUserManagerImpl propertyFileUserManager= null;
    private PropertyFileUserManagerImpl(String userPropertyFile){
        this.userPropertyFile=userPropertyFile;
    }
    public static PropertyFileUserManagerImpl getPropertyFileUserManagerImpl(String userPropertyFile){
        if(propertyFileUserManager == null)
                propertyFileUserManager = new PropertyFileUserManagerImpl((userPropertyFile));
        return propertyFileUserManager;
    }

    @Override
    public List<User> readAllUsers() {
        return null;
    }

    @Override
    public User createUser(String userID, String firstName, String lastName, String password, String email, String token, int validUntil) {
        final Logger createUserLogger = Logger.getLogger("Create User Logger");
        createUserLogger.log(Level.INFO, "Start creating" +email);

        List<User> users = readAllUsers();
        createUserLogger.log(Level.INFO, "Adding new User");
        createUserLogger.log(Level.INFO, "Start Storing all users");

        storeAllUsers(users);

        return null;
    }

    @Override
    public void createUserTable() {

    }

    @Override
    public SendBackToken logUserIn(String email, String password) {
        return null;
    }

    @Override
    public boolean logUserOff(String token) {
        return false;
    }

    @Override
    public String getEmailForToken(String token) {
        return null;
    }

    public void storeAllUsers(List<User> users){

        Properties properties = new Properties();
        final AtomicLong counter = new AtomicLong();
        counter.set(1);

        users.forEach(user -> {
            properties.setProperty("user." + counter.get() + ".firstname", user.getFirstName());
            properties.setProperty("user." + counter.get() + ".password", "" + user.getPassword());
            counter.getAndIncrement();
        });
        try{
            properties.store(new FileOutputStream(userPropertyFile), null);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

//test
}


