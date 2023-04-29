/**
 * @author: tora
 * @author: niku
 */

package finalproject;

import finalproject.db.Database;
import finalproject.db.User;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transfer {
    JFrame frame = new JFrame();
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JTextField tf = new JTextField();
    JButton exit = new JButton("CANCEL");
    JButton enter = new JButton("ENTER");
    JButton zero = new JButton("0");
    JButton one = new JButton("1");
    JButton two = new JButton("2");
    JButton three = new JButton("3");
    JButton four = new JButton("4");
    JButton five = new JButton("5");
    JButton six = new JButton("6");
    JButton seven = new JButton("7");
    JButton eight = new JButton("8");
    JButton nine = new JButton("9");
    JButton backspace = new JButton("<-");
    JButton clear = new JButton("CLEAR");
    ImageIcon image = new ImageIcon("pic7.jpeg");
    JLabel label = new JLabel("", image, JLabel.CENTER);
    JLabel accountID = new JLabel("Account ID:");
    JTextField accountIDField = new JTextField();
    JTextField receiverIDField = new JTextField();
    private final boolean isAdmin;
    JButton[] buttons = {zero, one, two, three, four, five, six, seven, eight, nine};

    Transfer(boolean isAdmin) {
        this.isAdmin = isAdmin;
        label1.setBounds(150, 60, 380, 100);
        label1.setText("DEPOSIT");
        label1.setFont(new Font(null, Font.BOLD, 60));
        label1.setForeground(Color.WHITE);

        label2.setBounds(65, 82, 500, 200);
        label2.setText("ENTER AMOUNT YOU WANT TO DEPOSIT");
        label2.setFont(new Font(null, Font.BOLD, 20));
        label2.setForeground(Color.WHITE);

        accountID.setBounds(75, 235, 400, 40);
        accountID.setFont(new Font(null, Font.BOLD, 25));
        accountID.setForeground(Color.BLACK);
        accountID.setBackground(Color.WHITE);

        accountIDField.setBounds(75, 275, 400, 40);
        accountIDField.setFont(new Font(null, Font.BOLD, 25));
        accountIDField.setForeground(Color.BLACK);
        accountIDField.setBackground(Color.WHITE);

        receiverIDField.setBounds(75, 330, 400, 40);
        receiverIDField.setFont(new Font(null, Font.BOLD, 25));
        receiverIDField.setForeground(Color.BLACK);
        receiverIDField.setBackground(Color.WHITE);

        tf.setBounds(75, 370, 400, 40);
        tf.setFont(new Font(null, Font.BOLD, 25));
        tf.setForeground(Color.BLACK);
        tf.setBackground(Color.WHITE);

        if (!isAdmin && BankSystem.currentUser != null) {
            accountIDField.setText(BankSystem.currentUser.id);
            accountIDField.setEditable(false);
        }

        exit.setBounds(295, 410, 200, 40);
        exit.setFont(new Font(null, Font.BOLD, 15));
        exit.setForeground(Color.BLACK);
        exit.setBackground(Color.WHITE);

        enter.setBounds(60, 410, 200, 40);
        enter.setFont(new Font(null, Font.BOLD, 15));
        enter.setForeground(Color.BLACK);
        enter.setBackground(Color.WHITE);

        zero.setBounds(710, 330, 80, 80);
        zero.setFont(new Font(null, Font.BOLD, 25));
        zero.setForeground(Color.BLACK);
        zero.setBackground(Color.WHITE);

        one.setBounds(610, 60, 80, 80);
        one.setFont(new Font(null, Font.BOLD, 25));
        one.setForeground(Color.BLACK);
        one.setBackground(Color.WHITE);

        two.setBounds(710, 60, 80, 80);
        two.setFont(new Font(null, Font.BOLD, 25));
        two.setForeground(Color.BLACK);
        two.setBackground(Color.WHITE);

        three.setBounds(810, 60, 80, 80);
        three.setFont(new Font(null, Font.BOLD, 25));
        three.setForeground(Color.BLACK);
        three.setBackground(Color.WHITE);

        four.setBounds(610, 150, 80, 80);
        four.setFont(new Font(null, Font.BOLD, 25));
        four.setForeground(Color.BLACK);
        four.setBackground(Color.WHITE);

        five.setBounds(710, 150, 80, 80);
        five.setFont(new Font(null, Font.BOLD, 25));
        five.setForeground(Color.BLACK);
        five.setBackground(Color.WHITE);

        six.setBounds(810, 150, 80, 80);
        six.setFont(new Font(null, Font.BOLD, 25));
        six.setForeground(Color.BLACK);
        six.setBackground(Color.WHITE);

        seven.setBounds(610, 240, 80, 80);
        seven.setFont(new Font(null, Font.BOLD, 25));
        seven.setForeground(Color.BLACK);
        seven.setBackground(Color.WHITE);

        eight.setBounds(710, 240, 80, 80);
        eight.setFont(new Font(null, Font.BOLD, 25));
        eight.setForeground(Color.BLACK);
        eight.setBackground(Color.WHITE);

        nine.setBounds(810, 240, 80, 80);
        nine.setFont(new Font(null, Font.BOLD, 25));
        nine.setForeground(Color.BLACK);
        nine.setBackground(Color.WHITE);

        clear.setBounds(610, 330, 80, 80);
        clear.setFont(new Font(null, Font.BOLD, 15));
        clear.setForeground(Color.BLACK);
        clear.setBackground(Color.WHITE);

        backspace.setBounds(810, 330, 80, 80);
        backspace.setFont(new Font(null, Font.BOLD, 20));
        backspace.setForeground(Color.BLACK);
        backspace.setBackground(Color.WHITE);

        label.setBounds(0, 0, 1000, 500);
        label.add(clear);
        label.add(backspace);
        label.add(zero);
        label.add(one);
        label.add(two);
        label.add(three);
        label.add(four);
        label.add(five);
        label.add(six);
        label.add(seven);
        label.add(eight);
        label.add(nine);
        label.add(label1);
        label.add(label2);
        label.add(tf);
        label.add(accountID);
        label.add(accountIDField);
        label.add(receiverIDField);
        label.add(exit);
        label.add(enter);

        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Transfer");
        frame.setSize(1000, 500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.WHITE);

        // action listeners
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

                if (!isAdmin) {
                    new CustomerMenu();
                    return;
                }
                new AdminMenu();
            }
        });

        for (JButton button : buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tf.setText(tf.getText() + button.getText());
                }
            });
        }

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.setText("");

                if (isAdmin) {
                    accountIDField.setText("");
                }
            }
        });

        backspace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = tf.getText();
                if (text.length() > 0) {
                    tf.setText(text.substring(0, text.length() - 1));
                }
            }
        });

        enter.addActionListener(getActionListener());
        tf.addActionListener(getActionListener());
        accountIDField.addActionListener(getActionListener());
        receiverIDField.addActionListener(getActionListener());
    }

    Transfer() {
        this(false);
    }

    private void tryTransfer(User u,String receiverID, double amount) {
        User receiver = Database.getUser(receiverID);

        if (receiver == null) {
            JOptionPane.showMessageDialog(null, "Receiver does not exist.");
            return;
        }

        try{
            u.transfer(receiverID, amount);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = tf.getText();
                String accountID = accountIDField.getText();
                String receiverID = receiverIDField.getText();

                if (amount.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter an amount.");
                    return;
                }

                if (accountID.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter an account ID.");
                    return;
                }

                if (receiverID.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a receiver ID.");
                    return;
                }

                if (accountID.equals(receiverID)) {
                    JOptionPane.showMessageDialog(null, "You cannot transfer money to yourself.");
                    return;
                }

                if (isAdmin) {
                    User user = Database.getUser(accountID);

                    if (user == null) {
                        JOptionPane.showMessageDialog(null, "Account ID does not exist.");
                        return;
                    }

                    tryTransfer(user, receiverID, Double.parseDouble(amount));
                    JOptionPane.showMessageDialog(null, "Transfer successful.");
                    frame.dispose();
                    new AdminMenu();
                    return;
                }

                tryTransfer(BankSystem.currentUser, receiverID, Double.parseDouble(amount));
                JOptionPane.showMessageDialog(null, "Transfer successful.");
                frame.dispose();
                new CustomerMenu();
            }
        };
    }
}