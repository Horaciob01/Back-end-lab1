package back.end.lab1.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Horacio
 */
public class OracleConnection {

    private static OracleConnection OC = null;
    private Connection connection;
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "sys as sysdba";
    private static final String PASSWORD = "root";

    private OracleConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OracleConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static OracleConnection getInstance(){
        if(OC == null)
            OC = new OracleConnection();
        return OC;
    }
    
    public String test() {
        String test = "";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from producto");
            while (rs.next()) {
                test += rs.getString("codigo");
            }
            connection.close(); 
        } catch (SQLException ex) {
            Logger.getLogger(OracleConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
    }

}
