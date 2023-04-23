/**
 * @author:  tora
 * @author:  niku
 */

package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transfer {
    JFrame frame = new JFrame();
    JLabel label1 = new JLabel("Transfer Amount");
    JLabel label2 = new JLabel("Enter account no. you want to transfer to");
    JTextField accountNoTF = new JTextField();
    JLabel label3 = new JLabel("Enter amount you want to transfer");
    JTextField amountTF = new JTextField();
    Customer customer;
    JButton exit = new JButton("CANCEL");
    JButton enter = new JButton("CONFIRM");
    
    ImageIcon image = new ImageIcon("tt.jpeg");
    JLabel label = new JLabel("", image, JLabel.CENTER);

    Transfer(Customer customer, boolean isAdmin) {
        
        this.customer = customer;
        
        label1.setBounds(200, 50, 550, 40);
        label1.setText("Transfer Amount");
        label1.setFont(new Font(null, Font.BOLD, 35));
        label1.setForeground(Color.BLACK);

        label2.setBounds(75, 150, 550, 40);
        label2.setText("Enter account no. you want to transfer to");
        label2.setFont(new Font(null, Font.BOLD, 25));
        label2.setForeground(Color.BLACK);

        accountNoTF.setBounds(75, 250, 550, 40);
        accountNoTF.setFont(new Font(null, Font.BOLD, 25));
        accountNoTF.setForeground(Color.BLACK);
        accountNoTF.setBackground(Color.WHITE);

        label3.setBounds(75, 350, 550, 40);
        label3.setText("Enter amount you want to transfer");
        label3.setFont(new Font(null, Font.BOLD, 25));
        label3.setForeground(Color.BLACK);

        amountTF.setBounds(75, 450, 550, 40);
        amountTF.setFont(new Font(null, Font.BOLD, 25));
        amountTF.setForeground(Color.BLACK);
        amountTF.setBackground(Color.WHITE);

        exit.setBounds(75, 550, 250, 40);
        exit.setFont(new Font(null, Font.BOLD, 25));
        exit.setForeground(Color.BLACK);
        exit.setBackground(Color.WHITE);

        enter.setBounds(350, 550, 250, 40);
        enter.setFont(new Font(null, Font.BOLD, 25));
        enter.setForeground(Color.BLACK);
        enter.setBackground(Color.WHITE);

        label.add(label1);
        label.add(label2);
        label.add(accountNoTF);
        label.add(label3);
        label.add(amountTF);
        label.add(exit);
        label.add(enter);
        label.setBounds(0, 0,680, 680); 
        
        frame.add(label);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Transfer");
        frame.setSize(680, 680);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // action listeners
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed(customer, isAdmin);
            }
        });

        accountNoTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed(customer, isAdmin);
            }
        });

        amountTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed(customer, isAdmin);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                if (isAdmin) {
                    new AdminMenu();
                    return;
                }
                new CustomerMenu(customer);
            }
        });
    }

    private void enterPressed(Customer customer, boolean isAdmin) {
        String accountNo = accountNoTF.getText();
        String amount = amountTF.getText();

        if (accountNo.isEmpty() || amount.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return;
        }

        if (accountNo.equals(customer.getAccountID())) {
            JOptionPane.showMessageDialog(null, "You can't transfer to your own account");
            accountNoTF.setText("");
            return;
        }

        try {
            Double.parseDouble(amount);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid amount");
            amountTF.setText("");
            return;
        }
        double amountToTransfer = Double.parseDouble(amount);
        if (amountToTransfer > customer.getBalance()) {
            JOptionPane.showMessageDialog(null, "You don't have enough balance\nCurrent balance: " + customer.getBalance());
            amountTF.setText("");
            return;
        }

        if (amountToTransfer <= 0) {
            JOptionPane.showMessageDialog(null, "Amount should be greater than 0");
            amountTF.setText("");
            return;
        }else if (amountToTransfer >= 50001) {
            JOptionPane.showMessageDialog(null, "Amount must be less than 50,000");
            amountTF.setText("");
            return;
        }

        try {
            this.customer.transfer(accountNo, amountToTransfer);
            JOptionPane.showMessageDialog(null, "Amount transferred successfully");
            //edit
            JOptionPane.showMessageDialog(null, "Current balance: " + customer.getBalance());
            amountTF.setText("");
            //
            frame.dispose();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }

        if (isAdmin) {
            new AdminMenu();
            return;
        }
        new CustomerMenu(customer);
    }
}