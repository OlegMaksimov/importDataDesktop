package test.java.com.komtek.imoprt.Connection;

import org.junit.Test;
import sample.Connection.JdbcConnection;

import static org.junit.Assert.*;

public class JdbcConnectionTest {

    @Test
    public void getInstance() {
        assertNotNull(JdbcConnection.getInstance().getConnection());
    }
}