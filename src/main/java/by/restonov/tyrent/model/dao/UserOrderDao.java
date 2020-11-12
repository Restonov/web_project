package by.restonov.tyrent.model.dao;

import by.restonov.tyrent.model.exception.DaoException;

import java.util.List;
import java.util.Map;

/**
 * Interface for operations
 * with Order's data
 *
 */
public interface UserOrderDao {

    /**
     *
     */
    List<Map<String, Object>> findOrderListByUserId(long userId) throws DaoException;
}
