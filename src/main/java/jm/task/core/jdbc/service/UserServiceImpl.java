package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    /// Меняем значение isUseHibernate в зависимости от задачи:
    /// 1.1.3 - false
    /// 1.1.4 - true
    private final boolean isUseHibernate = true;
    private final UserDao userDaoJDBC = new UserDaoJDBCImpl();
    private final UserDao userDaoHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() {
        getDao().createUsersTable();
    }

    public void dropUsersTable() {
        getDao().dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        getDao().saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        getDao().removeUserById(id);
    }

    public List<User> getAllUsers() {
        return getDao().getAllUsers();
    }

    public void cleanUsersTable() {
        getDao().cleanUsersTable();
    }

    /// Private
    private UserDao getDao() {
        if (isUseHibernate) {
            return userDaoHibernate;
        }
        return userDaoJDBC;
    }
}
