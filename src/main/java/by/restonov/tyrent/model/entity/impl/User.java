package by.restonov.tyrent.model.entity.impl;

import by.restonov.tyrent.model.entity.ApplicationEntity;

import java.io.Serializable;


/**
 * Class of User type
 */
public class User extends ApplicationEntity implements Serializable {
    private long userId;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String phone;
    private Role role;
    private State state;

    /**
     * User roles according to DB
     */
    public enum Role {
        GUEST(), CLIENT(), ADMINISTRATOR()
    }

    /**
     * User states according to DB
     */
    public enum State {
        ACTIVATED(), NOT_ACTIVATED(), BLOCKED(), FROZEN()
    }

    /**
     * Default constructor
     * @see #User(String)
     */
    public User(){
    }

    /**
     * Instantiates a new User with
     * login assign
     *
     * @param login the login
     * @see #User()
     */
    public User(String login) {
        this.login = login;
    }

    /**
     * Gets phone
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets state
     *
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * Sets state
     *
     * @param state the state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Gets role
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role
     *
     * @param userRole the user role
     */
    public void setRole(Role userRole) {
        this.role = userRole;
    }

     /**
     * Gets first name
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets login
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets id
     *
     * @return the id
     */
    public long getId() {
        return userId;
    }

    /**
     * Sets id
     *
     * @param id the id
     */
    public void setId(long id) {
        this.userId = id;
    }

    /**
     * Gets email
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email
     *
     * @param email the email
     */
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
        sb.append(firstName).append(" ").append(lastName);
        return sb.toString();
    }
}
