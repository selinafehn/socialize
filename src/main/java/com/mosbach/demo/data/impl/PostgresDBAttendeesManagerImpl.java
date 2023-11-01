package com.mosbach.demo.data.impl;

import com.mosbach.demo.data.api.Attendees;
import com.mosbach.demo.data.api.AttendeesManager;
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

public class PostgresDBAttendeesManagerImpl implements AttendeesManager {

    String databaseURL = "jdbc:postgresql://ec2-3-214-103-146.compute-1.amazonaws.com/ddba3pgnqq5msa";
    String username = "uiefynxlnqznhz";
    String password = "ba3c282752e67e5d6e0ef420e072f58f6c3c10ec5b179ff195d940efe66e8d1a";
    BasicDataSource basicDataSource;

    // dass die bytes randomized werden (stack overflow)
    private static SecureRandom random = new SecureRandom();
    private static Base64.Encoder encoder = Base64.getUrlEncoder();

    static PostgresDBAttendeesManagerImpl postgresDBAttendeesManagerImpl = null;
    private PostgresDBAttendeesManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }
    public static PostgresDBAttendeesManagerImpl getPostgresDBAttendeesManagerImpl() {
        if (postgresDBAttendeesManagerImpl == null)
            postgresDBAttendeesManagerImpl = new PostgresDBAttendeesManagerImpl();
        return postgresDBAttendeesManagerImpl;
    }

    @Override
    public void createAttendeesTable() {

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String createTable = "CREATE TABLE attendees (" +
                    "relid String PRIMARY KEY NOT NULL, " +
                    "userID String NOT NULL, " +
                    "meetupid String NOT NULL, " +
                    "host boolean ) ";

            String dropTable = "drop table favourites";
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
    public List<Attendees>readAllAttendees() {
            final Logger readUserLogger = Logger.getLogger("ReadAttendeeLogger");
            readUserLogger.log(Level.INFO,"Start reading ");

            List<Attendees> attendees = new ArrayList<>();
            Statement stmt = null;
            Connection connection = null;
            try {
                connection = basicDataSource.getConnection();
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM attendees");
                while (rs.next()) {
                    attendees.add(
                            new AttendeesImpl(
                                    rs.getString("relid"),
                                    rs.getString("userid"),
                                    rs.getString("meetupid"),
                                    rs.getBoolean("host")
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
                    attendees;
        }

    @Override
    public Attendees createAttendee(String relID, String userID, String meetupID, byte host) {
        final Logger createAttendeeLogger = Logger.getLogger("CreateUserLogger");
        createAttendeeLogger.log(Level.INFO,"Start creating " + relID);
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into attendees (relID, userID, meetupID, host) VALUES (" +
                    "'" + relID +"', " +
                    "'" + userID + "', " +
                    "'" + meetupID + "', " +
                    host +")";
            Logger.getLogger("DbAttendeeManager").log(Level.INFO,udapteSQL);

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
        return
                null;
    }

/**
    public List<Attendees>readAllAttendees() {
        final Logger readAttendeesLogger = Logger.getLogger("ReadAttendeesLogger");
        readAttendeesLogger.log(Level.INFO,"Start reading ");
        List<Attendees> attendees = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM attendees");
            while (rs.next()) {
                attendees.add(
                        (Attendees) new AttendeesImpl(
                                rs.getString("relid"),
                                rs.getString("userid"),
                                rs.getString("meetupid"),
                                rs.getByte("host")
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
                attendees;
    }


    @Override
    public Attendees createAttendee(String relID, String userID, String meetupID, byte host) {
        final Logger createAttendeesLogger = Logger.getLogger("CreateAttendeeLogger");
        createAttendeesLogger.log(Level.INFO,"Start creating ");
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into attendees (relID, userID, meetupID, host) VALUES (" +
                    "'" + relID +"', " +
                    "'" + userID + "', " +
                    "'" + meetupID + "', " +
                    host +")";
            Logger.getLogger("DbAttendeeManager").log(Level.INFO,udapteSQL);
            createAttendeesLogger.log(Level.INFO,"created attendee ");

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
        return
                null;
    }
*/

}
