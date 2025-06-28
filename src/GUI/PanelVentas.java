package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelVentas extends JPanel{
    private JLabel Title;
    private JTable ventasTabla;
    private DefaultTableModel DTM;
    private JComboBox ProductosBox;

    public PanelVentas(){
        setLayout(new BorderLayout());

        Title = new JLabel("Ventas");
        Title.setFont(new Font("Arial", Font.BOLD, 24));
        Title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        add(Title, BorderLayout.NORTH);

        DTM = new DefaultTableModel(new Object[]{
                "Nombre del Producto", "Precio", "Unidades", "Descuento"
        }, 0);
        ventasTabla = new JTable(DTM);
        JScrollPane scroll = new JScrollPane(ventasTabla);
        add(scroll, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        ProductosBox = new JComboBox();
        panelInferior.add(ProductosBox);





    }
}
