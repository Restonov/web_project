package by.restonov.tyrent.model.service.builder;

import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.entity.User;

import java.util.Map;

public enum UserBuilder {
    INSTANCE;

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
