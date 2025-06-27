package Persistencia;

import Modelo.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmpleadoDAO {
    public List<Empleado> cargarEmpleados(){
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT idempleados, nombre, apellido, mail, direccion, telefono, dni, legajo FROM empleados WHERE alta = 1";

        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Empleado emp = new Empleado(
                        rs.getInt("idempleados"),
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

    public void altaEmpleado(String nombre, String apellido, String mail, String direccion, String telefono, String dni, String legajo){
        String sql = "INSERT INTO empleados (nombre, apellido, mail, direccion, telefono, dni, legajo, alta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int telefonoInt = Integer.parseInt(telefono);
        int DniInt = Integer.parseInt(dni);
        int LegajoInt = Integer.parseInt(legajo);
        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, mail);
            ps.setString(4, direccion);
            ps.setInt(5, telefonoInt);
            ps.setInt(6, DniInt);
            ps.setInt(7, LegajoInt);
            ps.setInt(8, 1);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void bajaEmpleado(String id){
        String sql = "UPDATE empleados SET alta = 0 WHERE idempleados = ?";
        int EmpleadoId = Integer.parseInt(id);
        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, EmpleadoId);
            int estado = ps.executeUpdate();
            if(estado > 0){
                System.out.println("Baja de empleado exitosa");
            }else{
                System.out.println("Falla al dar baja al empleado");
            }
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEmpleado(String id, String nombre, String apellido, String mail, String direccion, String telefono, String dni, String legajo){
        String sql = "UPDATE empleados SET nombre = ?, apellido = ?, mail = ?, direccion = ?, telefono = ?, dni = ?, legajo = ? WHERE idempleados = ?";
        int telefonoInt = Integer.parseInt(telefono);
        int DniInt = Integer.parseInt(dni);
        int EmpleadosId = Integer.parseInt(id);
        int LegajoInt = Integer.parseInt(legajo);
        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, mail);
            ps.setString(4, direccion);
            ps.setInt(5, telefonoInt);
            ps.setInt(6, DniInt);
            ps.setInt(7, LegajoInt);
            ps.setInt(8, EmpleadosId);
            int estado = ps.executeUpdate();
            if(estado > 0){
                System.out.println("Actualizacion de empleado exitosa");
            }else{
                System.out.println("Falla al actualizar el empleado");
            }
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
