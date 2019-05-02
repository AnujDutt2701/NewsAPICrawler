package com.anujdutt.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    public static final String USER_NAME = "root";
    public static final String PASSWORD = "password";
    public static final String SERVER = "localhost";
    public static final String PORT_NUMBER = "3306";


    public static Connection getConnection() throws SQLException {
        System.out.println("getConnection()");
        Connection connection = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", USER_NAME);
        connectionProps.put("password", PASSWORD);

        connection = DriverManager.getConnection("jdbc:mysql://" + SERVER + ":" + PORT_NUMBER + "/", connectionProps);
        System.out.println("Connected to database");
        return connection;
    }
}
