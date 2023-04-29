/**
 * @author: tora
 */

package finalproject.db;

import java.sql.Timestamp;

public class Transaction {
    public String id;
    private final String userID;
    public final double amount;
    public final String type;
    public Timestamp timestamp;

    Transaction(String userID, double amount, String type) {
        this.userID = userID;
        this.amount = amount;
        this.type = type;
    }

    Transaction(String id, String userID, double amount, String type, Timestamp timestamp) {
        this.id = id;
        this.userID = userID;
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
    }

    public String userID() {
        return this.userID;
    }

    public String[] toArray() {
        return new String[] {
                this.timestamp.toString(),
                this.type.substring(0, 1).toUpperCase() + this.type.substring(1),
                String.valueOf(this.amount)
        };
    }
}
