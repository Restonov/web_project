package by.restonov.tyrent.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    public static final int GUEST_ROLE_ID = 0;
    public static final int CLIENT_ROLE_ID = 1;
    public static final int MODERATOR_ROLE_ID = 2;
    public static final int ADMINISTRATOR_ROLE_ID = 3;
    private int userId;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private Role role;
    private State state;
    private boolean onlineState;
    private List<Integer> ordersId = new ArrayList<>();

    //TODO psql enum
    public enum Role {
        GUEST(), CLIENT(), MODERATOR(), ADMINISTRATOR();
    }

    //TODO psql enum
    public enum State {
        ACTIVATED(), DEACTIVATED()
    }

    public User(){
    }

    public User(String login) {
        this.login = login;
    }

    public int receiveRoleId(Role role) {
        switch (role) {
            case CLIENT: return CLIENT_ROLE_ID;
            case MODERATOR : return MODERATOR_ROLE_ID;
            case ADMINISTRATOR : return ADMINISTRATOR_ROLE_ID;
            default : return GUEST_ROLE_ID;
        }
    }

    public void defineRole(int roleId) {
        switch (roleId) {
            case (CLIENT_ROLE_ID) : setRole(Role.CLIENT);
            break;
            case (MODERATOR_ROLE_ID) : setRole(Role.MODERATOR);
            break;
            case (ADMINISTRATOR_ROLE_ID) : setRole(Role.ADMINISTRATOR);
            break;
            default : setRole(Role.GUEST);
        }
    }

    public void addOrderId(int orderId) {
        this.ordersId.add(orderId);
    }

    public List<Integer> getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(List<Integer> ordersId) {
        this.ordersId = ordersId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role userRole) {
        this.role = userRole;
    }

    public boolean getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(boolean onlineState) {
        this.onlineState = onlineState;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        return login != null ? login.equals(user.login) : user.login == null;
    }

    @Override
    public int hashCode() {
        int result = 5;
        result = 31 * result + userId;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User = ");
        sb.append(firstName).append(", ").append(lastName);
        return sb.toString();
    }
}
