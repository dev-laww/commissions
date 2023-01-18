package project_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class AdminLogin extends JFrame {
    private final HashMap<String, String> admins;
    JLabel header = new JLabel("BANKING SYSTEM");
    JLabel header2 = new JLabel("ADMIN LOGIN");
    JLabel userID = new JLabel("Username:");
    JTextField userIdTf = new JTextField();
    JLabel pass = new JLabel("Password:");
    JPasswordField txtPass = new JPasswordField();
    JButton loginBtn = new JButton("Login");
    JButton cancelBtn = new JButton("Cancel");

    public AdminLogin() {
        this.admins  = BankingSystem.data.getAdmins();

        header.setBounds(300, 10, 390, 50);
        header.setFont(new Font("Stencil", 10, 25));
        this.add(header);

        header2.setBounds(340, 50, 290, 20);
        header2.setFont(new Font("Times New Roman", 8, 18));
        this.add(header2);


        userID.setBounds(295, 119, 350, 40);
        this.add(userID);
        userIdTf.setBounds(295, 150, 200, 30);
        this.add(userIdTf);


        pass.setBounds(295, 189, 350, 40);
        this.add(pass);
        txtPass.setBounds(295, 220, 200, 30);
        this.add(txtPass);


        loginBtn.setBounds(620, 380, 100, 20);
        this.add(loginBtn);
//        btnregister.addActionListener(this);

        cancelBtn.setBounds(500, 380, 100, 20);
        this.add(cancelBtn);
//        btnclear.addActionListener(this);


        this.setSize(800, 480);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Admin Login");

        userIdTf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        txtPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               action();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage().setVisible(true);
                dispose();
            }
        });
    }

    private void action() {
        String username = userIdTf.getText();
        String password = new String(txtPass.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields");
            return;
        }

        if (!admins.containsKey(username)) {
            JOptionPane.showMessageDialog(null, "Username does not exist");
            return;
        }

        if (!admins.get(username).equals(password)) {
            JOptionPane.showMessageDialog(null, "Incorrect password");
            return;
        }

        new AdminPanel().setVisible(true);
        BankingSystem.adminInfo = username;
        dispose();
    }
}