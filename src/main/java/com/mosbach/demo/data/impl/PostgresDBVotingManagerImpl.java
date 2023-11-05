package com.mosbach.demo.data.impl;

import com.mosbach.demo.data.api.Attendees;
import com.mosbach.demo.data.api.User;
import com.mosbach.demo.data.api.Voting;
import com.mosbach.demo.data.api.VotingManager;
import org.apache.commons.dbcp.BasicDataSource;

import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
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
                    "opt7 boolean) " ;
            //String dropTable = "drop table voting";
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
                               boolean opt6, boolean opt7){
        final Logger createAttendeeLogger = Logger.getLogger("CreateVotingLogger");
        createAttendeeLogger.log(Level.INFO,"Start creating " + voteID);
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into votings (voteID, userID, meetupID, opt1, opt2, opt3, opt4, opt5, opt6, opt7) VALUES (" +
                    "'" + voteID +"', " +
                    "'" + userID + "', " +
                    "'" + meetupID + "', " +
                    "'" + opt1 + "', " +
                    "'" + opt2 + "', " +
                    "'" + opt3 + "', " +
                    "'" + opt4 + "', " +
                    "'" + opt5 + "', " +
                    "'" + opt6 + "', " +
                    opt7 +")";
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

    @Override
    public List<Voting>readAllVotings() {
        final Logger readVotingLogger = Logger.getLogger("ReadVotingLogger");
        readVotingLogger.log(Level.INFO,"Start reading ");
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
    @Override
    public List<Voting>readVotingsForOption() {
        final Logger readVotingLogger = Logger.getLogger("ReadVotingsForOptionLogger");
        readVotingLogger.log(Level.INFO,"Start reading ");
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
    */

    public int readOptFromVoting(String meetupID){
        final Logger readopt1Logger = Logger.getLogger("Read Opt1 from Votings");
        readopt1Logger.log(Level.INFO, "Start reading");
        int counting1 = 0; int counting2 = 0; int counting3 = 0; int counting4 = 0; int counting5 = 0; int counting6 = 0; int counting7 = 0;
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT COUNT(userid) FROM votings WHERE opt1 = '" +true +"AND meetupid = " +meetupID +"'" );
            counting1 = rs1.getInt(0);
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs2 = stmt.executeQuery("SELECT COUNT(userid) FROM votings WHERE opt2 = '" +true +"AND meetupid = " +meetupID +"'" );
            counting2 = rs2.getInt(0);
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs3 = stmt.executeQuery("SELECT COUNT(userid) FROM votings WHERE opt3 = '" +true +"AND meetupid = " +meetupID +"'" );
            counting3 = rs3.getInt(0);
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs4 = stmt.executeQuery("SELECT COUNT(userid) FROM votings WHERE opt4 = '" +true +"AND meetupid = " +meetupID +"'" );
            counting4 = rs4.getInt(0);
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs5 = stmt.executeQuery("SELECT COUNT(userid) FROM votings WHERE opt5 = '" +true +"AND meetupid = " +meetupID +"'" );
            counting5 = rs5.getInt(0);
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs6 = stmt.executeQuery("SELECT COUNT(userid) FROM votings WHERE opt6 = '" +true +"AND meetupid = " +meetupID +"'" );
            counting6 = rs6.getInt(0);
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs7 = stmt.executeQuery("SELECT COUNT(userid) FROM votings WHERE opt7 = '" +true +"AND meetupid = " +meetupID +"'" );
            counting7 = rs7.getInt(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int max = 0;
        int counting = 0;
        int arr[] = {counting1,counting2,counting3,counting4,counting5,counting6,counting7};
        for(int i=0; i<arr.length; i++)
        {
            if(max < arr[i])
            {
                counting = i+1;
                max = arr[i];
            }
        }
        return counting;
    }

}
