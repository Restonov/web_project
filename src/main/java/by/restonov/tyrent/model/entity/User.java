package by.restonov.tyrent.model.entity;

public class User {
    private long userId;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String phone;
    private Role role;
    private State state;
    private boolean isOnline;

    public enum Role {
        GUEST(), CLIENT(), ADMINISTRATOR()
    }

    public enum State {
        ACTIVATED(), NOT_ACTIVATED(), BLOCKED()
    }

    public User(){
    }

    public User(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public boolean getOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        this.isOnline = online;
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

    public long getId() {
        return userId;
    }

    public void setId(long id) {
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
        if (!login.equals(user.login)) return false;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + login.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User = ");
        sb.append(firstName).append(", ").append(lastName);
        return sb.toString();
    }
}
