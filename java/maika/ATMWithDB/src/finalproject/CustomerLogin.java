/**
 * @author: tora
 * @author: niku
 */

package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerLogin {
    int attempts = 0;
    JFrame frame = new JFrame();
    JButton enter = new JButton();
    JButton cancel = new JButton();
    JLabel accIDLabel = new JLabel();
    JLabel pinCodeLabel = new JLabel();
    JTextField accIdTF = new JTextField();
    JPasswordField pinCodeTF = new JPasswordField();
    ImageIcon image = new ImageIcon("Login.jpg");
    JLabel background = new JLabel("", image, JLabel.CENTER);
    ArrayList<Customer> logininfo;

    CustomerLogin(ArrayList<Customer> customerList) {
        this.logininfo = customerList;

        accIDLabel.setForeground(Color.WHITE);
        pinCodeLabel.setForeground(Color.WHITE);

        accIDLabel.setBounds(80, 210, 200, 35);
        accIDLabel.setText("ACCOUNT ID");
        accIdTF.setBounds(220, 210, 150, 35);
        accIDLabel.setFont(new Font(null, Font.PLAIN, 20));

        pinCodeLabel.setBounds(80, 270, 200, 35);
        pinCodeLabel.setText("PIN CODE");
        pinCodeTF.setBounds(220, 270, 150, 35);
        pinCodeTF.setColumns(4);
        pinCodeLabel.setFont(new Font(null, Font.PLAIN, 20));

        enter.setBounds(160, 320, 90, 30);
        enter.setText("ENTER");
        enter.setFocusable(false);

        cancel.setBounds(260, 320, 90, 30);
        cancel.setText("CANCEL");
        cancel.setFocusable(false);

        frame.add(background);
        frame.add(cancel);
        frame.add(enter);
        frame.add(pinCodeTF);
        frame.add(accIdTF);
        frame.add(accIDLabel);
        frame.add(pinCodeLabel);
        frame.setSize(1000, 575);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        //background image
        background.add(accIDLabel);
        background.add(pinCodeLabel);
        background.add(enter);
        background.add(cancel);
        background.add(accIdTF);
        background.add(pinCodeTF);
        background.setBounds(0, 0, 1000, 575);

        frame.setVisible(true);

        // action listeners
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed();
            }
        });

        pinCodeTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed();
            }
        });

        accIdTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new FrontPage(BankSystem.idAndPassword).start();
            }
        });

    }

    public void enterPressed() {
        String accId = accIdTF.getText();
        String pinCode = String.valueOf(pinCodeTF.getPassword());

        if (accId.isEmpty() || pinCode.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields!");
            return;
        }

        try {
            Long.parseLong(accId);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Account ID must be a number!");
            return;
        }

        try {
            Integer.parseInt(pinCode);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Pin Code must be a number!");
            return;
        }

        if (pinCode.length() != 4) {
            JOptionPane.showMessageDialog(null, "Pin Code must be 4 digits!");
            return;
        }

        if (accId.length() != 12) {
            JOptionPane.showMessageDialog(null, "Account ID must be 12 digits!");
            return;
        }

        boolean found = false;
        Customer customer1 = null;
        for (Customer customer : logininfo) {
            if (!customer.getAccountID().equals(accId)) {
                continue;
            }

            if (customer.isLocked()) {
                JOptionPane.showMessageDialog(null, "Your account is locked!");
                return;
            }

            attempts++;


            if (attempts >= 4) {
                customer.setLocked();
                JOptionPane.showMessageDialog(null, "Too many attempts! Account has been blocked!");
                return;
            }

            customer1 = customer;

            if (customer.getPin().equals(pinCode)) {
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Account ID or Pin Code is incorrect!");
            accIdTF.setText("");
            pinCodeTF.setText("");
            return;
        }

        frame.dispose();
        JOptionPane.showMessageDialog(null, "LOGIN SUCCESSFULLY!");

        new CustomerMenu(customer1);
    }
}
