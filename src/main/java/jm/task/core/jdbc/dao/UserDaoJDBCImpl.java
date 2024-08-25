package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            try {
                String SQL = "CREATE TABLE `testbd`.`users` (\n" +
                        "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                        "  `name` VARCHAR(45) NOT NULL,\n" +
                        "  `lastname` VARCHAR(45) NOT NULL,\n" +
                        "  `age` TINYINT NOT NULL,\n" +
                        "  PRIMARY KEY (`id`));";
                statement.executeUpdate(SQL);
            } catch (SQLException e) {
                System.out.println("Таблица уже существует");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            try {
                String SQL = "DROP TABLE `testbd`.`users`;";
                statement.executeUpdate(SQL);
            } catch (SQLException e) {
                System.out.println("Не возможно удалить таблицу: таблицы не сущетвует");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = connection.createStatement()) {
            try {
                String SQL = "INSERT INTO users VALUES( id ,'" + name + "','" + lastName + "','" + age + "')";
                statement.executeUpdate(SQL);
                System.out.println("User с именем — " + name + " добавлен в базу данных");
            } catch (RuntimeException e) {
                System.out.println("Отсутствует таблица для добавления User-а");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            try {
                String SQL = "DELETE fROM users where id";
                statement.executeUpdate(SQL);
            } catch (SQLException e) {
                System.out.println("User не сущетвует");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String SQL = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL);) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String SQL = "DELETE fROM users";
            statement.executeUpdate(SQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
