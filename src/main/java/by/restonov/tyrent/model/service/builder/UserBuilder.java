package by.restonov.tyrent.model.service.builder;

import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.entity.impl.User;

import java.util.Map;


/**
 * User builder
 */
public enum UserBuilder{

    /**
     * Thread-safe singleton
     *
     */
    INSTANCE;

    /**
     * Build user from User data
     * that we get from jsp form
     *
     * @param data Map of User data
     * @return User instance
     */
    public User build(Map<String, String> data){
        String firstName = data.get(ParameterName.USER_FIRST_NAME);
        String lastName = data.get(ParameterName.USER_LAST_NAME);
        String login = data.get(ParameterName.USER_LOGIN);
        String email = data.get(ParameterName.USER_EMAIL);
        String phone = data.get(ParameterName.USER_PHONE);
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setLogin(login);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setRole(User.Role.CLIENT);
        newUser.setState(User.State.NOT_ACTIVATED);
        return newUser;
    }
}
