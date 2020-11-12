package by.restonov.tyrent.model.service.builder;

/**
 * Car builder
 */
public enum CarBuilder {

    /**
     * Thread-safe Singleton instance
     */
    INSTANCE;

    /**
     * Build user from User data
     * that we get from jsp form
     *
     * @param data Map of Car data
     * @return Car instance
     */
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

}
