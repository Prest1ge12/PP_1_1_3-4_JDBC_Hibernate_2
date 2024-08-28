package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import static jm.task.core.jdbc.util.Util.getSessionFactory;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();

        try {
            userService.createUsersTable();

            userService.saveUser("Вася", "Васильев", (byte) 22);
            userService.saveUser("Оля", "Морозова", (byte) 34);
            userService.saveUser("Иван", "Буча", (byte) 52);
            userService.saveUser("Олег", "Разумов", (byte) 22);


            for (User user : userService.getAllUsers()) {
                System.out.println(user);
            }

            userService.removeUserById(1);
            userService.removeUserById(2);
            userService.removeUserById(3);
            userService.removeUserById(4);

            userService.dropUsersTable();


        } finally {
            getSessionFactory().close();
        }

//        UserService userService = new UserServiceImpl();
//        userService.createUsersTable();
//
//        userService.saveUser("Вася", "Васильев", (byte) 22);
//        userService.saveUser("Оля", "Морозова", (byte) 34);
//        userService.saveUser("Иван", "Буча", (byte) 52);
//        userService.saveUser("Олег", "Разумов", (byte) 22);
//
//        for (int i = 0; i < userService.getAllUsers().size(); i++) {
//            System.out.println(userService.getAllUsers().get(i));
//        }
//
//        while (!userService.getAllUsers().isEmpty()) {
//            for (int i = 0; i < userService.getAllUsers().size(); i++) {
//                Long id = userService.getAllUsers().get(i).getId();
//                userService.removeUserById(id);
//            }
//        }
//        userService.dropUsersTable();
//
//        getInstance().closeConnection(); // закрытие соединения с бд
    }
}
