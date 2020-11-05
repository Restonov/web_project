package by.restonov.tyrent.model.dao;

import by.restonov.tyrent.entity.Car;
import by.restonov.tyrent.exception.DaoException;

public interface CarDao {
    void changeState(Car car, Car.State state) throws DaoException;
}
