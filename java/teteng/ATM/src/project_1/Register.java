package project_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class Register extends JFrame {
    JLabel header = new JLabel("BANKING SYSTEM");
    JLabel header2 = new JLabel("REGISTRATION");
    JLabel header3 = new JLabel("Enter your full name and contact details");
    JLabel fName = new JLabel("First Name:");
    JTextField txtFName = new JTextField();
    JLabel lName = new JLabel("Last name:");
    JTextField txtLName = new JTextField();
    JLabel lbLBranch = new JLabel("Branch:");
    String[] branchCHC = {"Baliuag", "Bustos", "San Rafael", "Angat"};
    JComboBox<String> cmbBranch = new JComboBox<>(branchCHC);
    JLabel age = new JLabel("Age:");
    JTextField txtAge = new JTextField();
    JLabel sex = new JLabel("Sex:");
    JRadioButton rbMale = new JRadioButton("Male");
    JRadioButton rbFemale = new JRadioButton("Female");
    ButtonGroup bg1 = new ButtonGroup();
    JLabel bDate = new JLabel("Birthdate:");
    JComboBox<Integer> dayBox = new JComboBox<>();
    JComboBox<String> monthBox = new JComboBox<>();
    JComboBox<Integer> yearBox = new JComboBox<>();
    JLabel mobileNo = new JLabel("Personal Mobile Number:");
    JTextField txtMobileNo = new JTextField();
    JLabel emailAdd = new JLabel("Personal Email Address:");
    JTextField txtEmailAdd = new JTextField();
    JLabel accountID = new JLabel("Account ID");
    JLabel txtAccID = new JLabel();
    JLabel confirmPass = new JLabel("Confirm Password:");
    JPasswordField txtConfirmPass = new JPasswordField();
    JLabel pass = new JLabel("Password:");
    JPasswordField txtPass = new JPasswordField();
    JButton btnRegister = new JButton("Register");
    JButton btnClear = new JButton("Clear");
    JButton btnBack = new JButton("Back");

    public Register() {
        header.setBounds(300, 10, 400, 50);
        header.setFont(new Font("Stencil", 10, 25));
        this.add(header);

        header2.setBounds(340, 50, 290, 20);
        header2.setFont(new Font("Times New Roman", 8, 18));
        this.add(header2);

        header3.setBounds(140, 80, 290, 20);
        this.add(header3);

        fName.setBounds(140, 98, 350, 40);
        this.add(fName);
        txtFName.setBounds(140, 130, 190, 20);
        this.add(txtFName);

        lName.setBounds(140, 148, 350, 40);
        this.add(lName);
        txtLName.setBounds(140, 180, 190, 20);
        this.add(txtLName);

        lbLBranch.setBounds(140, 198, 350, 40);
        this.add(lbLBranch);
        cmbBranch.setBounds(140, 230, 110, 19);
        this.add(cmbBranch);

        age.setBounds(265, 198, 350, 40);
        this.add(age);
        txtAge.setBounds(265, 230, 65, 20);
        this.add(txtAge);

        sex.setBounds(140, 298, 350, 40);
        this.add(sex);
        rbMale.setBounds(177, 307, 55, 25);
        this.add(rbMale);
        rbFemale.setBounds(233, 307, 80, 25);
        this.add(rbFemale);
        bg1.add(rbMale);
        bg1.add(rbFemale);

        bDate.setBounds(140, 248, 350, 40);
        this.add(bDate);

        dayBox.setBounds(140, 280, 38, 20);
        dayBox.setMaximumRowCount(5);
        monthBox.setBounds(182, 280, 87, 20);
        monthBox.setMaximumRowCount(5);
        yearBox.setBounds(273, 280, 55, 20);
        yearBox.setMaximumRowCount(5);

        for (int i = 1; i <= 31; i++) {
            dayBox.addItem(i);
        }

        String[] months = {"January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November", "December"};

        for (String month : months) {
            monthBox.addItem(month);
        }

        int year = 1940;
        for (int i = year; i <= year + 100; i++) {
            yearBox.addItem(i);
        }

        this.add(dayBox);
        this.add(monthBox);
        this.add(yearBox);

        mobileNo.setBounds(460, 98, 370, 40);
        this.add(mobileNo);
        txtMobileNo.setBounds(460, 130, 190, 20);
        this.add(txtMobileNo);

        emailAdd.setBounds(460, 148, 350, 40);
        this.add(emailAdd);
        txtEmailAdd.setBounds(460, 180, 190, 20);
        this.add(txtEmailAdd);

        accountID.setBounds(460, 198, 350, 40);
        this.add(accountID);
        txtAccID.setBounds(460, 230, 190, 20);
        txtAccID.setText(generateAccountId());
        this.add(txtAccID);

        pass.setBounds(460, 248, 350, 40);
        this.add(pass);
        txtPass.setBounds(460, 280, 190, 20);
        this.add(txtPass);

        confirmPass.setBounds(460, 298, 350, 40);
        this.add(confirmPass);
        txtConfirmPass.setBounds(460, 330, 190, 20);
        this.add(txtConfirmPass);

        btnRegister.setBounds(625, 390, 100, 20);
        this.add(btnRegister);

        btnClear.setBounds(505, 390, 100, 20);
        this.add(btnClear);

        btnBack.setBounds(385, 390, 100, 20);
        this.add(btnBack);

        this.setSize(800, 480);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Registration");

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtFName.setText("");
                txtLName.setText("");
                txtAge.setText("");
                txtMobileNo.setText("");
                txtEmailAdd.setText("");
                txtPass.setText("");
                txtConfirmPass.setText("");
                cmbBranch.setSelectedIndex(0);
                dayBox.setSelectedIndex(0);
                monthBox.setSelectedIndex(0);
                yearBox.setSelectedIndex(0);
                bg1.clearSelection();
            }
        });

        txtFName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        txtLName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        txtAge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        txtMobileNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        txtEmailAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        txtPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        txtConfirmPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminPanel().setVisible(true);
                dispose();
            }
        });
    }

    public static String generateAccountId() {
        Random random = new Random();
        long randomLong = Math.abs(random.nextLong());
        return Long.toString(randomLong).substring(0, 12);
    }

    private void action() {
        BankingSystem.data.getCustomers().forEach(System.out::println);

        String firstName = txtFName.getText();
        String lastName = txtLName.getText();
        String name = firstName + " " + lastName;
        String email = txtEmailAdd.getText();
        String contactNo = txtMobileNo.getText();
        String pin = String.valueOf(txtPass.getPassword());
        String confirmPin = String.valueOf(txtConfirmPass.getPassword());

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || contactNo.isEmpty() || txtAge.getText().isEmpty() || pin.isEmpty() || confirmPin.isEmpty() || !rbMale.isSelected() && !rbFemale.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please fill up all the fields!");
            return;
        }

        if (!pin.equals(confirmPin)) {
            JOptionPane.showMessageDialog(null, "Password does not match!");
            return;
        }

        if (pin.length() < 4) {
            JOptionPane.showMessageDialog(null, "Password must be at least 6 characters!");
            return;
        }

        try {
            int age = Integer.parseInt(txtAge.getText());
            if (age < 18) {
                JOptionPane.showMessageDialog(null, "You must be 18 years old and above to register!");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid age!");
            return;
        }

        if (contactNo.length() < 11) {
            JOptionPane.showMessageDialog(null, "Invalid mobile number!");
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(null, "Invalid email address!");
            return;
        }

        Customer customer = new Customer(name, email, contactNo, txtAccID.getText(), pin);
        BankingSystem.data.addCustomer(customer);
        JOptionPane.showMessageDialog(null, "Registration successful!");
        new AdminPanel().setVisible(true);
        dispose();
    }
}