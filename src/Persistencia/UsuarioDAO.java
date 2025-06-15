package Persistencia;

import Modelo.Usuario;
import java.sql.*;

public class UsuarioDAO {
    public Usuario validarUsuario(String username, String pass){
        try(Connection con = Conn.getConn()){
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps =con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                int id = rs.getInt("idusers");
                //todo: agregar devolucion de nombre de empleado para saludo
                return new Usuario(username, id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}