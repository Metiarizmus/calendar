package DBConnection;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private static final Logger log = Logger.getLogger(DBConnection.class);
    private static final PropertyInf inf = new PropertyInf();
    private static final String URL;
    private static final String LOGIN;
    private static final String PASSWORD;

    static {
        URL = inf.getConnectData().getProperty("URL");
        LOGIN = inf.getConnectData().getProperty("LOGIN");
        PASSWORD = inf.getConnectData().getProperty("PASSWORD");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            log.error("error in class.forName");
        } catch (ClassNotFoundException e) {
            log.error("class not found");
        }
    }

    private DBConnection() {

        try {
            this.connection = DriverManager.getConnection(URL,LOGIN,PASSWORD);
        } catch (SQLException throwables) {
            log.error("connection error");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new DBConnection();
                }
            } catch (SQLException throwables) {
                log.error("sqlException error");
            }
        }
        return instance;
    }


}