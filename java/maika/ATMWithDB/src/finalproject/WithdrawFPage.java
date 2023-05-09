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

public class WithdrawFPage {
    JFrame frame = new JFrame();
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JTextField tf = new JTextField();
    JButton exit = new JButton("CANCEL");
    JButton enter = new JButton("ENTER");
    JButton oneH = new JButton("100");
    JButton twoH = new JButton("200");
    JButton fiveH = new JButton("500");
    JButton oneK = new JButton("1000");
    JButton fiveK = new JButton("5000");
    JButton tenK = new JButton("10,000");
    JButton amount = new JButton("ENTER ANOTHER AMOUNT");
    ImageIcon image = new ImageIcon("pic9.png");
    JLabel label = new JLabel("", image, JLabel.CENTER);
    JLabel accountID = new JLabel("ACCOUNT ID:");
    JTextField accountIDField = new JTextField();
    JButton[] buttons = {oneH, twoH, fiveH, oneK, fiveK, tenK};
    private boolean isAdmin;

    WithdrawFPage(boolean isAdmin) {
        this.isAdmin = isAdmin;

        label1.setBounds(110, 60, 380, 100);
        label1.setText("WITHDRAW");
        label1.setFont(new Font(null, Font.BOLD, 60));
        label1.setForeground(Color.WHITE);

        label2.setBounds(55, 82, 500, 200);
        label2.setText("ENTER AMOUNT YOU WANT TO WITHDRAW");
        label2.setFont(new Font(null, Font.BOLD, 20));
        label2.setForeground(Color.WHITE);

        accountID.setBounds(65, 260, 400, 40);
        accountID.setFont(new Font(null, Font.BOLD, 25));
        accountID.setForeground(Color.BLACK);
        accountID.setBackground(Color.WHITE);

        accountIDField.setBounds(75, 310, 400, 40);
        accountIDField.setFont(new Font(null, Font.BOLD, 25));
        accountIDField.setForeground(Color.BLACK);
        accountIDField.setBackground(Color.WHITE);

        if (!isAdmin) {
            accountID.setVisible(false);
            accountIDField.setVisible(false);
        }

        tf.setBounds(75, isAdmin ? 210 : 235, 400, 40);
        tf.setFont(new Font(null, Font.BOLD, 25));
        tf.setEditable(false);
        tf.setForeground(Color.BLACK);
        tf.setBackground(Color.WHITE);

        exit.setBounds(295, isAdmin ? 390 : 330, 200, 40);
        exit.setFont(new Font(null, Font.BOLD, 15));
        exit.setForeground(Color.BLACK);
        exit.setBackground(Color.WHITE);

        enter.setBounds(60, isAdmin ? 390 : 330, 200, 40);
        enter.setFont(new Font(null, Font.BOLD, 15));
        enter.setForeground(Color.BLACK);
        enter.setBackground(Color.WHITE);

        oneH.setBounds(580, 60, 100, 100);
        oneH.setFont(new Font(null, Font.BOLD, 30));
        oneH.setForeground(Color.BLACK);
        oneH.setBackground(Color.WHITE);

        twoH.setBounds(710, 60, 100, 100);
        twoH.setFont(new Font(null, Font.BOLD, 30));
        twoH.setForeground(Color.BLACK);
        twoH.setBackground(Color.WHITE);

        fiveH.setBounds(840, 60, 100, 100);
        fiveH.setFont(new Font(null, Font.BOLD, 30));
        fiveH.setForeground(Color.BLACK);
        fiveH.setBackground(Color.WHITE);

        oneK.setBounds(580, 180, 100, 100);
        oneK.setFont(new Font(null, Font.BOLD, 30));
        oneK.setForeground(Color.BLACK);
        oneK.setBackground(Color.WHITE);

        fiveK.setBounds(710, 180, 100, 100);
        fiveK.setFont(new Font(null, Font.BOLD, 30));
        fiveK.setForeground(Color.BLACK);
        fiveK.setBackground(Color.WHITE);

        tenK.setBounds(840, 180, 100, 100);
        tenK.setFont(new Font(null, Font.BOLD, 20));
        tenK.setForeground(Color.BLACK);
        tenK.setBackground(Color.WHITE);

        amount.setBounds(580, 300, 360, 100);
        amount.setFont(new Font(null, Font.BOLD, 22));
        amount.setForeground(Color.BLACK);
        amount.setBackground(Color.WHITE);

        label.setBounds(0, 0, 1000, 500);
        label.add(accountID);
        label.add(accountIDField);
        label.add(accountIDField);
        label.add(oneK);
        label.add(twoH);
        label.add(oneH);
        label.add(fiveK);
        label.add(fiveH);
        label.add(tenK);
        label.add(amount);
        label.add(label1);
        label.add(label2);
        label.add(tf);
        label.add(exit);
        label.add(enter);

        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Withdraw");
        frame.setSize(1000, 500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.WHITE);

        // action listeners
        amount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Withdraw(isAdmin);
            }
        });

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
                    tf.setText(button.getText());
                }
            });
        }

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = tf.getText();
                String accountID = accountIDField.getText();

                if (amount.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter amount");
                    return;
                }

                if (accountID.isEmpty() && isAdmin) {
                    JOptionPane.showMessageDialog(null, "Please enter an account ID.");
                    return;
                }

                if (isAdmin) {
                    User user = Database.getUser(accountID);

                    if (user == null) {
                        JOptionPane.showMessageDialog(null, "Account does not exist.");
                        return;
                    }

                    if (!Withdraw.tryWithdraw(user, amount)) return;
                    frame.dispose();
                    new Receipt(user.id, true);
                    return;
                }

                if (!Withdraw.tryWithdraw(Database.user, amount)) return;
                frame.dispose();
                new Receipt(Database.user.id, false);
            }
        });
    }

    WithdrawFPage() {
        this(false);
    }
}
