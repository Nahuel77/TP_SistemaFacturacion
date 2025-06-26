package GUI;

import Modelo.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

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
    private JPanel Menu;
    private String userName;
    private static DefaultTableModel DTM;
    private PanelEmpleados panelEmpleados;
    private PanelClientes panelClientes;
    private PanelProveedores panelProveedores;
    private PanelStock panelStock;

    private String seccion;

    public Facturador(Usuario user, String seccion) {

        this.seccion = seccion;
        this.userName = user.getEmpleado();
        setContentPane(Facturador);
        setTitle("Facturador");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        CardLayout cardLayout = (CardLayout) Contenido.getLayout();
        Bienvenida.setText("Bienvenido usuario " + userName);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DTM = new DefaultTableModel(new Object[]{"Nombre", "Apellido", "E-Mail", "Direccion", "Telefono", "D.N.I", "Legajo"}, 0);

        Inicio_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(Contenido, "Inicio_card");
            }
        });

        panelClientes = new PanelClientes();
        Clientes_card.setLayout(new BorderLayout());
        Clientes_card.add(panelClientes, BorderLayout.CENTER);
        Clientes_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(Contenido, "Clientes_card");
                panelClientes.cargarListClientes();
            }
        });

        panelEmpleados = new PanelEmpleados();
        Empleados_card.setLayout(new BorderLayout());
        Empleados_card.add(panelEmpleados, BorderLayout.CENTER);
        Empleados_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(Contenido, "Empleados_card");
                panelEmpleados.cargarListaEmpleados();
            }
        });

        panelProveedores = new PanelProveedores();
        Proveedores_card.setLayout(new BorderLayout());
        Proveedores_card.add(panelProveedores, BorderLayout.CENTER);
        Proveedores_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(Contenido, "Proveedores_card");
            }
        });
        panelStock = new PanelStock();
        Stock_card.setLayout(new BorderLayout());
        Stock_card.add(panelStock, BorderLayout.CENTER);
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


    }
}
