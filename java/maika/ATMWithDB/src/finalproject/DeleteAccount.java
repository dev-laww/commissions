/**
 * @author:  tora
 * @author:  niku
 */

package finalproject;

import finalproject.db.User;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class DeleteAccount {
    static JTextField tfname = new JTextField();
    static JPasswordField tfPass = new JPasswordField();
    
        ImageIcon image = new ImageIcon("pic6.jpg");
    JLabel label = new JLabel("", image, JLabel.CENTER);

    JFrame frame = new JFrame();
    JLabel CreateAccountLabel = new JLabel("DELETE ACCOUNT");
    JLabel name = new JLabel("NAME:");
    JLabel AccountID = new JLabel("ACCOUNT ID:");
    JTextField tfID = new JTextField();
    JButton cancel = new JButton("CANCEL");
    JButton confirm = new JButton("CONFIRM");

    DeleteAccount() {
        //frame
        frame.setSize(400, 230);
        frame.setTitle("Delete Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.add(label);
        frame.add(CreateAccountLabel);
        frame.add(name);
        frame.add(tfname);
        frame.add(AccountID);
        frame.add(tfID);
        frame.add(tfPass);
        frame.add(cancel);
        frame.add(confirm);

        //label
        CreateAccountLabel.setBounds(110, 10, 200, 30);
        CreateAccountLabel.setFont(new Font(null, Font.BOLD, 20));
        CreateAccountLabel.setForeground(Color.WHITE);

        name.setBounds(40, 50, 100, 30);
        name.setFont(new Font(null, Font.BOLD, 15));
        name.setForeground(Color.WHITE);

        tfname.setBounds(160, 50, 200, 30);
//        tfname.setText(user.name);
        tfname.setEditable(false);

        AccountID.setBounds(40, 100, 200, 30);
        AccountID.setFont(new Font(null, Font.BOLD, 15));
        AccountID.setForeground(Color.WHITE);

        tfID.setBounds(160, 100, 200, 30);
//        tfID.setText(user.id);
        tfID.setEditable(false);

        label.add(name);
        label.add(tfname);
        label.add(AccountID);
        label.add(tfID);
        label.add(CreateAccountLabel);
        label.add(cancel);
        label.add(confirm);
        label.setBounds(0, 0, 400, 230);

        //button
        cancel.setBounds(100, 150, 100, 30);
        confirm.setBounds(200, 150, 100, 30);

        //action listener
    }
}


