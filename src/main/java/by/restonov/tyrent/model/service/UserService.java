package by.restonov.tyrent.model.service;

import by.restonov.tyrent.model.entity.User;
import by.restonov.tyrent.model.exception.DaoException;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.dao.EntityTransaction;
import by.restonov.tyrent.model.dao.impl.UserDaoImpl;
import by.restonov.tyrent.model.service.builder.UserBuilder;
import by.restonov.tyrent.util.DataValidator;
import by.restonov.tyrent.util.DataEncryption;
import by.restonov.tyrent.util.MailSender;

import javax.mail.MessagingException;
import java.util.*;

public class UserService {

    public boolean validateUser(Map<String, String> userData, String userPassword) {
        boolean validationResult = false;
        if (DataValidator.validateData(userData) && DataValidator.validatePassword(userPassword)) {
            validationResult = true;
        }
        return validationResult;
    }

    public boolean validateUser(String userLogin, String userPassword) {
        boolean validationResult = false;
        if (DataValidator.validateLatinText(userLogin) && DataValidator.validatePassword(userPassword)) {
            validationResult = true;
        }
        return validationResult;
    }

    public Optional<User> registerNewUserAndGet(Map<String, String> userData, String userPassword) throws ServiceException {
        Optional<User> optionalUser;
        if (checkUserNotExists(userData)) {
            UserBuilder builder = UserBuilder.INSTANCE;
            UserDaoImpl dao = new UserDaoImpl();
            EntityTransaction transaction = new EntityTransaction();
            try {
                transaction.initMultipleQueries(dao);
                User user = builder.build(userData);
                DataEncryption authentication = new DataEncryption();
                String hashedPassword = authentication.encrypt(userPassword.toCharArray());
                dao.add(user);
                dao.addUserPassword(user, hashedPassword);
                transaction.commit();
                user.setOnline(true);
                optionalUser = Optional.of(user);
            } catch (DaoException e) {
                try {
                    transaction.rollback();
                } catch (DaoException daoException) {
                    //TODO check catches in Services
                    daoException.printStackTrace();
                }
                throw new ServiceException("Error registering new User", e);
            } finally {
                try {
                    transaction.endMultipleQueries();
                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }
        } else {
            optionalUser = Optional.empty();
        }
        return optionalUser;
    }

    public boolean checkUserLoginAndPassword(String enteredLogin, String enteredPassword) throws ServiceException {
        boolean checkResult = false;
        UserDaoImpl dao = new UserDaoImpl();
        DataEncryption authentication = new DataEncryption();
        Optional<String> optionalPassword;
        EntityTransaction transaction = new EntityTransaction();
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

    public Optional<User> findUserByLogin(String login) throws ServiceException {
        Optional<User> optionalUser;
        UserDaoImpl dao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.initSingleQuery(dao);
            optionalUser = dao.findUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding user by login", e);
        } finally {
            transaction.endSingleQuery();
        }
        return optionalUser;
    }

    public Optional<User> findUserById(long id) throws ServiceException {
        Optional<User> optionalUser;
        UserDaoImpl dao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
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

    public Optional<User> findUserByEmail(String email) throws ServiceException {
        Optional<User> optionalUser;
        UserDaoImpl dao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
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

    public List<User> findUserList() throws ServiceException {
        List<User> userList;
        UserDaoImpl dao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
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

    public Optional<User> activateClientAndGet(String activationData, String userEmail) throws ServiceException {
        Optional<User> optionalUser = findUserByEmail(userEmail);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String userLogin = user.getLogin();
            DataEncryption encryption = new DataEncryption();
            if (encryption.decrypt(userLogin.toCharArray(), activationData)) {
                user.setState(User.State.ACTIVATED);
                updateUser(user);
                optionalUser = Optional.of(user);
            }
        }
        return optionalUser;
    }

    public void sendActivationEmail(String userEmail, String userLogin) throws ServiceException {
        DataEncryption encryption = new DataEncryption();
        String encryptedLogin = encryption.encrypt(userLogin.toCharArray());
        MailSender mail = new MailSender();
        try {
            mail.send(userEmail, encryptedLogin);
        } catch (MessagingException e) {
            throw new ServiceException("Error while sending email", e);
        }
    }

    public void updateUser(User user) throws ServiceException {
        UserDaoImpl dao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.initSingleQuery(dao);
            dao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("Error while user updating", e);
        } finally {
            transaction.endSingleQuery();
        }

    }

    private boolean checkUserNotExists(Map<String, String> userData) throws ServiceException {
        boolean userNotExists;
        UserDaoImpl dao = new UserDaoImpl();
        String userLogin = userData.get(ParameterName.USER_LOGIN);
        String userEmail = userData.get(ParameterName.USER_EMAIL);
        Optional<User> userWithSameLogin;
        Optional<User> userWithSameEmail;
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.initMultipleQueries(dao);
            userWithSameLogin = dao.findUserByLogin(userLogin);
            userWithSameEmail = dao.findUserByEmail(userEmail);
            transaction.commit();
            userNotExists = userWithSameLogin.isEmpty() && userWithSameEmail.isEmpty();
        } catch (DaoException e) {
            throw new ServiceException("Checking if user exists error", e);
        } finally {
            try {
                transaction.endMultipleQueries();
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        return userNotExists;
    }
}

