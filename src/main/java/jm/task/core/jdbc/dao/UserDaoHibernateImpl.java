package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSession;

public class UserDaoHibernateImpl implements UserDao {

    private UserDaoHibernateImpl() {
    }

    public static UserDaoHibernateImpl getUserDaoHibernateImplInstance() {
        return new UserDaoHibernateImpl();
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `users` ("
                + "`id` BIGINT NOT NULL AUTO_INCREMENT, "
                + "`name` VARCHAR(45) NOT NULL, "
                + "`lastname` VARCHAR(45) NOT NULL, "
                + "`age` TINYINT NOT NULL, "
                + "PRIMARY KEY (`id`))";

        try (Session session = getSession()) {
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Таблица не была создана!");
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS `users`";

        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Таблица не была удалена!");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Ошибка создания пользователя!");
            throw new HibernateException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                session.getTransaction().commit();
            } else {
                System.out.println("Пользователь с id: " + id + " не найден!");
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Ошибка удаления пользователя!");
                throw new HibernateException(e);
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = getSession()) {
            users = session.createQuery("FROM User", User.class).getResultList();
            System.out.println("Users: " + users);
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Ошибка получения пользователей!");
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Ошибка отчистки таблицы!");
            throw new HibernateException(e);
        }
    }
}
