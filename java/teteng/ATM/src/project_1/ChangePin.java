package project_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePin extends JFrame {
    Customer customer;
    JLabel header = new JLabel("BANKING SYSTEM");
    JLabel header2 = new JLabel("UPDATE PASSWORD");
    JLabel oldPin = new JLabel("Old Password:");
    JPasswordField txtOldPin = new JPasswordField();
    JLabel NewPin = new JLabel("New Password:");
    JPasswordField txtNewPin = new JPasswordField();
    JLabel cfmNewPass = new JLabel("Confirm New Password:");
    JPasswordField txtConfirmPass = new JPasswordField();
    JButton btnConfirm = new JButton("CONFIRM");
    JButton btnCancel = new JButton("CANCEL");

    public ChangePin() {
        customer = BankingSystem.customerInfo;

        header.setBounds(300, 10, 390, 50);
        header.setFont(new Font("Stencil", 10, 25));
        this.add(header);

        header2.setBounds(325, 50, 290, 20);
        header2.setFont(new Font("Times New Roman", 8, 18));
        this.add(header2);

        oldPin.setBounds(295, 114, 350, 40);
        this.add(oldPin);
        txtOldPin.setBounds(295, 145, 200, 25);
        this.add(txtOldPin);

        NewPin.setBounds(295, 184, 350, 40);
        this.add(NewPin);
        txtNewPin.setBounds(295, 215, 200, 25);
        this.add(txtNewPin);

        cfmNewPass.setBounds(295, 254, 350, 40);
        this.add(cfmNewPass);
        txtConfirmPass.setBounds(295, 285, 200, 25);
        this.add(txtConfirmPass);

        btnConfirm.setBounds(620, 380, 100, 20);
        this.add(btnConfirm);

        btnCancel.setBounds(500, 380, 100, 20);
        this.add(btnCancel);

        this.setSize(800, 480);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("UPDATE PASSWORD");

        txtOldPin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        txtNewPin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        txtConfirmPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CustomerPanel().setVisible(true);
                dispose();
            }
        });
    }

    private void action() {
        String oldPin = String.valueOf(txtOldPin.getPassword());
        String newPin = String.valueOf(txtNewPin.getPassword());
        String confirmPin = String.valueOf(txtConfirmPass.getPassword());

        if (oldPin.isEmpty() || newPin.isEmpty() || confirmPin.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields");
            return;
        }

        try {
            Integer.parseInt(oldPin);
            Integer.parseInt(newPin);
            Integer.parseInt(confirmPin);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid password");
            txtOldPin.setText("");
            txtNewPin.setText("");
            txtConfirmPass.setText("");
            return;
        }

        if (oldPin.length() != 4 || newPin.length() != 4 || confirmPin.length() != 4) {
            JOptionPane.showMessageDialog(null, "Password must be 4 digits");
            txtOldPin.setText("");
            txtNewPin.setText("");
            txtConfirmPass.setText("");
            return;
        }

        if (!oldPin.equals(customer.getPin())) {
            JOptionPane.showMessageDialog(null, "Invalid old password");
            txtOldPin.setText("");
            return;
        }

        if (!newPin.equals(confirmPin)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match");
            txtNewPin.setText("");
            txtConfirmPass.setText("");
            return;
        }

        customer.setPin(newPin);
        JOptionPane.showMessageDialog(null, "Password updated successfully");
        new CustomerPanel().setVisible(true);
        dispose();
    }
}