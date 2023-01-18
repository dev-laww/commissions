package project_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CustomerLogin extends JFrame {
    int attempts;
    ArrayList<Customer> customers;
    JLabel header = new JLabel("BANKING SYSTEM");
    JLabel header2 = new JLabel("CUSTOMER LOGIN");
    JLabel userId = new JLabel("Username:");
    JTextField txtUserId = new JTextField();
    JLabel pass = new JLabel("Password:");
    JPasswordField txtPass = new JPasswordField();
    JButton btnLogin = new JButton("Login");
    JButton btnCancel = new JButton("Cancel");

    public CustomerLogin() {
        this.attempts = 0;
        this.customers = BankingSystem.data.getCustomers();
        header.setBounds(300, 10, 390, 50);
        header.setFont(new Font("Stencil", 10, 25));
        this.add(header);

        header2.setBounds(315, 50, 290, 20);
        header2.setFont(new Font("Times New Roman", 8, 18));
        this.add(header2);

        userId.setBounds(295, 119, 350, 40);
        this.add(userId);
        txtUserId.setBounds(295, 150, 200, 30);
        this.add(txtUserId);

        pass.setBounds(295, 189, 350, 40);
        this.add(pass);
        txtPass.setBounds(295, 220, 200, 30);
        this.add(txtPass);

        btnLogin.setBounds(620, 380, 100, 20);
        this.add(btnLogin);

        btnCancel.setBounds(500, 380, 100, 20);
        this.add(btnCancel);

        this.setSize(800, 480);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("CUSTOMER LOGIN");

        txtUserId.addActionListener(new ActionListener() {
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

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage().setVisible(true);
                dispose();
            }
        });
    }

    private void action() {
        String userId = txtUserId.getText();
        String pin = String.valueOf(txtPass.getPassword());

        if (userId.isEmpty() || pin.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields");
            return;
        }

        try {
            Long.parseLong(userId);
            Integer.parseInt(pin);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number");
            return;
        }

        if (userId.length() != 12) {
            JOptionPane.showMessageDialog(null, "Please enter a valid user ID");
            return;
        }

        if (pin.length() != 4) {
            JOptionPane.showMessageDialog(null, "Please enter a valid pin");
            return;
        }

        if (BankingSystem.data.accountExists(userId)) {
            JOptionPane.showMessageDialog(null, "Account does not exist");
            return;
        }

        Customer c = BankingSystem.data.getCustomer(userId);
        attempts++;

        if (attempts >= 3) {
            JOptionPane.showMessageDialog(null, "You have exceeded the maximum number of attempts! Account Locked!");
            c.setLocked();
            new HomePage().setVisible(true);
            dispose();
            return;
        }

        if (c.isLocked()) {
            JOptionPane.showMessageDialog(null, "Account is locked! Please contact your bank");
            new HomePage().setVisible(true);
            dispose();
            return;
        }

        if (!c.getPin().equals(pin)) {
            JOptionPane.showMessageDialog(null, "Incorrect pin");
            txtPass.setText("");
            return;
        }

        BankingSystem.customerInfo = c;
        new CustomerPanel().setVisible(true);
        dispose();
    }
}