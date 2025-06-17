package Modelo;

public class Stock {
    private String nombre;
    private String proovedor;
    private float precio;
    private int cantidad;
    private String estados;

    public Stock(String nombre, String proovedor, float precio, int cantidad, String estados){
        this.nombre = nombre;
        this.proovedor = proovedor;
        this.precio = precio;
        this.cantidad = cantidad;
        this.estados = estados;
    }

    public String getNombre(){return nombre;}

    public String getProovedor(){ return proovedor; }

    public float getPrecio(){ return precio; }

    public int getCantidad(){ return cantidad; }

    public String getEstados(){ return estados; }
}
