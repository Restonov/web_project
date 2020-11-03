package by.restonov.jspservlet.model.service;

import by.restonov.jspservlet.entity.User;
import by.restonov.jspservlet.exception.DaoException;
import by.restonov.jspservlet.manager.ParameterName;
import by.restonov.jspservlet.model.dao.EntityTransaction;
import by.restonov.jspservlet.model.dao.impl.UserDaoImpl;
import by.restonov.jspservlet.util.UserPasswordAuthentication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class UserService {
    private static final Logger logger = LogManager.getLogger();

    public boolean checkUserNotExists(Map<String, String> userData) {
        UserDaoImpl dao = new UserDaoImpl();
        boolean userNotExists = true;
        String userLogin = userData.get(ParameterName.LOGIN);
        String userEmail = userData.get(ParameterName.EMAIL);
        Optional<User> userWithSameLogin;
        Optional<User> userWithSameEmail;
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.initMultipleQueries(dao);
            userWithSameLogin = dao.findUserByLogin(userLogin);
            userWithSameEmail = Optional.ofNullable(dao.findUserByEmail(userEmail));
            transaction.commit();
            if (userWithSameLogin.isPresent() || userWithSameEmail.isPresent()) {
                userNotExists = false;
            }
        } catch (DaoException e) {
            userNotExists = false;
            logger.error("Transaction error", e);
            try {
                transaction.rollback();
            } catch (DaoException daoException) {
                logger.error("Transaction rollback error", e);
                daoException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                transaction.endMultipleQueries();
            } catch (DaoException e) {
                logger.error("Transaction ending error", e);
                e.printStackTrace();
            }
        }
        return userNotExists;
    }

    public boolean checkUserLoginAndPassword(String enteredLogin, String enteredPassword) {
        boolean checkResult = false;
        UserDaoImpl dao = new UserDaoImpl();
        UserPasswordAuthentication authentication = new UserPasswordAuthentication();
        Optional<String> optionalPassword;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(dao);
        try {
            optionalPassword = dao.findPasswordByLogin(enteredLogin);
            if (optionalPassword.isPresent() &&
                    authentication.authenticate(enteredPassword.toCharArray(), optionalPassword.get())) {
                    checkResult = true;
            }
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            transaction.endSingleQuery();
        }
        return checkResult;
    }

    public boolean registerNewUser(User user, String password) {
        UserDaoImpl dao = new UserDaoImpl();
        boolean userWasRegistered = false;
        UserPasswordAuthentication authentication = new UserPasswordAuthentication();
        char[] unHashedPassword = password.toCharArray();
        String hashedPassword = authentication.hash(unHashedPassword);
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.initMultipleQueries(dao);
            dao.add(user);
            dao.addUserPassword(user, hashedPassword);
            transaction.commit();
            userWasRegistered = true;
        } catch (DaoException e) {
            logger.error("Transaction error", e);
            try {
                transaction.rollback();
            } catch (DaoException daoException) {
                logger.error("Transaction rollback error", e);
                daoException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                transaction.endMultipleQueries();
            } catch (DaoException e) {
                logger.error("Transaction ending error", e);
                e.printStackTrace();
            }
        }
        return userWasRegistered;
    }

    public Optional<User> findUserByLogin(String login) {
        Optional<User> optionalUser = Optional.empty();
        UserDaoImpl dao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(dao);
        try {
            optionalUser = dao.findUserByLogin(login);
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            transaction.endSingleQuery();
        }
        return optionalUser;
    }

    public List<User> findUserList() {
        List<User> userList = null;
        UserDaoImpl dao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(dao);
        try {
            userList = dao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            transaction.endSingleQuery();
        }
        return userList;
    }
}
