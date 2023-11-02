package com.mosbach.demo.data.impl;

import com.mosbach.demo.data.api.Attendees;
import com.mosbach.demo.data.api.Voting;
import com.mosbach.demo.data.api.VotingManager;
import org.apache.commons.dbcp.BasicDataSource;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresDBVotingManagerImpl implements VotingManager {

    String databaseURL = "jdbc:postgresql://ec2-3-214-103-146.compute-1.amazonaws.com/ddba3pgnqq5msa";
    String username = "uiefynxlnqznhz";
    String password = "ba3c282752e67e5d6e0ef420e072f58f6c3c10ec5b179ff195d940efe66e8d1a";
    BasicDataSource basicDataSource;

    // dass die bytes randomized werden (stack overflow)
    private static SecureRandom random = new SecureRandom();
    private static Base64.Encoder encoder = Base64.getUrlEncoder();

    static PostgresDBVotingManagerImpl postgresDBVotingManagerImpl = null;
    private PostgresDBVotingManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }
    public static PostgresDBVotingManagerImpl getPostgresDBVotingManagerImpl() {
        if (postgresDBVotingManagerImpl == null)
            postgresDBVotingManagerImpl = new PostgresDBVotingManagerImpl();
        return postgresDBVotingManagerImpl;
    }

    @Override
    public void createVotingTable() {

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String createTable = "CREATE TABLE voting (" +
                    "voteID bigint PRIMARY KEY NOT NULL, " +
                    "userID bigint NOT NULL, " +
                    "meetupID bigint NOT NULL, " +
                    "opt1 boolean, " +
                    "opt2 boolean, " +
                    "opt3 boolean, " +
                    "opt4 boolean, " +
                    "opt5 boolean, " +
                    "opt6 boolean, " +
                    "opt7 boolean, " +
                    "loc1 boolean, " +
                    "loc2 boolean, " +
                    "loc3 boolean, " +
                    "loc4 boolean, " +
                    "loc5 boolean, " +
                    "loc6 boolean, " +
                    "loc7 boolean ) " ;

            //String dropTable = "drop table favourites";
            //stmt.executeUpdate(dropTable);

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
    public Voting createVoting(String voteID, String userID, String meetupID, boolean opt1,
                               boolean opt2, boolean opt3, boolean opt4, boolean opt5,
                               boolean opt6, boolean opt7, boolean loc1, boolean loc2,
                               boolean loc3, boolean loc4, boolean loc5, boolean loc6, boolean loc7){
        final Logger createAttendeeLogger = Logger.getLogger("CreateVotingLogger");
        createAttendeeLogger.log(Level.INFO,"Start creating " + voteID);
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into voting (voteID, userID, meetupID, opt1, opt2, opt3, opt4, opt5, opt6, opt7,\n" +
                    "                              loc1, loc2, loc3, loc4, loc5, loc6, loc7) VALUES (" +
                    "'" + voteID +"', " +
                    "'" + userID + "', " +
                    "'" + meetupID + "', " +
                    "'" + opt1 + "', " +
                    "'" + opt2 + "', " +
                    "'" + opt3 + "', " +
                    "'" + opt4 + "', " +
                    "'" + opt5 + "', " +
                    "'" + opt6 + "', " +
                    "'" + opt7 + "', " +
                    "'" + loc1 + "', " +
                    "'" + loc2 + "', " +
                    "'" + loc3 + "', " +
                    "'" + loc4 + "', " +
                    "'" + loc5 + "', " +
                    "'" + loc6 + "', " +
                    loc7 +")";
            Logger.getLogger("DbVotingManager").log(Level.INFO,udapteSQL);
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
        return null;
    }


}
