package finalproject.db;

import javax.swing.*;

public class User {
    public String id;
    public String name;
    public String address;
    public double balance;
    public String status;
    public final String atmMachine;

    private String pin;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private String barangay;
    private String municipality;
    private String province;
    private final String email;
    private final String contact;

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
            String pin,
            String email,
            double balance,
            String atmMachine
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
        this.atmMachine = atmMachine;

        this.name = String.format("%s %s %s", this.firstName, this.middleName, this.lastName);
        this.address = String.format("%s, %s, %s", this.barangay, this.municipality, this.province);
    }

    public User(
            String status,
            String firstName,
            String middleName,
            String lastName,
            String barangay,
            String municipality,
            String province,
            String contact,
            String pin,
            String email,
            double balance,
            String atmMachine
    ) {
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
        this.atmMachine = atmMachine;
    }

    public void deposit(double amount) throws Exception {
        this.balance += amount;
//        Transaction transaction = new Transaction(this.id, amount, "deposit");
//
//        Database.addTransaction(transaction);
//        Database.updateUser(this);
    }

    public void withdraw(double amount) {
//        if (this.balance < amount) {
//            throw new Exception("Insufficient balance");
//        }

        this.balance -= amount;
//        Transaction transaction = new Transaction(this.id, amount, "withdraw");
//        Database.addTransaction(transaction);
//        Database.updateUser(this);
    }

    public void transfer(String accID, double amount) {
//        User user = Database.getUser(accID);
//
//        if (user == null) {
//            throw new Exception("Account not found");
//        }
//
//        if (this.balance < amount) {
//            throw new Exception("Insufficient balance");
//        }

        this.balance -= amount;
    }


    public void lock() {
        this.status = "locked";
    }

    public void unlock() {
        this.status = "active";
    }

    public void close() {
        this.status = "closed";
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
    }

    public void updateAddress(String barangay, String municipality, String province) {
        this.barangay = barangay;
        this.municipality = municipality;
        this.province = province;

        this.address = String.format("%s, %s, %s", this.barangay, this.municipality, this.province);
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