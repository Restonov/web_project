package by.restonov.tyrent.model.dao;

import by.restonov.tyrent.model.entity.User;
import by.restonov.tyrent.model.exception.DaoException;

import java.util.Optional;

/**
 * Interface for operations
 * with User's data
 *
 */
public interface UserDao {

    /**
     * add password to exists User
     *
     * @param user - exists User in DB
     * @param password - User password
     * @throws DaoException - general Exception of Dao layer
     */
    void addUserPassword(User user, String password) throws DaoException;

    /**
     * find in DB User password by exists User login
     *
     * @param login - User login
     * @return Optional of password
     * @throws DaoException - general Exception of Dao layer
     */
    Optional<String> findPasswordByLogin(String login) throws DaoException;

    /**
     * find in DB User by exists User login
     *
     * @param login - User login
     * @return Optional of User
     * @throws DaoException - general Exception of Dao layer
     */
    Optional<User> findUserByLogin(String login) throws DaoException;

    /**
     * find in DB User by exists User email
     *
     * @param email - User email
     * @return Optional of User
     * @throws DaoException - general Exception of Dao layer
     */
    Optional<User> findUserByEmail(String email) throws DaoException;
}
