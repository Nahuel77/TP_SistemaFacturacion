package GUI;

import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class Ventas extends JFrame {
    Ventas(Usuario user){
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        Menu menu = new Menu(6);
        PanelVentas ven = new PanelVentas(user);

        panelPrincipal.add(menu, BorderLayout.NORTH);
        panelPrincipal.add(ven, BorderLayout.CENTER);

        setContentPane(panelPrincipal);

        setVisible(true);

        menu.getInicio_btn().addActionListener(e->{
            System.out.println("Inicio clickeado");
            Inicio inicio = new Inicio(user);
            inicio.setVisible(true);
            dispose();

        });
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
    }
}
