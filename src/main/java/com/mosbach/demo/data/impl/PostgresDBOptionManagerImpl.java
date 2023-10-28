package com.mosbach.demo.data.impl;

import com.mosbach.demo.data.api.OptionsManager;
import org.apache.commons.dbcp.BasicDataSource;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

public class PostgresDBOptionManagerImpl implements OptionsManager {

    String databaseURL = "jdbc:postgresql://ec2-3-214-103-146.compute-1.amazonaws.com/ddba3pgnqq5msa";
    String username = "uiefynxlnqznhz";
    String password = "ba3c282752e67e5d6e0ef420e072f58f6c3c10ec5b179ff195d940efe66e8d1a";
    BasicDataSource basicDataSource;

    // dass die bytes randomized werden (stack overflow)
    private static SecureRandom random = new SecureRandom();
    private static Base64.Encoder encoder = Base64.getUrlEncoder();

    static PostgresDBOptionManagerImpl postgresDBOptionManager = null;
    private PostgresDBOptionManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }
    public static PostgresDBOptionManagerImpl getPostgresDBOptionManagerImpl() {
        if (postgresDBOptionManager == null)
            postgresDBOptionManager = new PostgresDBOptionManagerImpl();
        return postgresDBOptionManager;
    }

    @Override
    public void createOptionsTable() {

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String createTable = "CREATE TABLE options (" +
                    "optionid bigint PRIMARY KEY NOT NULL, " +
                    "dateandtime timestamp NOT NULL) ";

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
