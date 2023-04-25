package finalproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    public static HashMap<String, String> admin = new HashMap<>();
    public static ArrayList<User> users;
    public static ArrayList<Transaction> transactions;
    static {
        admin.put("admin", "admin");
        try {
            ArrayList<User> users = UserHandler.getAll();
            ArrayList<Transaction> transactions = TransactionHandler.getAll();

            Database.users = users.size() > 0 ? users : new ArrayList<>();
            Database.transactions = transactions.size() > 0 ? transactions : new ArrayList<>();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteUser(String id) throws SQLException {
        User user = UserHandler.getUser(id);
        UserHandler model = new UserHandler(user);
        model.delete();

        for (User it : users) {
            if (id.equals(it.id)) {
                users.remove(it);
                break;
            }
        }
    }

    public static void deleteTransaction(String id) throws SQLException {
        TransactionHandler model = new TransactionHandler(TransactionHandler.getTransaction(id));
        model.delete();

        for (Transaction it : transactions) {
            if (id.equals(it.id)) {
                transactions.remove(it);
                break;
            }
        }
    }

    public static void addUser(User user) throws SQLException {
        UserHandler model = new UserHandler(user);
        model.save();
        users.add(user);
    }

    public static void addTransaction(Transaction transaction) throws SQLException {
        TransactionHandler model = new TransactionHandler(transaction);
        model.save();
        transactions.add(transaction);
    }

    public static void updateUser(User user) throws SQLException {
        UserHandler model = new UserHandler(user);
        model.save();

        for (int i = 0; i < users.size(); i++) {
            if (user.id.equals(users.get(i).id)) {
                users.set(i, user);
                break;
            }
        }
    }

    public static void updateTransaction(Transaction transaction) throws SQLException {
        TransactionHandler model = new TransactionHandler(transaction);
        model.save();

        for (int i = 0; i < transactions.size(); i++) {
            if (transaction.id.equals(transactions.get(i).id)) {
                transactions.set(i, transaction);
                break;
            }
        }
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
        for (User user : users) {
            if (id.equals(user.id)) {
                return user;
            }
        }

        return null;
    }

    public static Transaction getTransaction(String id) {
        for (Transaction transaction : transactions) {
            if (id.equals(transaction.id)) {
                return transaction;
            }
        }

        return null;
    }
}
