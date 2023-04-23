/**
 * @author: tora
 * @author: niku
 */

package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transactions {
    JFrame frame = new JFrame();
    JTextArea textArea = new JTextArea();
    ImageIcon image = new ImageIcon("pic4.jpg");
    JLabel label = new JLabel("", image, JLabel.CENTER);
    JButton backButton = new JButton("BACK");
    JLabel titleLabel = new JLabel("CUSTOMER DETAILS / TRANSACTION HISTORY");

    Transactions() {
        Font font = new Font("JetBrains Mono NL", Font.PLAIN, 15);

        StringBuilder sb = new StringBuilder();
        for (Customer c : BankSystem.idAndPassword.getCustomerLoginInfo()) {
            sb.append(String.format("%-25s%25s%n", "Account Number:", c.getAccountID()))
                    .append(String.format("%-25s%25s%n", "Name:", c.getName()))
                    .append(String.format("%-25s%25s%n", "Address:", c.getAddress()))
                    .append(String.format("%-25s%25s%n", "Email Address:", c.getEmail()))
                    .append(String.format("%-25s%25s%n", "Contact Number:", c.getContactNo()))
                    .append(String.format("%-25s%25s%n%n", "Balance:", c.getBalance()))
                    .append("Transaction History:\n")
                    .append("-------------------------------------------------------------------\n")
                    .append(c.getTransactions(false))
                    .append("\n");
        }


        textArea.setFont(font);
        textArea.setEditable(false);
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setBounds(20, 80, 560, 470);
        textArea.setText(sb.toString());

        backButton.setBounds(30, 510, 70, 30);
        backButton.setFocusable(false);

        titleLabel.setBounds(72, 20, 500, 40);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font(null, Font.BOLD, 19));

        frame.add(titleLabel);
        frame.add(backButton);
        frame.add(textArea);
        frame.add(label);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        label.add(titleLabel);
        label.add(textArea);
        label.setBounds(0, 0, 600, 600);

        frame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminMenu();
            }
        });
    }
}
