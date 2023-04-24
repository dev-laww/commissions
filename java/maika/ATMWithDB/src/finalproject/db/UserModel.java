package finalproject.db;

import java.sql.*;

public class UserModel {
    private final User user;
    private Connection conn;

    UserModel(User user) {
        this.user = user;

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
        User user = this.user;
        Connection conn = this.conn;

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
    }

    public void delete() throws SQLException {
        User user = this.user;
        Connection conn = this.conn;

        PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHRE id = ?");
        ps.setString(1, user.id);
        ps.executeUpdate();
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