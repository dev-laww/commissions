package project_1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Withdraw extends JFrame {
    Customer customer;
    JLabel header = new JLabel("BANKING SYSTEM");
    JLabel header2 = new JLabel("WITHDRAW MONEY");
    JLabel instruction = new JLabel("Please fill up the requirements below to withdraw money.");
    JLabel userId = new JLabel("Enter Account Number:");
    JTextField txtUserId = new JTextField();
    JButton btnSearch = new JButton("Search");
    JLabel custName = new JLabel("Customer Name:");
    JLabel txtCustName = new JLabel();
    JLabel amount = new JLabel("Amount to withdraw:");
    JTextField txtAmount = new JTextField();
    JButton btnWithdraw = new JButton("Withdraw");
    JButton btnClear = new JButton("Clear");
    JButton btnCancel = new JButton("Cancel");

    public Withdraw(boolean isAdmin) {

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

        custName.setBounds(285, 160, 350, 20);
        this.add(custName);
        txtCustName.setBounds(405, 160, 190, 20);
        this.add(txtCustName);

        amount.setBounds(270, 200, 350, 20);
        this.add(amount);
        txtAmount.setBounds(405, 200, 190, 20);
        this.add(txtAmount);

        btnWithdraw.setBounds(250, 250, 100, 20);
        this.add(btnWithdraw);

        btnClear.setBounds(450, 250, 100, 20);
        this.add(btnClear);

        btnCancel.setBounds(350, 250, 100, 20);
        this.add(btnCancel);

        this.setSize(800, 480);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Withdraw");

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAction();
            }
        });

        txtUserId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAction();
            }
        });

        btnWithdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    withdrawAction(isAdmin);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        txtAmount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    withdrawAction(isAdmin);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCustName.setText("");
                txtAmount.setText("");
                txtUserId.setText("");
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

    private void withdrawAction(boolean isAdmin) throws Exception {
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

        customer.withdraw(Double.parseDouble(amount));
        JOptionPane.showMessageDialog(null, "Deposit successful.");
        txtAmount.setText("");
        StringBuilder sb = new StringBuilder();
        sb.append("Withdraw")
                .append("Account Number: ").append("XXXXXXXX")
                .append(customer.getAccountID(), 8, 12)
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
