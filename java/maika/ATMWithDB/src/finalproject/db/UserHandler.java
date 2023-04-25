package finalproject.db;

import java.sql.*;
import java.util.ArrayList;

public class UserHandler {
    private final User user;

    UserHandler(User user) {
        this.user = user;
    }

    public void save() throws SQLException {
        User user = this.user;
        Connection conn = Database.getConnection();

        if (conn == null) {
            throw new SQLException("Connection failed");
        }

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setString(1, user.id);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            ps = conn.prepareStatement(
                    "INSERT INTO users (name, address, contact, email, pin, status, balance) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            formatStatement(user, ps);
            ps.executeUpdate();

            conn.close();
            return;
        }

        ps = conn.prepareStatement(
                "UPDATE users SET name = ?, address = ?, contact = ?, email = ?, pin = ?, status = ?, balance = ? WHERE id = " + user.id
        );

        formatStatement(user, ps);
        ps.executeUpdate();

        conn.close();
    }

    public void delete() throws SQLException {
        User user = this.user;
        Connection conn = Database.getConnection();

        if (conn == null) {
            throw new SQLException("Connection failed");
        }

        PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHRE id = ?");
        ps.setString(1, user.id);
        ps.executeUpdate();

        conn.close();
    }

    public static User getUser(String id) throws SQLException {
        User user;
        Connection conn = Database.getConnection();

        if (conn == null) {
            return null;
        }

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            conn.close();
            return null;
        }

        user = new User(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("contact"),
                rs.getString("address"),
                rs.getString("pin"),
                rs.getDouble("balance"),
                rs.getString("status")
        );

        conn.close();
        return user;
    }

    public static ArrayList<User> getAll() throws SQLException {
        Connection conn = Database.getConnection();

        if (conn == null) {
            throw new SQLException("Connection failed");
        }

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
        ResultSet rs = ps.executeQuery();

        ArrayList<User> users = new ArrayList<>();

        while (rs.next()) {
            users.add(new User(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("contact"),
                    rs.getString("address"),
                    rs.getString("pin"),
                    rs.getDouble("balance"),
                    rs.getString("status")
            ));
        }

        conn.close();
        return users;
    }

    private void formatStatement(User user, PreparedStatement ps) throws SQLException {
        ps.setString(1, user.name);
        ps.setString(2, user.address());
        ps.setString(3, user.contact());
        ps.setString(4, user.email());
        ps.setString(5, user.pin());
        ps.setString(6, user.status);
        ps.setDouble(7, user.balance);
    }
}
