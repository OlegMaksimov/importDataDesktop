package sample.Connection;

import oracle.jdbc.driver.OracleConnection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    private static JdbcConnection ourInstance = new JdbcConnection();
    private String url = "jdbc:oracle:thin:@188.120.246.196:1521:nvds1", user = "VACCINATION", password = "vaccine";

    public static JdbcConnection getInstance() {
        return ourInstance;
    }

    private JdbcConnection() {

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
}
