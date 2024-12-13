package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import java.util.List;
import org.hibernate.*;
import static jm.task.core.jdbc.util.Util.*;

public class UserDaoHibernateImpl implements UserDao {
    /**
     * Не наследуется от Util а использует его статический метод
     */
    private SessionFactory sessionFactory = getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age TINYINT)");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }


    @Override
    public void dropUsersTable() {
        /*внутри try with resources не создавай try catch, все действия по откату транзакций реализуй в try resources;*/
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("DROP TABLE IF EXISTS users");
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            userList = session.createQuery("FROM User", User.class).getResultList();
            transaction.commit();
            return userList;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        /** Выводы на консоль не в слое Dao
         *             for (User user : userList) {
         *                 System.out.println("User: " + user.getId() + " " + user.getName() + " " + user.getLastName() + " " + user.getAge())
         */
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE TABLE users");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
