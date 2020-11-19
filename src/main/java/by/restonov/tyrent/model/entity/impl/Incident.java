package by.restonov.tyrent.model.entity.impl;


import by.restonov.tyrent.model.entity.ApplicationEntity;

import java.time.LocalDateTime;

/**
 * The type Incident.
 */
public class Incident extends ApplicationEntity {
    private long incidentId;
    private String incidentDescription;
    private long carId;
    private long userId;
    private int incidentTypeId;
    private String incidentTypeInfo;
    private LocalDateTime dateTime;

    /**
     * Default constructor
     */
    public Incident() {
    }

    /**
     * Gets date time.
     *
     * @return the date time
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets incident date & time
     *
     * @param dateTime incident date & time
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Gets incident id.
     *
     * @return the incident id
     */
    public long getIncidentId() {
        return incidentId;
    }

    /**
     * Sets incident id.
     *
     * @param incidentId the incident id
     */
    public void setIncidentId(long incidentId) {
        this.incidentId = incidentId;
    }

    /**
     * Gets incident description.
     *
     * @return the incident description
     */
    public String getIncidentDescription() {
        return incidentDescription;
    }

    /**
     * Sets incident description.
     *
     * @param incidentDescription the incident description
     */
    public void setIncidentDescription(String incidentDescription) {
        this.incidentDescription = incidentDescription;
    }

    /**
     * Gets car id.
     *
     * @return the car id
     */
    public long getCarId() {
        return carId;
    }

    /**
     * Sets car id.
     *
     * @param carId the car id
     */
    public void setCarId(long carId) {
        this.carId = carId;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets incident type id.
     *
     * @return the incident type id
     */
    public int getIncidentTypeId() {
        return incidentTypeId;
    }

    /**
     * Sets incident type id.
     *
     * @param incidentTypeId the incident type id
     */
    public void setIncidentTypeId(int incidentTypeId) {
        this.incidentTypeId = incidentTypeId;
    }

    /**
     * Gets incident type info.
     *
     * @return the incident type info
     */
    public String getIncidentTypeInfo() {
        return incidentTypeInfo;
    }

    /**
     * Sets incident type info.
     *
     * @param incidentTypeInfo the incident type info
     */
    public void setIncidentTypeInfo(String incidentTypeInfo) {
        this.incidentTypeInfo = incidentTypeInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Incident incident = (Incident) o;

        if (incidentId != incident.incidentId) return false;
        if (carId != incident.carId) return false;
        if (userId != incident.userId) return false;
        return incidentTypeId == incident.incidentTypeId;
    }

    @Override
    public int hashCode() {
        int result = (int) (incidentId ^ (incidentId >>> 32));
        result = 31 * result + (int) (carId ^ (carId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + incidentTypeId;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Incident{");
        sb.append("incidentId=").append(incidentId);
        sb.append(", incidentDescription='").append(incidentDescription).append('\'');
        sb.append(", carId=").append(carId);
        sb.append(", userId=").append(userId);
        sb.append(", incidentTypeId=").append(incidentTypeId);
        sb.append(", incidentTypeInfo='").append(incidentTypeInfo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
