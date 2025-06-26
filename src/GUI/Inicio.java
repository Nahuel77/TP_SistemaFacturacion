package GUI;

import Modelo.Usuario;
import javax.swing.*;
import java.awt.*;

public class Inicio extends JFrame {
    private String userName;

    public Inicio(Usuario user) {
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        Menu menu = new Menu(1);
        this.userName = user.getEmpleado();
        JLabel saludoLabel = new JLabel("Bienveni@ " + userName, JLabel.CENTER);
        saludoLabel.setFont(new Font("Arial", Font.BOLD, 24));

        panelPrincipal.add(menu, BorderLayout.NORTH);
        panelPrincipal.add(saludoLabel, BorderLayout.CENTER);

        setContentPane(panelPrincipal);

        setVisible(true);

        menu.getClientes_btn().addActionListener(e->{
            System.out.println("Clientes clickeado");
            Clientes clientes = new Clientes(user);
            clientes.setVisible(true);
            dispose();
        });
        menu.getEmpleados_btn().addActionListener(e->{
            System.out.println("Empleados clickeado");
            Empleados empleados = new Empleados(user);
            empleados.setVisible(true);
            dispose();
        });
        menu.getProveedores_btn().addActionListener(e->{
            System.out.println("Proveedores clickead");
            Proveedores proveedores = new Proveedores(user);
            proveedores.setVisible(true);
            dispose();
        });
        menu.getStock_btn().addActionListener(e->{
            System.out.println("Stock clickeado");
            Stock stock = new Stock(user);
            stock.setVisible(true);
            dispose();
        });
        menu.getFacturacion_btn().addActionListener(e->{
            System.out.println("Facturacion clickeado");
            Facturacion facturacion = new Facturacion(user);
            facturacion.setVisible(true);
            dispose();
        });
    }
}
