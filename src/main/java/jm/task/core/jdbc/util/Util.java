package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    public Connection connection;
    private static final String bdSrc = "jdbc:mysql://localhost:3306/testbd";
    private static final String user = "Mrangel";
    private static final String password = "root";

    {
        try {
            connection = DriverManager.getConnection(bdSrc, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
