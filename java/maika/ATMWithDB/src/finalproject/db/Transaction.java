package finalproject.db;

import java.util.Date;

public class Transaction {
    public String id;
    private final String userID;
    public final double amount;
    public final String type;

    Transaction(String userID, double amount, String type) {
        this.userID = userID;
        this.amount = amount;
        this.type = type;
    }

    Transaction(String id, String userID, double amount, String type) {
        this.id = id;
        this.userID = userID;
        this.amount = amount;
        this.type = type;
    }

    public String userID() {
        return this.userID;
    }


}
