package GUI;

import Modelo.Stock;
import Persistencia.ProveedorDAO;
import Persistencia.StockDAO;
import Utilidades.Validador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PanelStock extends JPanel {
    private JTable stockTabla;
    private DefaultTableModel DTM;
    private JLabel Title;
    private JButton Agregar;
    private JButton Eliminar;
    private JButton Editar;
    private JLabel NombreLbl;
    private JLabel ProveedorLbl;
    private JLabel PrecioLbl;
    private JLabel CantidadLbl;
    private JLabel EstadosLbl;
    private JTextField NombreIn;
    private JTextField ProveedorIn;
    private JTextField PrecioIn;
    private JTextField CantidadIn;
    private JTextField EstadosIn;
    private String idStock;

    public PanelStock(){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Title = new JLabel("Stock");
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
        ProveedorLbl = new JLabel("Proveedor:");
        ProveedorLbl.setPreferredSize(new Dimension(100, 40));
        ProveedorLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        ProveedorIn = new JTextField();
        ProveedorIn.setPreferredSize(new Dimension(170,40));
        inputTop.add(ProveedorLbl);
        inputTop.add(ProveedorIn);
        PrecioLbl = new JLabel("Precio:");
        PrecioLbl.setPreferredSize(new Dimension(100, 40));
        PrecioLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        PrecioIn = new JTextField();
        PrecioIn.setPreferredSize(new Dimension(170,40));
        inputTop.add(PrecioLbl);
        inputTop.add(PrecioIn);

        JPanel inputBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        CantidadLbl = new JLabel("Cantidad:");
        CantidadLbl.setPreferredSize(new Dimension(100, 40));
        CantidadLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        CantidadIn = new JTextField();
        CantidadIn.setPreferredSize(new Dimension(170,40));
        inputBottom.add(CantidadLbl);
        inputBottom.add(CantidadIn);
        EstadosLbl = new JLabel("Estados:");
        EstadosLbl.setPreferredSize(new Dimension(100, 40));
        EstadosLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        EstadosIn = new JTextField();
        EstadosIn.setPreferredSize(new Dimension(170,40));
        inputBottom.add(EstadosLbl);
        inputBottom.add(EstadosIn);

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
                "ID", "Nombre", "Proveedor", "Precio", "Cantidad", "Estados"
        }, 0);
        stockTabla = new JTable(DTM);
        JScrollPane scroll = new JScrollPane(stockTabla);

        add(Title, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        Agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    new StockDAO().altaStock(NombreIn.getText(), ProveedorIn.getText(), PrecioIn.getText(), CantidadIn.getText(), EstadosIn.getText());
                    cargarListaStock();
                    NombreIn.setText("");
                    ProveedorIn.setText("");
                    PrecioIn.setText("");
                    CantidadIn.setText("");
                    EstadosIn.setText("");
                }
            }
        });

        stockTabla.getSelectionModel().addListSelectionListener(e->{
            if(!e.getValueIsAdjusting()){
                int fila = stockTabla.getSelectedRow();
                if(fila!=-1){
                    this.idStock = stockTabla.getValueAt(fila, 0).toString();
                    String nombre = stockTabla.getValueAt(fila, 1).toString();
                    String proveedor = stockTabla.getValueAt(fila, 2).toString();
                    String precio = stockTabla.getValueAt(fila, 3).toString();
                    String cantidad = stockTabla.getValueAt(fila, 4).toString();
                    String estados = stockTabla.getValueAt(fila, 5).toString();

                    NombreIn.setText(nombre);
                    ProveedorIn.setText(proveedor);
                    PrecioIn.setText(precio);
                    CantidadIn.setText(cantidad);
                    EstadosIn.setText(estados);
                }
            }
        });

        Editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    new StockDAO().updateStock(idStock, NombreIn.getText(), ProveedorIn.getText(), PrecioIn.getText(), CantidadIn.getText(), EstadosIn.getText());
                    cargarListaStock();
                    NombreIn.setText("");
                    ProveedorIn.setText("");
                    PrecioIn.setText("");
                    CantidadIn.setText("");
                    EstadosIn.setText("");
                    idStock = null;
                }
            }
        });

        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idStock!=null){
                    new StockDAO().bajaStock(idStock);
                    cargarListaStock();
                    NombreIn.setText("");
                    ProveedorIn.setText("");
                    PrecioIn.setText("");
                    CantidadIn.setText("");
                    EstadosIn.setText("");
                    idStock = null;
                }
            }
        });
    }

    public void cargarListaStock(){
        DTM.setRowCount(0);
        List<Stock> stocks = new StockDAO().cargarStock();
        for(Stock stk : stocks){
            Object[] lista = {
                    stk.getId(),
                    stk.getNombre(),
                    stk.getProovedor(),
                    stk.getPrecio(),
                    stk.getCantidad(),
                    stk.getEstados()
            };
            DTM.addRow(lista);
        }
    }

    private boolean validarCampos(){
        Map<JTextField, String> campos = new LinkedHashMap<>();
        campos.put(NombreIn, "Nombre");
        campos.put(ProveedorIn, "Proveedor");
        campos.put(PrecioIn, "Precio");
        campos.put(CantidadIn, "Cantidad");
        campos.put(EstadosIn, "Estados");

        for (Map.Entry<JTextField, String> entry : campos.entrySet()) {
            if (entry.getKey().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo " + entry.getValue() + " es obligatorio.");
                entry.getKey().requestFocus();
                return false;
            }
        }
        if(!Validador.isNaN(PrecioIn.getText())){
            JOptionPane.showMessageDialog(null, "El precio ingresado debe ser del tipo numerico.");
            return false;
        }
        if(!Validador.isNaN(CantidadIn.getText())){
            JOptionPane.showMessageDialog(null, "La cantidad ingresada no es numerica");
            return false;
        }
        return true;
    }
}
