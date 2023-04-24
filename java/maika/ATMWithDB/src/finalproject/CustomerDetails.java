/**
 * @author:  tora
 * @author:  niku
 */
package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author maikaordonez
 */
public class CustomerDetails {
    JFrame frame = new JFrame();
    JTextArea textArea = new JTextArea();
    ImageIcon image = new ImageIcon("pic4.jpg");
    JLabel label = new JLabel("", image, JLabel.CENTER);
    JButton backButton = new JButton("BACK");
    JLabel titleLabel = new JLabel("CUSTOMER DETAILS / TRANSACTION HISTORY");


    CustomerDetails() {
        Font font = new Font("JetBrains Mono NL", Font.PLAIN, 14);

        textArea.setFont(font);
        textArea.setEditable(false);
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setBounds(20, 80, 560, 470);
        textArea.setText("");

        backButton.setBounds(30, 510, 70, 30);
        backButton.setFocusable(false);

        titleLabel.setBounds(72, 20, 500, 40);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font(null, Font.BOLD, 19));

        frame.add(titleLabel);
        frame.add(backButton);
        frame.add(textArea);
        frame.add(label);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        label.add(titleLabel);
        label.add(textArea);
        label.setBounds(0, 0, 600, 600);

        frame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminMenu();
            }
        });
    }
}
