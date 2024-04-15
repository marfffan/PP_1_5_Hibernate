package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.util.List;
import org.hibernate.*;

public class UserDaoHibernateImpl extends Util implements UserDao {

    SessionFactory sessionFactory = getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                SQLQuery query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age TINYINT)");
                query.executeUpdate();
                transaction.commit();

            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                SQLQuery query = session.createSQLQuery("DROP TABLE IF EXISTS users");
                query.executeUpdate();
                transaction.commit();

            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> userList;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            userList = session.createQuery("FROM User", User.class).getResultList();
            transaction.commit();

            for (User user : userList) {
                System.out.println("User: " + user.getId() + " " + user.getName() + " " + user.getLastName() + " " + user.getAge());
            }
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE TABLE users");
            query.executeUpdate();
            transaction.commit();
        }
    }
}
