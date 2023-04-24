/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author maikaordonez
 */
public class WithdrawFPage {
    
    JFrame frame = new JFrame();
    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JTextField tf = new JTextField();
    JButton exit = new JButton("CANCEL");
    JButton enter = new JButton("ENTER");
    
    JButton twenty = new JButton("20");
    JButton fifty = new JButton("50");
    JButton oneH = new JButton("100");
    JButton twoH= new JButton("200");
    JButton fiveH = new JButton("500");
    JButton oneK = new JButton("1000");
    JButton fiveK = new JButton("5000");
    JButton tenK = new JButton("10,000");
    JButton amount = new JButton("ANOTHER AMOUNT");
    
    ImageIcon image = new ImageIcon("pic7.jpeg");
    JLabel label = new JLabel("", image, JLabel.CENTER);

    WithdrawFPage() {
        
        label1.setBounds(110, 60, 380, 100);
        label1.setText("WITHDRAW");
        label1.setFont(new Font(null, Font.BOLD, 60));
        label1.setForeground(Color.WHITE);

        label2.setBounds(55, 82, 500, 200);
        label2.setText("ENTER AMOUNT YOU WANT TO WITHDRAW");
        label2.setFont(new Font(null, Font.BOLD, 20));
        label2.setForeground(Color.WHITE);

        tf.setBounds(75, 235, 400, 40);
        tf.setFont(new Font(null, Font.BOLD, 25));
        tf.setForeground(Color.BLACK);
        tf.setBackground(Color.WHITE);

        exit.setBounds(295, 330, 200, 40);
        exit.setFont(new Font(null, Font.BOLD, 15));
        exit.setForeground(Color.BLACK);
        exit.setBackground(Color.WHITE);

        enter.setBounds(60, 330, 200, 40);
        enter.setFont(new Font(null, Font.BOLD, 15));
        enter.setForeground(Color.BLACK);
        enter.setBackground(Color.WHITE);
                
        twenty.setBounds(580, 60, 100, 100);
        twenty.setFont(new Font(null, Font.BOLD, 30));
        twenty.setForeground(Color.BLACK);
        twenty.setBackground(Color.WHITE);
        
        fifty.setBounds(710, 60, 100, 100);
        fifty.setFont(new Font(null, Font.BOLD, 30));
        fifty.setForeground(Color.BLACK);
        fifty.setBackground(Color.WHITE);
        
        oneH.setBounds(840, 60, 100, 100);
        oneH.setFont(new Font(null, Font.BOLD, 30));
        oneH.setForeground(Color.BLACK);
        oneH.setBackground(Color.WHITE);
        
        twoH.setBounds(580, 180, 100, 100);
        twoH.setFont(new Font(null, Font.BOLD, 30));
        twoH.setForeground(Color.BLACK);
        twoH.setBackground(Color.WHITE);
        
        fiveH.setBounds(710, 180, 100, 100);
        fiveH.setFont(new Font(null, Font.BOLD, 30));
        fiveH.setForeground(Color.BLACK);
        fiveH.setBackground(Color.WHITE);
        
        oneK.setBounds(840, 180, 100, 100);
        oneK.setFont(new Font(null, Font.BOLD, 30));
        oneK.setForeground(Color.BLACK);
        oneK.setBackground(Color.WHITE);
        
        fiveK.setBounds(580, 300, 100, 100);
        fiveK.setFont(new Font(null, Font.BOLD, 30));
        fiveK.setForeground(Color.BLACK);
        fiveK.setBackground(Color.WHITE);
        
        tenK.setBounds(710, 300, 100, 100);
        tenK.setFont(new Font(null, Font.BOLD, 20));
        tenK.setForeground(Color.BLACK);
        tenK.setBackground(Color.WHITE);
        
        amount.setBounds(840, 300, 100, 100);
        amount.setFont(new Font(null, Font.BOLD, 8));
        amount.setForeground(Color.BLACK);
        amount.setBackground(Color.WHITE);
        
        label.setBounds(0, 0,1000, 500); 
        label.add(oneK);
        label.add(twoH);
        label.add(twenty);
        label.add(oneH);
        label.add(fiveK);
        label.add(fifty);
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
        
    }
    
    public static void main(String[] args) {
        WithdrawFPage log = new WithdrawFPage();
        log.start();
    }
    
    public void start() {
        frame.setVisible(true);
    }
}