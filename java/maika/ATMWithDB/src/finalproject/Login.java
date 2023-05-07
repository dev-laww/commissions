/**
 * @author: tora
 */

package finalproject;

import finalproject.db.Database;
import finalproject.db.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author maikaordonez
 */
public class Login {
    int attempts = 0;
    JFrame frame = new JFrame();
    JButton enter = new JButton();
    JButton clear = new JButton();
    JButton exit = new JButton();
    JLabel accIDLabel = new JLabel();
    JLabel pinCodeLabel = new JLabel();
    JTextField accIdTF = new JTextField();
    JLabel atm = new JLabel();
    JPasswordField pinCodeTF = new JPasswordField();
    ImageIcon image = new ImageIcon("LOGIN.jpg");
    JLabel background = new JLabel("", image, JLabel.CENTER);

    Login() {
        atm.setForeground(Color.WHITE);
        accIDLabel.setForeground(Color.WHITE);
        pinCodeLabel.setForeground(Color.WHITE);

        atm.setBounds(50, 120, 350, 100);
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

        enter.setBounds(120, 400, 90, 30);
        enter.setText("ENTER");
        enter.setFocusable(false);

        clear.setBounds(220, 400, 90, 30);
        clear.setText("CLEAR");
        clear.setFocusable(false);

        frame.add(atm);
        frame.add(background);
        frame.add(clear);
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
        background.add(clear);
        background.add(accIdTF);
        background.add(pinCodeTF);
        background.setBounds(0, 0, 1000, 575);

        frame.setVisible(true);

        // action listeners
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enter();
            }
        });

        accIdTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enter();
            }
        });

        pinCodeTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enter();
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accIdTF.setText("");
                pinCodeTF.setText("");
            }
        });
    }

    public static void start() {
        new Login();
    }

    private void enter() {
        String accID = accIdTF.getText();
        String pinCode = String.valueOf(pinCodeTF.getPassword());

        if (accID.isEmpty() || pinCode.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields!");
            return;
        }

        if (Database.admin.containsKey(accID)) {
            if (!Database.admin.get(accID).equals(pinCode)) {
                JOptionPane.showMessageDialog(null, "Incorrect password!");
                return;
            }

            frame.dispose();
            new AdminMenu();
            return;
        }

        try {
            Long.parseLong(accID);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Account ID must be a number!");
            return;
        }

        try {
            Integer.parseInt(pinCode);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Pin Code must be a number!");
            return;
        }

        if (pinCode.length() != 4) {
            JOptionPane.showMessageDialog(null, "Pin Code must be 4 digits!");
            return;
        }

        if (accID.length() != 8) {
            JOptionPane.showMessageDialog(null, "Account ID must be 8 digits!");
            return;
        }

        User user = Database.getUser(accID);

        if (user == null) {
            JOptionPane.showMessageDialog(null, "Account ID does not exist!");
            return;
        }

        if (user.isLocked()) {
            JOptionPane.showMessageDialog(null, "Account is locked!");
            return;
        }

//        if (!user.pin().equals("0000")) {
//            JOptionPane.showMessageDialog(null, "Please change pin to your default pin code!");
//            new Login();
//            return;            
//            }

        if (!user.checkPin(pinCode)) {
            JOptionPane.showMessageDialog(null, "Incorrect Pin Code!");
            attempts++;
            if (attempts == 3) {
                JOptionPane.showMessageDialog(null, "Too many attempts! Account locked!");
                user.lock();
                accIdTF.setText("");
                pinCodeTF.setText("");
            }
            return;
        }

        if (user.pin().equals("0000")) {
            JOptionPane.showMessageDialog(null, "Please change your pin code!");
            frame.dispose();

            JPasswordField passwordField = new JPasswordField();
            int result = JOptionPane.showConfirmDialog(null, passwordField, "Enter new pin:", JOptionPane.OK_CANCEL_OPTION);

            if (!(result == JOptionPane.OK_OPTION)) {
                JOptionPane.showMessageDialog(null, "Cancelled");
                new Login();
                return;
            }

            String newPin = String.valueOf(passwordField.getPassword());

            if (newPin.length() != 4) {
                JOptionPane.showMessageDialog(null, "New pin code must be 4 digits!");
                new Login();
                return;
            }

            if (newPin.equals("0000")) {
                JOptionPane.showMessageDialog(null, "Please enter a different pin code.");
                new Login();
                return;
            }

            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to change your default pin with this new pin?", "Confirm", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                user.updatePin(newPin);

                if (!Database.saveUser(user)) return;

                JOptionPane.showMessageDialog(null, "Pin code changed successfully!");

                frame.dispose();
                Database.user = user; // eto yung nakalimutan nyo gawin kaya may error hahaha
                new UserMenu();
                return;

            }

            new Login();
            return;
        }

        frame.dispose();
        Database.user = user;
        new UserMenu();
    }
}

