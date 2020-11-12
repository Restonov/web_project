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


/**
 * Service, that works with order data
 */
public class UserOrderService {

    /**
     * Attach car_id and user_id
     * to Order and add to DB
     *
     * @param userId User id
     * @param carId  Car id
     * @throws ServiceException default exception of service layer
     */
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

    /**
     * Update exists order
     *
     * @param order Order
     * @throws ServiceException default exception of service layer
     */
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

    /**
     * Find order list
     *
     * @return the list of Orders
     * @throws ServiceException default exception of service layer
     */
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

    /**
     * Find order list by user id
     *
     * @param userId User id
     * @return List of current User orders
     * @throws ServiceException default exception of service layer
     */
    //TODO change to extended Order
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

    /**
     * Find Order by it's id
     *
     * @param orderId Order id
     * @return Optional of Order type
     * @throws ServiceException default exception of service layer
     */
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

    /**
     * Change exists order state
     *
     * @param order Order
     * @param state Order state
     * @throws ServiceException default exception of service layer
     * @see #updateOrder(UserOrder)
     */
    public void changeOrderState(UserOrder order, UserOrder.State state) throws ServiceException {
        order.setState(state);
        updateOrder(order);
    }
}
