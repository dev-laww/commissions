package project_1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LockedAccounts extends JFrame {
    static String[] col = {"Name", "Account No.", "Balance"};
    static DefaultTableModel model = new DefaultTableModel(null, col);
    JLabel header = new JLabel("BANKING SYSTEM");
    JLabel header2 = new JLabel("Locked Accounts");
    JLabel accId = new JLabel("Account ID:");
    JTextField txtAccId = new JTextField();
    JButton btnCancel = new JButton("Cancel");
    JButton btnUnlock = new JButton("Unlock");
    JTable jt = new JTable(null, col);
    JScrollPane sp = new JScrollPane(jt);

    public LockedAccounts() {
        model.setDataVector(BankingSystem.data.getLockedAccounts(), col);
        header.setBounds(300, 10, 390, 50);
        header.setFont(new Font("Stencil", 10, 25));
        this.add(header);

        header2.setBounds(348, 40, 390, 50);
        header2.setFont(new Font("Times New Roman", 8, 18));
        this.add(header2);

        accId.setBounds(460, 100, 100, 20);
        this.add(accId);
        txtAccId.setBounds(550, 100, 150, 20);
        this.add(txtAccId);

        btnUnlock.setBounds(100, 380, 110, 20);
        this.add(btnUnlock);

        btnCancel.setBounds(590, 380, 110, 20);
        this.add(btnCancel);

        sp.setBounds(100, 150, 600, 200);
        jt.setModel(model);
        this.add(sp);

        this.setSize(800, 480);
        this.setTitle("Customer Panel");
        this.setLocationRelativeTo(null);//setLocationRelativeTo(this);
        this.setLayout(null);
        this.setDefaultCloseOperation(LockedAccounts.EXIT_ON_CLOSE);

        txtAccId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        btnUnlock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminPanel().setVisible(true);
                dispose();
            }
        });
    }

    private void action() {
        String accId = txtAccId.getText();

        if (accId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an account ID.");
            return;
        }

        if (BankingSystem.data.getLockedAccounts().length == 0) {
            JOptionPane.showMessageDialog(null, "No locked accounts.");
            return;
        }

        boolean exists = false;
        for (int i = 0; i < BankingSystem.data.getLockedAccounts().length; i++) {
            if (BankingSystem.data.getLockedAccounts()[i][1].equals(accId)) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            JOptionPane.showMessageDialog(null, "Account ID does not exist.");
            return;
        }

        Customer c = BankingSystem.data.getCustomer(accId);
        c.setLocked();
        model.setDataVector(BankingSystem.data.getLockedAccounts(), col);
        JOptionPane.showMessageDialog(null, "Account unlocked.");
        new AdminPanel().setVisible(true);
        dispose();
    }
}