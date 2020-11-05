package by.restonov.tyrent.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserOrder {
    private int orderId;
    private int carId;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
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
        int result = orderId;
        result = 31 * result + creationDateTime.hashCode();
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
