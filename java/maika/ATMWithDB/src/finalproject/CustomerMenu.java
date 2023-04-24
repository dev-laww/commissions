/**
 * @author:  tora
 * @author:  niku
 */

package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerMenu {
    JFrame frame = new JFrame();
    JButton miniStatement = new JButton();
    JButton deposit = new JButton();
    JButton withdraw = new JButton();
    JButton transfer = new JButton();
    JButton balance = new JButton();
    JButton changePin = new JButton();
    JButton exit = new JButton();
    ImageIcon image = new ImageIcon("pic6.png");
    JLabel label = new JLabel("", image, JLabel.CENTER);
    JPanel panel = new JPanel();

    CustomerMenu(Customer customer) {

        miniStatement.setBounds(60, 330, 380, 50);
        miniStatement.setText("VIEW MINISTATEMENT");
        miniStatement.setFont(new Font(null, Font.PLAIN, 25));
        miniStatement.setFocusable(false);

        deposit.setBounds(60, 35, 380, 50);
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

//        changePin.setBounds(110, 400, 380, 50);
//        changePin.setText("CHANGE PIN");
//        changePin.setFont(new Font(null, Font.PLAIN, 25));
//        changePin.setFocusable(false);

        exit.setBounds(60, 395, 380, 50);
        exit.setText("EXIT");
        exit.setFont(new Font(null, Font.PLAIN, 25));
        exit.setFocusable(false);


        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);



        label.add(exit);
        label.add(balance);
        label.add(deposit);
        label.add(withdraw);
        label.add(transfer);
        //label.add(changePin);
        label.add(miniStatement);
        label.add(panel);
        label.setBounds(0, 0, 500, 500);


        frame.add(exit);
        frame.add(balance);
        frame.add(deposit);
        frame.add(withdraw);
        frame.add(transfer);
        //frame.add(changePin);
        frame.add(miniStatement);
        frame.add(panel);
        frame.add(label);
        frame.setVisible(true);

        // action listeners
        
        miniStatement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MiniStatement(customer);
                frame.dispose();
            }
        });

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Deposit(customer, false);
                frame.dispose();
            }
        });

        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Withdraw(customer, false);
                frame.dispose();
            }
        });

        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Transfer(customer, false);
                frame.dispose();
            }
        });

        changePin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePin(customer);
                frame.dispose();
            }
        });

        balance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewBalance(customer);
                frame.dispose();
            }
        });


        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrontPage(BankSystem.idAndPassword).start();
                frame.dispose();
            }
        });
    }
}