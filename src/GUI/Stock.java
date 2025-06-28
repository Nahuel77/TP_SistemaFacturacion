package GUI;

import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class Stock extends JFrame {
    Stock(Usuario user){
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        Menu menu = new Menu(5);
        PanelStock stk = new PanelStock();

        panelPrincipal.add(menu, BorderLayout.NORTH);
        panelPrincipal.add(stk, BorderLayout.CENTER);

        setContentPane(panelPrincipal);
        stk.cargarListaStock();

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
        menu.getFacturacion_btn().addActionListener(e->{
            System.out.println("Ventas clickeado");
            Ventas ventas = new Ventas(user);
            ventas.setVisible(true);
            dispose();
        });
    }
}
