package GUI;

import Persistencia.NewUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextField Legajo_fld;
    private JLabel Legajo;

    public CreateUser(JDialog parent){
        super(parent, "Nuevo Usuario", true);
        setContentPane(CreateUser);
        setTitle("Nuevo Usuario");
        setLocationRelativeTo(null);
        setSize(400, 650);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validarCampos()){
                    int TelefonoInt = Integer.parseInt(Telefono_fld.getText());
                    int DniInt = Integer.parseInt(Dni_fld.getText());
                    int LegajoInt = Integer.parseInt(Legajo_fld.getText());
                    String PasswordStr = new String(Password_fld.getPassword());
                    NewUser.CreateNewUser(Nombre_Fld.getText(), Apellido_fld.getText(), Email_fld.getText(), Direccion_fld.getText(), TelefonoInt, DniInt, LegajoInt, Username_fld.getText(), PasswordStr);
                }
            }
        });
    }

    private boolean validarCampos(){
        JTextField[] campos = {Nombre_Fld, Apellido_fld, Email_fld, Direccion_fld, Telefono_fld, Dni_fld, Username_fld};
        for(JTextField campo : campos){
            if(campo.getText().trim().length() == 0) {
                return false;
            }
        }
        if(Password_fld.getPassword().length == 0) {
            return false;
        }
        return true;
    }
}
