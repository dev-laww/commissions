/**
 * @author: tora
 */

package finalproject;

import finalproject.db.Database;
import finalproject.db.Transaction;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MiniStatement {
    JFrame frame = new JFrame("TRANSACTION DETAILS");
    static String[] col = {"Date and Time", "Type", "Amount"};
    static DefaultTableModel model = new DefaultTableModel(null, col);
    JLabel header1 = new JLabel("VIEW MINISTATEMENT");
    JLabel accId = new JLabel();
    JLabel balance = new JLabel();
    JLabel header2 = new JLabel("TRANSACTION");
    JButton btnBack = new JButton("Back");
    JTable jt = new JTable(null, col);
    JScrollPane sp = new JScrollPane(jt);
    ImageIcon image = new ImageIcon("pic9.png");
    JLabel background = new JLabel("", image, JLabel.CENTER);
    
    MiniStatement(){
        ArrayList<Transaction> userTransactions = Database.getUserTransactions(Database.user.id);

        if (userTransactions == null || userTransactions.size() == 0) {
            JOptionPane.showMessageDialog(null, "No transactions found.");
            frame.dispose();
            new UserMenu();
            return;
        }

        Transaction transaction = new Transaction(Database.user.id, 0, "view ministatement");
        if (!Database.saveTransaction(transaction)) {
            frame.dispose();
            new UserMenu();
            return;
        }

        String[][] tableData = new String[userTransactions.size()][3];

        for (Transaction t : userTransactions) {
            tableData[userTransactions.indexOf(t)] = t.toArray();
        }

        model.setDataVector(tableData, col);

        header1.setBounds(230, 30, 390, 50);
        header1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        header1.setForeground(Color.WHITE);
        background.add(header1);

        accId.setBounds(100, 100, 600, 40);
        accId.setText("ACCOUNT NO: " + Database.user.id);
        accId.setForeground(Color.WHITE);
        background.add(accId);

        balance.setBounds(100, 120, 600, 40);
        balance.setText("Balance: " + String.format("%.2f", Database.user.balance));
        balance.setForeground(Color.WHITE);
        background.add(balance);

        header2.setBounds(348, 70, 390, 50);
        header2.setFont(new Font("Times New Roman", Font.BOLD, 18));
        header2.setForeground(Color.WHITE);
        background.add(header2);

        btnBack.setBounds(590, 380, 110, 20);
        background.add(btnBack);

        sp.setBounds(100, 160, 600, 200);
        jt.setModel(model);
        background.add(sp);

        background.setBounds(0,0,800,480);
        
        frame.add(background);
        frame.setSize(800, 480);
        frame.setLocationRelativeTo(null);//setLocationRelativeTo(frame);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // action listeners
        btnBack.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(frame, "Do you want to perform another transaction?", "Confirm", JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
            frame.dispose();
            new UserMenu();
        } else {
            frame.dispose();
            new Login();
                }
            }
        });
    }
}
