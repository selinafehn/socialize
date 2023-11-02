package com.mosbach.demo.data.impl;
import com.mosbach.demo.data.api.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mosbach.demo.data.impl.UserSession.sessionuser;

public class PostgresDBMeetupManagerImpl implements MeetupManager {

    String databaseURL = "jdbc:postgresql://ec2-3-214-103-146.compute-1.amazonaws.com/ddba3pgnqq5msa";
    String username = "uiefynxlnqznhz";
    String password = "ba3c282752e67e5d6e0ef420e072f58f6c3c10ec5b179ff195d940efe66e8d1a";
    BasicDataSource basicDataSource;

    UserManager userManager = PostgresDBUserManagerImpl.getPostgresDBUserManagerImpl();

    // dass die bytes randomized werden (stack overflow)
    private static SecureRandom random = new SecureRandom();
    private static Base64.Encoder encoder = Base64.getUrlEncoder();

    // Singleton
    static PostgresDBMeetupManagerImpl postgresDBMeetupManager = null;
    private PostgresDBMeetupManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }
    public static PostgresDBMeetupManagerImpl getPostgresDBUserManagerImpl() {
        if (postgresDBMeetupManager == null)
            postgresDBMeetupManager = new PostgresDBMeetupManagerImpl();
        return postgresDBMeetupManager;
    }


    public List<Meetup>readAllMeetup() {
        final Logger readMeetupLogger = Logger.getLogger("ReadMeetupLogger");
        readMeetupLogger.log(Level.INFO,"Start reading ");
        List<Meetup> meetups = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM meetup");
            while (rs.next()) {
                meetups.add(
                        new MeetupImpl(
                                rs.getString("meetupid"),
                                rs.getString("title"),
                                rs.getString("friends"),
                                rs.getString("option"),
                                rs.getString("location"),
                                rs.getLong("validuntil"),
                                rs.getString("description")
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
                meetups;
    }

    public List<Meetup>readMyMeetups(String token) {
        User user = userManager.getUserbyToken(token);
        final Logger readMeetupLogger = Logger.getLogger("ReadMyMeetupLogger");
        readMeetupLogger.log(Level.INFO,"Start reading ");
        List<Meetup> meetups = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM attendees a, meetup m WHERE a.meetupid = m.meetupid AND a.userid = '" +user.getUserID() +"'" );
            while (rs.next()) {
                meetups.add(
                        new MeetupImpl(
                                rs.getString("meetupid"),
                                rs.getString("title"),
                                rs.getString("friends"),
                                rs.getString("option"),
                                rs.getString("location"),
                                rs.getLong("validuntil"),
                                rs.getString("description")
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
                meetups;
    }

    public void createMeetupTable() {
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();


            String createTable = "CREATE TABLE meetup (" +
                    "meetupid varchar(100) PRIMARY KEY NOT NULL, " +
                    "title varchar(255) NOT NULL, " +
                    "friends varchar(255) NOT NULL, " +
                    "option varchar(255), " +
                    "location varchar(255), " +
                    "validuntil bigint NOT NULL, " +
                    "description varchar(255) NOT NULL) ";

            String droptable = "drop table meetups";
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
    public Meetup createMeetup(String meetupID, String title, String friends,  String option, String location, long validUntil, String description, List<String> attendees) {
        final Logger createMeetupLogger = Logger.getLogger("CreateMeetupLogger");
        createMeetupLogger.log(Level.INFO,"Start creating ");
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into meetup (meetupID, friends, description, title, option, location, validUntil) VALUES (" +
                    "'" + meetupID +"', " +
                    "'" + friends +"', "+
                    "'" + description + "', " +
                    "'" + title + "', " +
                    "'" + option + "', " +
                    "'" + location + "', " +
                    validUntil +")";
            Logger.getLogger("DbMeetupManager").log(Level.INFO,udapteSQL);
            stmt.executeUpdate(udapteSQL);

            /*
            String holen
            zerlegen in liste an mails
            for each mit liste holen user aus db anhand emailadresse
            getuser from DB by token/ email
            übergeben in interface
             */

            int i = 0;

            for(String s: attendees){
                boolean host = false;
                String userid = userManager.getUserbyEmail(s).getUserID();
                String relid = userid + meetupID;
                if( i == 0){
                    host = true;
                }
                i++;
                String updateSQL = "INSERT into attendees (relid, userid, meetupid, host) VALUES (" +
                        "'" + relid +"', " +
                        "'" + userid +"', "+
                        "'" + meetupID + "', " +
                        host +")";
                stmt.executeUpdate(updateSQL);
            }

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



        // TODO attendee update

        return null;
    }





}