package GUI;

import Modelo.Proveedor;
import Persistencia.ClienteDAO;
import Persistencia.ProveedorDAO;
import Utilidades.Validador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    private String idProveedor;

    public PanelProveedores(){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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
                "ID", "Nombre", "Apellido", "E-Mail", "Direccion", "Telefono", "D.N.I"
        }, 0);
        proveedoresTabla = new JTable(DTM);
        JScrollPane scroll = new JScrollPane(proveedoresTabla);

        add(Title, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        Agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    new ProveedorDAO().altaProveedor(NombreIn.getText(), ApellidoIn.getText(), EmailIn.getText(), DireccionIn.getText(), TelefonoIn.getText(), DniIn.getText());
                    cargarListaProveedores();
                    NombreIn.setText("");
                    ApellidoIn.setText("");
                    EmailIn.setText("");
                    DireccionIn.setText("");
                    TelefonoIn.setText("");
                    DniIn.setText("");
                }
            }
        });

        proveedoresTabla.getSelectionModel().addListSelectionListener(e->{
            if(!e.getValueIsAdjusting()){
                int fila = proveedoresTabla.getSelectedRow();
                if(fila!=-1){
                    this.idProveedor = proveedoresTabla.getValueAt(fila, 0).toString();
                    String nombre = proveedoresTabla.getValueAt(fila, 1).toString();
                    String apellido = proveedoresTabla.getValueAt(fila, 2).toString();
                    String email = proveedoresTabla.getValueAt(fila, 3).toString();
                    String direccion = proveedoresTabla.getValueAt(fila, 4).toString();
                    String telefono = proveedoresTabla.getValueAt(fila, 5).toString();
                    String dni = proveedoresTabla.getValueAt(fila, 6).toString();

                    NombreIn.setText(nombre);
                    ApellidoIn.setText(apellido);
                    EmailIn.setText(email);
                    DireccionIn.setText(direccion);
                    TelefonoIn.setText(telefono);
                    DniIn.setText(dni);
                }
            }
        });

        Editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    new ProveedorDAO().updateProveedor(idProveedor, NombreIn.getText(), ApellidoIn.getText(), EmailIn.getText(), DireccionIn.getText(), TelefonoIn.getText(), DniIn.getText());
                    cargarListaProveedores();
                    NombreIn.setText("");
                    ApellidoIn.setText("");
                    EmailIn.setText("");
                    DireccionIn.setText("");
                    TelefonoIn.setText("");
                    DniIn.setText("");
                    idProveedor = null;
                }
            }
        });

        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idProveedor!=null){
                    new ProveedorDAO().bajaProveedor(idProveedor);
                    cargarListaProveedores();
                    NombreIn.setText("");
                    ApellidoIn.setText("");
                    EmailIn.setText("");
                    DireccionIn.setText("");
                    TelefonoIn.setText("");
                    DniIn.setText("");
                    idProveedor = null;
                }
            }
        });
    }

    public void cargarListaProveedores(){
        DTM.setRowCount(0);
        List<Proveedor> proveedores = new ProveedorDAO().cargarProveedores();
        for(Proveedor pro : proveedores){
            Object[] lista = {
                    pro.getId(),
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

    private boolean validarCampos(){
        Map<JTextField, String> campos = new LinkedHashMap<>();
        campos.put(NombreIn, "Nombre");
        campos.put(ApellidoIn, "Apellido");
        campos.put(EmailIn, "Email");
        campos.put(DireccionIn, "Dirección");
        campos.put(TelefonoIn, "Teléfono");
        campos.put(DniIn, "DNI");

        for (Map.Entry<JTextField, String> entry : campos.entrySet()) {
            if (entry.getKey().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo " + entry.getValue() + " es obligatorio.");
                entry.getKey().requestFocus();
                return false;
            }
        }

        if(!Validador.isNaN(TelefonoIn.getText())){
            JOptionPane.showMessageDialog(null, "El telefono ingresado debe ser del tipo numerico.");
            return false;}
        if(!Validador.isNaN(DniIn.getText())){
            JOptionPane.showMessageDialog(null, "El DNI ingresado debe ser del tipo numerico.");
            return false;}

        return true;
    }
}
