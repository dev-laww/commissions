/**
 * @author:  tora
 * @author:  niku
 */

package finalproject;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

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
    JTextField tfAddress = new JTextField();
    JTextField tfID = new JTextField();
    JLabel pin = new JLabel("PINCODE:");
    JButton back = new JButton("BACK");
    JButton enter = new JButton("ENTER");

    ImageIcon image = new ImageIcon("pic6.jpg");
    JLabel label = new JLabel("", image, JLabel.CENTER);
    
    CreateAccount() {
        
        label.add(createAccLabel);
        label.add(name);
        label.add(tfname);
        label.add(address);
        label.add(tfAddress);
        label.add(emailAdd);
        label.add(tfEmailAdd);
        label.add(contactNo);
        label.add(tfContactNo);
        label.add(accountID);
        label.add(tfID);
        label.add(pin);
        label.add(tfPass);
        label.add(back);
        label.add(enter);




        //frame
        frame.add(label);
        frame.setSize(600, 500);
        frame.setTitle("Create Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        //label
        createAccLabel.setFont(new Font(null, Font.BOLD, 40));
        createAccLabel.setBounds(110, 18, 400, 50);
        createAccLabel.setForeground(Color.WHITE);
        
        
        name.setFont(new Font(null, Font.PLAIN, 20));
        name.setBounds(15, 95, 300, 40);
        name.setForeground(Color.WHITE);
        
        tfname.setBounds(170, 97, 380, 33);
        tfname.setFont(new Font(null, Font.BOLD, 15));
        
        address.setFont(new Font(null, Font.PLAIN, 20));
        address.setBounds(15, 145, 300, 40);
        address.setForeground(Color.WHITE);
        
        tfAddress.setBounds(170, 147, 380, 33);
        tfAddress.setFont(new Font(null, Font.BOLD, 15));
        
        emailAdd.setFont(new Font(null, Font.PLAIN, 20));
        emailAdd.setBounds(15, 195, 300, 40);
        emailAdd.setForeground(Color.WHITE);
        
        tfEmailAdd.setBounds(170, 197, 380, 33);
        tfEmailAdd.setFont(new Font(null, Font.BOLD, 15));
        
        contactNo.setFont(new Font(null, Font.PLAIN, 20));
        contactNo.setBounds(15, 245, 300, 40);
        contactNo.setForeground(Color.WHITE);
        
        tfContactNo.setBounds(170, 247, 380, 33);
        tfContactNo.setFont(new Font(null, Font.BOLD, 15));
        
        accountID.setFont(new Font(null, Font.PLAIN, 20));
        accountID.setBounds(15, 300, 300, 40);
        accountID.setForeground(Color.WHITE);
        
        tfID.setBounds(170, 302, 380, 33);
        tfID.setFont(new Font(null, Font.BOLD, 15));
        
        tfID.setText(generateAccountId());
        
        tfID.setEditable(false);
        
        pin.setFont(new Font(null, Font.PLAIN, 20));
        pin.setBounds(15, 355, 200, 40);
        pin.setForeground(Color.WHITE);
        
        tfPass.setBounds(170, 357, 380, 33);
        tfPass.setFont(new Font(null, Font.BOLD, 15));
        
        tfPass.setColumns(4);
        
        tfPass.setText("0000");
        
        
        //button
        back.setBounds(380, 410, 120, 30);
        back.setFocusable(false);
        
        
         enter.setBounds(240, 410, 120, 30);
        enter.setFocusable(false);

        // action listeners
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfname.setText("");
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

        tfID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed();
            }
        });

        tfPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed();
            }
        });

        tfAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed();
            }
        });

        tfname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed();
            }
        });

        tfEmailAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed();
            }
        });

        tfContactNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed();
            }
        });
    }

    public static String generateAccountId() {
        Random random = new Random();
        long randomLong = Math.abs(random.nextLong());
        return Long.toString(randomLong).substring(0, 12);
    }

    private void enterPressed() {
        ArrayList<Customer> customers = BankSystem.idAndPassword.getCustomerLoginInfo();
        String name = tfname.getText();
        String address = tfAddress.getText();
        String email = tfEmailAdd.getText();
        String contactNo = tfContactNo.getText();
        String id = tfID.getText();
        String pin = String.valueOf(tfPass.getPassword()).isEmpty() ? "0000" : String.valueOf(tfPass.getPassword());

        if (name.isEmpty() || address.isEmpty() || id.isEmpty() || email.isEmpty() || contactNo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields");
            return;
        }

        if (pin.length() != 4) {
            JOptionPane.showMessageDialog(null, "Pin code must be 4 digits");
            return;
        }

        boolean exists = false;
        for (Customer customer : customers) {
            if (customer.getAccountID().equals(id)) {
                exists = true;
                break;
            }
        }

        if (exists) {
            this.tfID.setText(generateAccountId());
            return;
        }

        try {
            Integer.parseInt(pin);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid pin code!");
            return;
        }

        Customer customer = new Customer(name, address, email, contactNo, id, pin);
        BankSystem.idAndPassword.addCustomer(customer);
        JOptionPane.showMessageDialog(null, "Account Created!");
        tfname.setText("");
        new AdminMenu();
        frame.dispose();
    }
} 
    

