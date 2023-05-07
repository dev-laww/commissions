/**
 * @author: tora
 */

package finalproject;

import finalproject.db.Database;
import finalproject.db.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateAccount {
    JFrame frame = new JFrame();
    ImageIcon image = new ImageIcon("pic6.png");
    JLabel label = new JLabel("", image, JLabel.CENTER);
    JButton backButton = new JButton("BACK");
    JButton updateButton = new JButton("UPDATE");
    JLabel accountIDLabel = new JLabel();
    JLabel accountNameLabel = new JLabel();
    JLabel barangay = new JLabel();
    JTextField tfBarangay = new JTextField();
    JLabel municipality = new JLabel();
    JTextField tfMunicipality = new JTextField();
    JLabel province = new JLabel();
    JTextField tfProvince = new JTextField();
    JLabel emailAddress = new JLabel();
    JTextField tfEmailAddress = new JTextField();
    JLabel contact = new JLabel();
    JTextField tfContact = new JTextField();
    JLabel label1 = new JLabel("UPDATE ACCOUNT");
    JTextField tfID = new JTextField();
    JButton btnSearch = new JButton("SEARCH");
    private final boolean isAdmin;

    UpdateAccount(boolean isAdmin) {
        this.isAdmin = isAdmin;

        label1.setFont(new Font(null, Font.BOLD, 40));
        label1.setBounds(110, 18, 400, 50);
        label1.setForeground(Color.WHITE);

        backButton.setBounds(30, 310, 90, 30);
        backButton.setFocusable(false);

        updateButton.setBounds(450, 310, 90, 30);
        updateButton.setFocusable(false);

        btnSearch.setBounds(350, 270, 90, 30);
        btnSearch.setFocusable(false);
        btnSearch.setVisible(isAdmin);

        accountIDLabel.setBounds(30, 80, 150, 30);
        accountIDLabel.setText(isAdmin ? "Account NO: " : "Customer ID: ");
        accountIDLabel.setFont(new Font(null, Font.BOLD, 15));
        accountIDLabel.setForeground(Color.WHITE);

        tfID.setBounds(150, 80, 400, 30);
        tfID.setFont(new Font(null, Font.BOLD, 15));
        tfID.setEditable(isAdmin);
        tfID.setText(BankSystem.currentUser == null ? "" : BankSystem.currentUser.id);
        tfID.setForeground(Color.BLACK);

        accountNameLabel.setBounds(30, 120, 300, 30);
        accountNameLabel.setText(BankSystem.currentUser == null ? "Account Name: " : "Customer Name: " + BankSystem.currentUser.name());
        accountNameLabel.setFont(new Font(null, Font.BOLD, 15));
        accountNameLabel.setForeground(Color.WHITE);

        barangay.setText("Address:");
        barangay.setBounds(30, 155, 100, 30);
        barangay.setForeground(Color.WHITE);
        barangay.setFont(new Font(null, Font.BOLD, 15));

        tfBarangay.setBounds(150, 153, 400, 30);
        tfBarangay.setFont(new Font(null, Font.BOLD, 15));
        tfBarangay.setText(BankSystem.currentUser == null ? "" : BankSystem.currentUser.address());

        municipality.setText("Municipality:");
        municipality.setBounds(30, 186, 200, 30);
        municipality.setForeground(Color.WHITE);
        municipality.setFont(new Font(null, Font.BOLD, 15));

        tfMunicipality.setBounds(150, 185, 400, 30);
        tfMunicipality.setFont(new Font(null, Font.BOLD, 15));
        tfMunicipality.setText(BankSystem.currentUser == null ? "" : BankSystem.currentUser.municipality());

        province.setText("Province:");
        province.setBounds(30, 217, 100, 30);
        province.setForeground(Color.WHITE);
        province.setFont(new Font(null, Font.BOLD, 15));

        tfProvince.setBounds(150, 215, 400, 30);
        tfProvince.setFont(new Font(null, Font.BOLD, 15));
        tfProvince.setText(BankSystem.currentUser == null ? "" : BankSystem.currentUser.province());

        emailAddress.setText("Email:");
        emailAddress.setBounds(30, 240, 100, 30);
        emailAddress.setForeground(Color.WHITE);
        emailAddress.setFont(new Font(null, Font.BOLD, 15));

        tfEmailAddress.setBounds(150, 243, 400, 30);
        tfEmailAddress.setFont(new Font(null, Font.BOLD, 15));
        tfEmailAddress.setText(BankSystem.currentUser == null ? "" : BankSystem.currentUser.email());

        contact.setText("Contact:");
        contact.setBounds(30, 270, 100, 30);
        contact.setForeground(Color.WHITE);
        contact.setFont(new Font(null, Font.BOLD, 15));

        tfContact.setBounds(150, 273, 400, 30);
        tfContact.setFont(new Font(null, Font.BOLD, 15));
        tfContact.setText(BankSystem.currentUser == null ? "" : BankSystem.currentUser.contact());

        label.add(label1);
        label.add(accountIDLabel);
        label.add(tfID);
        label.add(accountNameLabel);
        label.add(barangay);
        label.add(tfBarangay);
        label.add(municipality);
        label.add(tfMunicipality);
        label.add(province);
        label.add(tfProvince);
        label.add(emailAddress);
        label.add(tfEmailAddress);
        label.add(contact);
        label.add(tfContact);
        label.add(backButton);
        label.add(updateButton);
        label.add(btnSearch);
        label.setBounds(0, 0, 600, 350);

        frame.add(label);
        frame.setSize(600, 430);
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
                    new UserMenu();
                    return;
                }

                new AdminMenu();
            }
        });

        btnSearch.addActionListener(searchActionListener());
        updateButton.addActionListener(getActionListener());
        tfID.addActionListener(searchActionListener());
        tfBarangay.addActionListener(getActionListener());
        tfEmailAddress.addActionListener(getActionListener());
        tfContact.addActionListener(getActionListener());
    }

    UpdateAccount() {
        this(false);
    }

    private void tryUpdate(User u) {
        String barangay = tfBarangay.getText();
        String municipality = tfMunicipality.getText();
        String province = tfProvince.getText();
        String email = tfEmailAddress.getText();
        String phone = tfContact.getText();

        if (barangay.isEmpty() || municipality.isEmpty() || province.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill up all fields", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (u.barangay().equals(barangay) && u.municipality().equals(municipality) && u.province().equals(province) && u.email().equals(email) && u.contact().equals(phone)) {
            JOptionPane.showMessageDialog(null, "No changes made", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        u.updateDetails(barangay, municipality, province, email, phone);

        if (!Database.saveUser(u)) return;

        JOptionPane.showMessageDialog(null, "Account updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();

        if (!isAdmin) {
            new UserMenu();
            return;
        }

        new AdminMenu();
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

                accountNameLabel.setText("Account Name: " + user.name());
                tfBarangay.setText(user.barangay());
                tfMunicipality.setText(user.municipality());
                tfProvince.setText(user.province());
                tfEmailAddress.setText(user.email());
                tfContact.setText(user.contact());
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

