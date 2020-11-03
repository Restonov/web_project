package by.restonov.jspservlet.entity;

public class Car {
    private int carId;
    private String name;
    private int cost;
    private String type;
    private String description;
    private String descriptionEng;
    private String descriptionRus;
    private String imageBackground;
    private String imageMini;
    private String imageCabin;
    private State state;

    public enum State {
        BOOKED(), AVAILABLE()
    }

    public Car() {
    }

    public void defineState(String role) {
        if (role.equals(State.AVAILABLE.toString())) {
            setState(State.AVAILABLE);
        } else {
            setState(State.BOOKED);
        }
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

    public String getImageBackground() {
        return imageBackground;
    }

    public void setImageBackground(String imageBackground) {
        this.imageBackground = imageBackground;
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

    public int getId() {
        return carId;
    }

    public void setId(int carId) {
        this.carId = carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (carId != car.carId) return false;
        if (Double.compare(car.cost, cost) != 0) return false;
        if (!name.equals(car.name)) return false;
        return type.equals(car.type);
    }

    @Override
    public int hashCode() {
        int result = carId;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("name='").append(name).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
