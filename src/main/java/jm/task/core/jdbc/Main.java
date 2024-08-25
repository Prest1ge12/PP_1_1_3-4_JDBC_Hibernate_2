package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.lang.reflect.Array;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь



        UserDao userDao = new UserDaoJDBCImpl();
//        userDao.dropUsersTable();
//        userDao.createUsersTable();
//        userDao.saveUser("Вася", "Васильев", (byte) 22);
            System.out.println(userDao.getAllUsers().get(0));





//        UserDao userDao = new UserDaoJDBCImpl();
//        userDao.createUsersTable();
//
//        userDao.saveUser("Вася", "Васильев", (byte) 22);
//        userDao.saveUser("Оля", "Морозова", (byte) 34);
//        userDao.saveUser("Иван", "Буча", (byte) 52);
//        userDao.saveUser("Олег", "Разумов", (byte) 22);
//
//        for (int i = 0; i < userDao.getAllUsers().size(); i++) {
//            System.out.println(userDao.getAllUsers().get(i));
//
//        }
//
//        while (!userDao.getAllUsers().isEmpty()) {
//            for (int i = 0; i < userDao.getAllUsers().size(); i++) {
//                Long id = userDao.getAllUsers().get(i).getId();
//                userDao.removeUserById(id);
//            }
//        }
//        userDao.dropUsersTable();
    }
}
