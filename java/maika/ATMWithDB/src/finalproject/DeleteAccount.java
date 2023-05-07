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

public class DeleteAccount {
    JFrame frame = new JFrame("DELETE ACCOUNT");
    static String[] col = {"Status", "Account No.", "Name", "Address", "Email", "Contact", "Balance"};
    static DefaultTableModel model = new DefaultTableModel(null, col);
    JLabel header2 = new JLabel("DELETE ACCOUNT");
    JLabel accId = new JLabel("ACCOUNT NO:");
    JTextField txtAccId = new JTextField();
    JButton btnCancel = new JButton("CANCEL");
    JButton btnDelete = new JButton("DELETE");
    JButton btnSearch = new JButton();
    JTable jt = new JTable(null, col);
    JScrollPane sp = new JScrollPane(jt);
    ImageIcon image = new ImageIcon("pic9.png");
    JLabel background = new JLabel("", image, JLabel.CENTER);

    DeleteAccount() {
        String[][] tableData = new String[Database.users().size()][7];
        for (int i = 0; i < Database.users().size(); i++) {
            tableData[i] = Database.users().get(i).toArray();
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

        btnSearch.setBounds(535, 110, 100, 20);
        btnSearch.setText("SEARCH");
        btnSearch.setFocusable(false);
        background.add(btnSearch);

        txtAccId.setBounds(220, 110, 300, 20);
        background.add(txtAccId);

        btnDelete.setBounds(100, 380, 110, 20);
        btnDelete.setFocusable(false);
        background.add(btnDelete);

        btnCancel.setBounds(590, 380, 110, 20);
        btnCancel.setFocusable(false);
        background.add(btnCancel);

        sp.setBounds(100, 150, 600, 200);
        jt.setModel(model);
        background.add(sp);

        background.setBounds(0, 0, 800, 480);

        frame.add(background);

        frame.setSize(800, 480);
        frame.setLocationRelativeTo(null);//setLocationRelativeTo(frame);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        txtAccId.addActionListener(getActionListener());
        btnDelete.addActionListener(getActionListener());
        btnSearch.addActionListener(getActionListener());

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

                if (accId.length() != 8) {
                    JOptionPane.showMessageDialog(null, "Account ID must be 8 digits.");
                    return;
                }


                User user = Database.getUser(accId);

                if (user == null) {
                    JOptionPane.showMessageDialog(null, "Account not found.");
                    return;
                }

                model.setDataVector(new String[][]{user.toArray()}, col);

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


                try {
                    if (user.isLocked()) {
                        JOptionPane.showMessageDialog(null, "Account is locked.");
                        return;
                    }

                    if(!user.close()) return;

                    JOptionPane.showMessageDialog(null, "Account deleted.");
                    frame.dispose();
                    new AdminMenu();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting account.\n" + ex.getMessage());
                }
            }
        };
    }
}
