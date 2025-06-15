package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
    private static final String URL = "jdbc:mysql://localhost:3306/facturador";
    private static final String USER = "root";
    private static final String PASS = "1234";

    public static Connection getConn(){
        try{
            return DriverManager.getConnection(URL, USER, PASS);
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
