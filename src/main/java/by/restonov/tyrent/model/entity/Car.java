package by.restonov.tyrent.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Class of Car type
 */
public class Car implements Serializable {
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

    /**
     * Car states according to DB
     */
    public enum State {
        BOOKED(), AVAILABLE(), NOT_AVAILABLE()
    }

    /**
     * Default constructor
     */
    public Car() {
    }

    /**
     * Gets state
     *
     * @return state
     */
    public State getState() {
        return state;
    }

    /**
     * Sets state
     *
     * @param state state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Gets current description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description
     *
     * @param description Car description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets english description
     *
     * @return english description
     */
    public String getDescriptionEng() {
        return descriptionEng;
    }

    /**
     * Sets english description
     *
     * @param descriptionEng english description
     */
    public void setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
    }

    /**
     * Gets russian description rus
     *
     * @return russian description
     */
    public String getDescriptionRus() {
        return descriptionRus;
    }

    /**
     * Sets russian description
     *
     * @param descriptionRus russian description
     */
    public void setDescriptionRus(String descriptionRus) {
        this.descriptionRus = descriptionRus;
    }

    /**
     * Gets background image path
     *
     * @return background image path
     */
    public String getImageBig() {
        return imageBig;
    }

    /**
     * Sets background image path
     *
     * @param imageBig background image path
     */
    public void setImageBig(String imageBig) {
        this.imageBig = imageBig;
    }

    /**
     * Gets mini image path
     *
     * @return mini image path
     */
    public String getImageMini() {
        return imageMini;
    }

    /**
     * Sets mini image path
     *
     * @param imageMini mini image path
     */
    public void setImageMini(String imageMini) {
        this.imageMini = imageMini;
    }

    /**
     * Gets cabin image
     *
     * @return cabin image
     */
    public String getImageCabin() {
        return imageCabin;
    }

    /**
     * Sets cabin image
     *
     * @param imageCabin cabin image
     */
    public void setImageCabin(String imageCabin) {
        this.imageCabin = imageCabin;
    }

    /**
     * Gets car id
     *
     * @return car id
     */
    public long getId() {
        return carId;
    }

    /**
     * Sets car id
     *
     * @param carId car id
     */
    public void setId(long carId) {
        this.carId = carId;
    }

    /**
     * Gets car name
     *
     * @return car name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets car name
     *
     * @param name car name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets car cost
     *
     * @return car cost
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Sets car cost
     *
     * @param cost car cost
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * Gets type id
     *
     * @return the type id
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * Sets type id
     *
     * @param typeId the type id
     */
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car: ");
        sb.append(name);
        return sb.toString();
    }
}
