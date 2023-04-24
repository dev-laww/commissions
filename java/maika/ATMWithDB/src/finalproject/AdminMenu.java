/**
 * @author: tora
 * @author: niku
 */

package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminMenu {
    JFrame frame = new JFrame();
    JButton createAcc = new JButton();
    JButton updateAcc = new JButton();
    JButton deleteAcc = new JButton();
    JButton searchAcc = new JButton();
    JButton transact = new JButton();
    JButton withdraw = new JButton();
    JButton transfer = new JButton();
    JButton deposit = new JButton();
    JButton locked = new JButton();
    JButton exit = new JButton();
    ImageIcon image = new ImageIcon("pic6.jpg");
    JLabel label = new JLabel("", image, JLabel.CENTER);


    AdminMenu() {

        createAcc.setBounds(130,50,250,50);
        createAcc.setText("CREATE ACCOUNT");
        createAcc.setFocusable(false);

        transact.setBounds(130,130,250,50);
        transact.setText("CUSTOMER DETAILS");
        transact.setFocusable(false);

        transfer.setBounds(430,210,250,50);
        transfer.setText("TRANSFER");
        transfer.setFocusable(false);

        withdraw.setBounds(430,130,250,50);
        withdraw.setText("WITHDRAW");
        withdraw.setFocusable(false);

        deposit.setBounds(430,50,250,50);
        deposit.setText("DEPOSIT");
        deposit.setFocusable(false);

        updateAcc.setBounds(130,210,250,50);
        updateAcc.setText("UPDATE ACCOUNT");
        updateAcc.setFocusable(false);

        deleteAcc.setBounds(130,290,250,50);
        deleteAcc.setText("DELETE ACCOUNT");
        deleteAcc.setFocusable(false);

        searchAcc.setBounds(130,370,250,50);
        searchAcc.setText("SEARCH ACCOUNT");
        searchAcc.setFocusable(false);

        locked.setBounds(430,290,250,50);
        locked.setText("LOCKED ACCOUNTS");
        locked.setFocusable(false);

        exit.setBounds(430,370,250,50);
        exit.setText("EXIT");
        exit.setFocusable(false);

        label.setBounds(0,0,800,500);
        label.add(createAcc);
        label.add(transact);
        label.add(updateAcc);
        label.add(deleteAcc);
        label.add(searchAcc);
        label.add(transfer);
        label.add(deposit);
        label.add(withdraw);
        label.add(locked);
        label.add(exit);

        frame.add(label);
        frame.setSize(800,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        // action listeners
        createAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateAccount();
                frame.dispose();
            }
        });

        transact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Transactions();
                frame.dispose();
            }
        });

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer c;
                try {
                    c = search();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }

                if (c == null) {
                    return;
                }

                new Deposit(c,true);
                frame.dispose();
            }
        });

        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer c;
                try {
                    c = search();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }

                if (c == null) {
                    return;
                }

                new Withdraw(c, true);
                frame.dispose();
            }
        });

        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer c;
                try {
                    c = search();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }

                if (c == null) {
                    return;
                }

                new Transfer(c, true);
                frame.dispose();
            }
        });

        updateAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer c;
                try {
                    c = search();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }

                if (c == null) {
                    return;
                }

                new UpdateAccount(c);
                frame.dispose();
            }
        });

        deleteAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer c;
                try {
                    c = search();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }

                if (c == null) {
                    return;
                }
                new DeleteAccount(c);
                frame.dispose();
            }
        });

        searchAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer c;
                try {
                    c = search();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }

                if (c == null) {
                    return;
                }

                new CustomerDetails(c);
                frame.dispose();
            }
        });

        locked.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LockedAccounts();
                frame.dispose();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrontPage(BankSystem.idAndPassword).start();
                frame.dispose();
            }
        });
    }

    private Customer search() throws Exception {
        ArrayList<Customer> customers = BankSystem.idAndPassword.getCustomerLoginInfo();
        Customer customer = null;
        String id = JOptionPane.showInputDialog("Enter customer ID: ");

        if (id == null) {
            return null;
        }

        if (id.length() != 12) {
            throw new Exception("Invalid ID");
        }

        boolean found = false;
        for (Customer c : customers) {
            if (c.getAccountID().equals(id)) {
                found = true;
                customer = c;
                JOptionPane.showMessageDialog(null, "Customer found!");
                frame.dispose();
                break;
            }
        }

        if (!found) {
            throw new Exception("Customer not found");
        }

        return customer;
    }
}