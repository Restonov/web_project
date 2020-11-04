package by.restonov.jspservlet.model.dao.impl;

import by.restonov.jspservlet.entity.UserOrder;
import by.restonov.jspservlet.exception.DaoException;
import by.restonov.jspservlet.model.dao.AbstractDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UserOrderDaoImpl extends AbstractDao<Integer, UserOrder> {
    private static final Logger logger = LogManager.getLogger();
    private static final String INSERT_NEW_ORDER = "INSERT INTO orders (car_id, timestamp ,state) VALUES (?, ?, ?::order_state)";

    @Override
    public List<UserOrder> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<UserOrder> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<UserOrder> findByName(String name) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean add(UserOrder order) throws DaoException {
        boolean result = false;
        if (order != null) {
            try(PreparedStatement statement = connection.prepareStatement(INSERT_NEW_ORDER)){
                Timestamp timestamp = Timestamp.valueOf(order.getCreationDateTime());
                statement.setInt(1, order.getCarId());
                statement.setTimestamp(2, timestamp);
                statement.setString(3, order.getState().toString());
                statement.executeUpdate();
                result = true;
                logger.info("Order successfully added to database");
            } catch (SQLException e) {
                logger.error("Error while adding new order", e);
                throw new DaoException("Error while adding new order", e);
            }
        }
        return result;
    }

    @Override
    public UserOrder update(UserOrder entity) {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }
}
