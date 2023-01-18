package project_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Customer {
    private final String accountID;
    private final ArrayList<HashMap<String, Double>> transactions = new ArrayList<>();
    private String name;
    private String email;
    private String contactNo;
    private String pin;
    private double balance;
    private boolean isLocked = false;

    public Customer(String name, String email, String contactNo, String accountID, String pin) {
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.accountID = accountID;
        this.pin = pin;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return this.contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    public void setLocked() {
        this.isLocked = !isLocked;
    }

    public String getTransactions(boolean b) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        if (this.transactions.isEmpty()) {
            sb.append("No transactions yet.");
            return sb.toString();
        }

        for (HashMap<String, Double> transaction : this.transactions) {
            if (b && counter > 5) {
                break;
            }

            for (String key : transaction.keySet()) {
                sb.append(String.format("%-26s%24s%n", key, (transaction.get(key) > 0 ? "+ $" : "- $") + Math.abs(transaction.get(key))));
            }
            counter++;
        }
        return sb.toString();
    }

    public void addTransaction(HashMap<String, Double> transaction) {
        this.transactions.add(transaction);
    }

    public String getAccountID() {
        return this.accountID;
    }

    public String getPin() {
        return this.pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return this.balance;
    }

    public void transfer(String accID, double amount) throws Exception {
        Customer customer = null;

        if (accID.equals(this.accountID)) {
            throw new Exception("You cannot transfer to yourself.");
        }

        for (Customer c : BankingSystem.data.getCustomers()) {
            if (c.getAccountID().equals(accID)) {
                customer = c;
                break;
            }
        }

        if (this.balance < amount) {
            throw new Exception("Insufficient balance.");
        }

        try {
            assert customer != null;
        } catch (Exception e) {
            throw new Exception("Account ID not found.");
        }

        this.balance -= amount;
        customer.balance += amount;
        this.addTransaction(new HashMap<>(
                Map.of("Transfer to " + customer.getAccountID(), -amount)
        ));
        customer.addTransaction(new HashMap<>(
                Map.of("Transfer from " + this.getAccountID(), amount)
        ));
    }

    public void deposit(double amount) {
        this.balance += amount;
        addTransaction(new HashMap<>(
                Map.of("Deposit", amount)
        ));
    }

    public void withdraw(double amount) throws Exception {
        if (this.balance < amount) {
            throw new Exception("Insufficient balance");
        }

        this.balance -= amount;
        addTransaction(new HashMap<>(
                Map.of("Withdraw", -amount)
        ));
    }

    @Override
    public String toString() {
        return String.format("Name: %s%nAccount ID: %s%nPin: %s%nBalance: %.2f%n", this.name, this.accountID, this.pin, this.balance);
    }
}
