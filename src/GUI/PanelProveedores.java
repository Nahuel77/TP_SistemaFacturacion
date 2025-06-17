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
    private JLabel NombreLbl;
    private JLabel ApellidoLbl;
    private JLabel EmailLbl;
    private JLabel DireccionLbl;
    private JLabel TelefonoLbl;
    private JLabel DniLbl;
    private JTextField NombreIn;
    private JTextField ApellidoIn;
    private JTextField EmailIn;
    private JTextField DireccionIn;
    private JTextField TelefonoIn;
    private JTextField DniIn;

    public PanelProveedores(){
        setLayout(new BorderLayout());

        Title = new JLabel("Proveedores");
        Title.setFont(new Font("Arial", Font.BOLD, 24));
        Title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        Title.setHorizontalAlignment(SwingConstants.CENTER);

        //INPUTS
        JPanel inputTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        NombreLbl = new JLabel("Nombre:");
        NombreLbl.setPreferredSize(new Dimension(100, 40));
        NombreLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        NombreIn = new JTextField();
        NombreIn.setPreferredSize(new Dimension(170,40));
        inputTop.add(NombreLbl);
        inputTop.add(NombreIn);
        ApellidoLbl = new JLabel("Apellido:");
        ApellidoLbl.setPreferredSize(new Dimension(100, 40));
        ApellidoLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        ApellidoIn = new JTextField();
        ApellidoIn.setPreferredSize(new Dimension(170, 40));
        inputTop.add(ApellidoLbl);
        inputTop.add(ApellidoIn);
        EmailLbl = new JLabel("Email");
        EmailLbl.setPreferredSize(new Dimension(100, 40));
        EmailLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        EmailIn = new JTextField();
        EmailIn.setPreferredSize(new Dimension(170, 40));
        inputTop.add(EmailLbl);
        inputTop.add(EmailIn);

        JPanel inputBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        DireccionLbl = new JLabel("Direccion:");
        DireccionLbl.setPreferredSize(new Dimension(100, 40));
        DireccionLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        DireccionIn = new JTextField();
        DireccionIn.setPreferredSize(new Dimension(170, 40));
        inputBottom.add(DireccionLbl);
        inputBottom.add(DireccionIn);
        TelefonoLbl = new JLabel("Telefono");
        TelefonoLbl.setPreferredSize(new Dimension(100, 40));
        TelefonoLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        TelefonoIn = new JTextField();
        TelefonoIn.setPreferredSize(new Dimension(170, 40));
        inputBottom.add(TelefonoLbl);
        inputBottom.add(TelefonoIn);
        DniLbl = new JLabel("DNI:");
        DniLbl.setPreferredSize(new Dimension(100, 40));
        DniLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        DniIn = new JTextField();
        DniIn.setPreferredSize(new Dimension(170, 40));
        inputBottom.add(DniLbl);
        inputBottom.add(DniIn);

        JPanel inputs = new JPanel();
        inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));
        inputs.add(inputTop);
        inputs.add(inputBottom);

        Agregar = new JButton("Agregar");
        Eliminar = new JButton("Eliminar");
        Editar = new JButton("Editar");
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botones.setBorder(BorderFactory.createEmptyBorder(20, 5, 10, 5));
        botones.add(Agregar);
        botones.add(Eliminar);
        botones.add(Editar);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 5, 20, 5));
        panelInferior.add(inputs);
        panelInferior.add(botones);

        DTM = new DefaultTableModel(new Object[]{
                "Nombre", "Apellido", "E-Mail", "Direccion", "Telefono", "D.N.I"
        }, 0);
        proveedoresTabla = new JTable(DTM);
        JScrollPane scroll = new JScrollPane(proveedoresTabla);

        add(Title, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
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
