package Persistencia;

import java.sql.*;

public class NewUser {
    public static boolean CreateNewUser(String nombre, String apellido, String mail, String direccion, int telefono, int dni, int legajo, String username, String password){
        String sqlUser = "INSERT INTO users (username, password) VALUES (?, ?)";
        String sqlEmpl = "INSERT INTO empleados (nombre, apellido, mail, direccion, telefono, dni, legajo, iduser) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = Conn.getConn()){
            con.setAutoCommit(false);
            try(PreparedStatement psUser = con.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS)){
                psUser.setString(1, username);
                psUser.setString(2, password);
                psUser.executeUpdate();
                ResultSet rs = psUser.getGeneratedKeys();
                if(rs.next()){
                    int idUser = rs.getInt(1);
                    try(PreparedStatement psEmpl = con.prepareStatement(sqlEmpl)){
                        psEmpl.setString(1, nombre);
                        psEmpl.setString(2, apellido);
                        psEmpl.setString(3, mail);
                        psEmpl.setString(4, direccion);
                        psEmpl.setInt(5, telefono);
                        psEmpl.setInt(6, dni);
                        psEmpl.setInt(7, legajo);
                        psEmpl.setInt(8, idUser);

                        psEmpl.executeUpdate();
                        con.commit();
                        System.out.println("registro exitoso");
                        return true;
                    }
                }else{
                    con.rollback();
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
