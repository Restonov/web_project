package by.restonov.tyrent.model.service;

import by.restonov.tyrent.model.entity.UserOrder;
import by.restonov.tyrent.model.exception.DaoException;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.dao.EntityTransaction;
import by.restonov.tyrent.model.dao.impl.UserOrderDaoImpl;
import by.restonov.tyrent.model.service.builder.UserOrderBuilder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserOrderService {

    public void addOrder(long userId, long carId) throws ServiceException {
        UserOrderBuilder builder = UserOrderBuilder.INSTANCE;
        UserOrder order = builder.build(userId, carId);
        UserOrderDaoImpl dao = new UserOrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.initSingleQuery(dao);
            dao.add(order);
        } catch (DaoException e) {
            throw new ServiceException("Add order to database - error");
        } finally {
            transaction.endSingleQuery();
        }
    }

    public void updateOrder(UserOrder order) throws ServiceException {
        UserOrderDaoImpl dao = new UserOrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.initSingleQuery(dao);
            dao.update(order);
        } catch (DaoException e) {
            throw new ServiceException();
        } finally {
            transaction.endSingleQuery();
        }
    }

    public List<UserOrder> findOrderList() throws ServiceException {
        List<UserOrder> orderList;
        UserOrderDaoImpl dao = new UserOrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.initSingleQuery(dao);
            orderList = dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Find order list in DB - error");
        } finally {
            transaction.endSingleQuery();
        }
        return orderList;
    }

    public List<Map<String, Object>> findOrderListByUserId(long userId) throws ServiceException {
        List<Map<String, Object>> orderList;
        UserOrderDaoImpl dao = new UserOrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.initSingleQuery(dao);
            orderList = dao.findOrderListByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException();
        } finally {
            transaction.endSingleQuery();
        }
        return orderList;
    }

    public Optional<UserOrder> findOrderById(long orderId) throws ServiceException {
        Optional<UserOrder> optionalOrder;
        UserOrderDaoImpl dao = new UserOrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.initSingleQuery(dao);
            optionalOrder = dao.findById(orderId);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding order by id", e);
        } finally {
            transaction.endSingleQuery();
        }
        return optionalOrder;
    }

    public void changeOrderState(UserOrder order, UserOrder.State state) throws ServiceException {
        order.setState(state);
        updateOrder(order);
    }
}
