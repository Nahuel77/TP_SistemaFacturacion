package Modelo;

public class Empleado {
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private int telefono;
    private int dni;
    private int legajo;

    public Empleado(String nombre, String apellido, String email, String direccion, int telefono, int dni, int legajo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.dni = dni;
        this.legajo = legajo;
    }

    public String getNombre(){
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public int getDni() {
        return dni;
    }

    public int getLegajo() {
        return legajo;
    }
}