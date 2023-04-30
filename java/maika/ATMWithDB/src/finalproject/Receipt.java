package finalproject;

import finalproject.db.Database;
import finalproject.db.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Receipt {
    JLabel label = new JLabel();
    JFrame frame = new JFrame();
    JTextArea tf = new JTextArea();
    JButton ok = new JButton("OK");

    Receipt() {
        Font font = new Font("JetBrains Mono NL", Font.PLAIN,12);
        tf.setEditable(false);
        tf.setFont(font);
        tf.setEditable(false);
        tf.setBackground(Color.LIGHT_GRAY);
        tf.setBounds(20, 80, 560, 470);

        ok.setBounds(250, 560, 100, 30);
        ok.setFocusable(false);

        label.add(ok);
        label.add(tf);

        frame.add(label);
        frame.setSize(650, 700);
        frame.setTitle("Receipt");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        Transaction transaction = Database.getLastTransaction();

        if (transaction == null) {
            JOptionPane.showMessageDialog(null, "No transactions found.");
            return;
        }

        String sb = String.format("%s%n%n", transaction.timestamp) +
                String.format("XXXXX%s%n", transaction.userID().substring(transaction.userID().length() - 3)) +
                String.format("Serial No. %s%n", transaction.id) +
                String.format("%-20s %20.2f", transaction.type.substring(0, 1).toUpperCase() + transaction.type.substring(1), transaction.amount);

        tf.setText(sb);
    }
}
