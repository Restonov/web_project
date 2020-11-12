package by.restonov.tyrent.model.dao.builder;

import by.restonov.tyrent.model.entity.UserOrder;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.util.UserOrderIdGenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public enum UserOrderDaoBuilder {
    INSTANCE;

    public UserOrder build(ResultSet set) throws SQLException {
        long id = set.getLong(ParameterName.ORDER_ID);
        long carId = set.getLong(ParameterName.CAR_ID);
        long userId = set.getLong(ParameterName.USER_ID);
        LocalDateTime dateTime = set.getTimestamp(ParameterName.ORDER_TIMESTAMP).toLocalDateTime();
        String stateFromSet = set.getString(ParameterName.ORDER_STATE);
        UserOrder order = new UserOrder();
        order.setOrderId(id);
        order.setCarId(carId);
        order.setUserId(userId);
        order.setCreationDateTime(dateTime);
        order.setState(UserOrder.State.valueOf(stateFromSet));
        return order;
    }

    //TODO create extended order with these fields
    public Map<String, Object> buildMap(ResultSet set) throws SQLException {
        Map<String, Object> orderInfo = new HashMap<>();
        long orderId = set.getLong(ParameterName.ORDER_ID);
        String carName = set.getString(ParameterName.CAR_NAME);
        double carCost = set.getDouble(ParameterName.CAR_COST);
        LocalDateTime dateTime = set.getTimestamp(ParameterName.ORDER_TIMESTAMP).toLocalDateTime();
        String stateFromSet = set.getString(ParameterName.ORDER_STATE);
        orderInfo.put(ParameterName.ORDER_ID, orderId);
        orderInfo.put(ParameterName.CAR_NAME, carName);
        orderInfo.put(ParameterName.CAR_COST, carCost);
        orderInfo.put(ParameterName.ORDER_TIMESTAMP, dateTime);
        orderInfo.put(ParameterName.ORDER_STATE, stateFromSet);
        return orderInfo;
    }
}
