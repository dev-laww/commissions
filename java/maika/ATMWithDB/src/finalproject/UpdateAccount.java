/**
 * @author: tora
 * @author: niku
 */

package finalproject;

import finalproject.db.Database;
import finalproject.db.User;

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
    JLabel label1 = new JLabel("UPDATE ACCOUNT");
    JTextField tfID = new JTextField();
    JButton btnSearch = new JButton("SEARCH");
    private final boolean isAdmin;

    UpdateAccount(boolean isAdmin) {
        this.isAdmin = isAdmin;

        label1.setFont(new Font(null, Font.BOLD, 40));
        label1.setBounds(110, 18, 400, 50);
        label1.setForeground(Color.WHITE);

        backButton.setBounds(30, 270, 90, 30);
        backButton.setFocusable(false);

        updateButton.setBounds(470, 270, 90, 30);
        updateButton.setFocusable(false);

        btnSearch.setBounds(350, 270, 90, 30);
        btnSearch.setFocusable(false);
        btnSearch.setVisible(isAdmin);

        accountIDLabel.setBounds(30, 80, 150, 30);
        accountIDLabel.setText(isAdmin ? "Account ID: " : "Customer ID: ");
        accountIDLabel.setFont(new Font(null, Font.BOLD, 15));
        accountIDLabel.setForeground(Color.WHITE);

        tfID.setBounds(150, 80, 300, 30);
        tfID.setFont(new Font(null, Font.BOLD, 15));
        tfID.setEditable(isAdmin);
        tfID.setText(isAdmin ? "" : BankSystem.currentUser.id);
        tfID.setForeground(Color.WHITE);

        accountNameLabel.setBounds(30, 120, 300, 30);
        accountNameLabel.setText(isAdmin ? "Account Name: " : "Customer Name: " + BankSystem.currentUser.name);
        accountNameLabel.setFont(new Font(null, Font.BOLD, 15));
        accountNameLabel.setForeground(Color.WHITE);

        address.setText("Address:");
        address.setBounds(30, 155, 100, 30);
        address.setForeground(Color.WHITE);
        address.setFont(new Font(null, Font.BOLD, 15));

        tfAddress.setBounds(150, 153, 300, 30);
        tfAddress.setFont(new Font(null, Font.BOLD, 15));
        tfAddress.setText(isAdmin ? "" : BankSystem.currentUser.address());

        emailAddress.setText("Email:");
        emailAddress.setBounds(30, 186, 200, 30);
        emailAddress.setForeground(Color.WHITE);
        emailAddress.setFont(new Font(null, Font.BOLD, 15));

        tfEmailAddress.setBounds(150, 185, 300, 30);
        tfEmailAddress.setFont(new Font(null, Font.BOLD, 15));
        tfEmailAddress.setText(isAdmin ? "" : BankSystem.currentUser.email());

        phone.setText("Phone:");
        phone.setBounds(30, 217, 100, 30);
        phone.setForeground(Color.WHITE);
        phone.setFont(new Font(null, Font.BOLD, 15));

        tfPhone.setBounds(150, 215, 300, 30);
        tfPhone.setFont(new Font(null, Font.BOLD, 15));
        tfPhone.setText(isAdmin ? "" : BankSystem.currentUser.contact());

        label.add(label1);
        label.add(accountIDLabel);
        label.add(tfID);
        label.add(accountNameLabel);
        label.add(address);
        label.add(tfAddress);
        label.add(emailAddress);
        label.add(tfEmailAddress);
        label.add(phone);
        label.add(tfPhone);
        label.add(backButton);
        label.add(updateButton);
        label.add(btnSearch);
        label.setBounds(0, 0, 600, 350);

        frame.add(label);
        frame.setSize(600, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Action Listeners
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

                if (!isAdmin) {
                    new CustomerMenu();
                    return;
                }

                new AdminMenu();
            }
        });

        btnSearch.addActionListener(searchActionListener());
        updateButton.addActionListener(getActionListener());
        tfID.addActionListener(searchActionListener());
        tfAddress.addActionListener(getActionListener());
        tfEmailAddress.addActionListener(getActionListener());
        tfPhone.addActionListener(getActionListener());
    }

    UpdateAccount() {
        this(false);
    }

    public static void main(String[] args) {
        BankSystem.currentUser = Database.users.get(0);
        new UpdateAccount(true);
    }

    private void tryUpdate(User u) {
        String address = tfAddress.getText();
        String email = tfEmailAddress.getText();
        String phone = tfPhone.getText();

        if (address.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (u.address().equals(address) && u.email().equals(email) && u.contact().equals(phone)) {
            JOptionPane.showMessageDialog(null, "No changes made", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = new User(u.id, u.name, email, phone, address, u.pin(), u.balance, u.status);
        try {
            Database.updateUser(user);
            JOptionPane.showMessageDialog(null, "Account updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();

            if (!isAdmin) {
                new CustomerMenu();
                return;
            }

            new AdminMenu();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private ActionListener searchActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = tfID.getText();
                User user = Database.getUser(id);

                if (user == null) {
                    JOptionPane.showMessageDialog(null, "Account not found", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                tfAddress.setText(user.address());
                tfEmailAddress.setText(user.email());
                tfPhone.setText(user.contact());
            }
        };
    }

    private ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = tfID.getText();

                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isAdmin) {
                    tryUpdate(BankSystem.currentUser);
                    return;
                }

                User user = Database.getUser(id);

                if (user == null) {
                    JOptionPane.showMessageDialog(null, "Account not found", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                tryUpdate(user);
            }
        };
    }
}

