package com.mosbach.demo.data.impl;
import com.mosbach.demo.data.api.LocationManager;


import org.apache.commons.dbcp.BasicDataSource;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

public class PostgresDBLocationManagerImpl implements LocationManager {

    String databaseURL = "jdbc:postgresql://ec2-52-45-200-167.compute-1.amazonaws.com:5432/dek9s2en5qfdl1";
    String username = "rkmyfwyjvgqzgt";
    String password = "b29b9c1650eea403f4f9ea9fa2a19f21a86fa352d84d6e228932c09ed3a2f620";
    BasicDataSource basicDataSource;

    // dass die bytes randomized werden (stack overflow)
    private static SecureRandom random = new SecureRandom();
    private static Base64.Encoder encoder = Base64.getUrlEncoder();

    static PostgresDBLocationManagerImpl postgresDBLocationManager = null;
    private PostgresDBLocationManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }
    public static PostgresDBLocationManagerImpl getPostgresDBLocationManagerImpl() {
        if (postgresDBLocationManager == null)
            postgresDBLocationManager = new PostgresDBLocationManagerImpl();
        return postgresDBLocationManager;
    }

    @Override
    public void createLocationTable() {

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String createTable = "CREATE TABLE locations (" +
                    "locationid bigint PRIMARY KEY NOT NULL, " +
                    "place varchar(255), " +
                    "specification varchar(255) NOT NULL) ";

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
