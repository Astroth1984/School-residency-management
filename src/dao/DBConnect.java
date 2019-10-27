package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

    private static Connection conn;

    public static Connection getConnection() throws Exception
    {
        if(conn == null)
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestioninternat","root","");
        }
        return conn;
    }

}
