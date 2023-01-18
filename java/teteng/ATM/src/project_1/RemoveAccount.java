package project_1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RemoveAccount extends JFrame {
    static String[] col = {"Name", "Account No.", "Balance"};
    static DefaultTableModel model = new DefaultTableModel(null, col);
    JLabel header = new JLabel("BANKING SYSTEM");
    JLabel header2 = new JLabel("ACCOUNT DELETION");
    JLabel accId = new JLabel("Account ID:");
    JTextField txtAccId = new JTextField();
    JButton btnCancel = new JButton("Cancel");
    JButton btnDelete = new JButton("Delete");
    JTable jt = new JTable(null, col);
    JScrollPane sp = new JScrollPane(jt);

    public RemoveAccount() {
        model.setDataVector(BankingSystem.data.getCustomerData(), col);
        header.setBounds(300, 10, 390, 50);
        header.setFont(new Font("Stencil", 10, 25));
        this.add(header);

        header2.setBounds(318, 40, 390, 50);
        header2.setFont(new Font("Times New Roman", 8, 18));
        this.add(header2);
        this.add(sp);

        accId.setBounds(460, 100, 100, 20);
        this.add(accId);
        txtAccId.setBounds(550, 100, 150, 20);
        this.add(txtAccId);

        btnDelete.setBounds(100, 380, 110, 20);
        this.add(btnDelete);

        btnCancel.setBounds(590, 380, 110, 20);
        this.add(btnCancel);

        sp.setBounds(100, 150, 600, 200);
        jt.setModel(model);

        this.setSize(800, 480);
        this.setTitle("ACCOUNT DELETION");
        this.setLocationRelativeTo(null);//setLocationRelativeTo(this);
        this.setLayout(null);
        this.setDefaultCloseOperation(RemoveAccount.EXIT_ON_CLOSE);

        txtAccId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminPanel().setVisible(true);
                dispose();
            }
        });
    }
    private void action() {
        String accId = txtAccId.getText();
        ArrayList<Customer> customers = BankingSystem.data.getCustomers();
        try {
            Long.parseLong(accId);

            if (!(accId.length() == 12)) {
                JOptionPane.showMessageDialog(null, "Account ID must be 12 digits long");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Account ID");
            return;
        }

        boolean found = false;
        for (Customer customer : customers) {
            if (customer.getAccountID().equals(accId)) {
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Account ID not found");
            return;
        }

        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account?");
        if (choice == JOptionPane.YES_OPTION) {
            BankingSystem.data.removeCustomer(accId);
            model.setDataVector(BankingSystem.data.getCustomerData(), col);
            txtAccId.setText("");
            JOptionPane.showMessageDialog(null, "Account deleted successfully");
        }
    }
}