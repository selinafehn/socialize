package com.mosbach.demo.data.impl;

import com.mosbach.demo.data.api.Voting;
import com.mosbach.demo.data.api.VotingManager;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresDBVotingManagerImpl implements VotingManager {

    BasicDataSource basicDataSource;

    // dass die bytes randomized werden (stack overflow)
    private static SecureRandom random = new SecureRandom();
    private static Base64.Encoder encoder = Base64.getUrlEncoder();
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PostgresDBUserManagerImpl.class);

    static PostgresDBVotingManagerImpl postgresDBVotingManagerImpl = null;

    private PostgresDBVotingManagerImpl() {
        basicDataSource = PostgresDBConnectionHolder.getBasicDataSource();
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
            String createTable = "CREATE TABLE votings (" +
                    "voteID varchar PRIMARY KEY NOT NULL, " +
                    "userID varchar NOT NULL, " +
                    "meetupID varchar NOT NULL, " +
                    "opt1 boolean, " +
                    "opt2 boolean, " +
                    "opt3 boolean, " +
                    "opt4 boolean, " +
                    "opt5 boolean, " +
                    "opt6 boolean, " +
                    "opt7 boolean) ";
            String dropTable = "drop table IF EXISTS votings";
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
    public Voting createVoting(String voteID, String userID, String meetupID, boolean opt1,
                               boolean opt2, boolean opt3, boolean opt4, boolean opt5,
                               boolean opt6, boolean opt7) {
        final Logger createAttendeeLogger = Logger.getLogger("CreateVotingLogger");
        createAttendeeLogger.log(Level.INFO, "Start creating " + voteID);
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into votings (voteID, userID, meetupID, opt1, opt2, opt3, opt4, opt5, opt6, opt7) VALUES (" +
                    "'" + voteID + "', " +
                    "'" + userID + "', " +
                    "'" + meetupID + "', " +
                    "'" + opt1 + "', " +
                    "'" + opt2 + "', " +
                    "'" + opt3 + "', " +
                    "'" + opt4 + "', " +
                    "'" + opt5 + "', " +
                    "'" + opt6 + "', " +
                    opt7 + ")";
            Logger.getLogger("DbVotingManager").log(Level.INFO, udapteSQL);
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

    @Override
    public List<Voting> readAllVotings() {
        final Logger readVotingLogger = Logger.getLogger("ReadVotingLogger");
        readVotingLogger.log(Level.INFO, "Start reading ");
        List<Voting> votings = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM votings");
            while (rs.next()) {
                votings.add(
                        new VotingImpl(
                                rs.getString("voteid"),
                                rs.getString("userid"),
                                rs.getString("meetupid"),
                                rs.getBoolean("opt1"),
                                rs.getBoolean("opt2"),
                                rs.getBoolean("opt3"),
                                rs.getBoolean("opt4"),
                                rs.getBoolean("opt5"),
                                rs.getBoolean("opt6"),
                                rs.getBoolean("opt7")
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
                votings;
    }

    /**
     * @Override public List<Voting>readVotingsForOption() {
     * final Logger readVotingLogger = Logger.getLogger("ReadVotingsForOptionLogger");
     * readVotingLogger.log(Level.INFO,"Start reading ");
     * List<Voting> votings = new ArrayList<>();
     * Statement stmt = null;
     * Connection connection = null;
     * try {
     * connection = basicDataSource.getConnection();
     * stmt = connection.createStatement();
     * ResultSet rs = stmt.executeQuery("SELECT * FROM votings");
     * while (rs.next()) {
     * votings.add(
     * new VotingImpl(
     * rs.getString("voteid"),
     * rs.getString("userid"),
     * rs.getString("meetupid"),
     * rs.getBoolean("opt1"),
     * rs.getBoolean("opt2"),
     * rs.getBoolean("opt3"),
     * rs.getBoolean("opt4"),
     * rs.getBoolean("opt5"),
     * rs.getBoolean("opt6"),
     * rs.getBoolean("opt7")
     * )
     * );
     * }
     * } catch (SQLException e) {
     * e.printStackTrace();
     * }
     * try {
     * stmt.close();
     * connection.close();
     * } catch (SQLException e) {
     * e.printStackTrace();
     * }
     * return
     * votings;
     * }
     */

    public List<Voting> readVotingbyMeetupID(String meetupID) {
        final Logger readopt1Logger = Logger.getLogger("Read Opt1 from Votings");
        readopt1Logger.log(Level.INFO, "Start reading");
        Statement stmt = null;
        Connection connection = null;
        List<Voting> optionvoting = new ArrayList<>();
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM votings WHERE meetupid = '" + meetupID + "'");
            while (rs1.next()) {
                optionvoting.add(
                        new VotingImpl(
                                rs1.getString("voteid"),
                                rs1.getString("userid"),
                                rs1.getString("meetupid"),
                                rs1.getBoolean("opt1"),
                                rs1.getBoolean("opt2"),
                                rs1.getBoolean("opt3"),
                                rs1.getBoolean("opt4"),
                                rs1.getBoolean("opt5"),
                                rs1.getBoolean("opt6"),
                                rs1.getBoolean("opt7")
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
        return optionvoting;
    }

}

