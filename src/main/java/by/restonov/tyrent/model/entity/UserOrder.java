package by.restonov.tyrent.model.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserOrder {
    private long orderId;
    private long carId;
    private long userId;
    private LocalDateTime creationDateTime;
    private State state;

    public enum State {
        PROCESSED(), CANCELED(), COMPLETED()
    }

    public String showCreationDateTime() {
        LocalDateTime dateTime = getCreationDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserOrder order = (UserOrder) o;

        if (orderId != order.orderId) return false;
        if (carId != order.carId) return false;
        if (!creationDateTime.equals(order.creationDateTime)) return false;
        return state == order.state;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (carId ^ (carId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserOrder{");
        sb.append("orderId=").append(orderId);
        sb.append(", carId=").append(carId);
        sb.append(", creationDateTime=").append(creationDateTime);
        sb.append(", state=").append(state);
        sb.append('}');
        return sb.toString();
    }
}
