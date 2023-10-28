package com.mosbach.demo.data.impl;

import com.mosbach.demo.data.api.AttendeesManager;
import org.apache.commons.dbcp.BasicDataSource;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

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

            String createTable = "CREATE TABLE options (" +
                    "relid bigint PRIMARY KEY NOT NULL, " +
                    "userID bigint NOT NULL, " +
                    "meetupid bigint NOT NULL, " +
                    "host byte ) ";

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
}
