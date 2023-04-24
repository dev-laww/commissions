package finalproject.db;

import java.sql.*;

public class TransactionModel {
    private final Transaction transaction;
    private Connection conn;

    TransactionModel(Transaction transaction) {
        this.transaction = transaction;

        String url = "jdbc:mysql://localhost:3306/atm";
        String username = "root";
        String password = "tora";

        try {
            this.conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() throws SQLException {
        Transaction transaction = this.transaction;
        Connection conn = this.conn;

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM transactions WHERE id = ?");
        ps.setString(1, transaction.id);
        ResultSet rs = ps.executeQuery();

        if (rs.wasNull()) {
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
    }

    public void delete() throws SQLException {
        Transaction transaction = this.transaction;
        Connection conn = this.conn;

        PreparedStatement ps = conn.prepareStatement("DELETE FROM transactions WHRE id = ?");
        ps.setString(1, transaction.id);
        ps.executeUpdate();
    }

    public void close() throws SQLException {
        this.conn.close();
    }

    private void formatStatement(Transaction transaction, PreparedStatement ps) throws SQLException {
        ps.setString(1, transaction.userID());
        ps.setDouble(2, transaction.amount);
        ps.setString(3, transaction.type);
    }
}
