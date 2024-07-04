package com.mosbach.demo.data.impl;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.LoggerFactory;

public class PostgresDBConnectionHolder {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PostgresDBUserManagerImpl.class);

    private static BasicDataSource basicDataSource;

    public static BasicDataSource getBasicDataSource() {
        if (basicDataSource == null) {
             initializeDataSource();
        }
        return basicDataSource;
    }

    private static void initializeDataSource() {
        basicDataSource = new BasicDataSource();
        String jdbcHost = "localhost";
        String envKey = "JDBC_HOST";
        if (System.getenv(envKey) != null) {
            jdbcHost = System.getenv(envKey);
        } else if (System.getProperty(envKey) != null) {
            jdbcHost = System.getProperty(envKey);
        }
        String databaseURL = "jdbc:postgresql://" + jdbcHost + ":5432/";
        log.info("database connection URL: " + databaseURL);
        String username = "uiefynxlnqznhz";
        String password = "ba3c282752e67e5d6e0ef420e072f58f6c3c10ec5b179ff195d940efe66e8d1a";
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }

}
