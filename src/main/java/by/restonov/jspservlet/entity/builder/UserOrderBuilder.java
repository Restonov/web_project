package by.restonov.jspservlet.entity.builder;

import by.restonov.jspservlet.entity.UserOrder;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public enum UserOrderBuilder {
    INSTANCE;

    public UserOrder buildOrder(int carId) {
        UserOrder order = new UserOrder();
        LocalDateTime date = LocalDateTime.now();
        order.setState(UserOrder.State.ACTIVE);
        order.setCarId(carId);
        order.setCreationDateTime(date);
        return order;
    }
}
