/**
 * @author: tora
 */

package finalproject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu {
    JFrame frame = new JFrame();
    JButton createAcc = new JButton();
    JButton updateAcc = new JButton();
    JButton deleteAcc = new JButton();
    JButton searchAcc = new JButton();
    JButton withdraw = new JButton();
    JButton transfer = new JButton();
    JButton deposit = new JButton();
    JButton locked = new JButton();
    JButton exit = new JButton();
    ImageIcon image = new ImageIcon("pic6.jpg");
    JLabel label = new JLabel("", image, JLabel.CENTER);


    AdminMenu() {
        createAcc.setBounds(130, 50, 250, 50);
        createAcc.setText("CREATE ACCOUNT");
        createAcc.setFocusable(false);

        transfer.setBounds(430, 210, 250, 50);
        transfer.setText("TRANSFER");
        transfer.setFocusable(false);

        withdraw.setBounds(430, 130, 250, 50);
        withdraw.setText("WITHDRAW");
        withdraw.setFocusable(false);

        deposit.setBounds(430, 50, 250, 50);
        deposit.setText("DEPOSIT");
        deposit.setFocusable(false);

        updateAcc.setBounds(130, 210, 250, 50);
        updateAcc.setText("UPDATE ACCOUNT");
        updateAcc.setFocusable(false);

        deleteAcc.setBounds(130, 290, 250, 50);
        deleteAcc.setText("DELETE ACCOUNT");
        deleteAcc.setFocusable(false);

        searchAcc.setBounds(130, 370, 250, 50);
        searchAcc.setText("SEARCH ACCOUNT");
        searchAcc.setFocusable(false);

        locked.setBounds(430, 290, 250, 50);
        locked.setText("LOCKED ACCOUNTS");
        locked.setFocusable(false);

        exit.setBounds(430, 370, 250, 50);
        exit.setText("EXIT");
        exit.setFocusable(false);

        label.setBounds(0, 0, 800, 500);
        label.add(createAcc);
        label.add(updateAcc);
        label.add(deleteAcc);
        label.add(searchAcc);
        label.add(transfer);
        label.add(deposit);
        label.add(withdraw);
        label.add(locked);
        label.add(exit);

        frame.add(label);
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        // action listeners
        createAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CreateAccount();
            }
        });

        updateAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new UpdateAccount(true);
            }
        });

        searchAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Search();
            }
        });

        deleteAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new DeleteAccount();
            }
        });

        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Transfer(true);
            }
        });

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Deposit(true);
            }
        });

        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new WithdrawFPage(true);
            }
        });

        locked.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LockedAccounts();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Login();
            }
        });
    }
}
