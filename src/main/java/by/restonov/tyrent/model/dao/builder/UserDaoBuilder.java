package by.restonov.tyrent.model.dao.builder;

import by.restonov.tyrent.model.entity.User;
import by.restonov.tyrent.manager.ParameterName;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User builder
 */
public enum UserDaoBuilder {
    /**
     * Thread-safe Singleton instance
     */
    INSTANCE;

    /**
     * Build User from ResultSet data
     *
     * @param set ResultSet
     * @return User instance
     * @throws SQLException DB access error
     */
    public User build(ResultSet set) throws SQLException {
        String firstName = set.getString(ParameterName.USER_FIRST_NAME);
        String lastName = set.getString(ParameterName.USER_LAST_NAME);
        String login = set.getString(ParameterName.USER_LOGIN);
        String email = set.getString(ParameterName.USER_EMAIL);
        String phone = set.getString(ParameterName.USER_PHONE);
        User.State state = User.State.valueOf(set.getString(ParameterName.USER_STATE));
        User.Role role = User.Role.valueOf(set.getString(ParameterName.USER_ROLE));
        long userId = set.getLong(ParameterName.USER_ID);
        User newUser = new User();
        newUser.setLogin(login);
        newUser.setId(userId);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setState(state);
        newUser.setRole(role);
        return newUser;
    }
}
