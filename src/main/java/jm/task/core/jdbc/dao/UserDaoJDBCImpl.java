package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String  SQL_INSERT = "INSERT INTO users(name, lastName, age) VALUES (?,?,?)";
    private static final String  SQL_DELETE = "DELETE FROM users WHERE id = ?";
    private static final String  SQL_DELETE_ALL = "DELETE FROM users";
    private static final String  SQL_SELECT_ALL = "SELECT id, name, lastName, age FROM users";
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(SQL_CREATE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(SQL_DROP_TABLE);
        } catch (SQLSyntaxErrorException s) {
            //ничего не далаем если таблица не существует
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {
            stmt.setString(1,name);
            stmt.setString(2,lastName);
            stmt.setByte(3,age);
            stmt.executeUpdate();
            printAddedUserName(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_DELETE)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {
            List<User> users = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(SQL_DELETE_ALL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
