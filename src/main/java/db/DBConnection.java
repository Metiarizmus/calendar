package db;

import enums.StateProperties;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final PropertyInf inf = new PropertyInf();
    private static final String URL;
    private static final String LOGIN;
    private static final String PASSWORD;

    static {
        URL = inf.getDataFromProperties(StateProperties.CONNECTION).getProperty("URL");
        LOGIN = inf.getDataFromProperties(StateProperties.CONNECTION).getProperty("LOGIN");
        PASSWORD = inf.getDataFromProperties(StateProperties.CONNECTION).getProperty("PASSWORD");
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, LOGIN, PASSWORD);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("couldn't connect!");
            throw new RuntimeException(ex);
        }

    }




}