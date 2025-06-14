package GUI;

import javax.swing.*;

public class Login extends JDialog {
    private JTextField User;
    private JPanel Login;
    private JTextField Pass;
    private JButton NewUser_btn;
    private JButton Login_btn;

    public Login(){
        setTitle("Login");
        setSize(400,400);
        setContentPane(Login);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
