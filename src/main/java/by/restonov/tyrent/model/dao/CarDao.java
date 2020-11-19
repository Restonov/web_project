package by.restonov.tyrent.model.dao;

import by.restonov.tyrent.model.entity.impl.Car;
import by.restonov.tyrent.model.exception.DaoException;

import java.util.Optional;

/**
 * Interface for operations
 * with Car's data
 *
 */
public interface CarDao {

    /**
     * Return Car if DB contains it
     *
     * @param name Car name
     * @return Optional of Car type
     * @throws DaoException default dao layer exception
     */
    Optional<Car> findCarByName(String name) throws DaoException;

}
