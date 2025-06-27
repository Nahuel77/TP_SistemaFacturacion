package Persistencia;

import Modelo.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {
    public List<Stock> cargarStock(){
        List<Stock> stocks = new ArrayList<>();
        String sql = "SELECT idstock, nombre, proveedor, precio, cantidad, estados FROM stock WHERE alta = 1";
        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Stock stk = new Stock(
                        rs.getInt("idstock"),
                        rs.getString("nombre"),
                        rs.getString("proveedor"),
                        rs.getFloat("precio"),
                        rs.getInt("cantidad"),
                        rs.getString("estados")
                );
                stocks.add(stk);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stocks;
    }

    public void altaStock(String nombre, String proveedor, String precio, String cantidad, String estados){
        String sql = "INSERT INTO stock (nombre, proveedor, precio, cantidad, estados, alta) VALUES (?, ?, ?, ?, ?, ?)";
        float PrecioFloat = Float.parseFloat(precio);
        int CantidadInt = Integer.parseInt(cantidad);
        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, proveedor);
            ps.setFloat(3, PrecioFloat);
            ps.setInt(4, CantidadInt);
            ps.setString(5, estados);
            ps.setInt(6, 1);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStock(String id, String nombre, String proveedor, String precio, String cantidad, String estados){
        String sql = "UPDATE stock SET nombre = ?, proveedor = ?, precio = ?, cantidad = ?, estados = ? WHERE idstock = ?";
        float PrecioFloat = Float.parseFloat(precio);
        int CantidadInt = Integer.parseInt(cantidad);
        int StockId = Integer.parseInt(id);
        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, proveedor);
            ps.setFloat(3, PrecioFloat);
            ps.setInt(4, CantidadInt);
            ps.setString(5, estados);
            ps.setInt(6, StockId);
            int estado = ps.executeUpdate();
            if(estado > 0){
                System.out.println("Actualizacion de stock exitosa");
            }else{
                System.out.println("Falla al actualizar el stock");
            }
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void bajaStock(String id){
        String sql = "UPDATE stock SET alta = 0 WHERE idstock = ?";
        int StockId = Integer.parseInt(id);
        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, StockId);
            int estado = ps.executeUpdate();
            if(estado > 0){
                System.out.println("Baja de stock exitosa");
            }else{
                System.out.println("Falla al dar baja al stock");
            }
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
