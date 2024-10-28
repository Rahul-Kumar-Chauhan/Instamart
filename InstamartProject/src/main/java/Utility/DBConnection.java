package Utility;


import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnection {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {        
        ResourceBundle rb= ResourceBundle.getBundle("oracle");
        String url = rb.getString("db.url");
        String username = rb.getString("db.username");
        String password = rb.getString("db.password");
        Connection con = null;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to Oracle database!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }        
        return con;
	}
}