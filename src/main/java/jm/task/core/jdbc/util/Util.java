package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Configuration configuration = new Configuration().addAnnotatedClass(User.class);
    private static SessionFactory sessionFactory = configuration.buildSessionFactory();

    public static Session getSession (){
        Session session = sessionFactory.openSession();
        return session;
    }
//    private static final String bdSrc = "jdbc:mysql://localhost:3306/testbd";
//    private static final String user = "Mrangel";
//    private static final String password = "root";
//
//    private Util() {
//    }
//
//    public static Connection getConnection() throws SQLException {
//        Connection connection = null;
//        connection = DriverManager.getConnection(bdSrc, user, password);
//        return connection;
//    }
}

