package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
    // реализуйте настройку соеденения с БД

    /* Подключение с помощью Hibernate*/
    private static final Configuration configuration = new Configuration().addAnnotatedClass(User.class);
    private static SessionFactory sessionFactory;
    private static Session session;

    static {
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.getCurrentSession();
    }

    private Util() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null || !sessionFactory.isOpen()) {
            return configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    public static Session getSession() {
        if (session == null || !session.isOpen()) {
            return getSessionFactory().getCurrentSession();
        }
        return session;
    }
//    public static Configuration getConfiguration() {
//        return configuration;
//    }


//    /* Подключение с помощью JDBC*/
//    private static final String bdSrc = "jdbc:mysql://localhost:3306/testbd";
//    private static final String user = "Mrangel";
//    private static final String password = "root";
//    private static final String NameOfDb = "testbd";
//
//    public static Connection getConnection() throws SQLException {
//        Connection connection = null;
//        connection = DriverManager.getConnection(bdSrc, user, password);
//        return connection;
//    }
//
//    public static String getNameOfDb() {
//        return NameOfDb;
//    }
}

