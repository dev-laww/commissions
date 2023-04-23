/**
 * @author:  tora
 * @author:  niku
 */

package finalproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MiniStatement {
    JFrame frame = new JFrame();
    JTextArea textArea = new JTextArea();
    ImageIcon image =  new ImageIcon("pic4.jpg");
    JLabel label = new JLabel("",image,JLabel.CENTER);
    JButton backButton = new JButton("BACK");
    JLabel label1 = new JLabel("VIEW MINISTATEMENT");
    
    MiniStatement(Customer customer){
        Font font = new Font("JetBrains Mono NL", Font.PLAIN, 14);

        // Account Number: XXXXXXXX9123
        // Balance: $100.00
        // Transaction History:
        // --------------------------------------------------
        // 1. Withdrawal: -$100.00
        // 2. Deposit: +$100.00
        // 3. Withdrawal: -$100.00
        // 4. Transfer to 32132465841323: -$100.00

        String sb = String.format("%-25s%25s%n", "Account Number:", "XXXXXXXX" + customer.getAccountID().substring(7, 11)) +
                String.format("%-25s%25s%n%n", "Balance:", customer.getBalance()) +
                "Transaction History:\n" +
                "--------------------------------------------------\n" +
                customer.getTransactions(false);
        textArea.setFont(font);
        textArea.setEditable(false);
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setBounds(20, 80, 560, 470);
        textArea.setText(sb);
        
        backButton.setBounds(30,510,70,30);
        backButton.setFocusable(false);
        
        label1.setBounds(200,20,500,40);
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font(null,Font.BOLD,19));
        
        frame.add(label1);
        frame.add(backButton);
        frame.add(textArea);
        frame.add(label);
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        
        label.add(label1);
        label.add(textArea);
        label.setBounds(0,0,600,600);
        
        frame.setVisible(true);

        // action listeners
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CustomerMenu(customer);
            }
        });
    }
}
