package GUI;

import Persistencia.UsuarioDAO;
import Modelo.Usuario;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JDialog {
    private JTextField User;
    private JPanel Login;
    private JTextField Pass;
    private JButton NewUser_btn;
    private JButton Login_btn;

    public Login(){
        super((JFrame) null, "Login", true);
        setSize(400,400);
        setContentPane(Login);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Login_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = User.getText();
                String password = Pass.getText();

                Usuario user = new UsuarioDAO().validarUsuario(username, password);

                if(user != null){
                    dispose();
                    Facturador facturador = new Facturador(user);
                    System.out.println("Login Exitoso");
                }else{
                    System.out.println("Login Fallido");
                }
            }
        });
    }
}
