package by.restonov.tyrent.model.dao.impl;

import by.restonov.tyrent.model.entity.impl.Car;
import by.restonov.tyrent.model.dao.builder.CarDaoBuilder;
import by.restonov.tyrent.model.exception.DaoException;
import by.restonov.tyrent.model.dao.AbstractDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * DAO Interaction with DB "cars" table
 */
public class CarDaoImpl extends AbstractDao<Long, Car> {
    private static final Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_CARS= "SELECT car_id, car_name, car_cost, car_description_eng, car_description_rus, car_image_big, car_image_mini, car_image_cabin, car_state FROM cars";
    private static final String SELECT_CAR_BY_ID= "SELECT car_id, car_name, car_cost, car_description_eng, car_description_rus, car_image_big, car_image_mini, car_image_cabin, car_state FROM cars WHERE car_id = ?";
    private static final String UPDATE_CAR = "UPDATE cars SET car_name = ?, car_cost = ?, car_description_eng = ?, car_description_rus = ?, car_image_big = ?, car_image_mini = ?, car_image_cabin = ?, car_state = ?::car_state WHERE car_id = ?";

    @Override
    public List<Car> findAll() throws DaoException {
        List<Car> carList = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CARS)){
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CarDaoBuilder builder = new CarDaoBuilder();
                Car car = builder.build(resultSet);
                carList.add(car);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding all cars", e);
        } finally {
            assert resultSet != null;
            closeResultSet(resultSet);
        }
        return carList;
    }

    @Override
    public Optional<Car> findById(Long id) throws DaoException {
        Optional<Car> optionalCar = Optional.empty();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CAR_BY_ID)){
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CarDaoBuilder builder = new CarDaoBuilder();
                Car car = builder.build(resultSet);
                optionalCar = Optional.of(car);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding car by id", e);
        } finally {
            assert resultSet != null;
            closeResultSet(resultSet);
        }
        return optionalCar;
    }

    @Override
    public boolean add(Car entity){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(Car car) throws DaoException {
        boolean result = false;
        if (car != null) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_CAR)) {
                statement.setString(1, car.getName());
                statement.setDouble(2, car.getCost().doubleValue());
                statement.setString(3, car.getDescriptionEng());
                statement.setString(4, car.getDescriptionRus());
                statement.setString(5, car.getImageBig());
                statement.setString(6, car.getImageMini());
                statement.setString(7, car.getImageCabin());
                statement.setString(8, car.getState().toString());
                statement.setLong(9, car.getId());
                statement.executeUpdate();
                result = true;
                logger.info("Car successfully updated");
            } catch (SQLException e) {
                throw new DaoException("Error while car updating", e);
            }
        }
        return result;
    }

    @Override
    public boolean delete(Long id) {
        throw new UnsupportedOperationException();
    }
}
