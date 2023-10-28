package com.mosbach.demo.data.impl;
import com.mosbach.demo.data.api.User;
import com.mosbach.demo.data.api.UserManager;
import com.mosbach.demo.model.auth.SendBackToken;
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
public class PostgresDBUserManagerImpl implements UserManager {

    String databaseURL = "jdbc:postgresql://ec2-3-214-103-146.compute-1.amazonaws.com/ddba3pgnqq5msa";
    String username = "uiefynxlnqznhz";
    String password = "ba3c282752e67e5d6e0ef420e072f58f6c3c10ec5b179ff195d940efe66e8d1a";
    BasicDataSource basicDataSource;


    // dass die bytes randomized werden (stack overflow)
    private static SecureRandom random = new SecureRandom();
    private static Base64.Encoder encoder = Base64.getUrlEncoder();


    // Singleton
    static PostgresDBUserManagerImpl postgresDBUserManager = null;
    private PostgresDBUserManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }
    public static PostgresDBUserManagerImpl getPostgresDBUserManagerImpl() {
        if (postgresDBUserManager == null)
            postgresDBUserManager = new PostgresDBUserManagerImpl();
        return postgresDBUserManager;
    }


    // Be carefull: It deletes data if table already exists.
    // String dropTable = "DROP TABLE tasks";
    // stmt.executeUpdate(dropTable);

    public List<User>readAllUsers() {

        final Logger readUserLogger = Logger.getLogger("ReadUserLogger");
        readUserLogger.log(Level.INFO,"Start reading ");

        List<User> users = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                users.add(
                        new UserImpl(
                                rs.getString("userid"),
                                rs.getString("firstname"),
                                rs.getString("lastname"),
                                rs.getString("password"),
                                rs.getString("email"),
                                rs.getString("token"),
                                rs.getLong("validuntil")
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
                users;
    }

    public void createUserTable() {
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String createTable = "CREATE TABLE users (" +
                    "userid varchar(100) PRIMARY KEY NOT NULL, " +
                    "firstname varchar(255) NOT NULL," +
                    "lastname varchar(255) NOT NULL," +
                    "password varchar(255) NOT NULL," +
                    "email varchar(255) NOT NULL," +
                    "token varchar(255) NOT NULL," +
                    "validuntil bigint NOT NULL)";

            //String droptable = "drop table users";
            //stmt.executeUpdate(droptable);

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
    public User createUser(String userID, String firstName, String lastName, String password, String email, String token, int validUntil) {
        final Logger createUserLogger = Logger.getLogger("CreateUserLogger");
        createUserLogger.log(Level.INFO,"Start creating " + email);
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into users (userID, firstname, lastname, password, email, token, validUntil) VALUES (" +
                    "'" + userID +"', " +
                    "'" + firstName + "', " +
                    "'" + lastName + "', " +
                    "'" + password + "', " +
                    "'" + email + "', " +
                    "'" + token + "', " +
                    validUntil +")";
            Logger.getLogger("DbUSerManager").log(Level.INFO,udapteSQL);

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

    @Override
    public SendBackToken logUserIn(String email, String password) {

        final Logger loginUserLogger = Logger.getLogger("LoginUserLogger");
        loginUserLogger.log(Level.INFO,"Start logging in " + email);

        Statement stmt = null;
        Connection connection = null;
        List<User> user = readAllUsers();
        User testuser = null;

        for (User u : user){
            if (u.getEmail().equals(email)){
                testuser = u;
            }
        }
        if (!testuser.getPassword().equals(password)) return null;
        //token generation
        long validUntil = (System.currentTimeMillis()+(1800*1000));
        byte[] tokenbyte = new byte[16];
        random.nextBytes(tokenbyte);
        String token = encoder.encodeToString(tokenbyte);

        //SQL Statement for update database
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "UPDATE users SET token = " +
                    "'" +token +"', " +
                    "validuntil= " + validUntil +
                    " WHERE userid = '" + testuser.getUserID() +"'";
            Logger.getLogger("DbUSerManager").log(Level.INFO,udapteSQL);

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
       return new SendBackToken(token, validUntil);
    }

    @Override
    public boolean logUserOff(String token) {

        final Logger loginOffLogger = Logger.getLogger("LogoffUserLogger");
        loginOffLogger.log(Level.INFO,"Start logging off ");

        List<User> userlist = readAllUsers();

        User testuser = null;

        for (User u : userlist){
            if (u.getToken().equals(token)){
                testuser = u;
            }
        }

        if (testuser == null) return false;

        //SQL statement
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String udapteSQL = "UPDATE users SET token = " +
                    "' logged off ', " +
                    "validuntil= " + 0 +
                    " WHERE userid = '" + testuser.getUserID() +"'";

            Logger.getLogger("DbUSerManager").log(Level.INFO,udapteSQL);

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


        return true;
    }

    // ?????
    @Override
    public String getEmailForToken(String token) {
        // SQL WHERE
        return "not-found";
    }
}
