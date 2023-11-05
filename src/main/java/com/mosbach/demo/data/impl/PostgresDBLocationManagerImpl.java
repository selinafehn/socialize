package com.mosbach.demo.data.impl;
import com.mosbach.demo.data.api.LocationManager;
import org.apache.commons.dbcp.BasicDataSource;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

public class PostgresDBLocationManagerImpl implements LocationManager {

    String databaseURL = "jdbc:postgresql://ec2-3-214-103-146.compute-1.amazonaws.com/ddba3pgnqq5msa";
    String username = "uiefynxlnqznhz";
    String password = "ba3c282752e67e5d6e0ef420e072f58f6c3c10ec5b179ff195d940efe66e8d1a";
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

            String dropTable = "DROP TABLE locations";
            stmt.executeUpdate(dropTable);

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
