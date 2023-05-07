package finalproject.db;

import javax.swing.*;

public class User {
    public String id;
    private String name;
    public String address;
    public double balance;
    public String status;
    public final String atmId;
    public final String firstName;
    public final String middleName;
    public final String lastName;

    private String pin;
    private String barangay;
    private String municipality;
    private String province;
    private String email;
    private String contact;

    User(
            String id,
            String status,
            String firstName,
            String middleName,
            String lastName,
            String barangay,
            String municipality,
            String province,
            String contact,
            String email,
            String pin,
            double balance,
            String atmId
    ) {
        this.id = id;
        this.balance = balance;
        this.status = status;
        this.pin = pin;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.barangay = barangay;
        this.municipality = municipality;
        this.province = province;
        this.email = email;
        this.contact = contact;
        this.atmId = atmId;

        this.name = String.format("%s %s %s", this.firstName, this.middleName, this.lastName);
        this.address = String.format("%s, %s, %s", this.barangay, this.municipality, this.province);
    }

    public User(
            String firstName,
            String middleName,
            String lastName,
            String barangay,
            String municipality,
            String province,
            String contact,
            String email,
            String pin,
            double balance
    ) {
        this.balance = balance;
        this.status = "active";
        this.pin = pin;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.barangay = barangay;
        this.municipality = municipality;
        this.province = province;
        this.email = email;
        this.contact = contact;
        this.atmId = Database.atmId;
    }

    public void deposit(double amount) {
        this.balance += amount;

        if (!this.atmId.equals(Database.atmId))
            this.balance -= Database.serviceCharge;

        Transaction transaction = new Transaction(this.id, amount, "deposit");

        Database.saveTransaction(transaction);
        Database.saveUser(this);
    }

    public void withdraw(double amount) throws Exception {
        if (this.balance < amount) {
            throw new Exception("Insufficient balance");
        }

        if (!this.atmId.equals(Database.atmId))
            this.balance -= Database.serviceCharge;

        this.balance -= amount;
        Transaction transaction = new Transaction(this.id, amount, "withdraw");

        Database.saveTransaction(transaction);
        Database.saveUser(this);
    }

    public void transfer(String accID, double amount) throws Exception {
        User user = Database.getUser(accID);

        if (user == null) {
            throw new Exception("Account not found");
        }

        if (this.balance < amount) {
            throw new Exception("Insufficient balance");
        }

        if (!this.atmId.equals(Database.atmId))
            this.balance -= Database.serviceCharge;

        this.balance -= amount;

        Transaction transaction = new Transaction(this.id, user.id, amount);

        Database.saveTransaction(transaction);
        Database.saveUser(this);

        transaction = new Transaction(user.id, amount, "receive");
        user.balance += amount;

        Database.saveTransaction(transaction);
        Database.saveUser(user);
    }

    public void lock() {
        this.status = "locked";

        Database.saveUser(this);
    }

    public void unlock() {
        this.status = "active";

        Database.saveUser(this);
    }

    public boolean close() {
        this.status = "closed";

        return Database.saveUser(this) && Database.deleteUser(this.id);
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

        if (!Database.saveUser(this)) return;

        this.pin = newPin;
    }

    public void updateDetails(String barangay, String municipality, String province, String email, String contact) {
        this.email = email;
        this.contact = contact;
        this.barangay = barangay;
        this.municipality = municipality;
        this.province = province;

        this.address = String.format("%s, %s, %s", this.barangay, this.municipality, this.province);
    }

    public String name() {
        return this.name;
    }

    public String pin() {
        return this.pin;
    }

    public String barangay() {
        return this.barangay;
    }

    public String municipality() {
        return this.municipality;
    }

    public String province() {
        return this.province;
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