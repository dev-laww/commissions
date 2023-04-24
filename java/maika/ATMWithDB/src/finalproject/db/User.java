package finalproject.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
    public final String name;
    private final String id;
    private final String email;
    private final String contact;
    private final String address;
    private String pin;
    private double balance;
    public String status;

    User(
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

    User() {
        this.id = "0";
        this.name = "Default User";
        this.email = "email@mail.com";
        this.contact = "09121234567";
        this.address = "Address";
        this.pin = "0000";
        this.balance = 0;
        this.status = "Active";
    }

    public double checkBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        if (this.balance < amount) {
            throw new Exception("Insufficient balance");
        }

        this.balance -= amount;
    }

    public void transfer(String accID, double amount, Connection conn) throws Exception {
        User user = null;

        if (accID.equals(this.id)) {
            throw new Exception("You cannot transfer to yourself.");
        }

        if (this.balance < amount) {
            throw new Exception("Insufficient balance.");
        }

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            if (accID.equals(id)) {
                user = new User(
                        id,
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("contact"),
                        resultSet.getString("address"),
                        resultSet.getString("pin"),
                        resultSet.getDouble("balance"),
                        resultSet.getString("status")
                        );
                break;
            }
        }

        if (user == null) {
            throw new Exception("Account not found!");
        }

        this.balance -= amount;
        user.balance += amount;
    }

    public boolean checkPin(String pin) {
        if (pin.length() > 4)
            return false;

        return pin.equals(this.pin);
    }

    // Getters and setters
    public void updatePin(String newPin) {
        this.pin = newPin;
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
}
