package by.restonov.jspservlet.entity.builder;

import by.restonov.jspservlet.entity.Car;
import by.restonov.jspservlet.manager.ParameterName;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarBuilder {

    public Car buildCar() {
        return new Car();
    }

   //car creation from admin panel
//    public Car createCarFromMap(Map<String, String> data){
//        int carId = Integer.parseInt(data.get(ParameterName.CAR_ID));
//        String name = data.get(ParameterName.NAME);
//        int cost = Integer.parseInt(data.get(ParameterName.COST));
//        String type = data.get(ParameterName.TYPE);
//        String descriptionRus = data.get(ParameterName.DESCRIPTION_RUS);
//        String descriptionEng = data.get(ParameterName.DESCRIPTION_ENG);
//        Car car = new Car();
//        car.setName(name);
//        car.setId(carId);
//        car.setCost(cost);
//        car.setType(type);
//        car.setRusDescription(descriptionRus);
//        car.setEngDescription(descriptionEng);
//        return car;
//    }

    public Car buildCarFromResultSet(ResultSet set) throws SQLException {
        int carId = set.getInt(ParameterName.CAR_ID);
        String name = set.getString(ParameterName.NAME);
        int cost = set.getInt(ParameterName.COST);
        String type = set.getString(ParameterName.TYPE);
        String descriptionEng = set.getString(ParameterName.DESCRIPTION_ENG);
        String descriptionRus = set.getString(ParameterName.DESCRIPTION_RUS);
        String background = set.getString(ParameterName.IMAGE_BACKGROUND);
        String mini = set.getString(ParameterName.IMAGE_MINI);
        String cabin = set.getString(ParameterName.IMAGE_CABIN);
        String state = set.getString(ParameterName.CAR_STATE);

        Car car = new Car();
        car.setName(name);
        car.setId(carId);
        car.defineState(state);
        car.setCost(cost);
        car.setType(type);
        car.setDescriptionEng(descriptionEng);
        car.setDescriptionRus(descriptionRus);
        car.setImageBackground(background);
        car.setImageMini(mini);
        car.setImageCabin(cabin);
        return car;
    }
}
