package project_1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JFrame {
    static String[] col = {"Name", "Account No.", "Balance"};
    static DefaultTableModel model = new DefaultTableModel(null, col);
    JLabel header = new JLabel("BANKING SYSTEM");
    JLabel header2 = new JLabel("Admin Panel");
    JButton addBtn = new JButton("Add Account");
    JButton removeBtn = new JButton("Remove Account");
    JButton lockedBtn = new JButton("Locked Accounts");
    JButton depositBtn = new JButton("Deposit");
    JButton transferBtn = new JButton("Transfer");
    JButton withdrawBtn = new JButton("Withdraw");
    JButton logoutBtn = new JButton("Logout");
    JTable jt = new JTable(null, col);
    JScrollPane sp = new JScrollPane(jt);

    public AdminPanel() {
        model.setDataVector(BankingSystem.data.getCustomerData(), col);
        header.setBounds(300, 10, 390, 50);
        header.setFont(new Font("Stencil", 10, 25));
        this.add(header);

        header2.setBounds(360, 40, 390, 50);
        header2.setFont(new Font("Times New Roman", 8, 18));
        this.add(header2);

        this.add(sp);

        addBtn.setBounds(100, 100, 110, 20);
        this.add(addBtn);
        removeBtn.setBounds(220, 100, 130, 20);
        this.add(removeBtn);
        lockedBtn.setBounds(425, 380, 150, 20);
        this.add(lockedBtn);
        depositBtn.setBounds(365, 100, 100, 20);
        this.add(depositBtn);
        transferBtn.setBounds(475, 100, 100, 20);
        this.add(transferBtn);
        withdrawBtn.setBounds(590, 100, 110, 20);
        this.add(withdrawBtn);

        logoutBtn.setBounds(590, 380, 110, 20);
        this.add(logoutBtn);

        sp.setBounds(100, 150, 600, 200);
        jt.setModel(model);

        this.setSize(800, 480);
        this.setTitle("Admin Panel");
        this.setLocationRelativeTo(null);//setLocationRelativeTo(this);
        this.setLayout(null);
        this.setDefaultCloseOperation(AdminPanel.EXIT_ON_CLOSE);

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register().setVisible(true);
                dispose();
            }
        });
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveAccount().setVisible(true);
                dispose();
            }
        });
        lockedBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LockedAccounts().setVisible(true);
                dispose();
            }
        });
        depositBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Deposit(true).setVisible(true);
                dispose();
            }
        });
        transferBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Transfer(true).setVisible(true);
                dispose();
            }
        });
        withdrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Withdraw(true).setVisible(true);
                dispose();
            }
        });
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage().setVisible(true);
                dispose();
            }
        });
    }
}