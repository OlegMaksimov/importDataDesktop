package sample.Connection;

import oracle.jdbc.driver.OracleConnection;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnection {
    private static JdbcConnection ourInstance = new JdbcConnection();
    private Properties property = new Properties();
    private String url, user, password;

    public static JdbcConnection getInstance() {
        return ourInstance;
    }

    private JdbcConnection() {
        Logger logger = Logger.getLogger(this.getClass());
        this.url = "jdbc:oracle:thin:@188.120.246.196:1521:nvds1";
        this.user = "VACCINATION";
        this.password = "vaccine";
    }

    private JdbcConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public OracleConnection getConnection() {
        OracleConnection connection;
        try {
            connection = (OracleConnection) DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            connection = null;
        }

        return connection;
    }
    public static Boolean testConnection(String URL, String USER, String PASS) throws SQLException {
        OracleConnection connection;
        Boolean result = Boolean.FALSE;
        Logger logger = Logger.getLogger("JDBC_CONNECTION");
        try {
            connection = (OracleConnection) DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            logger.info(e.getMessage());
            connection = null;
        }
        result = !(connection == null);
        if (result) connection.close();
        return result;
    }

    public static void destroyAndReCreate(String URL, String USER, String Pass) {
        Logger logger = Logger.getLogger("JDBC_CONNECTION");
        try {
            ourInstance.getConnection().close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        ourInstance = new JdbcConnection(URL,USER,Pass);
    }
}
