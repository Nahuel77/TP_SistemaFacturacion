package Persistencia;

import Modelo.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    public List<Proveedor> cargarProveedores(){
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT idproveedores, nombre, apellido, mail, direccion, telefono, dni FROM proveedores WHERE alta = 1";

        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Proveedor pro = new Proveedor(
                        rs.getInt("idproveedores"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("mail"),
                        rs.getString("direccion"),
                        rs.getInt("telefono"),
                        rs.getInt("dni")
                );
                proveedores.add(pro);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return proveedores;
    }

    public void altaProveedor(String nombre, String apellido, String mail, String direccion, String telefono, String dni){
        String sql = "INSERT INTO proveedores (nombre, apellido, mail, direccion, telefono, dni, alta) VALUES (?, ?, ?, ?, ?, ?, ?)";
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

    public void updateProveedor(String id, String nombre, String apellido, String mail, String direccion, String telefono, String dni){
        String sql = "UPDATE proveedores SET nombre = ?, apellido = ?, mail = ?, direccion = ?, telefono = ?, dni = ? WHERE idproveedores = ?";
        int telefonoInt = Integer.parseInt(telefono);
        int DniInt = Integer.parseInt(dni);
        int ProveedorId = Integer.parseInt(id);
        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, mail);
            ps.setString(4, direccion);
            ps.setInt(5, telefonoInt);
            ps.setInt(6, DniInt);
            ps.setInt(7, ProveedorId);
            int estado = ps.executeUpdate();
            if(estado > 0){
                System.out.println("Actualizacion de proveedor exitosa");
            }else{
                System.out.println("Falla al actualizar el proveedor");
            }
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void bajaProveedor(String id){
        String sql = "UPDATE proveedores SET alta = 0 WHERE idproveedores = ?";
        int ProveedorId = Integer.parseInt(id);
        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ProveedorId);
            int estado = ps.executeUpdate();
            if(estado > 0){
                System.out.println("Baja de proveedor exitosa");
            }else{
                System.out.println("Falla al dar baja al proveedor");
            }
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
