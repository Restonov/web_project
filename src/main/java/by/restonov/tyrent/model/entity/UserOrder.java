package by.restonov.tyrent.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Class of User order type
 */
public class UserOrder {
    private long orderId;
    private long carId;
    private long userId;
    private LocalDateTime creationDateTime;
    private State state;
    private String carName;
    private String userName;
    private BigDecimal carCost;

    /**
     * Order states according to DB
     */
    public enum State {
        PROCESSED(), CANCELED(), COMPLETED()
    }

    /**
     * Convert LocalDateTime to
     * more clear format
     *
     * @return formatted Date and time
     */
    public String showCreationDateTime() {
        LocalDateTime dateTime = getCreationDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    /**
     * Gets user id
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets user id
     *
     * @param userId the user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets creation date time
     *
     * @return the creation date time
     */
    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    /**
     * Sets creation date time
     *
     * @param creationDateTime the creation date time
     */
    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    /**
     * Gets state
     *
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * Sets state
     *
     * @param state the state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Gets order id
     *
     * @return the order id
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * Sets order id
     *
     * @param orderId the order id
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets car id
     *
     * @return the car id
     */
    public long getCarId() {
        return carId;
    }

    /**
     * Sets car id
     *
     * @param carId the car id
     */
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
        final StringBuilder sb = new StringBuilder("UserOrder № ");
        sb.append(orderId).append(", ").append(state);
        return sb.toString();
    }
}
