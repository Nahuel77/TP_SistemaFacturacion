package GUI;

import Modelo.Cliente;
import Modelo.ListaVenta;
import Modelo.Stock;
import Modelo.Usuario;
import Persistencia.ClienteDAO;
import Persistencia.StockDAO;
import Utilidades.Validador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelVentas extends JPanel {
    private JTable ventasTabla;
    private DefaultTableModel DTM;
    private JComboBox ProductosBox;
    private JButton AgregarBtn;
    private JTextField DescuentoUniIn;
    private JTextField DescIn;
    private JTextPane Total;
    private JTextPane TotalConDesc;
    private JComboBox ClienteBox;
    private JButton Quitar;
    private JTextField UnidadesIn;

    public PanelVentas(Usuario user) {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel Title = new JLabel("Venta", SwingConstants.CENTER);
        Title.setFont(new Font("Arial", Font.BOLD, 24));
        add(Title, BorderLayout.NORTH);

        // Panel de Productos
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));

        DTM = new DefaultTableModel(new Object[]{
                "Nombre del Producto", "Precio", "Unidades", "Total", "Descuento por unidad", "Total con Descuento"
        }, 0);
        ventasTabla = new JTable(DTM);
        JScrollPane scroll = new JScrollPane(ventasTabla);
        panelIzquierdo.add(scroll);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInferior.add(new JLabel("Producto: "));
        ProductosBox = new JComboBox();
        ProductosBox.setPreferredSize(new Dimension(120, 30));
        panelInferior.add(ProductosBox);

        panelInferior.add(new JLabel("Unidades: "));
        UnidadesIn = new JTextField();
        UnidadesIn.setPreferredSize(new Dimension(60, 30));
        panelInferior.add(UnidadesIn);

        panelInferior.add(new JLabel("Descuento: "));
        DescuentoUniIn = new JTextField();
        DescuentoUniIn.setPreferredSize(new Dimension(60, 30));
        panelInferior.add(DescuentoUniIn);

        AgregarBtn = new JButton("Agregar");
        panelInferior.add(AgregarBtn);

        Quitar = new JButton("Quitar");
        panelInferior.add(Quitar);

        panelIzquierdo.add(panelInferior);
        add(panelIzquierdo, BorderLayout.CENTER);

        // Panel derecho
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setPreferredSize(new Dimension(350, 500));

        JLabel EmpleadoLb = new JLabel("Empleado: " + user.getEmpleado());
        EmpleadoLb.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDerecho.add(EmpleadoLb);
        panelDerecho.add(Box.createVerticalStrut(10));

        JPanel clientePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clientePanel.add(new JLabel("Cliente: "));
        ClienteBox = new JComboBox();
        ClienteBox.setPreferredSize(new Dimension(200, 25));
        clientePanel.add(ClienteBox);
        panelDerecho.add(clientePanel);
        panelDerecho.add(Box.createVerticalStrut(10));

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        totalPanel.add(new JLabel("Total:"));
        Total = new JTextPane();
        Total.setPreferredSize(new Dimension(100, 30));
        Total.setEditable(false);
        totalPanel.add(Total);
        panelDerecho.add(totalPanel);

        JPanel descuentoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        descuentoPanel.add(new JLabel("Descuento sobre total: "));
        DescIn = new JTextField();
        DescIn.setPreferredSize(new Dimension(100, 25));
        descuentoPanel.add(DescIn);
        panelDerecho.add(descuentoPanel);

        JButton AplicarDesc = new JButton("Aplicar");
        AplicarDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDerecho.add(AplicarDesc);
        panelDerecho.add(Box.createVerticalStrut(10));

        JPanel totalConDescPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        totalConDescPanel.add(new JLabel("Total con descuento aplicado: "));
        TotalConDesc = new JTextPane();
        TotalConDesc.setPreferredSize(new Dimension(100, 30));
        TotalConDesc.setEditable(false);
        totalConDescPanel.add(TotalConDesc);
        panelDerecho.add(totalConDescPanel);

        JPanel botonesFinales = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton TerminarVenta = new JButton("Terminar Venta");
        JButton CancelarVenta = new JButton("Cancelar Venta");
        botonesFinales.add(TerminarVenta);
        botonesFinales.add(CancelarVenta);
        panelDerecho.add(botonesFinales);

        add(panelDerecho, BorderLayout.EAST);

        TerminarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ventasTabla.getRowCount()==0){
                    JOptionPane.showMessageDialog(null, "No hay ventas en la lista.");
                    return;
                }
                List<ListaVenta> listaVentas = new ArrayList<>();
                for (int i = 0; i < DTM.getRowCount(); i++) {
                    String nombreProducto = DTM.getValueAt(i, 0).toString();
                    float precioProducto = Float.parseFloat(DTM.getValueAt(i, 1).toString());
                    int unidades = Integer.parseInt(DTM.getValueAt(i, 2).toString());
                    double total = Double.parseDouble(DTM.getValueAt(i, 3).toString());
                    int descuentoProductoUnidad = Integer.parseInt(DTM.getValueAt(i, 4).toString());
                    double totalDescuentoUnidad = Double.parseDouble(DTM.getValueAt(i, 5).toString());

                    ListaVenta venta = new ListaVenta(
                            nombreProducto,
                            precioProducto,
                            unidades,
                            total,
                            descuentoProductoUnidad,
                            totalDescuentoUnidad
                    );
                    listaVentas.add(venta);
                }
                if(ClienteBox.getSelectedItem()=="--"){
                    JOptionPane.showMessageDialog(null, "Se debe seleccionar un cliente.");
                    return;
                }
                String totalDesc = TotalConDesc.getText();
                if(Validador.isEmpty(totalDesc)){
                    totalDesc = Total.getText();
                }

                String cliente = (String) ClienteBox.getSelectedItem();
                Factura.mostrarFacturaEnVentana(listaVentas, Total.getText(), DescIn.getText(), totalDesc, user.getEmpleado(), cliente);
                limpiarDatos();
            }
        });

        CancelarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarDatos();
            }
        });

        // Lógica de botones
        Quitar.addActionListener(e -> {
            int i = ventasTabla.getSelectedRow();
            if (i >= 0) {
                DTM.removeRow(i);
                Total.setText(String.valueOf(calcularTotal()));
            }
        });

        AgregarBtn.addActionListener(e -> {
            String producto = (String) ProductosBox.getSelectedItem();
            String unidades = UnidadesIn.getText();
            String descuento = DescuentoUniIn.getText();

            if(Validador.isEmpty(descuento)){descuento=new String("0");}
            if (validarProductos(producto, unidades, descuento)) {
                setearListaVenta(producto, unidades, descuento);
                UnidadesIn.setText("");
                DescuentoUniIn.setText("");
                ProductosBox.setSelectedIndex(0);
                Total.setText(String.valueOf(calcularTotal()));
            }
        });

        AplicarDesc.addActionListener(e -> {
            double total = calcularTotal();
            try {
                //TODO comprobar si isEmpty
                double desc = Double.parseDouble(DescIn.getText());
                double totalConDesc = total - total * (desc / 100);
                TotalConDesc.setText(String.valueOf(totalConDesc));
            } catch (NumberFormatException ignored) {
            }
        });

        // Cargar datos al inicio
        cargarDatos();
    }

    private void cargarDatos() {
        modeloComboBox(ProductosBox, new StockDAO().cargarStock());
        modeloComboBox(ClienteBox, new ClienteDAO().cargarClientes());
    }

    private void limpiarDatos(){
        DefaultTableModel model = (DefaultTableModel) ventasTabla.getModel();
        model.setRowCount(0);
        UnidadesIn.setText("");
        DescuentoUniIn.setText("");
        ProductosBox.setSelectedIndex(0);
        ClienteBox.setSelectedIndex(0);
        Total.setText("");
        TotalConDesc.setText("");
        DescIn.setText("");
    }

    private void modeloComboBox(JComboBox box, List<?> lista) {
        box.removeAllItems();
        box.addItem("--");
        for (Object obj : lista) {
            if (obj instanceof Stock)
                box.addItem(((Stock) obj).getNombre());
            else if (obj instanceof Cliente)
                box.addItem(((Cliente) obj).getNombre());
        }
    }

    private boolean validarProductos(String producto, String unidades, String descuento) {
        if (producto.equals("--")){ return false;}
        if(!Validador.isNaN(unidades)){ return false;}
        if(!Validador.isEmpty(descuento)){
            if(!Validador.isNaN(descuento)){return false;}
        }
        return true;
    }

    private void setearListaVenta(String producto, String unidades, String descuento) {
        int precio = new StockDAO().getPriceByName(producto);
        int uni = Integer.parseInt(unidades);
        int desc = Integer.parseInt(descuento);
        double total = uni * precio;
        double totalConDesc = total - total * (desc / 100.0);
        DTM.addRow(new Object[]{producto, precio, unidades, total, desc, totalConDesc});
    }

    private double calcularTotal() {
        double total = 0;
        for (int i = 0; i < DTM.getRowCount(); i++) {
            try {
                total += Double.parseDouble(DTM.getValueAt(i, 5).toString());
            } catch (Exception ignored) {
            }
        }
        return total;
    }
}