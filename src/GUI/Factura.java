package GUI;

import Modelo.ListaVenta;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Factura extends JPanel {

    public Factura(List<ListaVenta> listaVenta, String total, String descuento, String totalDesc, String empleado, String cliente) {
        setLayout(new BorderLayout());

        JTextPane textoFactura = new JTextPane();
        textoFactura.setEditable(false);
        textoFactura.setFont(new Font("Monospaced", Font.PLAIN, 14));

        StringBuilder sb = new StringBuilder();
        sb.append("========= FACTURA =========\n");
        sb.append("Empleado: ").append(empleado).append("\n");
        sb.append("Cliente: ").append(cliente).append("\n\n");

        sb.append(String.format("%-20s %-10s %-10s %-10s %-10s %-10s\n",
                "Producto", "Precio", "Unid", "Total", "Desc%", "Total c/Desc"));
        sb.append("------------------------------------------------------------\n");

        for (ListaVenta venta : listaVenta) {
            sb.append(String.format("%-20s %-10.2f %-10d %-10.2f %-10d %-10.2f\n",
                    venta.getNombreProducto(),
                    venta.getPrecioProducto(),
                    venta.getUnidades(),
                    venta.getTotal(),
                    venta.getDescuentoProductoUnidad(),
                    venta.getTotalDescuentoUnidad()));
        }

        sb.append("\n------------------------------\n");
        sb.append("Total bruto: ").append(total).append("\n");
        sb.append("Descuento aplicado: ").append(descuento).append("%\n");
        sb.append("Total final: ").append(totalDesc).append("\n");

        textoFactura.setText(sb.toString());

        JScrollPane scrollPane = new JScrollPane(textoFactura);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void mostrarFacturaEnVentana(List<ListaVenta> listaVenta, String total, String descuento, String totalDesc, String empleado, String cliente) {
        JFrame frame = new JFrame("Factura");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.add(new Factura(listaVenta, total, descuento, totalDesc, empleado, cliente));
        frame.setVisible(true);
    }
}