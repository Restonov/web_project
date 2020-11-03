package by.restonov.jspservlet.entity;

import javax.servlet.http.HttpSession;

public class User {
    public static final int GUEST_ROLE_ID = 0;
    public static final int USER_ROLE_ID = 1;
    public static final int MODERATOR_ROLE_ID = 2;
    public static final int ADMINISTRATOR_ROLE_ID = 3;
    private HttpSession session;
    private int userId;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private Role role;
    private Status status;
    private boolean onlineState;
    private UserOrder order;

    public enum Role {
        GUEST(), USER(), MODERATOR(), ADMINISTRATOR();
    }

    public enum Status {
        ACTIVATED(), DEACTIVATED()
    }

    public User(){
    }

    public User(String login) {
        this.login = login;
    }

    public int receiveRoleId(Role role) {
        switch (role) {
            case USER : return USER_ROLE_ID;
            case MODERATOR : return MODERATOR_ROLE_ID;
            case ADMINISTRATOR : return ADMINISTRATOR_ROLE_ID;
            default : return GUEST_ROLE_ID;
        }
    }

    public void defineRole(int roleId) {
        switch (roleId) {
            case (USER_ROLE_ID) : setRole(Role.USER);
            break;
            case (MODERATOR_ROLE_ID) : setRole(Role.MODERATOR);
            break;
            case (ADMINISTRATOR_ROLE_ID) : setRole(Role.ADMINISTRATOR);
            break;
            default : setRole(Role.GUEST);
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role userRole) {
        this.role = userRole;
    }

    public UserOrder getOrder() {
        return order;
    }

    public void setOrder(UserOrder order) {
        this.order = order;
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

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
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
        if (session != null ? !session.equals(user.session) : user.session != null) return false;
        return login != null ? login.equals(user.login) : user.login == null;
    }

    @Override
    public int hashCode() {
        int result = session != null ? session.hashCode() : 0;
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
