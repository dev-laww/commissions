package project_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    JLabel header = new JLabel("BANKING SYSTEM");
    JLabel header2 = new JLabel("LOG IN AS:");
    JButton adminBtn = new JButton("Admin");
    JButton customerBtn = new JButton("Customer");
    JLabel toRegister = new JLabel("Log in as Admin to open an Account!");

    public HomePage() {
        header.setBounds(300, 10, 390, 50);
        header.setFont(new Font("Stencil", 10, 25));
        this.add(header);

        header2.setBounds(340, 70, 290, 20);
        header2.setFont(new Font("Times New Roman", 8, 18));
        this.add(header2);

        toRegister.setBounds(210, 280, 390, 50);
        toRegister.setFont(new Font("Centaur", 10, 25));
        this.add(toRegister);


        adminBtn.setBounds(250, 170, 100, 70);
        this.add(adminBtn);
//        btnregister.addActionListener(this);

        customerBtn.setBounds(410, 170, 100, 70);
        this.add(customerBtn);
//        btnclear.addActionListener(this);


        this.setSize(800, 480);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Homepage");

        adminBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminLogin().setVisible(true);
                dispose();
            }
        });

        customerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerLogin().setVisible(true);
                dispose();
            }
        });
    }
}