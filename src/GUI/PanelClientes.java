package GUI;

import Modelo.Cliente;
import Persistencia.ClienteDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelClientes extends JPanel {
    private JTable empleadosTabla;
    private DefaultTableModel DTM;
    private JLabel Title;
    private JButton Agregar;
    private JButton Eliminar;
    private JButton Editar;

    public PanelClientes(){
        setLayout(new BorderLayout());

        Title = new JLabel("Clientes");
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
        //Agregar.setSize();

        DTM = new DefaultTableModel(new Object[]{
                "Nombre", "Apellido", "E-Mail", "Direccion", "Telefono", "D.N.I"
        }, 0);
        empleadosTabla = new JTable(DTM);
        JScrollPane scroll = new JScrollPane(empleadosTabla);

        add(Title, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }

    public void cargarListClientes(){
        DTM.setRowCount(0);
        List<Cliente> clientes = new ClienteDAO().cargarClientes();
        for (Cliente cli : clientes){
            Object[] fila = {
                    cli.getNombre(),
                    cli.getApellido(),
                    cli.getEmail(),
                    cli.getDireccion(),
                    cli.getTelefono(),
                    cli.getDni()
            };
            DTM.addRow(fila);
        }
    }

    public void registrarCliente(){}

    public void actualizarCliente(){}

    public void bajaCliente(){}
}
