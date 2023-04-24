package finalproject.db;

import java.util.Date;

public class Transaction {
    public String id;
    private String userID;
    private double amount;
    private String type;

    Transaction(String userID, double amount, String type) {
        this.userID = userID;
        this.amount = amount;
        this.type = type;
    }
}
