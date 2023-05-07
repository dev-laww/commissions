/**
 * @author: tora
 */

package finalproject;

import finalproject.db.Database;
import finalproject.db.User;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccount {
    static JTextField tfname = new JTextField();//fist name
    static JTextField tfmname = new JTextField();
    static JTextField tflname = new JTextField();
    
    static JPasswordField tfPass = new JPasswordField();
    JFrame frame = new JFrame();
    JLabel createAccLabel = new JLabel("CREATE ACCOUNT");
    JLabel name = new JLabel("FIRST NAME:"); //first name
    JLabel mname = new JLabel("MIDDLE NAME:");
    JLabel lname = new JLabel("LAST NAME:");
    
    JLabel address = new JLabel("BARANGAY:"); //barangay
    JLabel municipality = new JLabel("MUNICIPALITY:");
    JLabel province = new JLabel("PROVINCE:");
    
    JLabel emailAdd = new JLabel("EMAIL:");
    JTextField tfEmailAdd = new JTextField();
    JLabel contactNo = new JLabel("CONTACT NO:");
    JTextField tfContactNo = new JTextField();
    JLabel accountID = new JLabel("ACCOUNT NO:");
    JLabel initialDeposit = new JLabel("INITIAL DEPOSIT:");
    JTextField tfInitialDeposit = new JTextField();
    
    JTextField tfAddress = new JTextField();//barangay
    JTextField tfmunicipality = new JTextField();
    JTextField tfprovince = new JTextField();
    
    JTextField tfID = new JTextField();
    JLabel pin = new JLabel("PINCODE:");
    JButton back = new JButton("BACK");
    JButton enter = new JButton("ENTER");
    ImageIcon image = new ImageIcon("pic9.png");
    JLabel label = new JLabel("", image, JLabel.CENTER);

    CreateAccount() {
//        label.add(accountID); remove nyo na to since di accurate yung account number
        label.add(createAccLabel);
        
        label.add(name);
        label.add(mname);
        label.add(lname);
        
        label.add(tfname);
        label.add(tflname);
        label.add(tfmname);
        
        label.add(address);
        label.add(municipality);
        label.add(province);       
        
        label.add(tfAddress);
        label.add(tfmunicipality);
        label.add(tfprovince);
        
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
        frame.setSize(650, 750);
        frame.setTitle("Create Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        //label
        createAccLabel.setFont(new Font(null, Font.BOLD, 40));
        createAccLabel.setBounds(25, 22, 400, 50);
        createAccLabel.setForeground(Color.WHITE);

        accountID.setFont(new Font(null, Font.PLAIN, 20));
        accountID.setBounds(15, 95, 300, 40);
        accountID.setForeground(Color.WHITE);

        tfID.setBounds(195, 97, 365, 33);
        tfID.setFont(new Font(null, Font.BOLD, 15));
        tfID.setText(Database.getLastUserID());
        tfID.setEditable(false);

        name.setFont(new Font(null, Font.PLAIN, 20));
        name.setBounds(15, 145, 300, 40);
        name.setForeground(Color.WHITE);

        tfname.setBounds(195, 147, 365, 33);
        tfname.setFont(new Font(null, Font.BOLD, 15));
        
        mname.setFont(new Font(null, Font.PLAIN, 20));
        mname.setBounds(15, 195, 300, 40);
        mname.setForeground(Color.WHITE);

        tfmname.setBounds(195, 197, 365, 33);
        tfmname.setFont(new Font(null, Font.BOLD, 15));
        
        lname.setFont(new Font(null, Font.PLAIN, 20));
        lname.setBounds(15, 245, 300, 40);
        lname.setForeground(Color.WHITE);

        tflname.setBounds(195, 247, 365, 33);
        tflname.setFont(new Font(null, Font.BOLD, 15));

        address.setFont(new Font(null, Font.PLAIN, 20));
        address.setBounds(15, 300, 300, 40);
        address.setForeground(Color.WHITE);

        tfAddress.setBounds(195, 302, 365, 33);
        tfAddress.setFont(new Font(null, Font.BOLD, 15));
        
        municipality.setFont(new Font(null, Font.PLAIN, 20));
        municipality.setBounds(15, 355, 300, 40);
        municipality.setForeground(Color.WHITE);

        tfmunicipality.setBounds(195, 357, 365, 33);
        tfmunicipality.setFont(new Font(null, Font.BOLD, 15));
        
        province.setFont(new Font(null, Font.PLAIN, 20));
        province.setBounds(15, 405, 300, 40);
        province.setForeground(Color.WHITE);

        tfprovince.setBounds(195, 407, 365, 33);
        tfprovince.setFont(new Font(null, Font.BOLD, 15));

        emailAdd.setFont(new Font(null, Font.PLAIN, 20));
        emailAdd.setBounds(15, 455, 300, 40);
        emailAdd.setForeground(Color.WHITE);

        tfEmailAdd.setBounds(195, 457, 365, 33);
        tfEmailAdd.setFont(new Font(null, Font.BOLD, 15));

        contactNo.setFont(new Font(null, Font.PLAIN, 20));
        contactNo.setBounds(15, 510, 300, 40);
        contactNo.setForeground(Color.WHITE);

        tfContactNo.setBounds(195, 512, 365, 33);
        tfContactNo.setFont(new Font(null, Font.BOLD, 15));

        initialDeposit.setFont(new Font(null, Font.PLAIN, 20));
        initialDeposit.setBounds(15, 565, 300, 40);
        initialDeposit.setForeground(Color.WHITE);

        tfInitialDeposit.setBounds(195, 567, 365, 33);
        tfInitialDeposit.setFont(new Font(null, Font.BOLD, 15));
        tfInitialDeposit.setText("5000.00");

        pin.setFont(new Font(null, Font.PLAIN, 20));
        pin.setBounds(15, 615, 200, 40);
        pin.setForeground(Color.WHITE);

        tfPass.setBounds(195, 617, 365, 33);
        tfPass.setFont(new Font(null, Font.BOLD, 15));

        tfPass.setColumns(4);
        tfPass.setText("0000");
        tfPass.setEditable(false);

        //button
        back.setBounds(380, 665, 120, 30);
        back.setFocusable(false);

        enter.setBounds(240, 665, 120, 30);
        enter.setFocusable(false);

        // action listeners
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminMenu();
            }
        });

        enter.addActionListener(getActionListener());
        tfname.addActionListener(getActionListener());
        tfAddress.addActionListener(getActionListener());
        tfEmailAdd.addActionListener(getActionListener());
        tfContactNo.addActionListener(getActionListener());
        tfInitialDeposit.addActionListener(getActionListener());
        tfPass.addActionListener(getActionListener());
    }

    private ActionListener getActionListener() {
        return new ActionListener() {
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
                    User user = new User(name, email, contactNo, address, pin, deposit, "active");
                    Database.addUser(user);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error adding user: " + ex.getMessage());
                    return;
                }

                JOptionPane.showMessageDialog(null, "Account Created!");
                tfname.setText("");
                tflname.setText("");
                tfmname.setText("");
                new AdminMenu();
                frame.dispose();
            }
        };
    }
} 
    

