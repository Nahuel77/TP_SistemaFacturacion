package Modelo;

public class ListaVenta {
    private String nombreProducto;
    private float precioProducto;
    private int unidades;
    private int descuentoProducto;

    ListaVenta(String nombreProducto, float precioProducto, int unidades, int descuentoProducto){
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.unidades = unidades;
        this.descuentoProducto = descuentoProducto;
    }

    public String getNombreProducto(){return nombreProducto;}

    public float getPrecioProducto(){return precioProducto;}

    public int getUnidades(){return unidades;}

    public int getDescuentoProducto(){return descuentoProducto;}

}
