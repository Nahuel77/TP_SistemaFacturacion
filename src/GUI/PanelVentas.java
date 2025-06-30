package GUI;

import Modelo.Cliente;
import Modelo.ListaVenta;
import Modelo.Stock;
import Modelo.Usuario;
import Persistencia.ClienteDAO;
import Persistencia.StockDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelVentas extends JPanel {
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

    public PanelVentas(Usuario user) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        Title = new JLabel("Venta");
        Title.setFont(new Font("Arial", Font.BOLD, 24));
        Title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        add(Title, BorderLayout.NORTH);

        // SECCIÓN PRODUCTOS (IZQUIERDA)
        JPanel ProductosSeccion = new JPanel();
        ProductosSeccion.setLayout(new BoxLayout(ProductosSeccion, BoxLayout.Y_AXIS));

        DTM = new DefaultTableModel(new Object[]{
                "Nombre del Producto", "Precio", "Unidades", "Total", "Descuento por unidad", "Total con Descuento"
        }, 0);
        ventasTabla = new JTable(DTM);
        JScrollPane scroll = new JScrollPane(ventasTabla);
        ProductosSeccion.add(scroll);

        JPanel panelInferior = new JPanel();
        ProductNameLb = new JLabel("Producto: ");
        panelInferior.add(ProductNameLb);

        modeloCombo = new DefaultComboBoxModel<>();
        List<Modelo.Stock> stocks = new StockDAO().cargarStock();
        modeloCombo.addElement("--");
        for (Stock stk : stocks) {
            modeloCombo.addElement(stk.getNombre());
        }
        ProductosBox = new JComboBox<>(modeloCombo);
        panelInferior.add(ProductosBox);

        JLabel UnidadesLb = new JLabel("Unidades: ");
        panelInferior.add(UnidadesLb);

        JTextField UnidadesIn = new JTextField();
        UnidadesIn.setPreferredSize(new Dimension(170, 40));
        panelInferior.add(UnidadesIn);

        DescuentoLb = new JLabel("Descuento: ");
        panelInferior.add(DescuentoLb);

        DescuentoUniIn = new JTextField();
        DescuentoUniIn.setPreferredSize(new Dimension(170, 40));
        panelInferior.add(DescuentoUniIn);

        AgregarBtn = new JButton("Agregar");
        panelInferior.add(AgregarBtn);

        ProductosSeccion.add(panelInferior);
        add(ProductosSeccion, BorderLayout.WEST);

        // SECCIÓN DATOS CLIENTE, DESCUENTO Y TOTAL(DERECHA)
        JPanel DatosClienteSeccion = new JPanel();
        DatosClienteSeccion.setLayout(new BoxLayout(DatosClienteSeccion, BoxLayout.Y_AXIS));
        DatosClienteSeccion.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Empleado
        EmpleadoLb = new JLabel("Empleado: " + user.getEmpleado());
        EmpleadoLb.setAlignmentX(Component.LEFT_ALIGNMENT);
        DatosClienteSeccion.add(EmpleadoLb);
        DatosClienteSeccion.add(Box.createVerticalStrut(10));

        // Cliente Panel
        JPanel ClientePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ClientePanel.setMaximumSize(new Dimension(300, 40));
        ClientePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        ClienteLb = new JLabel("Cliente: ");
        modeloClienteCombo = new DefaultComboBoxModel<>();
        List<Modelo.Cliente> clientes = new ClienteDAO().cargarClientes();
        modeloClienteCombo.addElement("--");
        for (Cliente cli : clientes) {
            modeloClienteCombo.addElement(cli.getNombre());
        }
        ClienteBox = new JComboBox<>(modeloClienteCombo);
        ClientePanel.add(ClienteLb);
        ClientePanel.add(ClienteBox);
        DatosClienteSeccion.add(ClientePanel);
        DatosClienteSeccion.add(Box.createVerticalStrut(10));

        // Descuento Panel
        JPanel DescuentoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        DescuentoPanel.setMaximumSize(new Dimension(300, 70));
        DescuentoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel DescLb = new JLabel("Descuento sobre total: ");
        JTextField DescIn = new JTextField();
        DescIn.setPreferredSize(new Dimension(170, 30));
        DescuentoPanel.add(DescLb);
        DescuentoPanel.add(DescIn);
        DatosClienteSeccion.add(DescuentoPanel);
        DatosClienteSeccion.add(Box.createVerticalStrut(10));

        // Total Panel
        JPanel TotalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        TotalPanel.setMaximumSize(new Dimension(300, 40));
        TotalPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        TotalLb = new JLabel("Total:");
        TotalLb.setFont(new Font("Arial", Font.BOLD, 15));
        Total = new JTextPane();
        Total.setText("");
        Total.setPreferredSize(new Dimension(100, 30));
        Total.setEditable(false);

        TotalPanel.add(TotalLb);
        TotalPanel.add(Total);
        DatosClienteSeccion.add(TotalPanel);
        DatosClienteSeccion.add(Box.createVerticalStrut(10));

        add(DatosClienteSeccion, BorderLayout.EAST);

        AgregarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String producto = (String) ProductosBox.getSelectedItem();
                String unidades = UnidadesIn.getText();
                String descuento = DescuentoUniIn.getText();
                if(validarProductos(producto, unidades, descuento)){
                    setearListaVenta(producto, unidades, descuento);
                    UnidadesIn.setText("");
                    DescuentoUniIn.setText("");
                    ProductosBox.setSelectedIndex(0);
                    double total = calcularTotal();
                    Total.setText(String.valueOf(total));
                }
            }
        });
    }

    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < ventasTabla.getRowCount(); i++) {
            Object valor = ventasTabla.getValueAt(i, 5);
            if (valor != null) {
                try {
                    total += Double.parseDouble(valor.toString());
                } catch (NumberFormatException e) {
                    System.out.println("Error al sumar total");
                }
            }
        }
        return total;
    }

    public boolean validarProductos(String producto, String unidades, String descuento){
        //CONSIDERAR dAR ALCANCE GLOBAL A LA FUNCION NAN PARA TODAS LAS VALIDACIONES
        if(producto == "--"){
            System.out.println("es --");
            return false;
        }

        try{
            int descInt = Integer.parseInt(descuento);
        }catch (NumberFormatException e){
            System.out.println("desc no es num");
            return false;
        }

        try{
            int cantidad = Integer.parseInt(unidades);
        }catch (NumberFormatException e){
            System.out.println("cant no es num");
            return false;
        }

        return true;
    }

    public void setearListaVenta(String productos, String unidades, String descuento){
        int precioUnidad = new StockDAO().getPriceByName(productos);
        int uni = Integer.parseInt(unidades);
        double total = uni*precioUnidad;
        double desc = Integer.parseInt(descuento);
        double descuentoTotalUnidades = total - total*(desc/100);
        Object[] fila = {productos, precioUnidad, unidades, total, descuento, descuentoTotalUnidades};
        DTM.addRow(fila);
    }
}