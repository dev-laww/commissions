package project_1;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerPanel extends JFrame {
    static String[] col = {"Name", "Account No.", "Balance"};
    static DefaultTableModel model = new DefaultTableModel(null, col);
    JLabel header = new JLabel("BANKING SYSTEM");
    JLabel header2 = new JLabel("Customer Panel");
    JButton depositBtn = new JButton("Deposit");
    JButton withdrawBtn = new JButton("Withdraw");
    JButton transferBtn = new JButton("Transfer");
    JButton changeBtn = new JButton("Change Password");
    JButton logoutBtn = new JButton("Logout");
    JTable jt = new JTable(null, col);
    JScrollPane sp = new JScrollPane(jt);

    public CustomerPanel() {
        Object[][] data = new Object[1][3];
        data[0][0] = BankingSystem.customerInfo.getName();
        data[0][1] = BankingSystem.customerInfo.getAccountID();
        data[0][2] = BankingSystem.customerInfo.getBalance();

        model.setDataVector(data, col);
        header.setBounds(300, 10, 390, 50);
        header.setFont(new Font("Stencil", 10, 25));
        this.add(header);

        header2.setBounds(348, 40, 390, 50);
        header2.setFont(new Font("Times New Roman", 8, 18));
        this.add(header2);

        this.add(sp);

        depositBtn.setBounds(100, 100, 110, 20);
        this.add(depositBtn);
        withdrawBtn.setBounds(255, 100, 130, 20);
        this.add(withdrawBtn);
        transferBtn.setBounds(420, 100, 100, 20);
        this.add(transferBtn);
        changeBtn.setBounds(550, 100, 150, 20);
        this.add(changeBtn);

        logoutBtn.setBounds(590, 380, 110, 20);
        this.add(logoutBtn);

        sp.setBounds(100, 150, 600, 200);
        jt.setModel(model);

        this.setSize(800, 480);
        this.setTitle("Customer Panel");
        this.setLocationRelativeTo(null);//setLocationRelativeTo(this);
        this.setLayout(null);
        this.setDefaultCloseOperation(CustomerPanel.EXIT_ON_CLOSE);

        depositBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Deposit(false).setVisible(true);
                dispose();
            }
        });

        withdrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Withdraw(false).setVisible(true);
                dispose();
            }
        });

        transferBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Transfer(false).setVisible(true);
                dispose();
            }
        });

        changeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePin().setVisible(true);
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
