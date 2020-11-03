package by.restonov.jspservlet.entity;

import java.util.Date;

public class UserOrder {
    private int orderId;
    private Date creationDate;
    private Car car;
    private State state;

    public enum State {
        ACTIVE(), INACTIVE()
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
