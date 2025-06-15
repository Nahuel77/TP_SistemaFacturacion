package GUI;

import Modelo.Usuario;

import javax.swing.*;

public class Facturador extends JFrame {
    private JPanel Facturador;

    public Facturador(Usuario user) {
            setContentPane(Facturador);
            setTitle("Bienvenido");
            setSize(250, 300);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
