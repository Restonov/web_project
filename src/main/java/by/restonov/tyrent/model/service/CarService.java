package by.restonov.tyrent.model.service;

import by.restonov.tyrent.model.entity.Car;
import by.restonov.tyrent.model.entity.UserOrder;
import by.restonov.tyrent.model.exception.DaoException;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.model.dao.EntityTransaction;
import by.restonov.tyrent.model.dao.impl.CarDaoImpl;

import java.util.List;
import java.util.Optional;


/**
 * Service, that works with car data
 */
public class CarService {

    /**
     * Check if car state = AVAILABLE
     *
     * @param carId car id
     * @return  Optional of Car type
     * @throws ServiceException default exception of service layer
     */
    public Optional<Car> checkCarAvailability(long carId) throws ServiceException {
        Optional<Car> optionalCar;
        optionalCar = findCarById(carId);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            if (car.getState() == Car.State.AVAILABLE) {
                optionalCar = Optional.of(car);
            } else {
                optionalCar = Optional.empty();
            }
        }
        return optionalCar;
    }

    /**
     * Change state of the car
     *
     * @param car Car
     * @param carState New Car state
     * @throws ServiceException default exception of service layer
     * @see #updateCar(Car car)
     */
    public void changeCarState(Car car, Car.State carState) throws ServiceException {
        car.setState(carState);
        updateCar(car);
    }

    /**
     * Define description and get car
     *
     * @param car Car
     * @param lang app language
     * @return Car with defined description
     */
    public Car defineDescriptionAndGet(Car car, String lang) {
        String carDescription = lang.equals(AttributeName.EN) ? car.getDescriptionEng() : car.getDescriptionRus();
        car.setDescription(carDescription);
        return car;
    }

    /**
     * Find list of the cars
     *
     * @return List of the cars
     * @throws ServiceException default exception of service layer
     */
    public List<Car> findCarList() throws ServiceException {
        List<Car> carList;
        CarDaoImpl dao = new CarDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.initSingleQuery(dao);
            carList = dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Error while finding car list", e);
        } finally {
            transaction.endSingleQuery();
        }
        return carList;
    }

    /**
     * Find car by name
     *
     * @param carName Name of the car
     * @return Optional of Car type
     * @throws ServiceException default exception of service layer
     */
    public Optional<Car> findCarByName(String carName) throws ServiceException {
        Optional<Car> optionalCar;
        CarDaoImpl dao = new CarDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.initSingleQuery(dao);
            optionalCar = dao.findByName(carName);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding car by name", e);
        } finally {
            transaction.endSingleQuery();
        }
        return optionalCar;
    }

    /**
     * Find car by id
     *
     * @param carId Id of the car
     * @return Optional of Car type
     * @throws ServiceException default exception of service layer
     */
    public Optional<Car> findCarById(long carId) throws ServiceException {
        Optional<Car> optionalCar;
        CarDaoImpl dao = new CarDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.initSingleQuery(dao);
            optionalCar = dao.findById(carId);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding car by id", e);
        } finally {
            transaction.endSingleQuery();
        }
        return optionalCar;
    }

    /**
     * Update existing car
     *
     * @param car Car
     * @throws ServiceException default exception of service layer
     */
    public void updateCar(Car car) throws ServiceException {
        CarDaoImpl dao = new CarDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.initSingleQuery(dao);
            dao.update(car);
        } catch (DaoException e) {
            throw new ServiceException();
        } finally {
            transaction.endSingleQuery();
        }
    }

    /**
     * Change Car state in accordance
     * to Order state
     *
     * @param car Car
     * @param orderState {@link UserOrder.State}
     * @throws ServiceException default exception of service layer
     * @see #changeCarState(Car, Car.State)
     */
    public void alignCarStateToOrderState(Car car, UserOrder.State orderState) throws ServiceException {
        Car.State carState;
        if (orderState == UserOrder.State.PROCESSED) {
            carState = Car.State.BOOKED;
        } else {
            carState = Car.State.AVAILABLE;
        }
        changeCarState(car, carState);
    }
}
