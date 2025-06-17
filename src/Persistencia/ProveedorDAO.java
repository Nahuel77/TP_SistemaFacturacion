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
        String sql = "SELECT nombre, apellido, mail, direccion, telefono, dni FROM proveedores";

        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Proveedor pro = new Proveedor(
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
}
