package project_1;

import javax.swing.*;
import java.awt.*;

public class Receipt extends JFrame {
    JLabel header = new JLabel("BANKING SYSTEM");
    JLabel header2 = new JLabel("Receipt");
    JTextArea receipt = new JTextArea();
    JButton btnOk = new JButton("OK");

    public Receipt(String receiptText, boolean isAdmin) {
        header.setBounds(300, 10, 390, 50);
        header.setFont(new Font("Stencil", 10, 25));
        this.add(header);

        header2.setBounds(350, 40, 390, 50);
        header2.setFont(new Font("Times New Roman", 8, 18));
        this.add(header2);

        receipt.setBounds(100, 100, 600, 300);
        receipt.setCaretColor(Color.GRAY);
        receipt.setEditable(false);
        receipt.setText(receiptText);
        this.add(receipt);

        btnOk.setBounds(350, 420, 100, 20);
        this.add(btnOk);

        this.setSize(800, 480);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("DepositReciept");

        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (!isAdmin) {
                    new CustomerPanel().setVisible(true);
                    dispose();
                    return;
                }

                AdminPanel adminPanel = new AdminPanel();
                adminPanel.setVisible(true);
                dispose();
            }
        });
    }
}

