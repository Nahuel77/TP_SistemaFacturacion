package GUI;

import Modelo.Usuario;

import javax.swing.*;

public class Facturador extends JFrame {
    private JPanel Facturador;
    private JLabel Bienvenida;
    private JButton Inicio_btn;
    private JButton Empleados_btn;
    private JButton Proveedores_btn;
    private JButton Stock_btn;
    private JButton Facturacion_btn;
    private JButton Clientes_btn;
    private String userName;

    public Facturador(Usuario user) {
            this.userName = user.getEmpleado();
            setContentPane(Facturador);
            setTitle("Facturador");
            setLocationRelativeTo(null);
            Bienvenida.setText("Bienvenido usuario " + userName);
            setSize(800, 600);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
