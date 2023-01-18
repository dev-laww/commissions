package project_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Deposit extends JFrame {
    Customer customer;
    JLabel header = new JLabel("BANKING SYSTEM");
    JLabel header2 = new JLabel("DEPOSIT MONEY");
    JLabel instruction = new JLabel("Please fill up the requirements below to deposit money.");
    JLabel userId = new JLabel("Enter Account Number:");
    JTextField txtSearch = new JTextField();
    JButton searchBtn = new JButton("Search");
    JLabel name = new JLabel("Name:");
    JLabel txtName = new JLabel();
    JLabel amount = new JLabel("Amount to deposit:");
    JTextField txtAmount = new JTextField();
    JButton btnDeposit = new JButton("Deposit");
    JButton btnClear = new JButton("Clear");
    JButton btnCancel = new JButton("Cancel");

    Deposit(boolean isAdmin) {
        ;
        Customer c = BankingSystem.customerInfo;

        if (c != null && !isAdmin) {
            customer = c;
            txtName.setText(customer.getName());
        }

        if (!isAdmin) {
            searchBtn.setVisible(false);
            txtSearch.setVisible(false);
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
        txtSearch.setBounds(405, 120, 130, 20);
        this.add(txtSearch);
        searchBtn.setBounds(540, 120, 90, 20);
        this.add(searchBtn);

        name.setBounds(265, 150, 360, 20);
        this.add(name);
        txtName.setBounds(405, 150, 130, 20);
        this.add(txtName);

        amount.setBounds(270, 200, 350, 20);
        this.add(amount);
        txtAmount.setBounds(405, 200, 190, 20);
        this.add(txtAmount);

        btnDeposit.setBounds(250, 250, 100, 20);
        this.add(btnDeposit);

        btnClear.setBounds(450, 250, 100, 20);
        this.add(btnClear);

        btnCancel.setBounds(350, 250, 100, 20);
        this.add(btnCancel);

        this.setSize(800, 480);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Deposit");

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAction();
            }
        });

        txtSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAction();
            }
        });

        btnDeposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositAction(isAdmin);
            }
        });

        txtAmount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositAction(isAdmin);
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtName.setText("");
                txtAmount.setText("");
                txtSearch.setText("");
                customer = null;
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
    }

    private void searchAction() {
        String accId = txtSearch.getText();

        if (accId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill up all the requirements.");
            return;
        }

        if (accId.length() != 12) {
            JOptionPane.showMessageDialog(null, "Account number must be 12 digits.");
            txtSearch.setText("");
            return;
        }

        if (BankingSystem.data.accountExists(accId)) {
            JOptionPane.showMessageDialog(null, "Account does not exist.");
            txtSearch.setText("");
            return;
        }

        customer = BankingSystem.data.getCustomer(accId);
        txtName.setText(customer.getName());
        txtSearch.setText("");
    }

    private void depositAction(boolean isAdmin) {
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

        if (Integer.parseInt(amount) < 1) {
            JOptionPane.showMessageDialog(null, "Amount must be greater than 0.");
            txtAmount.setText("");
            return;
        }

        if (customer == null) {
            JOptionPane.showMessageDialog(null, "Please search for an account first.");
            txtAmount.setText("");
            return;
        }


        customer.deposit(Double.parseDouble(amount));
        JOptionPane.showMessageDialog(null, "Deposit successful.");
        txtAmount.setText("");
        StringBuilder sb = new StringBuilder();
        sb.append("Deposit")
                .append("Account Number: ").append("XXXXXXXX")
                .append(customer.getAccountID(), 8, 12)
                .append("\n")
                .append("Name: ").append(customer.getName())
                .append("\n")
                .append("Amount: ").append(amount)
                .append("\n")
                .append("Balance: ").append(customer.getBalance() - Double.parseDouble(amount))
                .append("\n")
                .append("New Balance: ").append(customer.getBalance());
        new Receipt(sb.toString(), isAdmin).setVisible(true);
        dispose();
    }
}