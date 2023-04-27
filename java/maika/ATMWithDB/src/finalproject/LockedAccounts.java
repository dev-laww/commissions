/**
 * @author: tora
 * @author: niku
 */

package finalproject;

import finalproject.db.Database;
import finalproject.db.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class LockedAccounts {
    JFrame frame = new JFrame("Locked Accounts");
    static String[] col = {"Status", "Account No.", "Name"};
    static DefaultTableModel model = new DefaultTableModel(null, col);
    JLabel header2 = new JLabel("Locked Accounts");
    JLabel accId = new JLabel("Account ID:");
    JTextField txtAccId = new JTextField();
    JButton btnCancel = new JButton("Cancel");
    JButton btnUnlock = new JButton("Unlock");
    JTable jt = new JTable(null, col);
    JScrollPane sp = new JScrollPane(jt);

    LockedAccounts() {
        int count = 0;
        for (User u : Database.users) {
            if (u.isLocked()) count++;
        }

        if (count == 0) {
            JOptionPane.showMessageDialog(null, "No locked accounts found.");
            new AdminMenu();
            frame.dispose();
            return;
        }

        String[][] tableData = new String[count][3];
        int j = 0;
        for (User u : Database.users) {
            if (!u.isLocked()) continue;
            tableData[j] = u.toLockedArray();
        }

        model.setDataVector(tableData, col);

        header2.setBounds(348, 40, 390, 50);
        header2.setFont(new Font("Times New Roman", 8, 18));
        frame.add(header2);

        accId.setBounds(460, 100, 100, 20);
        frame.add(accId);
        txtAccId.setBounds(550, 100, 150, 20);
        frame.add(txtAccId);

        btnUnlock.setBounds(100, 380, 110, 20);
        frame.add(btnUnlock);

        btnCancel.setBounds(590, 380, 110, 20);
        frame.add(btnCancel);

        sp.setBounds(100, 150, 600, 200);
        jt.setModel(model);
        frame.add(sp);

        frame.setSize(800, 480);
        frame.setTitle("Customer Panel");
        frame.setLocationRelativeTo(null);//setLocationRelativeTo(frame);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        txtAccId.addActionListener(getActionListener());
        btnUnlock.addActionListener(getActionListener());

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminMenu();
                frame.dispose();
            }
        });
    }

    private ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accId = txtAccId.getText();

                if (accId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter an account ID.");
                    return;
                }

                User user = Database.getUser(accId);

                if (user == null) {
                    JOptionPane.showMessageDialog(null, "Account not found.");
                    return;
                }

                if (!user.isLocked()) {
                    JOptionPane.showMessageDialog(null, "Account is not locked.");
                    return;
                }

                user.unlock();
                frame.dispose();
                JOptionPane.showMessageDialog(null, "Account unlocked.");
            }
        };
    }
}
