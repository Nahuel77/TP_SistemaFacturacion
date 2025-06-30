import GUI.Login;

//login de prueba acc: test pass: 1234

public class Main {
    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
    }
}

//TODO: agregar validaciones a los inputs. Que solo se pueda agregar numeros en los campos numericos
//TODO: solucionar entrada de flotantes con coma o punto. (no funcionan con coma). Tampoco guardan los decimales correctamente.
//TODO: bloquear el ingreso de numeros negativos donde corresponda
// Agregar boton para calcular precio final con descuento final. Agregar boron de quitar elemento de listas
//Si alcanza el tiempo, modularizar ventas