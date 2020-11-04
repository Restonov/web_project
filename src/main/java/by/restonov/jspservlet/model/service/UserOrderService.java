package by.restonov.jspservlet.model.service;

import by.restonov.jspservlet.entity.Car;
import by.restonov.jspservlet.entity.UserOrder;
import by.restonov.jspservlet.entity.builder.UserOrderBuilder;
import by.restonov.jspservlet.exception.DaoException;
import by.restonov.jspservlet.exception.ServiceException;
import by.restonov.jspservlet.model.dao.EntityTransaction;
import by.restonov.jspservlet.model.dao.impl.UserOrderDaoImpl;

public class UserOrderService {

    public UserOrder makeOrder(int carId) throws ServiceException {
        UserOrderBuilder builder = UserOrderBuilder.INSTANCE;
        UserOrder order = builder.buildOrder(carId);
        UserOrderDaoImpl dao = new UserOrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(dao);
        try {
            dao.add(order);
        } catch (DaoException e) {
            throw new ServiceException();
        } finally {
            transaction.endSingleQuery();
        }
        return order;
    }
}
