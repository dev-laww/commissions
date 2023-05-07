/**
 * @author: tora
 */

package finalproject;

import finalproject.db.Database;
import finalproject.db.User;

import javax.swing.*;
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
    ImageIcon image = new ImageIcon("pic9.png");
    JLabel label = new JLabel("", image, JLabel.CENTER);
    JLabel accountID = new JLabel("ACCOUNT NO:");
    JLabel transferID = new JLabel("TRANSFER TO:");
    JTextField accountIDField = new JTextField();
    JTextField receiverIDField = new JTextField();
    JButton point = new JButton(".");
    private final boolean isAdmin;
    JButton[] buttons = {zero, one, two, three, four, five, six, seven, eight, nine, point};

    Transfer(boolean isAdmin) {
        this.isAdmin = isAdmin;

        label1.setBounds(80, 0, 400, 150);
        label1.setText("TRANSFER MONEY");
        label1.setFont(new Font(null, Font.BOLD, 40));
        label1.setForeground(Color.WHITE);

        label2.setBounds(65, 60, 500, 150);
        label2.setText("ENTER AMOUNT YOU WANT TO TRANSFER");
        label2.setFont(new Font(null, Font.BOLD, 20));
        label2.setForeground(Color.WHITE);

        accountID.setBounds(65, 215, 400, 40);
        accountID.setFont(new Font(null, Font.BOLD, 25));
        accountID.setForeground(Color.WHITE);
        accountID.setBackground(Color.WHITE);

        accountIDField.setBounds(75, 260, 400, 40);
        accountIDField.setFont(new Font(null, Font.BOLD, 25));
        accountIDField.setForeground(Color.BLACK);
        accountIDField.setBackground(Color.WHITE);

        transferID.setBounds(65, isAdmin ? 305 : 215, 400, 40);
        transferID.setFont(new Font(null, Font.BOLD, 25));
        transferID.setForeground(Color.WHITE);
        transferID.setBackground(Color.WHITE);

        receiverIDField.setBounds(75, isAdmin ? 350 : 265, 400, 40);
        receiverIDField.setFont(new Font(null, Font.BOLD, 25));
        receiverIDField.setForeground(Color.BLACK);
        receiverIDField.setBackground(Color.WHITE);

        tf.setBounds(75, isAdmin ? 170 : 165, 400, 40);
        tf.setFont(new Font(null, Font.BOLD, 25));
        tf.setForeground(Color.BLACK);
        tf.setBackground(Color.WHITE);
        tf.setEditable(false);

        if (!isAdmin) {
            accountIDField.setVisible(false);
            accountID.setVisible(false);
        }

        exit.setBounds(295, isAdmin ? 420 : 330, 200, 40);
        exit.setFont(new Font(null, Font.BOLD, 15));
        exit.setForeground(Color.BLACK);
        exit.setBackground(Color.WHITE);

        enter.setBounds(60, isAdmin ? 420 : 330, 200, 40);
        enter.setFont(new Font(null, Font.BOLD, 15));
        enter.setForeground(Color.BLACK);
        enter.setBackground(Color.WHITE);

        point.setBounds(710, 420, 80, 80);
        point.setFont(new Font(null, Font.BOLD, 25));
        point.setForeground(Color.BLACK);
        point.setBackground(Color.WHITE);

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

        label.setBounds(0, 0, 1000, 550);
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
        label.add(point);
        label.add(transferID);
        label.add(accountID);
        label.add(accountIDField);
        label.add(receiverIDField);
        label.add(exit);
        label.add(enter);

        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 550);
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
                    new UserMenu();
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
        accountIDField.addActionListener(getActionListener());
        receiverIDField.addActionListener(getActionListener());
    }

    Transfer() {
        this(false);
    }

    private void tryTransfer(User u, String receiverID, double amount) {

        try {
            u.transfer(receiverID, amount);
            JOptionPane.showMessageDialog(null, "Transfer successful.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Transfer Failed!\n" + e.getMessage());
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

                if (receiverID.length() != 8 && isAdmin) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid account ID of receiver.");
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

                if (Double.parseDouble(amount.replace(",", "")) <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
                    tf.setText("");
                    return;
                }

                if (Double.parseDouble(amount.replace(",", "")) >= 25000.10) {
                    JOptionPane.showMessageDialog(null, "LIMIT REACH");
                    tf.setText("");
                    return;
                }

                User receiver = Database.getUser(receiverID);

                if (receiver == null) {
                    JOptionPane.showMessageDialog(null, "Receiver does not exist.");
                    return;
                }

                if (receiver.isLocked()) {
                    JOptionPane.showMessageDialog(null, "Receiver account is locked.");
                    return;
                }

                if (isAdmin) {
                    User user = Database.getUser(accountID);

                    if (user == null) {
                        JOptionPane.showMessageDialog(null, "Account ID does not exist.");
                        return;
                    }

                    if (user.id.equals(receiverID)) {
                        JOptionPane.showMessageDialog(null, "You cannot transfer money to the same user.");
                        return;
                    }

                    if (user.balance < Double.parseDouble(amount.replace(",", ""))) {
                        JOptionPane.showMessageDialog(null, "Insufficient funds.");
                        return;
                    }

                    if(user.isLocked()) {
                        JOptionPane.showMessageDialog(null, "Account is locked.");
                        return;
                    }

                    tryTransfer(user, receiverID, Double.parseDouble(amount));
                    frame.dispose();
                    new Receipt(user.id, true);
                    return;
                }

                if (BankSystem.currentUser.balance < Double.parseDouble(amount.replace(",", ""))) {
                    JOptionPane.showMessageDialog(null, "Insufficient funds.");
                    return;
                }

                if(BankSystem.currentUser.isLocked()) {
                    JOptionPane.showMessageDialog(null, "Account is locked.");
                    return;
                }

                if (BankSystem.currentUser.id.equals(receiverID)) {
                    JOptionPane.showMessageDialog(null, "You cannot transfer money to yourself.");
                    return;
                }

                tryTransfer(BankSystem.currentUser, receiverID, Double.parseDouble(amount));
                frame.dispose();
                new Receipt(BankSystem.currentUser.id, false);
            }
        };
    }
}