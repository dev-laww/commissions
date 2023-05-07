/**
 * @author: tora
 */

package finalproject;

import finalproject.db.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMenu {
    JFrame frame = new JFrame();
    JButton miniStatement = new JButton();
    JButton deposit = new JButton();
    JButton withdraw = new JButton();
    JButton transfer = new JButton();
    JButton balance = new JButton();
    JButton exit = new JButton();
    ImageIcon image = new ImageIcon("pic6.png");
    JLabel label = new JLabel("", image, JLabel.CENTER);
    JPanel panel = new JPanel();

    UserMenu() {
        miniStatement.setBounds(60, 325, 380, 50);
        miniStatement.setText("VIEW MINISTATEMENT");
        miniStatement.setFont(new Font(null, Font.PLAIN, 25));
        miniStatement.setFocusable(false);

        deposit.setBounds(60, 40, 380, 50);
        deposit.setText("DEPOSIT");
        deposit.setFont(new Font(null, Font.PLAIN, 25));
        deposit.setFocusable(false);

        withdraw.setBounds(60, 115, 380, 50);
        withdraw.setText("WITHDRAW");
        withdraw.setFont(new Font(null, Font.PLAIN, 25));
        withdraw.setFocusable(false);

        transfer.setBounds(60, 185, 380, 50);
        transfer.setText("TRANSFER");
        transfer.setFont(new Font(null, Font.PLAIN, 25));
        transfer.setFocusable(false);

        balance.setBounds(60, 255, 380, 50);
        balance.setText("VIEW BALANCE");
        balance.setFont(new Font(null, Font.PLAIN, 25));
        balance.setFocusable(false);

        exit.setBounds(60, 395, 380, 50);
        exit.setText("EXIT");
        exit.setFont(new Font(null, Font.PLAIN, 25));
        exit.setFocusable(false);


        frame.setSize(500, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        label.add(exit);
        label.add(balance);
        label.add(deposit);
        label.add(withdraw);
        label.add(transfer);
        label.add(miniStatement);
        label.add(panel);
        label.setBounds(0, 0, 500, 550);


        frame.add(exit);
        frame.add(balance);
        frame.add(deposit);
        frame.add(withdraw);
        frame.add(transfer);
        frame.add(miniStatement);
        frame.add(panel);
        frame.add(label);
        frame.setVisible(true);

        // action listeners
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database.user = null;
                frame.dispose();
                new Login();
            }
        });

        balance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ViewBalance();
            }
        });

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Deposit();
            }
        });

        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new WithdrawFPage();
            }
        });

        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Transfer();
            }
        });

        miniStatement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MiniStatement();
            }
        });
    }
}
