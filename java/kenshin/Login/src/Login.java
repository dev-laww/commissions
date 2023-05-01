import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Login extends JFrame{
    private JTextField usernameTextField;
    private JPasswordField passwordField;

    public Login() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(300, 250);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 80, 25);
        panel.add(usernameLabel);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(140, 50, 120, 25);
        panel.add(usernameTextField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 90, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 90, 120, 25);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 140, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("Administrator") && password.equals("admin")) {
                    new Admin().setVisible(true);
                    dispose();
                } else if (username.equals("Cashier") && password.equals("cashier")) {
                    new Cashier().setVisible(true);
                    dispose();
                } else if (username.equals("Warehouse") && password.equals("warehouse")) {
                    new Warehouse().setVisible(true);
                    dispose();
                } else if (username.equals("Engineering") && password.equals("engineering")) {
                    new Engineering().setVisible(true);
                    dispose();
                } else if (username.equals("Technical Support") && password.equals("techsupport")) {
                    new TechnicalSupport().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Login Failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}