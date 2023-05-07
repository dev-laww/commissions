package finalproject;

import finalproject.db.Database;
import finalproject.db.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Receipt {
    JLabel label = new JLabel();
    JFrame frame = new JFrame();
    JTextArea tf = new JTextArea();
    JButton ok = new JButton("OK");

    Receipt(String userID, boolean isFromAdmin) {
        Font font = new Font("JetBrains Mono NL", Font.PLAIN,12);
        tf.setEditable(false);
        tf.setFont(font);
        tf.setEditable(false);
        tf.setBackground(Color.LIGHT_GRAY);
        tf.setBounds(10, 10, 265, 240);

        ok.setBounds(115, 200, 70, 30);
        ok.setFocusable(false);

        label.add(ok);
        label.add(tf);

        frame.add(label);
        frame.setSize(285, 290);
        frame.setTitle("RECEIPT");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                if (isFromAdmin) {
                    new AdminMenu();
                    return;
                }

                new UserMenu();
            }
        });

        ArrayList<Transaction> transactions = Database.getUserTransactions(userID);

        if (transactions == null) {
            JOptionPane.showMessageDialog(null, "No transactions found.");
            return;
        }

        Transaction transaction = transactions.get(transactions.size() - 1);

        String sb = "\t  JJTM MACHINE\n\n" + String.format("  DATE : TIME \n  %s%n%n", transaction.timestamp) +
                String.format("  ACCOUNT ID: XXXXX%s%n", transaction.userID().substring(transaction.userID().length() - 3)) +
                String.format("  Serial No. %s%n", transaction.id) +
                String.format("  %-20s %20.2f", transaction.type.substring(0, 1).toUpperCase() + transaction.type.substring(1), transaction.amount) +
                "\n\n\n        Thank you for using JJTM MACHINE";

        tf.setText(sb);
    }
}
