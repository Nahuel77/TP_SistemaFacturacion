package GUI;

import javax.swing.*;

public class CreateUser extends JDialog{
    private JPanel CreateUser;
    private JLabel Nombre;
    private JTextField Nombre_Fld;
    private JLabel Apellido;
    private JTextField Apellido_fld;
    private JLabel Email;
    private JTextField Email_fld;
    private JTextField Direccion_fld;
    private JTextField Telefono_fld;
    private JTextField Dni_fld;
    private JLabel Direccion;
    private JLabel Telefono;
    private JLabel Dni;
    private JTextField Username_fld;
    private JPasswordField Password_fld;
    private JLabel Username;
    private JLabel Password;
    private JButton Crear;

    public CreateUser(JDialog parent){
        super(parent, "Nuevo Usuario", true);
        setContentPane(CreateUser);
        setTitle("Nuevo Usuario");
        setLocationRelativeTo(null);
        setSize(400, 600);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
