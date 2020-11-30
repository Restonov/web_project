package model.service;

import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.dao.EntityTransaction;
import by.restonov.tyrent.model.dao.impl.UserDaoImpl;
import by.restonov.tyrent.model.entity.impl.User;
import by.restonov.tyrent.model.exception.DaoException;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.service.UserService;
import by.restonov.tyrent.model.service.builder.UserBuilder;

import by.restonov.tyrent.util.DataEncryptor;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "javax.crypto.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*" })
@PrepareForTest(UserBuilder.class)
public class UserServiceTest extends PowerMockTestCase {
    UserService userService;
    Map<String, String> userData;
    UserDaoImpl dao;
    EntityTransaction transaction;
    UserBuilder builder;
    User user;

    @BeforeClass
    public void setUp() {
        dao = mock(UserDaoImpl.class);
        transaction = mock(EntityTransaction.class);
        builder = mock(UserBuilder.class);
        userService = new UserService(dao, transaction, builder);
        user = new User("testUser");
        user.setState(User.State.ACTIVATED);

        userData = new HashMap<>();
        userData.put(ParameterName.USER_FIRST_NAME, "Firstname");
        userData.put(ParameterName.USER_LAST_NAME, "Lastname");
        userData.put(ParameterName.USER_EMAIL, "email@mail.com");
        userData.put(ParameterName.USER_PHONE, "375256400333");
        userData.put(ParameterName.USER_LOGIN, "login");
    }

    @AfterClass
    public void tearDown() {
        userService = null;
        userData = null;
        dao = null;
        transaction = null;
        builder = null;
        user = null;
    }

    @Test
    public void validateUserDataPositiveTest() {
        Assert.assertTrue(userService.validateUser(userData, "123456Q"));
    }

    @Test
    public void validateUserLoginAndPassPositiveTest() {
        Assert.assertTrue(userService.validateUser(userData.get(ParameterName.USER_LOGIN), "123456Q"));
    }

    @Test
    public void registerNewUserAndGetTest() throws Exception {
        String password = "123456";

        doNothing().when(transaction).initMultipleQueries(dao);
        when(builder.build(userData)).thenReturn(user);
        when(dao.add(user)).thenReturn(true);
        when(dao.addUserPassword(user, password)).thenReturn(true);
        doNothing().when(transaction).endMultipleQueries();

        Optional<User> finalUser = userService.registerNewUserAndGet(userData, password);
        verify(dao).add(user);
        Assert.assertTrue(finalUser.isPresent());
    }

    @Test
    public void checkUserLoginAndPasswordTest() throws ServiceException, DaoException {
        String password = "123456";
        DataEncryptor dataEncryptor = new DataEncryptor();
        String encryptedPass = dataEncryptor.encrypt(password.toCharArray());

        doNothing().when(transaction).initSingleQuery(dao);
        when(dao.findPasswordByLogin(userData.get(ParameterName.USER_LOGIN))).thenReturn(Optional.of(encryptedPass));
        doNothing().when(transaction).endSingleQuery();

        boolean result = userService.checkUserLoginAndPassword(userData.get(ParameterName.USER_LOGIN),
                                    password);
        verify(dao).findPasswordByLogin(userData.get(ParameterName.USER_LOGIN));
        Assert.assertTrue(result);
    }

    @Test
    public void findUserByLoginTest() throws DaoException, ServiceException {
        String login = "Login";

        doNothing().when(transaction).initSingleQuery(dao);
        when(dao.findUserByLogin(login)).thenReturn(Optional.of(user));
        doNothing().when(transaction).endSingleQuery();

        Optional<User> user = userService.findUserByLogin(login);
        verify(dao).findUserByLogin(login);
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void findUserByIdTest() throws DaoException, ServiceException {
        long id = 123;

        doNothing().when(transaction).initSingleQuery(dao);
        when(dao.findById(id)).thenReturn(Optional.of(user));
        doNothing().when(transaction).endSingleQuery();

        Optional<User> user = userService.findUserById(id);

        verify(dao).findById(id);
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void findUserByEmailTest() throws DaoException, ServiceException {
        String email = "email@email.com";

        doNothing().when(transaction).initSingleQuery(dao);
        when(dao.findUserByEmail(email)).thenReturn(Optional.of(user));
        doNothing().when(transaction).endSingleQuery();

        Optional<User> user = userService.findUserByEmail(email);

        verify(dao).findUserByEmail(email);
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void updateUserTest() throws DaoException, ServiceException {
        doNothing().when(transaction).initSingleQuery(dao);
        when(dao.update(user)).thenReturn(true);
        doNothing().when(transaction).endSingleQuery();

        userService.updateUser(user);
        verify(dao, times(1)).update(user);
    }
}
