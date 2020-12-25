package by.restonov.tyrent.model.service;

import by.restonov.tyrent.model.entity.impl.User;
import by.restonov.tyrent.model.exception.DaoException;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.dao.EntityTransaction;
import by.restonov.tyrent.model.dao.impl.UserDaoImpl;
import by.restonov.tyrent.model.service.builder.UserBuilder;
import by.restonov.tyrent.util.DataValidator;
import by.restonov.tyrent.util.DataEncryptor;
import by.restonov.tyrent.util.MailSender;

import javax.mail.MessagingException;
import java.util.*;


/**
 * Service, that works with user data
 */
public class UserService {
    private UserDaoImpl dao;
    private EntityTransaction transaction;
    private UserBuilder builder;

    /**
     * Default init constructor
     *
     */
    public UserService() {
        this.dao = new UserDaoImpl();
        this.transaction = new EntityTransaction();
        this.builder = UserBuilder.INSTANCE;
    }

    /**
     * Constructor with dao and transaction params
     *
     * @param dao UserDao
     * @param transaction EntityTransaction
     */
    public UserService(UserDaoImpl dao, EntityTransaction transaction, UserBuilder builder) {
        this.dao = dao;
        this.transaction = transaction;
        this.builder = builder;
    }

    /**
     * Validate new user data and password
     * with RegEx pattern
     * while user sign up
     *
     * @param userData - data from jsp form
     * @param userPassword - also from sjp form
     * @return validation result
     * @see DataValidator
     */
    public boolean validateUser(Map<String, String> userData, String userPassword) {
        boolean validationResult = false;
        if (DataValidator.validateData(userData) && DataValidator.validatePassword(userPassword)) {
            validationResult = true;
        }
        return validationResult;
    }

    /**
     * Validate exists user login and password
     * with RegEx pattern
     * while user sign in
     *
     * @param userLogin - user login from jsp form
     * @param userPassword - user password from jsp form
     * @return validation result
     * @see DataValidator
     */
    public boolean validateUser(String userLogin, String userPassword) {
        boolean validationResult = false;
        if (DataValidator.validateLatinText(userLogin) && DataValidator.validatePassword(userPassword)) {
            validationResult = true;
        }
        return validationResult;
    }

