/**
 * @author: tora
 */

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
                    """
                                INSERT INTO users (
                                    status,
                                    first_name,
                                    middle_name,
                                    last_name,
                                    barangay,
                                    municipality,
                                    province,
                                    email,
                                    contact,
                                    pin,
                                    balance,
                                    atm_id
                                ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)
                            """
            );

            formatStatement(user, ps);
            ps.executeUpdate();

            conn.close();
            return;
        }

        ps = conn.prepareStatement(
                """
                            UPDATE users SET
                                status = ?,
                                first_name = ?,
                                middle_name = ?,
                                last_name = ?,
                                barangay = ?,
                                municipality = ?,
                                province = ?,
                                contact = ?,
                                email = ?,
                                pin = ?,
                                balance = ?,
                                atm_id = ?
                            WHERE id =
                        """ + user.id
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

        PreparedStatement ps = conn.prepareStatement("INSERT INTO dropped_users SELECT * FROM users WHERE id = ?");
        ps.setString(1, user.id);
        ps.executeUpdate();

        ps = conn.prepareStatement("INSERT INTO dropped_transactions SELECT * FROM transactions WHERE user_id = ?");
        ps.setString(1, user.id);
        ps.executeUpdate();

        ps = conn.prepareStatement("DELETE FROM users WHERE id = ?");
        ps.setString(1, user.id);
        ps.executeUpdate();

        conn.close();
    }

    public static User getUser(String id) throws SQLException {
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

        User user = userFromRs(rs);

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
            users.add(userFromRs(rs));
        }

        conn.close();
        return users;
    }

    private static User userFromRs(ResultSet rs) throws SQLException {
        return new User(
                rs.getString("id"),
                rs.getString("status"),
                rs.getString("first_name"),
                rs.getString("middle_name"),
                rs.getString("last_name"),
                rs.getString("barangay"),
                rs.getString("municipality"),
                rs.getString("province"),
                rs.getString("contact"),
                rs.getString("email"),
                rs.getString("pin"),
                rs.getDouble("balance"),
                rs.getString("atm_id")
        );
    }

    private void formatStatement(User user, PreparedStatement ps) throws SQLException {
        ps.setString(1, user.status);
        ps.setString(2, user.firstName);
        ps.setString(3, user.middleName);
        ps.setString(4, user.lastName);
        ps.setString(5, user.barangay());
        ps.setString(6, user.municipality());
        ps.setString(7, user.province());
        ps.setString(8, user.contact());
        ps.setString(9, user.email());
        ps.setString(10, user.pin());
        ps.setDouble(11, user.balance);
        ps.setString(12, user.atmId);
    }
}