package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    String  SQL_CREATE_TABLE = """
                    CREATE TABLE IF NOT EXISTS users
                    (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(50) NOT NULL,
                    lastName VARCHAR(50) NOT NULL,
                    age TINYINT NOT NULL)
                    """;
    String  SQL_DROP_TABLE = "DROP TABLE  IF EXISTS users";

    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();

    default void printAddedUserName(String name) {
        System.out.printf("User с именем — %s добавлен в базу данных\n", name);
    }
}
