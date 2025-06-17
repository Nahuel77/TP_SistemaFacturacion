package GUI;

import Modelo.Empleado;
import Persistencia.EmpleadoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelEmpleados extends JPanel {
    private JTable empleadosTabla;
    private DefaultTableModel DTM;
    private JLabel Title;
    private JButton Agregar;
    private JButton Eliminar;
    private JButton Editar;

    public PanelEmpleados(){
        setLayout(new BorderLayout());

        Title = new JLabel("Empleados");
        Title.setFont(new Font("Arial", Font.BOLD, 24));
        Title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        Title.setHorizontalAlignment(SwingConstants.CENTER);

        Agregar = new JButton("Agregar");
        Eliminar = new JButton("Eliminar");
        Editar = new JButton("Editar");
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botones.add(Agregar);
        botones.add(Eliminar);
        botones.add(Editar);

        DTM = new DefaultTableModel(new Object[]{
                "Nombre", "Apellido", "E-Mail", "Direccion", "Telefono", "D.N.I", "Legajo"
        }, 0);
        empleadosTabla = new JTable(DTM);
        JScrollPane scroll = new JScrollPane(empleadosTabla);

        add(Title, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }

    public void cargarListaEmpleados(){
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

    public void registrarEmpleado(){}

    public void actualizarEmpleado(){}

    public void bajaEmpleado(){}
}
