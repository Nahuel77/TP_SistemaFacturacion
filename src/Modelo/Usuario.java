package Modelo;

public class Usuario {
    private String userName;
    private int id;
    private String empleado;

    public Usuario(String userName, int id, String empleado){
        this.userName = userName;
        this.id = id;
        this.empleado = empleado;
    }

    public String getUsername(){
        return userName;
    }

    public String getEmpleado(){
        return empleado;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
}