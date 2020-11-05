package by.restonov.tyrent.model.dao;

import by.restonov.tyrent.entity.User;
import by.restonov.tyrent.exception.DaoException;

import java.util.Optional;

public interface UserDao {
    void addUserPassword(User user, String password) throws DaoException;
    Optional<String> findPasswordByLogin(String login) throws DaoException;
    Optional<User> findUserByLogin(String login) throws DaoException;
    User findUserByEmail(String email) throws DaoException;
    void addOrderId(User user, int orderId) throws DaoException;
}
