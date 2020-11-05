package by.restonov.tyrent.entity.builder;

import by.restonov.tyrent.entity.UserOrder;
import by.restonov.tyrent.manager.ParameterName;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public enum UserOrderBuilder {
    INSTANCE;
    //TODO store order id somewhere
    private static final AtomicInteger orderIdCounter = new AtomicInteger(0);

    public UserOrder buildOrder(int carId) {
        UserOrder order = new UserOrder();
        LocalDateTime dateTime = LocalDateTime.now();
        order.setState(UserOrder.State.PROCESSED);
        order.setOrderId(orderIdCounter.incrementAndGet());
        order.setCarId(carId);
        order.setCreationDateTime(dateTime);
        return order;
    }

    public UserOrder buildOrderFromResultSet(ResultSet set) throws SQLException {
        int id = set.getInt(ParameterName.ORDER_ID);
        int carId = set.getInt(ParameterName.CAR_ID);
        LocalDateTime dateTime = set.getTimestamp(ParameterName.ORDER_TIMESTAMP).toLocalDateTime();
        String stateFromSet = set.getString(ParameterName.STATE);
        UserOrder.State state = UserOrder.State.valueOf(stateFromSet);
        UserOrder order = new UserOrder();
        order.setOrderId(id);
        order.setCarId(carId);
        order.setCreationDateTime(dateTime);
        order.setState(state);
        return order;
    }

    public Map<String, Object> buildMapFromResultSet(ResultSet set) throws SQLException {
        Map<String, Object> orderInfo = new HashMap<>();
        int orderId = set.getInt(ParameterName.ORDER_ID);
        String carName = set.getString(ParameterName.NAME);
        //TODO car_cost BIG_DECIMAL
        int carCost = set.getInt(ParameterName.COST);
        LocalDateTime dateTime = set.getTimestamp(ParameterName.ORDER_TIMESTAMP).toLocalDateTime();
        String stateFromSet = set.getString(ParameterName.STATE);
        orderInfo.put(ParameterName.ORDER_ID, orderId);
        orderInfo.put(ParameterName.NAME, carName);
        orderInfo.put(ParameterName.COST, carCost);
        orderInfo.put(ParameterName.ORDER_TIMESTAMP, dateTime);
        orderInfo.put(ParameterName.ORDER_STATE, stateFromSet);
        return orderInfo;
    }
}
