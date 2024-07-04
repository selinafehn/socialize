package com.mosbach.demo.data.impl;

import com.mosbach.demo.data.api.Options;
import com.mosbach.demo.data.api.OptionsManager;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.sql.*;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresDBOptionManagerImpl implements OptionsManager {

    BasicDataSource basicDataSource;

    // dass die bytes randomized werden (stack overflow)
    private static SecureRandom random = new SecureRandom();
    private static Base64.Encoder encoder = Base64.getUrlEncoder();
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PostgresDBUserManagerImpl.class);

    static PostgresDBOptionManagerImpl postgresDBOptionManager = null;

    private PostgresDBOptionManagerImpl() {
        basicDataSource = PostgresDBConnectionHolder.getBasicDataSource();
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
                    "optionid varchar PRIMARY KEY NOT NULL, " +
                    "optionserial serial NOT NULL, " +
                    "meetupid varchar NOT NULL, " +
                    "dateandtime timestamp NOT NULL) ";

            String dropTable = "drop table IF EXISTS options";
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
    public Options createOptions(String optionid, String optionserial, String meetupid, Timestamp dateandtime) {
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) AS recordCount FROM options WHERE meetupid = " + "'" + meetupid + "'");
            rs1.next();
            int count = rs1.getInt("recordCount") + 1;

            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into options (optionid, optionserial, meetupid, dateandtime) VALUES (" +
                    "'" + optionid + "', " +
                    "'" + count + "', " +
                    "'" + meetupid + "', " + "'" +
                    dateandtime + "'" + ")";
            Logger.getLogger("DbUSerManager").log(Level.INFO, udapteSQL);
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


}
