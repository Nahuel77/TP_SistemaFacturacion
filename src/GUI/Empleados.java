package GUI;

import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class Empleados extends JFrame {
    Empleados(Usuario user){
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        Menu menu = new Menu(3);
        PanelEmpleados emp = new PanelEmpleados();

        panelPrincipal.add(menu, BorderLayout.NORTH);
        panelPrincipal.add(emp, BorderLayout.CENTER);

        setContentPane(panelPrincipal);
        emp.cargarListaEmpleados();

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
            System.out.println("Ventas clickeado");
            Ventas ventas = new Ventas(user);
            ventas.setVisible(true);
            dispose();
        });
    }
}
