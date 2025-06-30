package Modelo;

public class ListaVenta {
    private String nombreProducto;
    private float precioProducto;
    private int unidades;
    private double total;
    private int descuentoProductoUnidad;
    private double totalDescuentoUnidad;

    public ListaVenta(String nombreProducto, float precioProducto, int unidades, double total, int descuentoProducto, double descuentoTotalU){
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.unidades = unidades;
        this.total = total;
        this.descuentoProductoUnidad = descuentoProducto;
        this.totalDescuentoUnidad = descuentoTotalU;
    }

    public String getNombreProducto(){return nombreProducto;}

    public float getPrecioProducto(){return precioProducto;}

    public int getUnidades(){return unidades;}

    public double getTotal(){return total;}

    public int getDescuentoProductoUnidad(){return descuentoProductoUnidad;}

    public double getTotalDescuentoUnidad(){return totalDescuentoUnidad;}

}
