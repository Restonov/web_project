package by.restonov.tyrent.entity.builder;

import by.restonov.tyrent.entity.User;
import by.restonov.tyrent.manager.ParameterName;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum UserBuilder {
    INSTANCE;

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
        newUser.setRole(User.Role.CLIENT);
        return newUser;
    }

    public User buildUserFromResultSet(ResultSet set) throws SQLException {
        User newUser = new User();
        String firstName = set.getString(ParameterName.FIRST_NAME);
        String lastName = set.getString(ParameterName.LAST_NAME);
        String login = set.getString(ParameterName.LOGIN);
        String email = set.getString(ParameterName.EMAIL);
        int userId = set.getInt(ParameterName.USER_ID);
        int roleId = set.getInt(ParameterName.ROLE_ID);
        newUser.setLogin(login);
        newUser.defineRole(roleId);
        newUser.setId(userId);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        Array idRawArray = null;
        try {
            idRawArray = set.getArray(ParameterName.ORDER_ID_LIST);
        } finally {
            if (idRawArray != null) {
                Integer[] idArray = (Integer[]) idRawArray.getArray();
                List<Integer> idList = Arrays.stream(idArray).collect(Collectors.toList());
                newUser.setOrdersId(idList);
            }
        }
        return newUser;
    }
}
