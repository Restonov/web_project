package by.restonov.tyrent.model.dao;

import by.restonov.tyrent.exception.DaoException;

import java.util.Map;

public interface UserOrderDao {
    Map<String, Object> findOrderInfo(int orderId) throws DaoException;
}
