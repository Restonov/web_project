package by.restonov.jspservlet.model.dao;

import by.restonov.jspservlet.entity.Car;
import by.restonov.jspservlet.exception.DaoException;

public interface CarDao {
    void changeState(Car car, Car.State state) throws DaoException;
}
