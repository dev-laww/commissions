/**
 * @author: tora
 */

package finalproject.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    public static HashMap<String, String> admin = new HashMap<>();

    static {
        admin.put("admin", "admin");
    }

    public static void deleteUser(String id) throws SQLException {
        User user = UserHandler.getUser(id);
        UserHandler model = new UserHandler(user);
        model.delete();
    }

    public static void addUser(User user) throws SQLException {
        UserHandler model = new UserHandler(user);
        model.save();
    }

    public static void addTransaction(Transaction transaction) throws SQLException {
        TransactionHandler model = new TransactionHandler(transaction);
        model.save();
    }

    public static void updateUser(User user) throws SQLException {
        UserHandler model = new UserHandler(user);
        model.save();
    }

    public static Connection getConnection() {
        Connection conn;
        String url = "jdbc:mysql://localhost:3306/atm";
        String username = "root";
        String password = "tora";

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return conn;
    }

    public static User getUser(String id) {
        try {
            return UserHandler.getUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Transaction getTransaction(String id) {
        try {
            return TransactionHandler.getTransaction(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Transaction> getUserTransactions(String userID) {
        try {
            return TransactionHandler.getUserTransactions(userID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getLastUserID() {
        Connection conn = Database.getConnection();

        if (conn == null) {
            return null;
        }

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT id from users ORDER BY id DESC LIMIT 1");
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return "10000000";
            }

            int id = rs.getInt("id");

            return String.valueOf(id + 1);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<User> users() {
        try {
            return UserHandler.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static ArrayList<Transaction> transactions() {
        try {
            return TransactionHandler.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static Transaction getLastTransaction() {
        Connection conn = Database.getConnection();

        if (conn == null)
            return null;

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from transactions ORDER BY datetime DESC LIMIT 1");
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            return new Transaction(
                    rs.getString("id"),
                    rs.getString("user_id"),
                    rs.getDouble("amount"),
                    rs.getString("type"),
                    rs.getTimestamp("datetime")
            );

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
