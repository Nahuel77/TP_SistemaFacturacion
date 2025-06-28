package GUI;

import Modelo.Cliente;
import Modelo.Stock;
import Modelo.Usuario;
import Persistencia.ClienteDAO;
import Persistencia.StockDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelVentas extends JPanel{
    private JLabel Title;
    private JTable ventasTabla;
    private DefaultTableModel DTM;
    private JComboBox ProductosBox;
    private JButton AgregarBtn;
    private DefaultComboBoxModel<String> modeloCombo;
    private JTextField DescuentoUniIn;
    private JLabel ProductNameLb;
    private JLabel DescuentoLb;
    private JTextPane Total;
    private JLabel TotalLb;
    private JLabel ClienteLb;
    private JLabel EmpleadoLb;
    private DefaultComboBoxModel<String> modeloClienteCombo;
    private JComboBox ClienteBox;

    public PanelVentas(Usuario user){
        setLayout(new BorderLayout());

        Title = new JLabel("Ventas");
        Title.setFont(new Font("Arial", Font.BOLD, 24));
        Title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        add(Title, BorderLayout.NORTH);

        JPanel ProductosSeccion = new JPanel();
        ProductosSeccion.setLayout(new BoxLayout(ProductosSeccion, BoxLayout.Y_AXIS));

        DTM = new DefaultTableModel(new Object[]{
                "Nombre del Producto", "Precio", "Unidades", "Descuento"
        }, 0);
        ventasTabla = new JTable(DTM);
        JScrollPane scroll = new JScrollPane(ventasTabla);
        ProductosSeccion.add(scroll);

        JPanel panelInferior = new JPanel();
        ProductNameLb = new JLabel("Producto: ");
        panelInferior.add(ProductNameLb);
        modeloCombo = new DefaultComboBoxModel<>();
        List<Modelo.Stock> stocks = new StockDAO().cargarStock();
        for(Stock stk : stocks){
            modeloCombo.addElement(stk.getNombre());
        }
        ProductosBox = new JComboBox<>(modeloCombo);
        panelInferior.add(ProductosBox);
        //TODO en este field "DescuentoUniIn", realizar una validacion que verifique que la cantidad de unidades
        // ingresadas no sean mayores a las unidades en stock
        DescuentoLb = new JLabel("Descuento: ");
        panelInferior.add(DescuentoLb);
        DescuentoUniIn = new JTextField();
        DescuentoUniIn.setPreferredSize(new Dimension(170,40));
        panelInferior.add(DescuentoUniIn);
        AgregarBtn = new JButton("Agregar");
        panelInferior.add(AgregarBtn);

        ProductosSeccion.add(panelInferior);

        add(ProductosSeccion, BorderLayout.WEST);

        JPanel DatosClienteSeccion = new JPanel();
        DatosClienteSeccion.setLayout(new BoxLayout(DatosClienteSeccion, BoxLayout.Y_AXIS));


        EmpleadoLb = new JLabel("Empleado " + user.getEmpleado() );
        DatosClienteSeccion.add(EmpleadoLb);
        JPanel ClientePanel = new JPanel();
        ClienteLb = new JLabel("Cliente: ");
        ClientePanel.add(ClienteLb);

        modeloClienteCombo = new DefaultComboBoxModel<>();
        List<Modelo.Cliente> clientes = new ClienteDAO().cargarClientes();
        for(Cliente cli : clientes){
            modeloClienteCombo.addElement(cli.getNombre());
        }
        ClienteBox = new JComboBox<>(modeloClienteCombo);
        ClientePanel.add(ClienteBox);
        DatosClienteSeccion.add(ClientePanel);

        JPanel TotalPanel = new JPanel();
        TotalLb = new JLabel("Total:");
        TotalPanel.add(TotalLb);
        Total = new JTextPane();
        Total.setText("2000");
        TotalPanel.add(Total);
        DatosClienteSeccion.add(TotalPanel);

        add(DatosClienteSeccion, BorderLayout.EAST);

    }

    public void calcularTotal(){}
}
