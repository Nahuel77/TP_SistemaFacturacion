package GUI;

import Modelo.Empleado;
import Modelo.Usuario;
import Persistencia.EmpleadoDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import javax.swing.table.DefaultTableModel;
import java.util.List;

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
    private JTable Empleados_tabla;
    private String userName;
    private static DefaultTableModel DTM;

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

        DTM = new DefaultTableModel(new Object[]{"Nombre", "Apellido", "E-Mail", "Direccion", "Telefono", "D.N.I", "Legajo"}, 0);

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
                Empleados_tabla.setModel(DTM);
                DTM.setRowCount(0);

                List<Empleado> empleados = new EmpleadoDAO().cargarEmpleados();
                for(Empleado emp : empleados){
                    Object[] fila = {
                            emp.getNombre(),
                            emp.getApellido(),
                            emp.getEmail(),
                            emp.getDireccion(),
                            emp.getTelefono(),
                            emp.getDni(),
                            emp.getLegajo()
                    };
                    DTM.addRow(fila);
                }
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
