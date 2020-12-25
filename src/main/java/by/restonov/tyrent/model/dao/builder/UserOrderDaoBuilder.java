package by.restonov.tyrent.model.dao.builder;

import by.restonov.tyrent.model.entity.impl.UserOrder;
import by.restonov.tyrent.manager.ParameterName;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * User order builder
 */
public class UserOrderDaoBuilder {

    /**
     * Build User order from ResultSet data
     *
     * @param set ResultSet
     * @return Order instance
     * @throws SQLException DB access error
     */
    public UserOrder build(ResultSet set) throws SQLException {
        long id = set.getLong(ParameterName.ORDER_ID);
        long carId = set.getLong(ParameterName.CAR_ID);
        String carName = set.getString(ParameterName.CAR_NAME);
        double carCost = set.getDouble(ParameterName.CAR_COST);
        long userId = set.getLong(ParameterName.USER_ID);
        LocalDateTime dateTime = set.getTimestamp(ParameterName.ORDER_TIMESTAMP).toLocalDateTime();
        String stateFromSet = set.getString(ParameterName.ORDER_STATE);
        UserOrder order = new UserOrder();
        order.setOrderId(id);
        order.setCarId(carId);
        order.setCarName(carName);
        order.setCarCost(BigDecimal.valueOf(carCost));
        order.setUserId(userId);
        order.setCreationDateTime(dateTime);
        order.setState(UserOrder.State.valueOf(stateFromSet));
        return order;
    }
}
