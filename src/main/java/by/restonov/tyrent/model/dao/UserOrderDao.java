package by.restonov.tyrent.model.dao;

import by.restonov.tyrent.model.entity.impl.UserOrder;
import by.restonov.tyrent.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * Interface for operations
 * with Order's data
 *
 */
public interface UserOrderDao {
    /**
     *
     */
    Optional<List<UserOrder>> findOrderListByUserId(long userId) throws DaoException;
}
