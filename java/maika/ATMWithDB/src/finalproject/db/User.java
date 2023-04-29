/**
 * @author: tora
 */

package finalproject.db;

import javax.swing.*;

public class User {
    public final String name;
    public String id;
    private final String email;
    private final String contact;
    private final String address;
    private String pin;
    public double balance;
    public String status;

    public User(
            String id,
            String name,
            String email,
            String contact,
            String address,
            String pin,
            double balance,
            String status
    ) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.pin = pin;
        this.balance = balance;

        String[] stats = {
                "active",
                "closed",
                "locked"
        };

        for (String stat : stats) {
            if (stat.equals(status)) {
                this.status = status;
                break;
            }
        }
    }

    public User(
            String name,
            String email,
            String contact,
            String address,
            String pin,
            double balance,
            String status
    ) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.pin = pin;
        this.balance = balance;

        String[] stats = {
                "active",
                "closed",
                "locked"
        };

        for (String stat : stats) {
            if (stat.equals(status)) {
                this.status = status;
                break;
            }
        }
    }

    public User() {
        this.id = "0";
        this.name = "Default User";
        this.email = "email@mail.com";
        this.contact = "09121234567";
        this.address = "Address";
        this.pin = "0000";
        this.balance = 0;
        this.status = "Active";
    }

    public void deposit(double amount) throws Exception {
        this.balance += amount;
        Transaction transaction = new Transaction(this.id, amount, "deposit");

        Database.addTransaction(transaction);
        Database.updateUser(this);
    }

    public void withdraw(double amount) throws Exception {
        if (this.balance < amount) {
            throw new Exception("Insufficient balance");
        }

        this.balance -= amount;
        Transaction transaction = new Transaction(this.id, amount, "withdraw");
        Database.addTransaction(transaction);
        Database.updateUser(this);
    }

    public void transfer(String accID, double amount) throws Exception {
        User user = Database.getUser(accID);

        if (user == null) {
            throw new Exception("Account not found");
        }

        if (this.balance < amount) {
            throw new Exception("Insufficient balance");
        }

        this.balance -= amount;
        Transaction transaction = new Transaction(this.id, amount, "transfer");
        Database.updateUser(this);
        Database.addTransaction(transaction);
        user.balance += amount;
        transaction = new Transaction(user.id, amount, "receive");
        Database.updateUser(user);
        Database.addTransaction(transaction);
    }

    public void lock() {
        this.status = "locked";
        UserHandler db = new UserHandler(this);
        try {
            db.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unlock() {
        this.status = "active";
        UserHandler db = new UserHandler(this);
        try {
            db.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        this.status = "closed";
        UserHandler db = new UserHandler(this);
        try {
            db.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open() {
        this.status = "active";
    }

    public boolean isLocked() {
        return this.status.equals("locked");
    }

    public boolean checkPin(String pin) {
        if (pin.length() > 4)
            return false;

        return pin.equals(this.pin);
    }

    // Getters and setters
    public void updatePin(String newPin) {
        try {
            Integer.parseInt(newPin);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid pin");
            return;
        }

        if (pin.length() > 4) {
            JOptionPane.showMessageDialog(null, "Invalid pin");
            return;
        }

        this.pin = newPin;

        try {
            Database.updateUser(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String pin() {
        return this.pin;
    }

    public String name() {
        return this.name;
    }

    public String address() {
        return this.address;
    }

    public String email() {
        return this.email;
    }

    public String contact() {
        return this.contact;
    }

    public String[] toLockedArray() {
        return new String[]{
                this.status.substring(0, 1).toUpperCase() + this.status.substring(1),
                this.id,
                this.name
        };
    }

    public String[] toArray() {
        return new String[]{
                this.status.substring(0, 1).toUpperCase() + this.status.substring(1),
                this.id,
                this.name,
                this.address,
                this.email,
                this.contact,
                String.valueOf(this.balance),
        };
    }
}
