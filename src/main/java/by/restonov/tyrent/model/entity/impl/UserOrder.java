package by.restonov.tyrent.model.entity.impl;

import by.restonov.tyrent.model.entity.ApplicationEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * Class of User order type
 */
public class UserOrder extends ApplicationEntity {
    private long orderId;
    private long carId;
    private long userId;
    private String carName;
    private BigDecimal carCost;
    private LocalDateTime creationDateTime;
    private State state;

    /**
     * Gets car name
     *
     * @return the car name
     */
    public String getCarName() {
        return carName;
    }

    /**
     * Sets car name.
     *
     * @param carName the car name
     */
    public void setCarName(String carName) {
        this.carName = carName;
    }

    /**
     * Gets car cost.
     *
     * @return the car cost
     */
    public BigDecimal getCarCost() {
        return carCost;
    }

    /**
     * Sets car cost.
     *
     * @param carCost the car cost
     */
    public void setCarCost(BigDecimal carCost) {
        this.carCost = carCost;
    }

    /**
     * Order states according to DB
     */
    public enum State {
        /**
         * Processed state.
         */
        PROCESSED(),
        /**
         * Canceled state.
         */
        CANCELED(),
        /**
         * Completed state.
         */
        COMPLETED()
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
