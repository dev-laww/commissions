package finalproject.db;

import java.sql.*;

public class UserModel {
    private final User user;

    UserModel(User user) {
        this.user = user;
    }

    public void save() throws SQLException {
        User user = this.user;
        Connection conn = DatabaseHandler.getConnection();

        if (conn == null) {
            throw new SQLException("Connection failed");
        }

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setString(1, user.id);
        ResultSet rs = ps.executeQuery();

        if (rs.wasNull()) {
            ps = conn.prepareStatement(
                    "INSERT INTO users (name, address, contact, email, pin, status, balance) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            formatStatement(user, ps);
            ps.executeUpdate();
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
        Connection conn = DatabaseHandler.getConnection();

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
        Connection conn = DatabaseHandler.getConnection();

        if (conn == null) {
            throw new SQLException("Connection failed");
        }

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.wasNull()) {
            throw new SQLException("User not found");
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
