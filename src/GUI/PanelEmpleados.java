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

    public PanelEmpleados(){
        setLayout(new BorderLayout());
        DTM = new DefaultTableModel(new Object[]{
                "Nombre", "Apellido", "E-Mail", "Direccion", "Telefono", "D.N.I", "Legajo"
        }, 0);
        empleadosTabla = new JTable(DTM);
        JScrollPane scroll = new JScrollPane(empleadosTabla);
        add(scroll, BorderLayout.CENTER);
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
}
