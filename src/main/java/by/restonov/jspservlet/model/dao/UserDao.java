package by.restonov.jspservlet.model.dao;

import by.restonov.jspservlet.entity.User;
import by.restonov.jspservlet.exception.DaoException;

import java.util.Optional;

public interface UserDao {
    void addUserPassword(User user, String password) throws DaoException;
    Optional<String> findPasswordByLogin(String login) throws DaoException;
    Optional<User> findUserByLogin(String login) throws DaoException;
    User findUserByEmail(String email) throws DaoException;
}
