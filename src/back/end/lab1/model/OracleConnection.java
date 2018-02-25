package back.end.lab1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Horacio
 */
public class OracleConnection {

    protected Connection connection;
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "sys as sysdba";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

    protected void connect() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    protected void disconnect() throws SQLException {
        if (!connection.isClosed()) {
            connection.close();
        }
    }

}
