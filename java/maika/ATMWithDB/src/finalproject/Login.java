/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author maikaordonez
 */
public class Login {
    
    JFrame frame = new JFrame();
    JButton enter = new JButton();
    JButton cancel = new JButton();
    JLabel accIDLabel = new JLabel();
    JLabel pinCodeLabel = new JLabel();
    JTextField accIdTF = new JTextField();
    JLabel atm = new JLabel();
    JPasswordField pinCodeTF = new JPasswordField();
    ImageIcon image = new ImageIcon("Login.jpg");
    JLabel background = new JLabel("", image, JLabel.CENTER);
    
    Login(){
    
        atm.setForeground(Color.WHITE);
        accIDLabel.setForeground(Color.WHITE);
        pinCodeLabel.setForeground(Color.WHITE);
        
        atm.setBounds(50, 120, 350,100);
        atm.setText("JJTM MACHINE");
        atm.setFont(new Font(null, Font.BOLD, 46));

        accIDLabel.setBounds(90, 230, 200, 35);
        accIDLabel.setText("ACCOUNT NUMBER");
        accIdTF.setBounds(88, 270, 250, 35);
        accIDLabel.setFont(new Font(null, Font.PLAIN, 20));

        pinCodeLabel.setBounds(90, 310, 200, 35);
        pinCodeLabel.setText("PIN CODE");
        pinCodeTF.setBounds(88, 350, 250, 35);
        pinCodeLabel.setFont(new Font(null, Font.PLAIN, 20));

        enter.setBounds(110, 400, 90, 30);
        enter.setText("ENTER");
        enter.setFocusable(false);

        cancel.setBounds(210, 400, 90, 30);
        cancel.setText("CLEAR");
        cancel.setFocusable(false);

        frame.add(atm);
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
        background.add(atm);
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
                frame.dispose();
                new AdminMenu();
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
    
    public static void main(String[] args) {
        Login log = new Login();
        log.start();
    }
    
    public void start() {
        frame.setVisible(true);
    }

}

