package by.restonov.tyrent.model.service.builder;

import by.restonov.tyrent.model.entity.UserOrder;
import by.restonov.tyrent.util.UserOrderIdGenerator;

import java.time.LocalDateTime;

public enum UserOrderBuilder {
    INSTANCE;

    public UserOrder build(long userId, long carId) {
        UserOrder order = new UserOrder();
        LocalDateTime dateTime = LocalDateTime.now();
        order.setState(UserOrder.State.PROCESSED);
        order.setOrderId(UserOrderIdGenerator.generateId(carId, userId, dateTime));
        order.setCarId(carId);
        order.setUserId(userId);
        order.setCreationDateTime(dateTime);
        return order;
    }
}
