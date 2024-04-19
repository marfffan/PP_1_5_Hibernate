package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImpl extends Util implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoJDBCImpl.class.getName());

    Connection conn = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                " name VARCHAR(255)," +
                " lastName VARCHAR(255)," +
                " age TINYINT)";
        // Оборачиваем Ststement в try с ресурсами для автоматического закрытия
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            //Добавляем логер и вывод ошибки в консоль
            logger.log(Level.SEVERE, "Ошибка создания таблицы пользователей", e);
            throw new RuntimeException("Ошибка создания таблицы пользователей", e);
        }
    }

    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS users";
        // Оборачиваем Ststement в try с ресурсами для автоматического закрытия
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate(sql);
            //Добавил закрытие ресурса connections ри удалении таблицы
            conn.close();
        } catch (SQLException e) {
            //Добавляем логер и вывод ошибки в консоль
            logger.log(Level.SEVERE, "Ошибка удаления таблицы пользователей", e);
            throw new RuntimeException("Ошибка удаления таблицы пользователей", e);
        }
        //Добавил закрытие ресурса connections ри удалении таблицы
//        try (conn.close()) {
//            System.out.println("Соединение закрыто");
//        } catch (SQLException e) {
//            //Добавляем логер и вывод ошибки в консоль
//            logger.log(Level.SEVERE, "Ошибка закрытия соединения", e);
//            throw new RuntimeException("Ошибка закрытия соединения", e);

    }


    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        // Оборачиваем Ststement в try с ресурсами для автоматического закрытия
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            //Добавляем логер и вывод ошибки в консоль
            logger.log(Level.SEVERE, "Ошибка сохранения пользователя", e);
            throw new RuntimeException("Ошибка сохранения пользователя", e);
        }
    }

    public void removeUserById(long id) {

        String sql = "DELETE FROM users WHERE id = ?";
        // Оборачиваем Ststement в try с ресурсами для автоматического закрытия
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            //Добавляем логер и вывод ошибки в консоль
            logger.log(Level.SEVERE, "Ошибка удаления пользователя по ID", e);
            throw new RuntimeException("Ошибка удаления пользователя по ID", e);
        }
    }


    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        // Оборачиваем Ststement и resultset в try с ресурсами для автоматического закрытия
        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            //Добавляем логер и вывод ошибки в консоль
            logger.log(Level.SEVERE, "Ошибка возврата пользователей", e);
            throw new RuntimeException("Ошибка возврата пользователей", e);

        }
        return userList;
    }


    public void cleanUsersTable() {

        String sql = "DELETE FROM users";
        // Оборачиваем Ststement в try с ресурсами для автоматического закрытия
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            //Добавляем логер и вывод ошибки в консоль
            logger.log(Level.SEVERE, "Ошибка очистки таблицы", e);
            throw new RuntimeException("Ошибка очистки таблицы", e);
        }
    }
}
