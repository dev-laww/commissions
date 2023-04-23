/**
 * @author:  tora
 * @author:  niku
 */

package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class LockedAccounts {
    JFrame frame = new JFrame();
    JLabel label1 = new JLabel("LOCKED ACCOUNTS");
    JLabel label2 = new JLabel("Enter the account number of the account you want to unlock");
    JTextField accountID = new JTextField();
    JButton exit = new JButton("CANCEL");
    JButton enter = new JButton("CONFIRM");
    JTextArea lockedAccounts = new JTextArea();

    LockedAccounts() {
        Font font = new Font("JetBrains Mono NL", Font.PLAIN, 14);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(label1);
        panel.add(topPanel, BorderLayout.PAGE_START);

        // Locked Accounts:
        // 123456789123 - Name
        // 123456789123 - Name
        // 123456789123 - Name

        StringBuilder sb = new StringBuilder();
        sb.append("Locked Accounts:\n");
        for (Customer c : BankSystem.idAndPassword.getCustomerLoginInfo()) {
            if (c.isLocked()) {
                sb.append(c.getAccountID()).append(" - ").append(c.getName()).append("\n");
            }
        }

        lockedAccounts.setFont(font);
        lockedAccounts.setEditable(false);
        lockedAccounts.setBackground(Color.LIGHT_GRAY);
        lockedAccounts.setBounds(20, 80, 560, 470);
        lockedAccounts.setText(sb.toString());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.add(lockedAccounts);
        panel.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(0, 1));
        bottomPanel.add(label2);
        bottomPanel.add(accountID);
        bottomPanel.add(exit);
        bottomPanel.add(enter);
        panel.add(bottomPanel, BorderLayout.PAGE_END);

        
        frame.add(panel);
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // action listeners
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminMenu();
            }
        });

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               enterPressed();
            }
        });

        accountID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed();
            }
        });
    }

    private void enterPressed() {
        HashMap<String, String> admins = BankSystem.idAndPassword.getTellerLoginInfo();
        JPasswordField passwordField = new JPasswordField();
        ArrayList<Customer> customers = BankSystem.idAndPassword.getCustomerLoginInfo();
        int result = JOptionPane.showConfirmDialog(null, passwordField, "Enter admin password:", JOptionPane.OK_CANCEL_OPTION);

        if (!(result == JOptionPane.OK_OPTION)) {
            JOptionPane.showMessageDialog(null, "Cancelled");
            return;
        }

        String adminPass = String.valueOf(passwordField.getPassword());

        if (!admins.get(BankSystem.tellerLoginInfo).equals(adminPass)) {
            JOptionPane.showMessageDialog(null, "Wrong password");
            return;
        }

        String accountIDString = accountID.getText();
        if (accountIDString.length() != 12) {
            JOptionPane.showMessageDialog(null, "Invalid account ID");
            return;
        }

        boolean found = false;
        for (Customer c : customers) {
            if (c.getAccountID().equals(accountIDString)) {
                found = true;
                //new CustomerDetails(c);
                frame.dispose();
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Customer not found!");
            return;
        }

        for (Customer c : customers) {
            if (c.getAccountID().equals(accountIDString)) {
                c.setLocked();
                JOptionPane.showMessageDialog(null, "Account unlocked!");
                frame.dispose();
                //new AdminMenu();
                return;
            }
        }
    }
}
