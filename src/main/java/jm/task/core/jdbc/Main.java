package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserDaoJDBCImpl udj = new UserDaoJDBCImpl();

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        udj.createUsersTable();

        userService.saveUser("Ada", "Wong", (byte) 30);
        udj.saveUser("Ada", "Wong", (byte) 30);
        userService.saveUser("Leon", "Walker", (byte) 32);
        udj.saveUser("Leon", "Walker", (byte) 32);
        userService.saveUser("Dima", "Kuplinow", (byte) 35);
        udj.saveUser("Dima", "Kuplinow", (byte) 35);
        userService.saveUser("Ashli", "Abama", (byte) 19);

        userService.getAllUsers();
        udj.getAllUsers();

        userService.cleanUsersTable();
        udj.cleanUsersTable();

        userService.dropUsersTable();
        udj.dropUsersTable();

    }
}
