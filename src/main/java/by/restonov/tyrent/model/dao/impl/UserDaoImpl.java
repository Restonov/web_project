package by.restonov.tyrent.model.dao.impl;

import by.restonov.tyrent.entity.User;
import by.restonov.tyrent.entity.builder.UserBuilder;
import by.restonov.tyrent.exception.DaoException;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.dao.AbstractDao;
import by.restonov.tyrent.model.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type User dao.
 */
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String INSERT_NEW_USER = "INSERT INTO users (first_name, last_name, login, email, role_id) VALUES (?, ?, ?, ?, ?)";
    //todo + order_id_list from bd
    private static final String SELECT_USER_BY_LOGIN = "SELECT user_id, first_name, last_name, login, email, role_id, order_id_list FROM users WHERE login = ?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT user_id, first_name, last_name, login, email, role_id FROM users WHERE email = ?";
    private static final String SELECT_USER_BY_ID = "SELECT user_id, first_name, last_name, login, email, role_id FROM users WHERE user_id = ?";
    private static final String SELECT_ALL_USERS = "SELECT user_id, first_name, last_name, login, email, role_id FROM users";
    private static final String SELECT_PASSWORD = "SELECT password FROM users WHERE login = ?";
    private static final String UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE login = ?";
    private static final String UPDATE_USER = "UPDATE users SET first_name = ?, last_name = ?, login = ?, email = ?, role_id = ?, order_id_list = order_id_list || {?} WHERE login = ?";
    private static final String UPDATE_USER_ORDER_ID_LIST = "UPDATE users SET order_id_list = order_id_list || ? WHERE login = ?";

    public Optional<User> findByName(String name) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        UserBuilder factory = UserBuilder.INSTANCE;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN)){
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = factory.buildUserFromResultSet(resultSet);
                optionalUser = Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error("Error while finding user by name", e);
            throw new DaoException("Error while finding user by name", e);
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Error while closing resultSet", e);
                e.printStackTrace();
            }
        }
        return optionalUser;
    }

    /**
     * Find user data by email
     * and create User with factory
     * @param email User's email
     * @return new User
     * @throws DaoException - dao layer exception
     */
    @Override
    public User findUserByEmail(String email) throws DaoException {
        User newUser = null;
        UserBuilder factory = UserBuilder.INSTANCE;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL)){
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                newUser = factory.buildUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error while finding user by email", e);
            throw new DaoException("Error while finding user by email", e);
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Error while closing resultSet", e);
                e.printStackTrace();
            }
        }
        return newUser;
    }

    @Override
    public void addOrderId(User user, int orderId) throws DaoException {
        if (user != null) {
            try(PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ORDER_ID_LIST)){
                statement.setInt(1, orderId);
                statement.setString(2, user.getLogin());
                statement.executeUpdate();
                logger.info("Order id successfully added to database");
            } catch (SQLException e) {
                logger.error("Error while adding new order id", e);
                throw new DaoException("Error while adding new order id", e);
            }
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        UserBuilder builder = UserBuilder.INSTANCE;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS)){
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User newUser = builder.buildUserFromResultSet(resultSet);
                userList.add(newUser);
            }
        } catch (SQLException e) {
            logger.error("Error while finding all users", e);
            throw new DaoException("Error while finding all users", e);
        } finally {
            assert resultSet != null;
            closeResultSet(resultSet);
        }
        return userList;
    }

    @Override
    public Optional<User> findById(Integer id) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserBuilder builder = UserBuilder.INSTANCE;
                User user = builder.buildUserFromResultSet(resultSet);
                optionalUser = Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error("Error while finding user by id", e);
            throw new DaoException("Error while finding user by id", e);
        }
        return optionalUser;
    }

    @Override
    public boolean add(User user) throws DaoException {
        boolean result = false;
        if (user != null) {
            try(PreparedStatement statement = connection.prepareStatement(INSERT_NEW_USER)){
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLastName());
                statement.setString(3, user.getLogin());
                statement.setString(4, user.getEmail());
                int roleId = user.receiveRoleId(user.getRole());
                statement.setInt(5, roleId);
                statement.executeUpdate();
                result = true;
                logger.info("User successfully added to database");
            } catch (SQLException e) {
                logger.error("Error while adding new user", e);
                throw new DaoException("Error while adding new user", e);
            }
        }
        return result;
    }

    @Override
    public void addUserPassword(User user, String password) throws DaoException {
        if (user != null && password != null) {
            try(PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD)){
                statement.setString(1, password);
                statement.setString(2, user.getLogin());
                statement.executeUpdate();
                logger.info("User password successfully added to database");
            } catch (SQLException e) {
                logger.error("Error while adding password", e);
                throw new DaoException("Error while adding password", e);
            }
        }
    }

    @Override
    public Optional<String> findPasswordByLogin(String login) throws DaoException {
        Optional<String> optionalPassword = Optional.empty();
        ResultSet set = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PASSWORD)){
            statement.setString(1, login);
            set = statement.executeQuery();
            if (set.next()) {
                String password = set.getString(ParameterName.PASSWORD);
                optionalPassword = Optional.ofNullable(password);
            }
        } catch (SQLException e) {
            logger.error("Error while finding user by id", e);
            throw new DaoException("Error while finding user by id", e);
        } finally {
            assert set != null;
            closeResultSet(set);
        }
        return optionalPassword;
    }

    @Override
    public User update(User user) throws DaoException {
        if (user != null) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLastName());
                statement.setString(3, user.getLogin());
                statement.setString(4, user.getEmail());
                int roleId = user.receiveRoleId(user.getRole());
                statement.setInt(5, roleId);
                statement.setString(5, user.getLogin());
                statement.executeUpdate();
                logger.info("User successfully updated");
            } catch (SQLException e) {
                logger.error("User not updated", e);
                throw new DaoException("User not updated", e);
            }
        }
        return user;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        throw new UnsupportedOperationException();
    }
}