package by.restonov.tyrent.model.dao;

import by.restonov.tyrent.model.entity.User;
import by.restonov.tyrent.model.exception.DaoException;

import java.util.Optional;

public interface UserDao {
    void addUserPassword(User user, String password) throws DaoException;
    Optional<String> findPasswordByLogin(String login) throws DaoException;
    Optional<User> findUserByLogin(String login) throws DaoException;
    Optional<User> findUserByEmail(String email) throws DaoException;
}
