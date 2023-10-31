package com.mosbach.demo.data.impl;
import com.mosbach.demo.data.api.Meetup;
import com.mosbach.demo.data.api.MeetupManager;
import com.mosbach.demo.data.api.User;
import org.apache.commons.dbcp.BasicDataSource;
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

    public List<Meetup>readMyMeetups() {
        final Logger readMeetupLogger = Logger.getLogger("ReadMeetupLogger");
        readMeetupLogger.log(Level.INFO,"Start reading ");

        List<Meetup> meetups = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM meetup WHERE userid = " +UserSession.sessionuser.getUserID() );
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

            String dropTable = "drop table";
            stmt.executeUpdate(dropTable);

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
    public void createMeetup(String meetupID, String title, String friends,  String option, String location, long validUntil, String description) {
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



}