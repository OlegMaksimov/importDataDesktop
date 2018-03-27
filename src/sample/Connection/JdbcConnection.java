package sample.Connection;

import oracle.jdbc.driver.OracleConnection;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
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
        try {

            this.property.load(new FileInputStream("src/Property.properties"));
            this.url = property.getProperty("db.url");
            this.user = property.getProperty("db.user");
            this.password = property.getProperty("db.pass");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
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
