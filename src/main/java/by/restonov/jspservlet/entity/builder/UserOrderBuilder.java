package by.restonov.jspservlet.entity.builder;

import by.restonov.jspservlet.entity.Car;
import by.restonov.jspservlet.entity.UserOrder;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public enum UserOrderBuilder {
    INSTANCE;

    private static final AtomicInteger ID = new AtomicInteger(1);

    public UserOrder build(Car car) {
        UserOrder order = new UserOrder();
        order.setState(UserOrder.State.ACTIVE);
        order.setOrderId(ID.incrementAndGet());
        order.setCar(car);
        order.setCreationDate(new Date());
        return order;
    }
}
