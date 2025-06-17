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
    private JLabel NombreLbl;
    private JLabel ApellidoLbl;
    private JLabel EmailLbl;
    private JLabel DireccionLbl;
    private JLabel TelefonoLbl;
    private JLabel DniLbl;
    private JLabel LegajoLbl;
    private JTextField NombreIn;
    private JTextField ApellidoIn;
    private JTextField EmailIn;
    private JTextField DireccionIn;
    private JTextField TelefonoIn;
    private JTextField DniIn;
    private JTextField LegajoIn;

    public PanelEmpleados(){
        setLayout(new BorderLayout());

        Title = new JLabel("Empleados");
        Title.setFont(new Font("Arial", Font.BOLD, 24));
        Title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        Title.setHorizontalAlignment(SwingConstants.CENTER);

        //Inputs
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
        DireccionLbl = new JLabel("Direccion:");
        DireccionLbl.setPreferredSize(new Dimension(100, 40));
        DireccionLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        DireccionIn = new JTextField();
        DireccionIn.setPreferredSize(new Dimension(170, 40));
        inputTop.add(DireccionLbl);
        inputTop.add(DireccionIn);

        JPanel inputBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
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
        LegajoLbl = new JLabel("Legajo:");
        LegajoLbl.setPreferredSize(new Dimension(100, 40));
        LegajoLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        LegajoIn = new JTextField();
        LegajoIn.setPreferredSize(new Dimension(170, 40));
        inputBottom.add(LegajoLbl);
        inputBottom.add(LegajoIn);

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
                "Nombre", "Apellido", "E-Mail", "Direccion", "Telefono", "D.N.I", "Legajo"
        }, 0);
        empleadosTabla = new JTable(DTM);
        JScrollPane scroll = new JScrollPane(empleadosTabla);

        add(Title, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
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
