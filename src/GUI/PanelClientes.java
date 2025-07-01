package GUI;

import Modelo.Cliente;
import Persistencia.ClienteDAO;
import Utilidades.Validador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class PanelClientes extends JPanel {
    private JTable clientesTabla;
    private DefaultTableModel DTM;
    private JLabel Title;
    private JLabel NombreLbl;
    private JLabel ApellidoLbl;
    private JLabel EmailLbl;
    private JLabel DireccionLbl;
    private JLabel TelefonoLbl;
    private JLabel DniLbl;
    private JButton Agregar;
    private JButton Eliminar;
    private JButton Editar;
    private JTextField NombreIn;
    private JTextField ApellidoIn;
    private JTextField EmailIn;
    private JTextField DireccionIn;
    private JTextField TelefonoIn;
    private JTextField DniIn;
    private String idCliente;

    public PanelClientes(){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Title = new JLabel("Clientes");
        Title.setFont(new Font("Arial", Font.BOLD, 24));
        Title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        Title.setHorizontalAlignment(SwingConstants.CENTER);

        // INPUTS
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
                "ID", "Nombre", "Apellido", "E-Mail", "Dirección", "Telefono", "D.N.I."
        }, 0);
        clientesTabla = new JTable(DTM);
        JScrollPane scroll = new JScrollPane(clientesTabla);

        add(Title, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        Agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    new ClienteDAO().altaCliente(NombreIn.getText(), ApellidoIn.getText(), EmailIn.getText(), DireccionIn.getText(), TelefonoIn.getText(), DniIn.getText());
                    cargarListClientes();
                    NombreIn.setText("");
                    ApellidoIn.setText("");
                    EmailIn.setText("");
                    DireccionIn.setText("");
                    TelefonoIn.setText("");
                    DniIn.setText("");
                }
            }
        });

        clientesTabla.getSelectionModel().addListSelectionListener(e->{
            if(!e.getValueIsAdjusting()){
                int fila = clientesTabla.getSelectedRow();
                if(fila!=-1){
                    this.idCliente = clientesTabla.getValueAt(fila, 0).toString();
                    String nombre = clientesTabla.getValueAt(fila, 1).toString();
                    String apellido = clientesTabla.getValueAt(fila, 2).toString();
                    String email = clientesTabla.getValueAt(fila, 3).toString();
                    String direccion = clientesTabla.getValueAt(fila, 4).toString();
                    String telefono = clientesTabla.getValueAt(fila, 5).toString();
                    String dni = clientesTabla.getValueAt(fila, 6).toString();

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
                    new ClienteDAO().updateCliente(idCliente, NombreIn.getText(), ApellidoIn.getText(), EmailIn.getText(), DireccionIn.getText(), TelefonoIn.getText(), DniIn.getText());
                    cargarListClientes();
                    NombreIn.setText("");
                    ApellidoIn.setText("");
                    EmailIn.setText("");
                    DireccionIn.setText("");
                    TelefonoIn.setText("");
                    DniIn.setText("");
                    idCliente = null;
                }
            }
        });

        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idCliente!=null){
                    new ClienteDAO().bajaCliente(idCliente);
                    cargarListClientes();
                    NombreIn.setText("");
                    ApellidoIn.setText("");
                    EmailIn.setText("");
                    DireccionIn.setText("");
                    TelefonoIn.setText("");
                    DniIn.setText("");
                    idCliente = null;
                }
            }
        });
    }

    public void cargarListClientes(){
        DTM.setRowCount(0);
        List<Cliente> clientes = new ClienteDAO().cargarClientes();
        for (Cliente cli : clientes){
            Object[] fila = {
                    cli.getId(),
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
                JOptionPane.showMessageDialog(null, "El campo " + entry.getValue() + " es necesario");
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
