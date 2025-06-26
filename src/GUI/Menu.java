package GUI;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private JPanel Menu;
    private JButton Inicio_btn;
    private JButton Clientes_btn;
    private JButton Empleados_btn;
    private JButton Proveedores_btn;
    private JButton Stock_btn;
    private JButton Facturacion_btn;

    public Menu(int currentSection){
        setLayout(new GridLayout(1, 6));
        Inicio_btn = new JButton("Inicio");
        Clientes_btn = new JButton("Clientes");
        Empleados_btn = new JButton("Empleados");
        Proveedores_btn = new JButton("Proveedores");
        Stock_btn = new JButton("Stock");
        Facturacion_btn = new JButton("Facturación");
        switch (currentSection){
            case 1:
                add(Clientes_btn);
                add(Empleados_btn);
                add(Proveedores_btn);
                add(Stock_btn);
                add(Facturacion_btn);
                break;
            case 2:
                add(Inicio_btn);
                add(Empleados_btn);
                add(Proveedores_btn);
                add(Stock_btn);
                add(Facturacion_btn);
                break;
            case 3:
                add(Inicio_btn);
                add(Clientes_btn);
                add(Proveedores_btn);
                add(Stock_btn);
                add(Facturacion_btn);
                break;
            case 4:
                add(Inicio_btn);
                add(Clientes_btn);
                add(Empleados_btn);
                add(Stock_btn);
                add(Facturacion_btn);
                break;
            case 5:
                add(Inicio_btn);
                add(Clientes_btn);
                add(Empleados_btn);
                add(Proveedores_btn);
                add(Facturacion_btn);
                break;
            case 6:
                add(Inicio_btn);
                add(Clientes_btn);
                add(Empleados_btn);
                add(Proveedores_btn);
                add(Stock_btn);
                break;
            default:
                add(Inicio_btn);
                add(Clientes_btn);
                add(Empleados_btn);
                add(Proveedores_btn);
                add(Stock_btn);
                add(Facturacion_btn);
                break;
        }
    }
    public JButton getInicio_btn() { return Inicio_btn; }
    public JButton getClientes_btn() { return Clientes_btn; }
    public JButton getEmpleados_btn() { return Empleados_btn; }
    public JButton getProveedores_btn() { return Proveedores_btn; }
    public JButton getStock_btn() { return Stock_btn; }
    public JButton getFacturacion_btn() { return Facturacion_btn; }
}
