/**
 * @author:  tora
 * @author:  niku
 */

package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author maikaordonez
 */
public class UpdateAccount {
    JFrame frame = new JFrame();
    ImageIcon image = new ImageIcon("pic6.jpg");
    JLabel label = new JLabel("", image, JLabel.CENTER);
    JButton backButton = new JButton("BACK");
    JButton updateButton = new JButton("UPDATE");
    JLabel accountIDLabel = new JLabel();
    JLabel accountNameLabel = new JLabel();
    JLabel address = new JLabel();
    JTextField tfAddress = new JTextField();
    JLabel emailAddress = new JLabel();
    JTextField tfEmailAddress = new JTextField();
    JLabel phone = new JLabel();
    JTextField tfPhone = new JTextField();
    JLabel Label1 = new JLabel("UPDATE ACCOUNT");

    UpdateAccount(Customer customer) {
        
        Label1.setFont(new Font(null, Font.BOLD, 40));
        Label1.setBounds(110, 18, 400, 50);
        Label1.setForeground(Color.WHITE);
        
        backButton.setBounds(30, 270, 90, 30);
        backButton.setFocusable(false);

        updateButton.setBounds(470, 270, 90, 30);
        updateButton.setFocusable(false);

        accountIDLabel.setBounds(30, 50, 300, 100);
        accountIDLabel.setText("Account ID: " + customer.getAccountID());
        accountIDLabel.setFont(new Font(null, Font.BOLD, 15));
        accountIDLabel.setForeground(Color.WHITE);

        accountNameLabel.setBounds(30, 80, 300, 100);
        accountNameLabel.setText("Account Name: " + customer.getName());
        accountNameLabel.setFont(new Font(null, Font.BOLD, 15));
        accountNameLabel.setForeground(Color.WHITE);

        address.setText("Address:");
        address.setBounds(30, 155, 100, 30);
        address.setForeground(Color.WHITE);
        address.setFont(new Font(null, Font.BOLD, 15));

        tfAddress.setBounds(150, 153, 300, 30);
        tfAddress.setFont(new Font(null, Font.BOLD, 15));
        tfAddress.setText(customer.getAddress());

        emailAddress.setText("Email:");
        emailAddress.setBounds(30, 186, 200, 30);
        emailAddress.setForeground(Color.WHITE);
        emailAddress.setFont(new Font(null, Font.BOLD, 15));

        tfEmailAddress.setBounds(150, 185, 300, 30);
        tfEmailAddress.setFont(new Font(null, Font.BOLD, 15));
        tfEmailAddress.setText(customer.getEmail());

        phone.setText("Phone:");
        phone.setBounds(30,217, 100, 30);
        phone.setForeground(Color.WHITE);
        phone.setFont(new Font(null, Font.BOLD, 15));

        tfPhone.setBounds(150, 215, 300, 30);
        tfPhone.setFont(new Font(null, Font.BOLD, 15));
        tfPhone.setText(customer.getContactNo());

        label.add(Label1);
        label.add(accountIDLabel);
        label.add(accountNameLabel);
        label.add(address);
        label.add(tfAddress);
        label.add(emailAddress);
        label.add(tfEmailAddress);
        label.add(phone);
        label.add(tfPhone);
        label.add(backButton);
        label.add(updateButton);
        label.setBounds(0, 0, 600, 350);

        frame.add(label);
        frame.setSize(600, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        tfAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed(customer);
            }
        });

        tfEmailAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed(customer);
            }
        });

        tfPhone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed(customer);
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed(customer);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminMenu();
            }
        });
    }

    private void enterPressed(Customer c) {
        String address = tfAddress.getText();
        String email = tfEmailAddress.getText();
        String phone = tfPhone.getText();
       
        if (c.getAddress().equals(address) && c.getEmail().equals(email) && c.getContactNo().equals(phone)) {
            JOptionPane.showMessageDialog(null, "Change at least one field");
            return;
        }

       

        if (address.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill up all fields");
            return;
        }

        c.setAddress(address);
        c.setEmail(email);
        c.setContactNo(phone);

        JOptionPane.showMessageDialog(null, "Account updated successfully");
        frame.dispose();
        new AdminMenu();
    }
}

