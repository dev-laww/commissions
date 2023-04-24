package finalproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
    public static void deleteUser(String id) throws SQLException {
        UserModel model = new UserModel(UserModel.getUser(id));
        model.delete();
    }

    public static void deleteTransaction(String id) throws SQLException {
        TransactionModel model = new TransactionModel(TransactionModel.getTransaction(id));
        model.delete();
    }

    public static void addUser(User user) throws SQLException {
        UserModel model = new UserModel(user);
        model.save();
    }

    public static void addTransaction(Transaction transaction) throws SQLException {
        TransactionModel model = new TransactionModel(transaction);
        model.save();
    }

    public static void updateUser(User user) throws SQLException {
        UserModel model = new UserModel(user);
        model.save();
    }

    public static void updateTransaction(Transaction transaction) throws SQLException {
        TransactionModel model = new TransactionModel(transaction);
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
}
