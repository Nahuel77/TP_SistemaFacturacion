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
        String sql = "SELECT idclientes, nombre, apellido, mail, direccion, telefono, dni FROM clientes WHERE alta = 1";

        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cliente cli = new Cliente(
                        rs.getInt("idclientes"),
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
        String sql = "INSERT INTO clientes (nombre, apellido, mail, direccion, telefono, dni, alta) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
            ps.setInt(7, 1);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCliente(String id, String nombre, String apellido, String mail, String direccion, String telefono, String dni){
        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, mail = ?, direccion = ?, telefono = ?, dni = ? WHERE idclientes = ?";
        int telefonoInt = Integer.parseInt(telefono);
        int DniInt = Integer.parseInt(dni);
        int ClienteId = Integer.parseInt(id);
        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, mail);
            ps.setString(4, direccion);
            ps.setInt(5, telefonoInt);
            ps.setInt(6, DniInt);
            ps.setInt(7, ClienteId);
            int estado = ps.executeUpdate();
            if(estado > 0){
                System.out.println("Actualizacion de cliente exitosa");
            }else{
                System.out.println("Falla al actualizar el cliente");
            }
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void bajaCliente(String id){
        String sql = "UPDATE clientes SET alta = 0 WHERE idclientes = ?";
        int ClienteId = Integer.parseInt(id);
        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ClienteId);
            int estado = ps.executeUpdate();
            if(estado > 0){
                System.out.println("Baja de cliente exitosa");
            }else{
                System.out.println("Falla al dar baja al cliente");
            }
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
