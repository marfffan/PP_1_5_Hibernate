package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ada", "Wong", (byte) 30);
        userService.saveUser("Leon", "Walker", (byte) 32);
        userService.saveUser("Dima", "Kuplinow", (byte) 35);
        userService.saveUser("Ashli", "Abama", (byte) 19);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
