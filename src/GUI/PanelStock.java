package GUI;

import Modelo.Stock;
import Persistencia.StockDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelStock extends JPanel {
    private JTable stockTabla;
    private DefaultTableModel DTM;
    private JLabel Title;

    public PanelStock(){
        setLayout(new BorderLayout());

        Title = new JLabel("Stock");
        Title.setFont(new Font("Arial", Font.BOLD, 24));
        Title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        Title.setHorizontalAlignment(SwingConstants.CENTER);

        DTM = new DefaultTableModel(new Object[]{
                "Nombre", "Apellido", "E-Mail", "Direccion", "Telefono", "D.N.I"
        }, 0);
        stockTabla = new JTable(DTM);
        JScrollPane scroll = new JScrollPane(stockTabla);

        add(Title, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    public void cargarListaStock(){
        DTM.setRowCount(0);
        List<Stock> stocks = new StockDAO().cargarStock();
        for(Stock stk : stocks){
            Object[] lista = {
                    stk.getNombre(),
                    stk.getProovedor(),
                    stk.getPrecio(),
                    stk.getCantidad(),
                    stk.getEstados()
            };
            DTM.addRow(lista);
        }
    }

    public void registrarStock(){}

    public void actualizarStock(){}

    public void bajaStock(){}
}
