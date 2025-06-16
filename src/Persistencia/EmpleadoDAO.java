package Persistencia;

import Modelo.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmpleadoDAO {
    public List<Empleado> cargarEmpleados(){
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT nombre, apellido, mail, direccion, telefono, dni, legajo FROM empleados";

        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Empleado emp = new Empleado(
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("mail"),
                    rs.getString("direccion"),
                    rs.getInt("telefono"),
                    rs.getInt("dni"),
                    rs.getInt("legajo")
                );
                empleados.add(emp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return empleados;
    }
}
