/**
 * @author: tora
 */

package finalproject;

import finalproject.db.Database;
import finalproject.db.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAccount {
    JFrame frame = new JFrame("Delete Account");
    static String[] col = {"Status", "Account No.", "Name", "Address", "Email", "Contact", "Balance"};
    static DefaultTableModel model = new DefaultTableModel(null, col);
    JLabel header2 = new JLabel("Delete Account");
    JLabel accId = new JLabel("Account ID:");
    JTextField txtAccId = new JTextField();
    JButton btnCancel = new JButton("Cancel");
    JButton btnDelete = new JButton("Delete");
    JTable jt = new JTable(null, col);
    JScrollPane sp = new JScrollPane(jt);

    DeleteAccount() {
        String[][] tableData = new String[Database.users.size()][7];
        for (User u : Database.users) {
            tableData[Database.users.indexOf(u)] = u.toArray();
        }
        model.setDataVector(tableData, col);

        header2.setBounds(348, 40, 390, 50);
        header2.setFont(new Font("Times New Roman", 8, 18));
        frame.add(header2);

        accId.setBounds(460, 100, 100, 20);
        frame.add(accId);
        txtAccId.setBounds(550, 100, 150, 20);
        frame.add(txtAccId);

        btnDelete.setBounds(100, 380, 110, 20);
        frame.add(btnDelete);

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
        btnDelete.addActionListener(getActionListener());

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

                try {
                    Database.deleteUser(user.id);
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
