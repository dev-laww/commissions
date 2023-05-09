/**
 * @author: tora
 */

package finalproject;

import finalproject.db.Database;
import finalproject.db.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewBalance {
    JFrame frame = new JFrame();
    JLabel label1 = new JLabel("Welcome to your account");
    JLabel label2 = new JLabel("Your balance is: ");
    JButton exit = new JButton("CANCEL");
    JLabel balance = new JLabel();

    ImageIcon image = new ImageIcon("pic9.png");
    JLabel label = new JLabel("", image, JLabel.CENTER);

    ViewBalance() {
        label1.setBounds(155, 50, 550, 40);
        label1.setText("VIEW BALANCE");
        label1.setFont(new Font(null, Font.BOLD, 40));
        label1.setForeground(Color.WHITE);

        label2.setBounds(90, 120, 600, 40);
        label2.setText("CURRENT BALANCE: ");
        label2.setFont(new Font(null, Font.BOLD, 25));
        label2.setForeground(Color.WHITE);

        balance.setBounds(350, 120, 225, 40);
        balance.setText(String.format("%.2f", Database.user.balance));
        balance.setFont(new Font(null, Font.BOLD, 25));
        balance.setForeground(Color.WHITE);

        exit.setBounds(200, 200, 200, 40);
        exit.setFont(new Font(null, Font.BOLD, 20));
        exit.setForeground(Color.BLACK);
        exit.setBackground(Color.WHITE);


        label.add(label1);
        label.add(label2);
        label.add(balance);
        label.add(exit);
        label.setBounds(0, 0, 600, 300);

        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Transaction transaction = new Transaction(Database.user.id, 0, "view balance");
        if (!Database.saveTransaction(transaction)) {
            frame.dispose();
            new UserMenu();
            return;
        }

        exit.addActionListener(new ActionListener() {
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
