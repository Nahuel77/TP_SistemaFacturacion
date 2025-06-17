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

    public void altaCliente(String nombre, String apellido, String mail, String direccion, String telefono, String dni){
        String sql = "INSERT INTO clientes (nombre, apellido, mail, direccion, telefono, dni) VALUES (?, ?, ?, ?, ?, ?)";
        int telefonoInt = Integer.parseInt(telefono);
        int DniInt = Integer.parseInt(dni);
        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, mail);
            ps.setString(4, direccion);
            ps.setInt(5, telefonoInt);
            ps.setInt(6, DniInt);
            ps.executeUpdate();
            //System.out.println("Alta de cliente exitosa");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
