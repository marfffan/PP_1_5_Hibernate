package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserService usj = new UserServiceImpl();

        usj.createUsersTable();
        usj.saveUser("Ada", "Wong", (byte) 30);
        usj.saveUser("Leon", "Walker", (byte) 32);
        usj.saveUser("Dima", "Kuplinow", (byte) 35);


        usj.getAllUsers();
        usj.cleanUsersTable();
        usj.dropUsersTable();

    }
}
