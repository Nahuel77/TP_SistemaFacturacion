package Modelo;

public class Usuario {
    private String userName;
    private int id;

    public Usuario(String userName, int id){
        this.userName = userName;
        this.id = id;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
}