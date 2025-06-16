package GUI;

import Modelo.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;

public class Facturador extends JFrame {
    private JPanel Facturador;
    private JLabel Bienvenida;
    private JButton Inicio_btn;
    private JButton Empleados_btn;
    private JButton Proveedores_btn;
    private JButton Stock_btn;
    private JButton Facturacion_btn;
    private JButton Clientes_btn;
    private JPanel Contenido;
    private JPanel Inicio_card;
    private JPanel Empleados_card;
    private JPanel Proveedores_card;
    private JPanel Stock_card;
    private JPanel Facturacion_card;
    private JPanel Clientes_card;
    private JLabel Empleados_lb;
    private String userName;

    public Facturador(Usuario user) {

        this.userName = user.getEmpleado();
        setContentPane(Facturador);
        setTitle("Facturador");
        setSize(800, 600);
        setLocationRelativeTo(null);
        CardLayout cardLayout = (CardLayout) Contenido.getLayout();
        Bienvenida.setText("Bienvenido usuario " + userName);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Inicio_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(Contenido, "Inicio_card");
            }
        });
        Empleados_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(Contenido, "Empleados_card");
            }
        });
        Proveedores_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(Contenido, "Proveedores_card");
            }
        });
        Stock_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(Contenido, "Stock_card");
            }
        });
        Facturacion_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(Contenido, "Facturacion_card");
            }
        });
        Clientes_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(Contenido, "Clientes_card");
            }
        });
    }
}