    /**
     * Register new user, hash password and get Optional user
     * if user with the same login or email
     * not exists in DB
     *
     * @param userData user data
     * @param userPassword user password
     * @return Optional of User type
     * @throws ServiceException default exception of service layer
     * @see DataEncryptor
     */
    public Optional<User> registerNewUserAndGet(Map<String, String> userData, String userPassword) throws ServiceException {
        Optional<User> optionalUser;
        String userLogin = userData.get(ParameterName.USER_LOGIN);
        String userEmail = userData.get(ParameterName.USER_EMAIL);
        Optional<User> userWithSameLogin;
        Optional<User> userWithSameEmail;
        try {
            transaction.initMultipleQueries(dao);
            userWithSameLogin = dao.findUserByLogin(userLogin);
            userWithSameEmail = dao.findUserByEmail(userEmail);
            if(userWithSameLogin.isEmpty() && userWithSameEmail.isEmpty()) {
                User user = builder.build(userData);
                optionalUser = Optional.of(user);
                DataEncryptor authentication = new DataEncryptor();
                String hashedPassword = authentication.encrypt(userPassword.toCharArray());
                dao.add(user);
                dao.addUserPassword(user, hashedPassword);
            } else {
                optionalUser = Optional.empty();
            }
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Error rollback transaction", e);
            }
            throw new ServiceException("Register new user - error", e);
        } finally {
            try {
                transaction.endMultipleQueries();
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        return optionalUser;
    }

    /**
     * Find exists login and password and check if it equals
     * to data from jsp form
     * when user sign in
     *
     * @param enteredLogin the entered login
     * @param enteredPassword the entered password
     * @return check result
     * @throws ServiceException default exception of service layer
     * @see DataEncryptor
     */
    public boolean checkUserLoginAndPassword(String enteredLogin, String enteredPassword) throws ServiceException {
        boolean checkResult = false;
        DataEncryptor authentication = new DataEncryptor();
        Optional<String> optionalPassword;
        try {
            transaction.initSingleQuery(dao);
            optionalPassword = dao.findPasswordByLogin(enteredLogin);
            if (optionalPassword.isPresent() &&
                    authentication.decrypt(enteredPassword.toCharArray(), optionalPassword.get())) {
                checkResult = true;
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while user log in", e);
        } finally {
            transaction.endSingleQuery();
        }
        return checkResult;
    }

    /**
     * Find user in DB by it's login
     *
     * @param login user login
     * @return Optional of User type
     * @throws ServiceException default exception of service layer
     */
    public Optional<User> findUserByLogin(String login) throws ServiceException {
        Optional<User> optionalUser;
        try {
            transaction.initSingleQuery(dao);
            optionalUser = dao.findUserByLogin(login);
            if (optionalUser.isPresent() && optionalUser.get().getState() == User.State.FROZEN) {
                optionalUser = Optional.empty();
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while finding user by login", e);
        } finally {
            transaction.endSingleQuery();
        }
        return optionalUser;
    }

    /**
     * Find user in DB by it's id
     *
     * @param id user id
     * @return Optional of User type
     * @throws ServiceException default exception of service layer
     */
    public Optional<User> findUserById(long id) throws ServiceException {
        Optional<User> optionalUser;
        try {
            transaction.initSingleQuery(dao);
            optionalUser = dao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding user by id", e);
        } finally {
            transaction.endSingleQuery();
        }
        return optionalUser;
    }

    /**
     * Find user in DB by it's email
     *
     * @param email user email
     * @return Optional of User type
     * @throws ServiceException default exception of service layer
     */
    public Optional<User> findUserByEmail(String email) throws ServiceException {
        Optional<User> optionalUser;
        try {
            transaction.initSingleQuery(dao);
            optionalUser = dao.findUserByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding user by email", e);
        } finally {
            transaction.endSingleQuery();
        }
        return optionalUser;
    }

    /**
     * Find all users in DB
     *
     * @return List of Users
     * @throws ServiceException default exception of service layer
     */
    public List<User> findUserList() throws ServiceException {
        List<User> userList;
        try {
            transaction.initSingleQuery(dao);
            userList = dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Error while finding user list", e);
        } finally {
            transaction.endSingleQuery();
        }
        return userList;
    }

    /**
     * Activate client and get Optional of User
     *
     * @param activationData hashed user login
     * @param userEmail user email
     * @return Optional of User type
     * @throws ServiceException default exception of service layer
     * @see #findUserByEmail(String userEmail)
     * @see DataEncryptor
     */
    public Optional<User> activateClient(String activationData, String userEmail) throws ServiceException {
        Optional<User> optionalUser = findUserByEmail(userEmail);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String userLogin = user.getLogin();
            DataEncryptor encryption = new DataEncryptor();
            if (encryption.decrypt(userLogin.toCharArray(), activationData)) {
                user.setState(User.State.ACTIVATED);
                updateUser(user);
                optionalUser = Optional.of(user);
            }
        }
        return optionalUser;
    }

    /**
     * Send activation email with encrypted login and email
     *
     * @param userEmail user email
     * @param userLogin user login
     * @throws ServiceException default exception of service layer
     * @see DataEncryptor
     */
    public void sendActivationEmail(String userEmail, String userLogin) throws ServiceException {
        DataEncryptor encryption = new DataEncryptor();
        String encryptedLogin = encryption.encrypt(userLogin.toCharArray());
        MailSender mail = new MailSender();
        try {
            mail.send(userEmail, encryptedLogin);
        } catch (MessagingException e) {
            throw new ServiceException("Error while sending email", e);
        }
    }

    /**
     * Update exists user
     *
     * @param user User
     * @throws ServiceException default exception of service layer
     */
    public void updateUser(User user) throws ServiceException {
        try {
            transaction.initSingleQuery(dao);
            dao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("Error while user updating", e);
        } finally {
            transaction.endSingleQuery();
        }

    }

    /**
     * Change User State in DB
     *
     * @param user target User
     * @param userState new User State
     * @throws ServiceException default exception of service layer
     */
    public void changeUserState(User user, User.State userState) throws ServiceException {
        user.setState(userState);
        updateUser(user);
    }
}

