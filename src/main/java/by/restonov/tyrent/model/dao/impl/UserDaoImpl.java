package by.restonov.tyrent.model.dao.impl;

import by.restonov.tyrent.model.entity.User;
import by.restonov.tyrent.model.dao.builder.UserDaoBuilder;
import by.restonov.tyrent.model.exception.DaoException;
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
 * DAO Interaction with DB "users" table
 */
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String INSERT_NEW_USER = "INSERT INTO users (user_first_name, user_last_name, user_login, user_email, user_phone, user_state, user_role) VALUES (?, ?, ?, ?, ?, ?::user_state, ?::user_role)";
    private static final String SELECT_USER_BY_LOGIN ="SELECT user_id, user_first_name, user_last_name, user_login, user_email, user_phone, user_state, user_role FROM users WHERE user_login = ?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT user_id, user_first_name, user_last_name, user_login, user_email, user_phone, user_state, user_role FROM users WHERE user_email = ?";
    private static final String SELECT_USER_BY_ID = "SELECT user_id, user_first_name, user_last_name, user_login, user_email, user_phone, user_state, user_role FROM users WHERE user_id = ?";
    private static final String SELECT_ALL_USERS = "SELECT user_id, user_first_name, user_last_name, user_login, user_email, user_phone, user_state, user_role FROM users ORDER BY user_id ASC";
    private static final String SELECT_PASSWORD = "SELECT user_password FROM users WHERE user_login = ?";
    private static final String UPDATE_PASSWORD = "UPDATE users SET user_password = ? WHERE user_login = ?";
    private static final String UPDATE_USER = "UPDATE users SET user_first_name = ?, user_last_name = ?, user_login = ?, user_email = ?, user_phone = ?, user_state = ?::user_state, user_role = ?::user_role WHERE user_id = ?";

    @Override
    public Optional<User> findByName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        UserDaoBuilder builder = UserDaoBuilder.INSTANCE;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN)){
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = builder.build(resultSet);
                optionalUser = Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by name", e);
        } finally {
            assert resultSet != null;
            closeResultSet(resultSet);
        }
        return optionalUser;
    }

    /**
     * Find user data by email
     * and create User with builder
     * @param email User's email
     * @return Optional
     * @throws DaoException - dao layer exception
     */
    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        UserDaoBuilder builder = UserDaoBuilder.INSTANCE;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL)){
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User newUser = builder.build(resultSet);
                optionalUser = Optional.of(newUser);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by email", e);
        } finally {
            assert resultSet != null;
            closeResultSet(resultSet);
        }
        return optionalUser;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        UserDaoBuilder builder = UserDaoBuilder.INSTANCE;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS)){
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User newUser = builder.build(resultSet);
                userList.add(newUser);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding all users", e);
        } finally {
            assert resultSet != null;
            closeResultSet(resultSet);
        }
        return userList;
    }

    @Override
    public Optional<User> findById(Long id) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)){
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserDaoBuilder builder = UserDaoBuilder.INSTANCE;
                User user = builder.build(resultSet);
                optionalUser = Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by id", e);
        } finally {
            assert resultSet != null;
            closeResultSet(resultSet);
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
                statement.setString(5, user.getPhone());
                statement.setString(6, user.getState().toString());
                statement.setString(7, user.getRole().toString());
                statement.executeUpdate();
                result = true;
                logger.info("User: {} successfully added to database", user.getLogin());
            } catch (SQLException e) {
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
                logger.info("User: {} password successfully added to database", user.getLogin());
            } catch (SQLException e) {
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
                String password = set.getString(ParameterName.USER_PASSWORD);
                optionalPassword = Optional.of(password);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by id", e);
        } finally {
            assert set != null;
            closeResultSet(set);
        }
        return optionalPassword;
    }

    @Override
    public boolean update(User user) throws DaoException {
        boolean result = false;
        if (user != null) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLastName());
                statement.setString(3, user.getLogin());
                statement.setString(4, user.getEmail());
                statement.setString(5, user.getPhone());
                statement.setString(6, user.getState().toString());
                statement.setString(7, user.getRole().toString());
                statement.setLong(8, user.getId());
                statement.executeUpdate();
                result = true;
                logger.info("User successfully updated");
            } catch (SQLException e) {
                throw new DaoException("User not updated", e);
            }
        }
        return result;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }
}