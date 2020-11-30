package model.service;

import by.restonov.tyrent.model.dao.EntityTransaction;
import by.restonov.tyrent.model.dao.impl.UserOrderDaoImpl;
import by.restonov.tyrent.model.entity.impl.UserOrder;
import by.restonov.tyrent.model.exception.DaoException;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.service.UserOrderService;
import by.restonov.tyrent.model.service.builder.UserOrderBuilder;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "javax.crypto.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*" })
@PrepareForTest(UserOrderBuilder.class)
public class UserOrderServiceTest extends PowerMockTestCase {
    UserOrderService service;
    EntityTransaction transaction;
    UserOrderDaoImpl dao;
    UserOrderBuilder builder;
    UserOrder order;

    @BeforeClass
    public void setUp() {
        transaction = mock(EntityTransaction.class);
        dao = mock(UserOrderDaoImpl.class);
        builder = mock(UserOrderBuilder.class);
        service = new UserOrderService(dao, transaction, builder);
        order = new UserOrder();
    }

    @AfterClass
    public void tearDown() {
        service = null;
        transaction = null;
        dao = null;
        builder = null;
        order = null;
    }

    @Test
    public void addOrderTest() throws DaoException, ServiceException {
        long userId = 5;
        long carId = 550;
        doNothing().when(transaction).initSingleQuery(dao);
        when(builder.build(userId, carId)).thenReturn(order);
        when(dao.add(order)).thenReturn(true);
        doNothing().when(transaction).endSingleQuery();

        service.addOrder(userId, carId);

        verify(transaction).initSingleQuery(dao);
        verify(dao).add(order);
        verify(transaction).endSingleQuery();
    }

    @Test
    public void updateOrderTest() throws DaoException, ServiceException {
        doNothing().when(transaction).initSingleQuery(dao);
        when(dao.update(order)).thenReturn(true);
        doNothing().when(transaction).endSingleQuery();

        service.updateOrder(order);

        verify(transaction).initSingleQuery(dao);
        verify(dao).update(order);
        verify(transaction).endSingleQuery();
    }

    @Test
    public void findOrderListTest() throws DaoException, ServiceException {
        List<UserOrder> orderList = List.of(order, order);
        doNothing().when(transaction).initSingleQuery(dao);
        when(dao.findAll()).thenReturn(orderList);
        doNothing().when(transaction).endSingleQuery();

        List<UserOrder> resultList = service.findOrderList();

        verify(transaction).initSingleQuery(dao);
        verify(dao).findAll();
        verify(transaction).endSingleQuery();
        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void findOrderListByUserIdTest() throws DaoException, ServiceException {
        List<UserOrder> orderList = List.of(order, order);
        long userId = 5;
        doNothing().when(transaction).initSingleQuery(dao);
        when(dao.findOrderListByUserId(userId)).thenReturn(Optional.of(orderList));
        doNothing().when(transaction).endSingleQuery();

        Optional<List<UserOrder>> resultList = service.findOrderListByUserId(userId);

        verify(transaction).initSingleQuery(dao);
        verify(dao).findOrderListByUserId(userId);
        verify(transaction).endSingleQuery();
        Assert.assertTrue(resultList.isPresent());
    }

    @Test
    public void findOrderByIdTest() throws DaoException, ServiceException {
        long orderId = 5;
        doNothing().when(transaction).initSingleQuery(dao);
        when(dao.findById(orderId)).thenReturn(Optional.of(order));
        doNothing().when(transaction).endSingleQuery();

        Optional<UserOrder> resultOrder = service.findOrderById(orderId);

        verify(transaction).initSingleQuery(dao);
        verify(dao).findById(orderId);
        verify(transaction).endSingleQuery();
        Assert.assertTrue(resultOrder.isPresent());
    }
}
