package by.restonov.tyrent.model.service;

import by.restonov.tyrent.entity.Car;
import by.restonov.tyrent.exception.DaoException;
import by.restonov.tyrent.model.dao.EntityTransaction;
import by.restonov.tyrent.model.dao.impl.CarDaoImpl;

import java.util.List;
import java.util.Optional;

public class CarService {

    public Optional<Car> checkCarAvailability(int carId) {
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

    public void changeCarState(Car car, Car.State state) {
        CarDaoImpl dao = new CarDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(dao);
        try {
            dao.changeState(car, state);
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            transaction.endSingleQuery();
        }
    }

    public List<Car> findCarList() {
        List<Car> carList = null;
        CarDaoImpl dao = new CarDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(dao);
        try {
            carList = dao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            transaction.endSingleQuery();
        }
        return carList;
    }

    public Optional<Car> findCarByName(String carName) {
        Optional<Car> optionalCar = Optional.empty();
        CarDaoImpl dao = new CarDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(dao);
        try {
            optionalCar = dao.findByName(carName);
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            transaction.endSingleQuery();
        }
        return optionalCar;
    }

    public Optional<Car> findCarById(Integer carId) {
        Optional<Car> optionalCar = Optional.empty();
        CarDaoImpl dao = new CarDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initSingleQuery(dao);
        try {
            optionalCar = dao.findById(carId);
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            transaction.endSingleQuery();
        }
        return optionalCar;
    }
}