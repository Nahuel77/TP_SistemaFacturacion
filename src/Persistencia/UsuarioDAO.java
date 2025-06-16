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
                String sql2 = "SELECT nombre FROM empleados WHERE iduser = ?";
                PreparedStatement ps2 = con.prepareStatement(sql2);
                ps2.setInt(1, id);
                ResultSet rs2 = ps2.executeQuery();
                rs2.next();
                String empleado = rs2.getString("nombre");
                System.out.println(empleado);
                return new Usuario(username, id, empleado);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}