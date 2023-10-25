package com.mosbach.demo.data.impl;

import com.mosbach.demo.data.api.Meetup;
import com.mosbach.demo.data.api.MeetupManager;
import org.apache.commons.dbcp.BasicDataSource;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mosbach.demo.data.api.Meetup;
import com.mosbach.demo.data.api.User;
import com.mosbach.demo.data.api.UserManager;
import com.mosbach.demo.model.auth.SendBackToken;
import com.mosbach.demo.model.task.Task;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PostgresDBMeetupManagerImpl implements MeetupManager {

    String databaseURL = "jdbc:postgresql://ec2-52-45-200-167.compute-1.amazonaws.com:dek9s2en5qfdl1";
    String username = "rkmyfwyjvgqzgt";
    String password = "b29b9c1650eea403f4f9ea9fa2a19f21a86fa352d84d6e228932c09ed3a2f620";
    BasicDataSource basicDataSource;


    // dass die bytes randomized werden (stack overflow)
    private static SecureRandom random = new SecureRandom();
    private static Base64.Encoder encoder = Base64.getUrlEncoder();

    // Singleton
    static PostgresDBMeetupManagerImpl postgresDBTaskManager = null;
    private PostgresDBMeetupManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }
    public static PostgresDBMeetupManagerImpl getPostgresDBUserManagerImpl() {
        if (postgresDBTaskManager == null)
            postgresDBTaskManager = new PostgresDBMeetupManagerImpl();
        return postgresDBTaskManager;
    }


    @Override
    public List<Meetup> readAllMeetups() {
        return null;
    }

    public void createMeetupTable() {
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String createTable = "CREATE TABLE meetup (" +
                    "meetupid varchar(100) PRIMARY KEY NOT NULL, " +
                    "description varchar(255) NOT NULL, "+
                    "title varchar(255) NOT NULL, "+
                    "option varchar(255) NOT NULL, "+
                    "location varchar(255)  NOT NULL, "+
                    "validuntil bigint NOT NULL) ";

            String droptable = "drop table meetup";
            stmt.executeUpdate(droptable);

            stmt.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void createMeetup(String meetupID, String description, String title, String option, String location, int validUntil) {
        final Logger createMeetupLogger = Logger.getLogger("CreateMeetupLogger");
        createMeetupLogger.log(Level.INFO,"Start creating ");
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into users (userID, firstname, lastname, password, email, token, validUntil) VALUES (" +
                    "'" + meetupID +"', " +
                    "'" + description + "', " +
                    "'" + title + "', " +
                    "'" + option + "', " +
                    "'" + location + "', " +
                    validUntil +")";
            Logger.getLogger("DbUSerManager").log(Level.INFO,udapteSQL);

            stmt.executeUpdate(udapteSQL);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /** HARTWIG CODE

    public void createTaskTable() {

        // Be carefull: It deletes data if table already exists.
        //
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            // String dropTable = "DROP TABLE tasks";
            // stmt.executeUpdate(dropTable);

            String createTable = "CREATE TABLE tasks (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name varchar(100) NOT NULL, " +
                    "priority int NOT NULL)";

            stmt.executeUpdate(createTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public List<Meetup> readAllTasks() {

        final Logger readTaskLogger = Logger.getLogger("ReadTaskLogger");
        readTaskLogger.log(Level.INFO,"Start reading tasks ");

        List<Meetup> tasks = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tasks");

            while (rs.next()) {
                tasks.add(
                        new MeetupImpl(
                                rs.getString("name"),
                                rs.getInt("priority")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return
                tasks;
    }


    @Override
    public void addTask(String name, int priority) {

        final Logger createTaskLogger = Logger.getLogger("CreateTaskLogger");
        createTaskLogger.log(Level.INFO,"Start creating task " + name);

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into tasks (name, priority) VALUES (" +
                    "'" + name + "', " +
                    priority + ")";

            stmt.executeUpdate(udapteSQL);

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

**/

}