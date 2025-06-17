package Persistencia;

import Modelo.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public List<Cliente> cargarClientes(){
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT nombre, apellido, mail, direccion, telefono, dni FROM clientes";

        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cliente cli = new Cliente(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("mail"),
                        rs.getString("direccion"),
                        rs.getInt("telefono"),
                        rs.getInt("dni")
                );
                clientes.add(cli);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }
}
