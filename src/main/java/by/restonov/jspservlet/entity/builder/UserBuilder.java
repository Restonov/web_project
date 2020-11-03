package by.restonov.jspservlet.entity.builder;

import by.restonov.jspservlet.entity.User;
import by.restonov.jspservlet.manager.ParameterName;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserBuilder {

    public User buildUser() {
        return new User();
    }

    public User buildUserFromMap(Map<String, String> data){
        String firstName = data.get(ParameterName.FIRST_NAME);
        String lastName = data.get(ParameterName.LAST_NAME);
        String login = data.get(ParameterName.LOGIN);
        String email = data.get(ParameterName.EMAIL);
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setLogin(login);
        newUser.setEmail(email);
        newUser.setRole(User.Role.USER);
        return newUser;
    }

    public User buildUserFromResultSet(ResultSet set) throws SQLException {
        String firstName = set.getString(ParameterName.FIRST_NAME);
        String lastName = set.getString(ParameterName.LAST_NAME);
        String login = set.getString(ParameterName.LOGIN);
        String email = set.getString(ParameterName.EMAIL);
        int userId = set.getInt(ParameterName.USER_ID);
        int roleId = set.getInt(ParameterName.ROLE_ID);
        User newUser = new User(login);
        newUser.defineRole(roleId);
        newUser.setId(userId);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        return newUser;
    }
}
