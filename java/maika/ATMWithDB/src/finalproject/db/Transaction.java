/**
 * @author: tora
 */

package finalproject.db;

import java.sql.Timestamp;

public class Transaction {
    public String id;
    private final String userID;
    private String transferUserID;
    public final double amount;
    public final String type;
    public Timestamp timestamp;

    public Transaction(String userID, double amount, String type) {
        this.userID = userID;
        this.amount = amount;
        this.type = type;
    }

    public Transaction(String userID, String transferUserID, double amount) {
        this.userID = userID;
        this.transferUserID = transferUserID;
        this.amount = amount;
        this.type = "transfer";
    }

    Transaction(String id, String userID, String transferUserID, double amount, String type, Timestamp timestamp) {
        this.id = id;
        this.userID = userID;
        this.transferUserID = transferUserID;
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
    }

    public String userID() {
        return this.userID;
    }

    public String transferUserID() {
        return this.transferUserID;
    }

    public String[] toArray() {
        return new String[] {
                this.timestamp.toString(),
                this.type.substring(0, 1).toUpperCase() + this.type.substring(1),
                String.valueOf(this.amount)
        };
    }
}