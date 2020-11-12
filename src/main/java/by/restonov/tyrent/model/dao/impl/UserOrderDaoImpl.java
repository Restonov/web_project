package by.restonov.tyrent.model.dao.impl;

import by.restonov.tyrent.model.entity.UserOrder;
import by.restonov.tyrent.model.dao.builder.UserOrderDaoBuilder;
import by.restonov.tyrent.model.exception.DaoException;
import by.restonov.tyrent.model.dao.AbstractDao;
import by.restonov.tyrent.model.dao.UserOrderDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class UserOrderDaoImpl extends AbstractDao<Long, UserOrder> implements UserOrderDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String INSERT_NEW_ORDER = "INSERT INTO orders (order_id, car_id, user_id, order_timestamp, order_state) VALUES (?, ?, ?, ?, ?::order_state)";
    private static final String SELECT_ORDER_BY_ID = "SELECT order_id, car_id, user_id, order_timestamp, order_state FROM orders WHERE order_id = ?";
    private static final String SELECT_ALL_ORDERS = "SELECT order_id, car_id, user_id, order_timestamp, order_state FROM orders ORDER BY order_state ASC";
    private static final String SELECT_ORDER_INFO = "SELECT order_id, car_name, car_cost, order_timestamp, order_state FROM orders INNER JOIN cars ON orders.user_id = ?";
    private static final String UPDATE_ORDER = "UPDATE orders SET car_id = ?, user_id = ?, order_timestamp = ?, order_state = ?::order_state WHERE order_id = ?";

    @Override
    public List<UserOrder> findAll() throws DaoException {
        List<UserOrder> orderList = new ArrayList<>();
        UserOrderDaoBuilder builder = UserOrderDaoBuilder.INSTANCE;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ORDERS)){
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserOrder order = builder.build(resultSet);
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding all orders", e);
        } finally {
            assert resultSet != null;
            closeResultSet(resultSet);
        }
        return orderList;
    }

    @Override
    public Optional<UserOrder> findById(Long id) throws DaoException {
        Optional<UserOrder> optionalUserOrder = Optional.empty();
        UserOrderDaoBuilder builder = UserOrderDaoBuilder.INSTANCE;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_BY_ID)){
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserOrder order = builder.build(resultSet);
                optionalUserOrder = Optional.of(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding order by id", e);
        } finally {
            assert resultSet != null;
            closeResultSet(resultSet);
        }
        return optionalUserOrder;
    }

    @Override
    public Optional<UserOrder> findByName(String name) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(UserOrder order) throws DaoException {
        boolean result = false;
        if (order != null) {
            try(PreparedStatement statement = connection.prepareStatement(INSERT_NEW_ORDER)){
                Timestamp timestamp = Timestamp.valueOf(order.getCreationDateTime());
                statement.setLong(1, order.getOrderId());
                statement.setLong(2, order.getCarId());
                statement.setLong(3, order.getUserId());
                statement.setTimestamp(4, timestamp);
                statement.setString(5, order.getState().toString());
                statement.executeUpdate();
                result = true;
                logger.info("Order successfully added to database");
            } catch (SQLException e) {
                throw new DaoException("Error while adding new order", e);
            }
        }
        return result;
    }

    @Override
    public boolean update(UserOrder order) throws DaoException {
        boolean result = false;
        if (order != null) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER)) {
                Timestamp timestamp = Timestamp.valueOf(order.getCreationDateTime());
                statement.setLong(1, order.getCarId());
                statement.setLong(2, order.getUserId());
                statement.setTimestamp(3, timestamp);
                statement.setString(4, order.getState().toString());
                statement.setLong(5, order.getOrderId());
                statement.executeUpdate();
                result = true;
                logger.info("Order successfully updated");
            } catch (SQLException e) {
                throw new DaoException("Error while update order", e);
            }
        }
        return result;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        return false;
    }

    @Override
    public List<Map<String, Object>> findOrderListByUserId(long userId) throws DaoException {
        List<Map<String, Object>> orderList = new ArrayList<>();
        Map<String, Object> orderInfo;
        UserOrderDaoBuilder builder = UserOrderDaoBuilder.INSTANCE;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_INFO)){
            statement.setLong(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                while (resultSet.next()) {
                    orderInfo = builder.buildMap(resultSet);
                    orderList.add(orderInfo);
                }
            } else {
                orderList = null;
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding order info", e);
        } finally {
            assert resultSet != null;
            closeResultSet(resultSet);
        }
        return orderList;
    }
}
