package database;

import domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {

    private final String SQL_SELECT = "SELECT id_user, username, password FROM user";
    private final String SQL_SELECT_BY_ID = "SELECT id_user, username, password FROM user WHERE id_user=?";
    private final String SQL_SELECT_BY_NAME = "SELECT id_user, username, password FROM user WHERE username=?";
    private final String SQL_INSERT = "INSERT INTO user(username, password) VALUES(?,?)";
    private final String SQL_UPDATE = "UPDATE user SET username=?, password=? WHERE id_user=?";
    private final String SQL_DELETE = "DELETE FROM user WHERE id_user=?";

    public List<User> getAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> listUser = new ArrayList<>();
        User user = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_user");
                String name = rs.getString("username");
                String password = rs.getString("password");
                user = new User(id, name, password);
                listUser.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                DBConnection.close(rs);
                DBConnection.close(ps);
                DBConnection.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return listUser;
    }

    public User getById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(SQL_SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("username");
                String password = rs.getString("password");
                user = new User(id, name, password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                DBConnection.close(rs);
                DBConnection.close(ps);
                DBConnection.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return user;
    }

    public User getByName(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(SQL_SELECT_BY_NAME);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_user");
                String name = rs.getString("username");
                String password = rs.getString("password");
                user = new User(id, name, password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                DBConnection.close(rs);
                DBConnection.close(ps);
                DBConnection.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return user;
    }

    public int create(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        int affectedRows = 0;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            affectedRows = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(user.getUsername() + " ya existe en la base de datos");
            return affectedRows;
            //ex.printStackTrace(System.out);
        } finally {
            try {
                DBConnection.close(ps);
                DBConnection.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return affectedRows;
    }

    public User update(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        int affectedRows = 0;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getIdUser());
            affectedRows = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(user.getUsername() + " ya existe en la base de datos");
            return user;
            //ex.printStackTrace(System.out);
        } finally {
            try {
                DBConnection.close(ps);
                DBConnection.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return user;
    }

    public int delete(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        int affectedRows = 0;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, user.getIdUser());
            affectedRows = ps.executeUpdate();
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        } finally {
            try {
                DBConnection.close(ps);
                DBConnection.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return affectedRows;
    }
}
