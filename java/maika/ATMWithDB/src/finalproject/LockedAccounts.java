/**
 * @author: tora
 */

package finalproject;

import static finalproject.Search.col;
import static finalproject.Search.model;
import finalproject.db.Database;
import finalproject.db.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LockedAccounts {
    JFrame frame = new JFrame("LOCKED ACCOUNTS");
    static String[] col = {"Status", "Account No.", "Name"};
    static DefaultTableModel model = new DefaultTableModel(null, col);
    JLabel header2 = new JLabel("LOCKED ACCOUNT");
    JLabel accId = new JLabel("ACCOUNT NO:");
    JTextField txtAccId = new JTextField();
    JButton btnCancel = new JButton("CANCEL");
    JButton btnUnlock = new JButton("UNLOCK");
    JButton btnSearch = new JButton();
    JTable jt = new JTable(null, col);
    JScrollPane sp = new JScrollPane(jt);
    ImageIcon image = new ImageIcon("pic9.png");
    JLabel background = new JLabel("", image, JLabel.CENTER);
    private User user;

    LockedAccounts() {
        int count = 0;
        for (User u : Database.users()) {
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
        for (User u : Database.users()) {
            if (!u.isLocked()) continue;
            tableData[j] = u.toLockedArray();
            j++;
        }

        model.setDataVector(tableData, col);

        header2.setBounds(230, 35, 390, 50);
        header2.setFont(new Font("Times New Roman", 8, 35)); 
        header2.setForeground(Color.WHITE);
        background.add(header2);

        accId.setBounds(100, 110, 150, 20);
        accId.setFont(new Font(null, Font.BOLD, 15));
        accId.setForeground(Color.WHITE);
        background.add(accId);
        
        
        txtAccId.setBounds(220, 110, 300, 20);
        background.add(txtAccId);

        btnSearch.setBounds(535, 110, 100, 20);
        btnSearch.setText("SEARCH");
        btnSearch.setFocusable(false);
        background.add(btnSearch);
        
        btnUnlock.setBounds(100, 380, 110, 20);
        btnUnlock.setFocusable(false);
        background.add(btnUnlock);

        btnCancel.setBounds(590, 380, 110, 20);
        btnCancel.setFocusable(false);
        background.add(btnCancel);

        sp.setBounds(100, 150, 600, 200);
        jt.setModel(model);
        background.add(sp);
        
        background.setBounds(0,0,800,480);

        frame.add(background);

        frame.setSize(800, 480);
        frame.setLocationRelativeTo(null);//setLocationRelativeTo(frame);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        txtAccId.addActionListener(searchActionListener());
        btnUnlock.addActionListener(getActionListener());
        btnSearch.addActionListener(searchActionListener());

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminMenu();
                frame.dispose();
            }
        });
    }

    private ActionListener searchActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accId = txtAccId.getText();

                if (accId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter an account ID.");
                    return;
                }

                if (accId.length() != 8) {
                    JOptionPane.showMessageDialog(null, "Account ID must be 8 digits.");
                    return;
                }

                user = Database.getUser(accId);

                if (user == null) {
                    JOptionPane.showMessageDialog(null, "Account not found.");
                    txtAccId.setText("");
                    return;
                }

                if (!user.isLocked()) {
                    JOptionPane.showMessageDialog(null, "Account is not locked.");
                    return;
                }

                model.setDataVector(new String[][]{user.toArray()}, col);
            }
        };
    }

    private ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user == null) {
                    JOptionPane.showMessageDialog(null, "Please search for an account first.");
                    return;
                }

                JPasswordField passwordField = new JPasswordField();
                int result = JOptionPane.showConfirmDialog(null, passwordField, "Enter admin password:", JOptionPane.OK_CANCEL_OPTION);

                if (!(result == JOptionPane.OK_OPTION)) {
                    JOptionPane.showMessageDialog(null, "Cancelled");
                    return;
                }

                String adminPass = String.valueOf(passwordField.getPassword());
                String storedPassword = Database.admin.get("1234");
                if (storedPassword != null && !storedPassword.equals(adminPass)) {
                JOptionPane.showMessageDialog(null, "Wrong password");
                return;
                }

                user.unlock();
                frame.dispose();
                JOptionPane.showMessageDialog(null, "Account unlocked.");
                new LockedAccounts();
            }
        };
    }
}
