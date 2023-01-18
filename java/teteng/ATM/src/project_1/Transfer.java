package project_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transfer extends JFrame {
    Customer customer;
    Customer receiver;
    JLabel header = new JLabel("BANKING SYSTEM");
    JLabel header2 = new JLabel("WITHDRAW MONEY");
    JLabel instruction = new JLabel("Please fill up the requirements below to transfer money.");
    JLabel userId = new JLabel("Enter Account Number:");
    JTextField txtUserId = new JTextField();
    JLabel txtCustName = new JLabel();
    JLabel receiverId = new JLabel("Account number of recipient:");
    JTextField txtReceiverId = new JTextField();
    JButton btnReceiverSearch = new JButton("Search");
    JLabel custName = new JLabel("Customer name:");
    JButton btnSearch = new JButton("Search");
    JLabel amount = new JLabel("Amount to transfer:");
    JTextField txtAmount = new JTextField();
    JLabel recipientName = new JLabel("Recipient name:");
    JLabel txtRecipientName = new JLabel();
    JButton btnTransfer = new JButton("Transfer");
    JButton btnCancel = new JButton("Cancel");
    JButton btnClear = new JButton("Clear");


    public Transfer(boolean isAdmin) {
        Customer c = BankingSystem.customerInfo;

        if (c != null && !isAdmin) {
            customer = c;
            txtCustName.setText(customer.getName());
        }

        if (!isAdmin) {
            btnSearch.setVisible(false);
            txtUserId.setVisible(false);
            userId.setVisible(false);
        }

        header.setBounds(300, 10, 390, 50);
        header.setFont(new Font("Stencil", 10, 25));
        this.add(header);

        header2.setBounds(320, 40, 390, 50);
        header2.setFont(new Font("Times New Roman", 8, 18));
        this.add(header2);

        instruction.setBounds(140, 90, 350, 20);
        this.add(instruction);

        userId.setBounds(265, 120, 360, 20);
        this.add(userId);
        txtUserId.setBounds(405, 120, 130, 20);
        this.add(txtUserId);
        btnSearch.setBounds(540, 120, 90, 20);
        this.add(btnSearch);


        receiverId.setBounds(200, 160, 360, 20);
        this.add(receiverId);
        txtReceiverId.setBounds(405, 160, 130, 20);
        this.add(txtReceiverId);
        btnReceiverSearch.setBounds(540, 160, 90, 20);
        this.add(btnReceiverSearch);

        custName.setBounds(265, 200, 360, 20);
        this.add(custName);
        txtCustName.setBounds(405, 200, 130, 20);
        this.add(txtCustName);

        amount.setBounds(270, 240, 350, 20);
        this.add(amount);
        txtAmount.setBounds(405, 240, 190, 20);
        this.add(txtAmount);

        recipientName.setBounds(265, 280, 360, 20);
        this.add(recipientName);
        txtRecipientName.setBounds(405, 280, 130, 20);
        this.add(txtRecipientName);

        btnTransfer.setBounds(250, 320, 100, 20);
        this.add(btnTransfer);

        btnCancel.setBounds(450, 320, 100, 20);
        this.add(btnCancel);

        btnClear.setBounds(350, 320, 100, 20);
        this.add(btnClear);

        this.setSize(800, 480);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Transfer");

        txtUserId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAction();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAction();
            }
        });

        txtReceiverId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchReceiverAction();
            }
        });

        btnReceiverSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchReceiverAction();
            }
        });

        btnTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    transferAction(isAdmin);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        txtAmount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    transferAction(isAdmin);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtReceiverId.setText("");
                txtCustName.setText("");
                txtAmount.setText("");
                txtRecipientName.setText("");
            }
        });
    }

    private void searchAction() {
        String accId = txtUserId.getText();

        if (accId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill up all the requirements.");
            return;
        }

        if (accId.length() != 12) {
            JOptionPane.showMessageDialog(null, "Account number must be 12 digits.");
            txtUserId.setText("");
            return;
        }

        if (BankingSystem.data.accountExists(accId)) {
            JOptionPane.showMessageDialog(null, "Account does not exist.");
            txtUserId.setText("");
            return;
        }

        customer = BankingSystem.data.getCustomer(accId);
        txtCustName.setText(customer.getName());
        txtUserId.setText("");
    }

    private void searchReceiverAction() {
        String accId = txtReceiverId.getText();

        if (accId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill up all the requirements.");
            return;
        }

        if (accId.length() != 12) {
            JOptionPane.showMessageDialog(null, "Account number must be 12 digits.");
            txtReceiverId.setText("");
            return;
        }

        if (BankingSystem.data.accountExists(accId)) {
            JOptionPane.showMessageDialog(null, "Account does not exist.");
            txtReceiverId.setText("");
            return;
        }

        receiver = BankingSystem.data.getCustomer(accId);
        txtRecipientName.setText(receiver.getName());
        txtReceiverId.setText("");
    }

    private void transferAction(boolean isAdmin) throws Exception {
        String amount = txtAmount.getText();

        if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill up all the requirements.");
            return;
        }

        if (!amount.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "Amount must be a number.");
            txtAmount.setText("");
            return;
        }

        if (customer.getBalance() < Integer.parseInt(amount)) {
            JOptionPane.showMessageDialog(null, "Insufficient balance.");
            txtAmount.setText("");
            return;
        }

        if (customer.getAccountID().equals(receiver.getAccountID())) {
            JOptionPane.showMessageDialog(null, "You cannot transfer to yourself.");
            txtAmount.setText("");
            return;
        }

        customer.transfer(receiver.getAccountID(), Integer.parseInt(amount));

        JOptionPane.showMessageDialog(null, "Transfer successful.");
        txtAmount.setText("");
        StringBuilder sb = new StringBuilder();
        sb.append("Transfer")
                .append("Account Number: ").append("XXXXXXXX")
                .append(customer.getAccountID(), 8, 12)
                .append("\n")
                .append("Transfer to: ").append("XXXXXXXX").append(receiver.getAccountID(), 8, 12)
                .append("\n")
                .append("Name: ").append(customer.getName())
                .append("\n")
                .append("Amount: ").append(amount)
                .append("\n")
                .append("Balance: ").append(customer.getBalance())
                .append("\n")
                .append("New Balance: ").append(customer.getBalance() - Double.parseDouble(amount));
        new Receipt(sb.toString(), isAdmin).setVisible(true);
        dispose();
    }
}

