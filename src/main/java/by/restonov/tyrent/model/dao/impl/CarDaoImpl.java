package by.restonov.tyrent.model.dao.impl;

import by.restonov.tyrent.entity.Car;
import by.restonov.tyrent.entity.builder.CarBuilder;
import by.restonov.tyrent.exception.DaoException;
import by.restonov.tyrent.model.dao.AbstractDao;
import by.restonov.tyrent.model.dao.CarDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl extends AbstractDao<Integer, Car> implements CarDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_CARS= "SELECT car_id, name, cost, type, description_eng, description_rus, image_background, image_mini, image_cabin, car_state FROM cars";
    private static final String SELECT_CAR_BY_NAME= "SELECT car_id, name, cost, type, description_eng, description_rus, image_background, image_mini, image_cabin, car_state FROM cars WHERE name = ?";
    private static final String SELECT_CAR_BY_ID= "SELECT car_id, name, cost, type, description_eng, description_rus, image_background, image_mini, image_cabin, car_state FROM cars WHERE car_id = ?";
    private static final String UPDATE_STATE = "UPDATE cars SET car_state = ? WHERE car_id = ?";

    @Override
    public List<Car> findAll() throws DaoException {
        List<Car> carList = new ArrayList<>();
        CarBuilder factory = CarBuilder.INSTANCE;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CARS)){
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = factory.buildCarFromResultSet(resultSet);
                carList.add(car);
            }
        } catch (SQLException e) {
            logger.error("Error while finding all cars", e);
            throw new DaoException("Error while finding all cars", e);
        } finally {
            assert resultSet != null;
            closeResultSet(resultSet);
        }
        return carList;
    }

    @Override
    public Optional<Car> findById(Integer id) throws DaoException {
        Optional<Car> optionalCar = Optional.empty();
        CarBuilder factory = CarBuilder.INSTANCE;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CAR_BY_ID)){
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Car car = factory.buildCarFromResultSet(resultSet);
                optionalCar = Optional.of(car);
            }
        } catch (SQLException e) {
            logger.error("Error while finding car by id", e);
            throw new DaoException("Error while finding car by id", e);
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Error while closing resultSet", e);
                e.printStackTrace();
            }
        }
        return optionalCar;
    }

    @Override
    public Optional<Car> findByName(String name) throws DaoException {
        Optional<Car> optionalCar = Optional.empty();
        CarBuilder factory = CarBuilder.INSTANCE;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CAR_BY_NAME)){
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Car car = factory.buildCarFromResultSet(resultSet);
                optionalCar = Optional.of(car);
            }
        } catch (SQLException e) {
            logger.error("Error while finding car by name", e);
            throw new DaoException("Error while finding car by name", e);
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Error while closing resultSet", e);
                e.printStackTrace();
            }
        }
        return optionalCar;
    }

    @Override
    public void changeState(Car car, Car.State state) throws DaoException {
        if (car != null && state != null) {
            try(PreparedStatement statement = connection.prepareStatement(UPDATE_STATE)){
                statement.setString(1, state.toString());
                statement.setInt(2, car.getId());
                statement.executeUpdate();
                logger.info("Car state updated");
            } catch (SQLException e) {
                logger.error("Error while state update", e);
                throw new DaoException("Error while state update", e);
            }
        }
    }

    @Override
    public boolean add(Car entity) throws DaoException {
        return false;
    }

    @Override
    public Car update(Car entity) {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }
}
