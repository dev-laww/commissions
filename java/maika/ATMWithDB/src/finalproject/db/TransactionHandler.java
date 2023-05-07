/**
 * @author: tora
 */

package finalproject.db;

import java.sql.*;
import java.util.ArrayList;

public class TransactionHandler {
    private final Transaction transaction;

    TransactionHandler(Transaction transaction) {
        this.transaction = transaction;
    }

    public void save() throws SQLException {
        Transaction transaction = this.transaction;
        Connection conn = Database.getConnection();

        if (conn == null) {
            throw new SQLException("Connection failed");
        }

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM transactions WHERE id = ?");
        ps.setString(1, transaction.id);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            ps = conn.prepareStatement(
                    "INSERT INTO transactions (user_id, amount, type) VALUES (?, ?, ?)"
            );

            formatStatement(transaction, ps);
            ps.executeUpdate();
            return;
        }

        ps = conn.prepareStatement(
                "UPDATE transactions SET user_id = ?, amount = ?, type = ? WHERE id = " + transaction.id
        );

        formatStatement(transaction, ps);
        ps.executeUpdate();

        conn.close();
    }

    public void delete() throws SQLException {
        Transaction transaction = this.transaction;
        Connection conn = Database.getConnection();

        if (conn == null) {
            throw new SQLException("Connection failed");
        }

        PreparedStatement ps = conn.prepareStatement("DELETE FROM transactions WHRE id = ?");
        ps.setString(1, transaction.id);
        ps.executeUpdate();

        conn.close();
    }

    public static Transaction getTransaction(String id) throws SQLException{
        Transaction transaction;
        Connection conn = Database.getConnection();

        if (conn == null) {
            throw new SQLException("Connection failed");
        }

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            return null;
        }

        transaction = new Transaction(
                rs.getString("id"),
                rs.getString("user_id"),
                rs.getDouble("amount"),
                rs.getString("type"),
                rs.getTimestamp("timestamp")
        );

        conn.close();
        return transaction;
    }

    public static ArrayList<Transaction> getUserTransactions(String userID) throws SQLException {
        Connection conn = Database.getConnection();

        if (conn == null) {
            throw new SQLException("Connection failed");
        }

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM transactions WHERE user_id = ? ORDER BY STR_TO_DATE(datetime,'%m/%d/%Y%h:%i:%s %p')");
        ps.setString(1, userID);
        ResultSet rs = ps.executeQuery();

        ArrayList<Transaction> transactions = new ArrayList<>();

        while (rs.next()) {
            Transaction transaction = new Transaction(
                    rs.getString("id"),
                    rs.getString("user_id"),
                    rs.getDouble("amount"),
                    rs.getString("type"),
                    rs.getTimestamp("datetime")
            );

            transactions.add(transaction);
        }

        if (transactions.size() == 0) {
            return null;
        }

        conn.close();

        return transactions;
    }

    public static ArrayList<Transaction> getAll() throws SQLException {
        Connection conn = Database.getConnection();

        if (conn == null) {
            throw new SQLException("Connection failed");
        }

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM transactions");
        ResultSet rs = ps.executeQuery();

        ArrayList<Transaction> transactions = new ArrayList<>();

        while (rs.next()) {
            Transaction transaction = new Transaction(
                    rs.getString("id"),
                    rs.getString("user_id"),
                    rs.getDouble("amount"),
                    rs.getString("type"),
                    rs.getTimestamp("datetime")
            );

            transactions.add(transaction);
        }

        conn.close();
        return transactions;
    }

    private void formatStatement(Transaction transaction, PreparedStatement ps) throws SQLException {
        ps.setString(1, transaction.userID());
        ps.setDouble(2, transaction.amount);
        ps.setString(3, transaction.type);
    }
}
