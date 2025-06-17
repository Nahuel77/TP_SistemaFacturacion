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
        String sql = "SELECT nombre, proveedor, precio, cantidad, estados FROM stock";
        try(Connection con = Conn.getConn()){
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Stock stk = new Stock(
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
}
