package by.restonov.tyrent.model.service.builder;

import by.restonov.tyrent.model.entity.impl.UserOrder;
import by.restonov.tyrent.util.UserOrderIdGenerator;

import java.time.LocalDateTime;

/**
 * User builder
 */
public enum UserOrderBuilder {

    /**
     * Thread-safe singleton
     *
     */
    INSTANCE;

    /**
     * Build new order
     * attach user_id, car_id
     * new generated order_id
     * and current {@link LocalDateTime}
     *
     * @param userId User id
     * @param carId Car id
     * @return Order instance
     */
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
