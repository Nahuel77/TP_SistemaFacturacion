import GUI.Login;

public class Main {
    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
        System.out.println("Esto se ejecuta después de cerrar el login. Probando Modal");
    }
}