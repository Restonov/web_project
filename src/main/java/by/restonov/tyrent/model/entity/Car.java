package by.restonov.tyrent.model.entity;

import java.math.BigDecimal;

public class Car {
    private long carId;
    private String name;
    private BigDecimal cost;
    private int typeId;
    private String description;
    private String descriptionEng;
    private String descriptionRus;
    private String imageBig;
    private String imageMini;
    private String imageCabin;
    private State state;

    public enum State {
        BOOKED(), AVAILABLE(), NOT_AVAILABLE()
    }

    public Car() {
    }

      public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionEng() {
        return descriptionEng;
    }

    public void setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
    }

    public String getDescriptionRus() {
        return descriptionRus;
    }

    public void setDescriptionRus(String descriptionRus) {
        this.descriptionRus = descriptionRus;
    }

    public String getImageBig() {
        return imageBig;
    }

    public void setImageBig(String imageBig) {
        this.imageBig = imageBig;
    }

    public String getImageMini() {
        return imageMini;
    }

    public void setImageMini(String imageMini) {
        this.imageMini = imageMini;
    }

    public String getImageCabin() {
        return imageCabin;
    }

    public void setImageCabin(String imageCabin) {
        this.imageCabin = imageCabin;
    }

    public long getId() {
        return carId;
    }

    public void setId(long carId) {
        this.carId = carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (carId != car.carId) return false;
        if (typeId != car.typeId) return false;
        return name.equals(car.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (carId ^ (carId >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + typeId;
        return result;
    }
}
