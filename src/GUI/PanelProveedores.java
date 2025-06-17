package GUI;

import Modelo.Proveedor;
import Persistencia.ProveedorDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelProveedores extends JPanel {
    private JTable proveedoresTabla;
    private DefaultTableModel DTM;
    private JLabel Title;
    private JButton Agregar;
    private JButton Eliminar;
    private JButton Editar;

    public PanelProveedores(){
        setLayout(new BorderLayout());

        Title = new JLabel("Proveedores");
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
                "Nombre", "Apellido", "E-Mail", "Direccion", "Telefono", "D.N.I"
        }, 0);
        proveedoresTabla = new JTable(DTM);
        JScrollPane scroll = new JScrollPane(proveedoresTabla);

        add(Title, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }

    public void cargarListaProveedores(){
        DTM.setRowCount(0);
        List<Proveedor> proveedores = new ProveedorDAO().cargarProveedores();
        for(Proveedor pro : proveedores){
            Object[] lista = {
                    pro.getNombre(),
                    pro.getApellido(),
                    pro.getEmail(),
                    pro.getDireccion(),
                    pro.getTelefono(),
                    pro.getDni()
            };
            DTM.addRow(lista);
        }
    }

    public void registrarProveedores(){}

    public void actualizarProveedores(){}

    public void bajaProveedores(){}
}
