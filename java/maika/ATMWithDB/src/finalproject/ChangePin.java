/**
 * @author:  tora
 * @author:  niku
 */

package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePin {
    JFrame frame = new JFrame();
    JLabel label1 = new JLabel("Old Pin");
    JPasswordField oldPinTF = new JPasswordField();
    JLabel label2 = new JLabel("New Pin");
    JPasswordField newPinTF = new JPasswordField();
    JLabel label3 = new JLabel("Confirm New Pin");
    JPasswordField confirmPinTF = new JPasswordField();
    JButton exit = new JButton("CANCEL");
    JButton enter = new JButton("CONFIRM");
    
    ImageIcon image = new ImageIcon("mm.jpg");
    JLabel label = new JLabel("", image, JLabel.CENTER);

    ChangePin() {
        label1.setBounds(70, 50, 550, 40);
        label1.setText("Old Pin");
        label1.setFont(new Font(null, Font.BOLD, 35));
        label1.setForeground(Color.BLACK);

        oldPinTF.setBounds(70, 150, 550, 40);
        oldPinTF.setFont(new Font(null, Font.BOLD, 25));
        oldPinTF.setForeground(Color.BLACK);
        oldPinTF.setBackground(Color.WHITE);
        oldPinTF.setColumns(4);

        label2.setBounds(70, 250, 550, 40);
        label2.setText("New Pin");
        label2.setFont(new Font(null, Font.BOLD, 35));
        label2.setForeground(Color.BLACK);

        newPinTF.setBounds(70, 350, 550, 40);
        newPinTF.setFont(new Font(null, Font.BOLD, 25));
        newPinTF.setForeground(Color.BLACK);
        newPinTF.setBackground(Color.WHITE);
        newPinTF.setColumns(4);

        label3.setBounds(70, 450, 550, 40);
        label3.setText("Confirm New Pin");
        label3.setFont(new Font(null, Font.BOLD, 35));
        label3.setForeground(Color.BLACK);

        confirmPinTF.setBounds(70, 550, 550, 40);
        confirmPinTF.setFont(new Font(null, Font.BOLD, 25));
        confirmPinTF.setForeground(Color.BLACK);
        confirmPinTF.setBackground(Color.WHITE);
        confirmPinTF.setColumns(4);

        exit.setBounds(90, 650, 250, 40);
        exit.setFont(new Font(null, Font.BOLD, 25));
        exit.setForeground(Color.BLACK);
        exit.setBackground(Color.WHITE);

        enter.setBounds(350, 650, 250, 40);
        enter.setFont(new Font(null, Font.BOLD, 25));
        enter.setForeground(Color.BLACK);
        enter.setBackground(Color.WHITE);

        label.add(label1);
        label.add(oldPinTF);
        label.add(label2);
        label.add(newPinTF);
        label.add(label3);
        label.add(confirmPinTF);
        label.add(exit);
        label.add(enter);
        label.setBounds(0, 0,700, 800); 
        
        frame.setTitle("CHANGE PIN CODE");
        frame.add(label);
        frame.setSize(700, 800);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // action listeners
    }
}
