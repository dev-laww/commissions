/**
 * @author: tora
 */

package finalproject;

import finalproject.db.Database;
import finalproject.db.Transaction;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MiniStatement {
    JFrame frame = new JFrame("Locked Accounts");
    static String[] col = {"Date and Time", "Type", "Amount"};
    static DefaultTableModel model = new DefaultTableModel(null, col);
    JLabel header1 = new JLabel("Mini Statement");
    JLabel accId = new JLabel();
    JLabel balance = new JLabel();
    JLabel header2 = new JLabel("Transactions");
    JButton btnBack = new JButton("Back");
    JTable jt = new JTable(null, col);
    JScrollPane sp = new JScrollPane(jt);
    
    MiniStatement(){
        ArrayList<Transaction> userTransactions = Database.getUserTransactions(BankSystem.currentUser.id);

        if (userTransactions == null) {
            JOptionPane.showMessageDialog(null, "No transactions found.");
            frame.dispose();
            new UserMenu();
            return;
        }

        if (userTransactions.size() == 0) {
            JOptionPane.showMessageDialog(null, "No transactions found.");
            frame.dispose();
            new UserMenu();
            return;
        }

        String[][] tableData = new String[userTransactions.size()][3];

        for (Transaction t : userTransactions) {
            tableData[userTransactions.indexOf(t)] = t.toArray();
        }

        model.setDataVector(tableData, col);

        header1.setBounds(348, 10, 390, 50);
        header1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        frame.add(header1);

        accId.setBounds(100, 100, 600, 40);
        accId.setText("Account ID: " + BankSystem.currentUser.id);
        frame.add(accId);

        balance.setBounds(100, 120, 600, 40);
        balance.setText("Balance: " + String.format("%.2f", BankSystem.currentUser.balance));
        frame.add(balance);

        header2.setBounds(348, 40, 390, 50);
        header2.setFont(new Font("Times New Roman", Font.BOLD, 18));
        frame.add(header2);

        btnBack.setBounds(590, 380, 110, 20);
        frame.add(btnBack);

        sp.setBounds(100, 150, 600, 200);
        jt.setModel(model);
        frame.add(sp);

        frame.setSize(800, 480);
        frame.setLocationRelativeTo(null);//setLocationRelativeTo(frame);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // action listeners
        btnBack.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new UserMenu();
            }
        });
    }
}
