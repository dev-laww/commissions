/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontPage {
    JFrame frame = new JFrame();
    JButton tellerButton = new JButton();
    JButton customerButton = new JButton();
    ImageIcon image = new ImageIcon("FrontPage.jpg");
    JLabel label = new JLabel("", image, JLabel.CENTER);

    FrontPage() {
        tellerButton.setBounds(90, 350, 120, 40);
        tellerButton.setFocusable(false);
        tellerButton.setText("ADMIN");

        customerButton.setBounds(740, 350, 120, 40);
        customerButton.setFocusable(false);
        customerButton.setText("CUSTOMER");

        frame.add(label);
        frame.add(customerButton);
        frame.add(tellerButton);
        frame.setSize(940, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        //background image
        label.add(tellerButton);
        label.add(customerButton);
        label.setBounds(0, 0, 940, 700);

        // action listeners
        tellerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminLogin();
                frame.dispose();
            }
        });

        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerLogin();
                frame.dispose();
            }
        });
    }

    public void start() {
        frame.setVisible(true);
    }
}
