/**
 * @author: tora
 * @author: niku
 */

package finalproject;

import finalproject.db.Database;
import finalproject.db.User;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

public class CreateAccount {
    static JTextField tfname = new JTextField();
    static JPasswordField tfPass = new JPasswordField();
    JFrame frame = new JFrame();
    JLabel createAccLabel = new JLabel("CREATE ACCOUNT");
    JLabel name = new JLabel("NAME:");
    JLabel address = new JLabel("ADDRESS:");
    JLabel emailAdd = new JLabel("EMAIL:");
    JTextField tfEmailAdd = new JTextField();
    JLabel contactNo = new JLabel("CONTACT NO:");
    JTextField tfContactNo = new JTextField();
    JLabel accountID = new JLabel("ACCOUNT ID:");
    JLabel initialDeposit = new JLabel("INITIAL DEPOSIT:");
    JTextField tfInitialDeposit = new JTextField();
    JTextField tfAddress = new JTextField();
    JTextField tfID = new JTextField();
    JLabel pin = new JLabel("PINCODE:");
    JButton back = new JButton("BACK");
    JButton enter = new JButton("ENTER");
    ImageIcon image = new ImageIcon("pic6.jpg");
    JLabel label = new JLabel("", image, JLabel.CENTER);

    CreateAccount() {
        label.add(accountID);
        label.add(createAccLabel);
        label.add(name);
        label.add(tfname);
        label.add(address);
        label.add(tfAddress);
        label.add(emailAdd);
        label.add(tfEmailAdd);
        label.add(contactNo);
        label.add(tfContactNo);
        label.add(tfID);
        label.add(initialDeposit);
        label.add(tfInitialDeposit);
        label.add(pin);
        label.add(tfPass);
        label.add(back);
        label.add(enter);

        //frame
        frame.add(label);
        frame.setSize(600, 600);
        frame.setTitle("Create Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        //label
        createAccLabel.setFont(new Font(null, Font.BOLD, 40));
        createAccLabel.setBounds(110, 18, 400, 50);
        createAccLabel.setForeground(Color.WHITE);

        accountID.setFont(new Font(null, Font.PLAIN, 20));
        accountID.setBounds(15, 95, 300, 40);
        accountID.setForeground(Color.WHITE);

        tfID.setBounds(170, 97, 380, 33);
        tfID.setFont(new Font(null, Font.BOLD, 15));
        tfID.setText(generateAccountId());
        tfID.setEditable(false);

        name.setFont(new Font(null, Font.PLAIN, 20));
        name.setBounds(15, 145, 300, 40);
        name.setForeground(Color.WHITE);

        tfname.setBounds(170, 147, 380, 33);
        tfname.setFont(new Font(null, Font.BOLD, 15));

        address.setFont(new Font(null, Font.PLAIN, 20));
        address.setBounds(15, 195, 300, 40);
        address.setForeground(Color.WHITE);

        tfAddress.setBounds(170, 197, 380, 33);
        tfAddress.setFont(new Font(null, Font.BOLD, 15));

        emailAdd.setFont(new Font(null, Font.PLAIN, 20));
        emailAdd.setBounds(15, 245, 300, 40);
        emailAdd.setForeground(Color.WHITE);

        tfEmailAdd.setBounds(170, 247, 380, 33);
        tfEmailAdd.setFont(new Font(null, Font.BOLD, 15));

        contactNo.setFont(new Font(null, Font.PLAIN, 20));
        contactNo.setBounds(15, 300, 300, 40);
        contactNo.setForeground(Color.WHITE);

        tfContactNo.setBounds(170, 302, 380, 33);
        tfContactNo.setFont(new Font(null, Font.BOLD, 15));

        initialDeposit.setFont(new Font(null, Font.PLAIN, 20));
        initialDeposit.setBounds(15, 355, 300, 40);
        initialDeposit.setForeground(Color.WHITE);

        tfInitialDeposit.setBounds(185, 357, 365, 33);
        tfInitialDeposit.setFont(new Font(null, Font.BOLD, 15));
        tfInitialDeposit.setText("5000.00");

        pin.setFont(new Font(null, Font.PLAIN, 20));
        pin.setBounds(15, 405, 200, 40);
        pin.setForeground(Color.WHITE);

        tfPass.setBounds(170, 407, 380, 33);
        tfPass.setFont(new Font(null, Font.BOLD, 15));

        tfPass.setColumns(4);
        tfPass.setText("0000");

        //button
        back.setBounds(380, 475, 120, 30);
        back.setFocusable(false);

        enter.setBounds(240, 475, 120, 30);
        enter.setFocusable(false);

        // action listeners
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminMenu();
            }
        });

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String initialDeposit = tfInitialDeposit.getText();
                String name = tfname.getText();
                String address = tfAddress.getText();
                String email = tfEmailAdd.getText();
                String contactNo = tfContactNo.getText();
                String id = tfID.getText();
                String pin = String.valueOf(tfPass.getPassword());
                double deposit;

                if (name.isEmpty() || address.isEmpty() || email.isEmpty() || contactNo.isEmpty() || id.isEmpty() || pin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill up all the fields");
                    return;
                }

                try {
                    Integer.parseInt(pin);
                    deposit = Double.parseDouble(initialDeposit);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input");
                    return;
                }

                if (deposit < 5000) {
                    JOptionPane.showMessageDialog(null, "Initial deposit must be at least 5000.00");
                    return;
                }

                try {
                    User user = new User(id, name, email, contactNo, address, pin, deposit, "active");
                    Database.addUser(user);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error adding user: " + ex.getMessage());
                    return;
                }

                JOptionPane.showMessageDialog(null, "Account Created!");
                tfname.setText("");
                new AdminMenu();
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        new CreateAccount();
    }

    private String generateAccountId() {
        ArrayList<User> users = Database.users;

        if (users.size() == 0) {
            return "10000000";
        }

        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.id.compareTo(o2.id);
            }
        });

        String lastId = users.get(users.size() - 1).id;
        int id = Integer.parseInt(lastId) + 1;

        return String.valueOf(id);
    }
} 
    

