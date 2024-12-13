package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private UserDao userDaoHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
    }
/**Выводы на консоль не в слое DAO*/
    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.println("User с именем - " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        /** Распечатали список Юзеров */
        List<User> users = userDaoHibernate.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

        return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable();
    }
}
