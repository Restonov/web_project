package by.restonov.tyrent.model.dao.builder;

import by.restonov.tyrent.model.entity.impl.Car;
import by.restonov.tyrent.manager.ParameterName;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Car builder
 */
public enum CarDaoBuilder {
    /**
     * Thread-safe Singleton instance
     */
    INSTANCE;

    /**
     * Build Car from ResultSet data
     *
     * @param set ResultSet
     * @return Car instance
     * @throws SQLException DB access error
     */
    public Car build(ResultSet set) throws SQLException {
        long carId = set.getLong(ParameterName.CAR_ID);
        String name = set.getString(ParameterName.CAR_NAME);
        double cost = set.getDouble(ParameterName.CAR_COST);
        String descriptionEng = set.getString(ParameterName.DESCRIPTION_ENG);
        String descriptionRus = set.getString(ParameterName.DESCRIPTION_RUS);
        String big = set.getString(ParameterName.IMAGE_BIG);
        String mini = set.getString(ParameterName.IMAGE_MINI);
        String cabin = set.getString(ParameterName.IMAGE_CABIN);
        String state = set.getString(ParameterName.CAR_STATE);
        Car car = new Car();
        car.setId(carId);
        car.setName(name);
        car.setCost(BigDecimal.valueOf(cost));
        car.setDescriptionEng(descriptionEng);
        car.setDescriptionRus(descriptionRus);
        car.setImageBig(big);
        car.setImageMini(mini);
        car.setImageCabin(cabin);
        car.setState(Car.State.valueOf(state));
        return car;
    }
}
