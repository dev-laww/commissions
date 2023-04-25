/**
 * @author:  tora
 * @author:  niku
 */

package finalproject;

import finalproject.db.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

//CUSTOMER LOGIN

public class AdminLogin {
    JFrame frame = new JFrame();
    JButton enter = new JButton();
    JButton cancel = new JButton();
    JLabel accIDLabel = new JLabel();
    JLabel pinCodeLabel = new JLabel();
    JTextField accIdTF = new JTextField();
    JPasswordField pinCodeTF = new JPasswordField();
    ImageIcon image = new ImageIcon("Login.jpg");
    JLabel background = new JLabel("", image, JLabel.CENTER);

    AdminLogin() {

        accIDLabel.setForeground(Color.WHITE);
        pinCodeLabel.setForeground(Color.WHITE);

        accIDLabel.setBounds(80, 210, 200, 35);
        accIDLabel.setText("ACCOUNT ID");
        accIdTF.setBounds(220, 210, 150, 35);
        accIDLabel.setFont(new Font(null, Font.PLAIN, 20));

        pinCodeLabel.setBounds(80, 270, 200, 35);
        pinCodeLabel.setText("PIN CODE");
        pinCodeTF.setBounds(220, 270, 150, 35);
        pinCodeLabel.setFont(new Font(null, Font.PLAIN, 20));

        enter.setBounds(160, 320, 90, 30);
        enter.setText("ENTER");
        enter.setFocusable(false);

        cancel.setBounds(260, 320, 90, 30);
        cancel.setText("CANCEL");
        cancel.setFocusable(false);

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
                enterPressed();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new FrontPage().start();
            }
        });

        accIdTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed();
            }
        });

        pinCodeTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed();
            }
        });

    }

    public void enterPressed() {
        HashMap<String, String> admin = Database.admin;
        String userID = accIdTF.getText();
        String password = String.valueOf(pinCodeTF.getPassword());

        if (userID.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "PLEASE FILL IN ALL FIELDS");
            return;
        }

        if (!admin.containsKey(userID)) {
            JOptionPane.showMessageDialog(null, "USERNAME NOT FOUND");
            return;
        }

        if (!admin.get(userID).equals(password)) {
            JOptionPane.showMessageDialog(null, "WRONG USERNAME OR PASSWORD");
            return;
        }

        new AdminMenu();
        frame.dispose();
    }
}
