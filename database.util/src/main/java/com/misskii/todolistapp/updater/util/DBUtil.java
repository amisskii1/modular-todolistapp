package com.misskii.todolistapp.updater.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static final String DB_DRIVER_CLASS = "jdbc.driver";
    private static final String URL = "jdbc.url";
    private static final String USERNAME="jdbc.username";
    private static final String PASSWORD="jdbc.password";

    private static Connection connection = null;
    private static Properties properties = null;
    static{
        try {
            properties = new Properties();
            properties.load(new FileInputStream("C:\\Users\\anton\\Desktop\\javatodolistapp\\database.util\\src\\database.properties"));
            Class.forName(properties.getProperty(DB_DRIVER_CLASS));
            connection = DriverManager.getConnection(properties.getProperty(URL),properties.getProperty(USERNAME) , properties.getProperty(PASSWORD) );
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }
}
