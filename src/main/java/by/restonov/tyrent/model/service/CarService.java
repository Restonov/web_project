package by.restonov.tyrent.model.service;

import by.restonov.tyrent.model.dao.impl.UserOrderDaoImpl;
import by.restonov.tyrent.model.entity.Car;
import by.restonov.tyrent.model.entity.UserOrder;
import by.restonov.tyrent.model.exception.DaoException;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.model.dao.EntityTransaction;
import by.restonov.tyrent.model.dao.impl.CarDaoImpl;

import java.util.List;
import java.util.Optional;

public class CarService {

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

    public void changeCarState(Car car, Car.State carState) throws ServiceException {
        car.setState(carState);
        updateCar(car);
    }

    public Car defineDescriptionAndGet(Car car, String lang) {
        String carDescription = lang.equals(AttributeName.EN) ? car.getDescriptionEng() : car.getDescriptionRus();
        car.setDescription(carDescription);
        return car;
    }

    public List<Car> findCarList() throws ServiceException {
        List<Car> carList = null;
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
