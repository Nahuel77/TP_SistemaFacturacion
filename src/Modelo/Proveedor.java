package Modelo;

public class Proveedor {
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private int telefono;
    private int dni;
    private int id;

    public Proveedor(int id, String nombre, String apellido, String email, String direccion, int telefono, int dni){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.dni = dni;
        this.id = id;
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

    public int getId(){return id;}
}
